package be.glever.ant.usb;

import be.glever.ant.AntException;
import be.glever.ant.channel.AntChannel;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.AntMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.message.channel.ChannelEventResponseCode;
import be.glever.ant.message.configuration.*;
import be.glever.ant.message.control.CloseChannelMessage;
import be.glever.ant.message.control.OpenChannelMessage;
import be.glever.ant.message.control.RequestMessage;
import be.glever.ant.message.control.ResetSystemMessage;
import be.glever.ant.message.requestedresponse.AntVersionMessage;
import be.glever.ant.message.requestedresponse.CapabilitiesResponseMessage;
import be.glever.ant.message.requestedresponse.ChannelStatusMessage;
import be.glever.ant.message.requestedresponse.SerialNumberMessage;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;
import org.reactivestreams.Publisher;

import reactor.core.publisher.*;
import reactor.core.scheduler.Schedulers;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import static java.lang.String.format;

/**
 * Provides abstraction of and interaction with an ANT USB dongle.
 * Clients can
 */
abstract public class AbstractAntUsbDevice implements Closeable {
    private static final int SLEEP_AFTER_RESET = 500;
    private static final Log LOG = Log.getLogger(AbstractAntUsbDevice.class);
    private final FluxProcessor<AntMessage, AntMessage> antMessageProcessor = DirectProcessor.<AntMessage>create()
            .serialize();
    private final FluxSink<AntMessage> antMessageSink = antMessageProcessor.sink();
    private final FluxProcessor<Throwable, Throwable> errorProcessor = DirectProcessor.<Throwable>create().serialize();
    private final FluxSink<Throwable> errorSink = errorProcessor.sink();
    private byte[] antVersion;
    private AntUsbDeviceCapabilities capabilities;
    private byte[] serialNumber;
    /**
     * ANT USB reader.
     */
    protected AbstractAntUsbReader antUsbReader;
    /**
     * ANT USB writer.
     */
    protected AbstractAntUsbWriter antUsbWriter;
    private AntChannel[] antChannels;

    private boolean isInitialized = false;

    /**
     * Creates an AntUsbDevice from an usb device.
     */
    public AbstractAntUsbDevice() {
    }

    private static Publisher<AntMessage> mapToErrorIfRequired(AntMessage antMessage) {
        LOG.debug(() -> format("(mapToErrorIfRequired) in flux, received message %s", antMessage));
        if (antMessage instanceof ChannelEventOrResponseMessage channelEventOrResponseMessage) {
            if (channelEventOrResponseMessage.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
                return Flux.error(new AntException(antMessage));
            }
        }
        return Flux.just(antMessage);
    }

    /**
     * Initialize.
     *
     * @throws AntException if an error occurs
     */
    public void initialize() throws AntException {
        if (isInitialized)
            return;
        LOG.info(() -> "<initialize>:");
        try {
            initUsbInterface();
            initAntDevice();
            isInitialized = true;
        } catch (Exception e) {
            LOG.error(() -> "(initialize): release usb interface and close connectin failed", e);
            throw new AntException(e);
        }
    }

    /**
     * Closes the usb device.
     * 
     * @throws IOException if an error occurs.
     */
    @Override
    public void close() throws IOException {
        LOG.debug(() -> "<close>");
        try {
            resetUsbDevice();
            isInitialized = false;
        } catch (Exception e) {
            LOG.error(() -> "(close): Reset ant stick failed. Continuing with shutdown of usb interface.", e);
        }
        try {
            this.antUsbReader.stop();
            closeInterface();
            isInitialized = false;
        } catch (Exception e) {
            LOG.error(() -> "(close): release usb interface and close connectin failed", e);
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Close interface.
     */
    protected abstract void closeInterface();

    /**
     * Closes any channel still open between the stick and ant devices.
     */
    public void closeAllChannels() throws AntException {
        LOG.debug(() -> "<closeAllChannels>");
        short maxChannels = capabilities.getMaxChannels();
        for (int i = 0; i < maxChannels; i++) {
            RequestMessage requestMessage = new RequestMessage((byte) i, ChannelStatusMessage.MSG_ID);
            ChannelStatusMessage responseMessage = (ChannelStatusMessage) sendBlocking(requestMessage);
            byte channelNumber = responseMessage.getChannelNumber();
            ChannelStatusMessage.CHANNEL_STATUS channelStatus = responseMessage.getChannelStatus();
            LOG.debug(() -> format("(closeAllChannels) %s is in state %s.", channelNumber, channelStatus));
            switch (channelStatus) {
                case UnAssigned:
                    break;
                case Assigned:
                    closeChannel(channelNumber);
                    break;
                default:
                    throw new AntException(
                            "Don't know (yet) how to close channel in current state " + channelStatus + ".");
            }
        }
    }

    /**
     * Gets the capabilities of the stick.
     * 
     * @return the capabilities of the stick.
     */
    public AntUsbDeviceCapabilities getCapabilities() {
        return capabilities;
    }

    /**
     * Opens a channel.
     * 
     * @param channel the channel to open.
     * @return a Mono that completes when the channel is opened.
     */
    public Mono<Void> openChannel(AntChannel channel) {
        LOG.info(() -> "<openChannel>");
        byte channelNumber = getAvailableChannelNumber();
        LOG.info(() -> format("(openChannel): try to open channel an assign channel number %s ", channelNumber));

        BiFunction<AntMessage, AntMessage, Boolean> responseMatcher = RequestMatcher::isMatchingResponse;
        return send(new NetworkKeyMessage(channelNumber, channel.getNetwork().getNetworkKey()), responseMatcher)
                .then(send(new AssignChannelMessage(channelNumber, channel.getChannelType().getValue(),
                        channel.getNetwork().getNetworkNumber()), responseMatcher))
                .then(send(new ChannelIdMessage(channelNumber, channel.getChannelId().getDeviceNumber(),
                        channel.getChannelId().getDeviceType(),
                        channel.getChannelId().getTransmissionType().getValue()), responseMatcher))
                .then(send(new ChannelPeriodMessage(channelNumber, channel.getChannelPeriod()), responseMatcher))
                .then(send(new ChannelRfFrequencyMessage(channelNumber, channel.getRfFrequency()), responseMatcher))
                .then(send(new OpenChannelMessage(channelNumber), responseMatcher))
                .then(Mono.fromRunnable(() -> {
                    this.antChannels[channelNumber] = channel;
                    channel.setChannelNumber(channelNumber);
                    channel.subscribeTo(this.antMessageProcessor);
                    LOG.info(() -> format(
                            "(openChannel): channel successfully opened and assigned to channel number %s ",
                            channelNumber));
                }))
                .then();
    }

    /**
     * Closes the channel with the given channel number.
     * 
     * @param channelNumber The number of channel to close.
     */
    public void closeChannel(byte channelNumber) {
        LOG.info(() -> format("<closeChannel> channel number %s", channelNumber));
        try {
            ChannelEventOrResponseMessage closeResp = (ChannelEventOrResponseMessage) sendBlocking(
                    new CloseChannelMessage(channelNumber));
            if (closeResp.getResponseCode() == ChannelEventResponseCode.EVENT_CHANNEL_CLOSED) {
                LOG.warn(() -> format("(closeChannel): channel %s already closed.", channelNumber));
            } else if (closeResp.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
                throw new AntException(format("response code %s", closeResp.getResponseCode()));
            }
            LOG.info(() -> format("(closeChannel): successfully close channel %s.", channelNumber));
        } catch (AntException e) {
            LOG.error(() -> format("(closeChannel): Error on close channel %s, message '%s'", channelNumber,
                    e.getMessage()));
        }
        // wait 1 second before send unassign message
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            ChannelEventOrResponseMessage unassignResp = (ChannelEventOrResponseMessage) sendBlocking(
                    new UnassignChannelMessage(channelNumber));
            if (unassignResp.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
                throw new AntException(format("response code %s", unassignResp.getResponseCode()));
            } else {
                LOG.info(() -> format("(closeChannel): successfully unassign channel %s.", channelNumber));
            }
        } catch (AntException e) {
            LOG.error(() -> format("(closeChannel): Error on unassign channel %s, error %s'", channelNumber, e));
        } finally {
            LOG.info(() -> format("(closeChannel): unset channel number %s from ant channels.", channelNumber));
            antChannels[channelNumber] = null;
        }
    }

    /**
     * Sends a message to the usb stick.
     *
     * @param antMessage The message to send.
     */
    public void send(AntMessage antMessage) {
        antUsbWriter.write(antMessage);
    }

    /**
     * Returns a Mono containing the response for a given request. Note that the
     * request is not sent unless subscribed to the Publisher.
     *
     * @param requestMessage The message to send.
     * @param responseFilter the response filter.
     * @return the send.
     */
    public Mono<AntMessage> send(AntMessage requestMessage,
            BiFunction<AntMessage, AntMessage, Boolean> responseFilter) {
        Flux<AntMessage> response = this.antMessageProcessor
                .filter(responseMessage -> responseFilter.apply(requestMessage, responseMessage))
                .concatMap(AbstractAntUsbDevice::mapToErrorIfRequired);

        Mono<Void> messageSender = Mono.fromRunnable(() -> this.antUsbWriter.write(requestMessage));
        return Flux.merge(response, messageSender).map(antMessage -> (AntMessage) antMessage).next();
    }

    /**
     * Sends a message and blocks current thread until it's expected response is
     * returned.
     *
     * @param requestMessage the message to send.
     * @return the response.
     * @deprecated Best to combine sending through {@link #send(AntMessage)} and
     *             listening on # instead.
     */
    @Deprecated
    public AntMessage sendBlocking(AntBlockingMessage requestMessage) {
        Flux<AntMessage> response = this.antMessageProcessor
                .filter(responseMessage -> RequestMatcher.isMatchingResponse(requestMessage, responseMessage))
                .concatMap(AbstractAntUsbDevice::mapToErrorIfRequired)
                .take(1);

        Mono<Void> messageSender = Mono.fromRunnable(() -> this.antUsbWriter.write(requestMessage));
        return (AntMessage) Flux.merge(response, messageSender).blockFirst(Duration.ofSeconds(10));
    }

    /**
     * Returns the first available channel number.
     * 
     * @return the first available channel number.
     */
    private byte getAvailableChannelNumber() {
        LOG.info(() -> "<getAvailableChannelNumber>");
        for (int i = 0; i < antChannels.length; i++) {
            if (antChannels[i] == null) {
                int channelNumber = i;
                LOG.info(() -> format("(getAvailableChannelNumber): channel %s available.", channelNumber));
                return (byte) i;
            }
        }
        LOG.error(() -> "(getAvailableChannelNumber): no available channels found.");
        throw new IllegalStateException("No available channels found");
    }

    /**
     * Init usb interface.
     *
     * @throws AntException if an error occurs
     */
    abstract protected void initUsbInterface() throws AntException;

    /**
     * Subscribe to.
     *
     * @param antUsbMessageReader the ant usb message reader
     */
    protected void subscribeTo(AbstractAntUsbReader antUsbMessageReader) {
        antUsbMessageReader
                .antMessages()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(this.antMessageSink::next)
                .doOnError(this.errorSink::next)
                .subscribe();

        this.errorProcessor.doOnNext(
                error -> LOG.error(() -> "TODO Handle error " + error))
                .subscribe();
    }

    private void initAntDevice() throws AntException, InterruptedException {
        resetUsbDevice();

        CapabilitiesResponseMessage capabilitiesResponseMessage = (CapabilitiesResponseMessage) sendBlocking(
                RequestMessage.forMessageId(CapabilitiesResponseMessage.MSG_ID));
        this.capabilities = new AntUsbDeviceCapabilities(capabilitiesResponseMessage);
        this.antChannels = new AntChannel[capabilities.getMaxChannels()];

        AntVersionMessage antVersionMessage = (AntVersionMessage) sendBlocking(
                RequestMessage.forMessageId(AntVersionMessage.MSG_ID));
        this.antVersion = antVersionMessage.getAntVersion();

        SerialNumberMessage serialNumberMessage = (SerialNumberMessage) sendBlocking(
                RequestMessage.forMessageId(SerialNumberMessage.MSG_ID));
        this.serialNumber = serialNumberMessage.getSerialNumber();

        LOG.debug(() -> format("Capabilities: %s", this.capabilities.toString()));
        LOG.debug(() -> format("Ant Version: %s", ByteUtils.hexString(this.antVersion)));
        LOG.debug(() -> format("SerialNumber: %s", ByteUtils.hexString(this.serialNumber)));
    }

    private void resetUsbDevice() throws InterruptedException {
        Thread.sleep(SLEEP_AFTER_RESET);
        antUsbWriter.write(new ResetSystemMessage());
        Thread.sleep(SLEEP_AFTER_RESET);
    }
}
