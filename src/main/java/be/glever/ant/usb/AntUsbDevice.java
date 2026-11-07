package be.glever.ant.usb;

import be.glever.ant.AntException;
import be.glever.ant.channel.AntChannel;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.AntMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.message.channel.ChannelEventResponseCode;
import be.glever.ant.message.configuration.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static java.lang.String.format;

/**
 * Provides abstraction of and interaction with an ANT USB dongle.
 * Clients can
 */
public class AntUsbDevice implements Closeable {
    private static final int SLEEP_AFTER_RESET = 500;
    private static final Log LOG = Log.getLogger(AntUsbDevice.class);
    private final FluxProcessor<AntMessage, AntMessage> antMessageProcessor = DirectProcessor.<AntMessage>create().serialize();
    private final FluxSink<AntMessage> antMessageSink = antMessageProcessor.sink();
    private final FluxProcessor<Throwable, Throwable> errorProcessor = DirectProcessor.<Throwable>create().serialize();
    private final FluxSink<Throwable> errorSink = errorProcessor.sink();
    private javax.usb.UsbDevice device = null;
    private android.hardware.usb.UsbDevice androidDevice = null;
    private android.hardware.usb.UsbDeviceConnection androidConnection;
    private byte[] antVersion;
    private AntUsbDeviceCapabilities capabilities;
    private byte[] serialNumber;
    private AntUsbReader antUsbReader;
    private AntUsbWriter antUsbWriter;
    private AntChannel[] antChannels;

    private boolean isInitialized = false;


    public AntUsbDevice(android.hardware.usb.UsbDevice androidDevice) {
        this.androidDevice = androidDevice;
    }

    public AntUsbDevice(javax.usb.UsbDevice device) {
        this.device = device;
    }

    public javax.usb.UsbDevice getUsbDevice() {
        return device;
    }

    public android.hardware.usb.UsbDevice getAndroidDevice() {
        return androidDevice;
    }

    private static Publisher<AntMessage> mapToErrorIfRequired(AntMessage antMessage) {
        LOG.debug(() -> format("in flux, received message %s", antMessage));
        if (antMessage instanceof ChannelEventOrResponseMessage channelEventOrResponseMessage) {
            if (channelEventOrResponseMessage.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
                return Flux.error(new AntException(antMessage));
            }
        }
        return Flux.just(antMessage);
    }

    private static boolean forceClaim(javax.usb.UsbInterface usbInterface) {
        return true;
    }

    public void initialize(android.content.Context androidContext) throws AntException {
        if (isInitialized)
            return;

        try {
            if (androidContext == null) {
                initUsbInterface();
            } else {
                initAndroidUsbInterface(androidContext);
            }
            initAntDevice();
            isInitialized = true;
        } catch (Exception e) {
            throw new AntException(e);
        }
    }


    @Override
    public void close() throws IOException {
        try {
            resetUsbDevice();
            isInitialized = false;
        } catch (Exception e) {
            LOG.error(() -> "Reset ant stick failed. Continuing with shutdown of usb interface.", e);
        }

        try {
            this.antUsbReader.stop();
            if (this.device != null ) {
                javax.usb.UsbInterface activeUsbInterface = getActiveUsbInterface();
                if (activeUsbInterface != null && activeUsbInterface.isClaimed()) {
                    activeUsbInterface.release();
                }
            }
            if (this.androidDevice != null && androidConnection != null) {
                    androidConnection.releaseInterface(getActiveAndroidUsbInterface());
                    androidConnection.close();
            }
            isInitialized = false;
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * Closes any channel still open between the stick and ant devices.
     */
    public void closeAllChannels() throws AntException {
        short maxChannels = capabilities.getMaxChannels();
        for (int i = 0; i < maxChannels; i++) {
            RequestMessage requestMessage = new RequestMessage((byte) i, ChannelStatusMessage.MSG_ID);
            ChannelStatusMessage responseMessage = (ChannelStatusMessage) sendBlocking(requestMessage);
            byte channelNumber = responseMessage.getChannelNumber();
            ChannelStatusMessage.CHANNEL_STATUS channelStatus = responseMessage.getChannelStatus();
            LOG.debug(() -> format("Channel %s is in state %s.", channelNumber, channelStatus));

            switch (channelStatus) {
                case UnAssigned:
                    break;
                case Assigned:
                    closeChannel(channelNumber);
                    break;
                default:
                    throw new AntException("Don't know (yet) how to close channel in current state " + channelStatus + ".");
            }
        }
    }

    public AntUsbDeviceCapabilities getCapabilities() {
        return capabilities;
    }

    public Mono<Void> openChannel(AntChannel channel) {

        byte channelNumber = getAvailableChannelNumber();

        BiFunction<AntMessage, AntMessage, Boolean> responseMatcher = RequestMatcher::isMatchingResponse;
        return send(new NetworkKeyMessage(channelNumber, channel.getNetwork().getNetworkKey()), responseMatcher)
                .then(send(new AssignChannelMessage(channelNumber, channel.getChannelType().getValue(), channel.getNetwork().getNetworkNumber()), responseMatcher))
                .then(send(new ChannelIdMessage(channelNumber, channel.getChannelId().getDeviceNumber(), channel.getChannelId().getDeviceType(), channel.getChannelId().getTransmissionType().getValue()), responseMatcher))
                .then(send(new ChannelPeriodMessage(channelNumber, channel.getChannelPeriod()), responseMatcher))
                .then(send(new ChannelRfFrequencyMessage(channelNumber, channel.getRfFrequency()), responseMatcher))
                .then(send(new OpenChannelMessage(channelNumber), responseMatcher))
                .then(Mono.fromRunnable(() -> {
                    this.antChannels[channelNumber] = channel;
                    channel.setChannelNumber(channelNumber);
                    channel.subscribeTo(this.antMessageProcessor);
                }))
                .then();
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
     * Returns a Mono containing the response for a given request. Note that the request is not sent unless subscribed to the Publisher.
     *
     * @param requestMessage The message to send.
     */
    public Mono<AntMessage> send(AntMessage requestMessage, BiFunction<AntMessage, AntMessage, Boolean> responseFilter) {
        Flux<AntMessage> response = this.antMessageProcessor
                .filter(responseMessage -> responseFilter.apply(requestMessage, responseMessage))
                .concatMap(AntUsbDevice::mapToErrorIfRequired);

        Mono<Void> messageSender = Mono.fromRunnable(() -> this.antUsbWriter.write(requestMessage));
        return Flux.merge(response, messageSender).map(antMessage -> (AntMessage) antMessage).next();
    }

    /**
     * Sends a message and blocks current thread until it's expected response is returned.
     *
     * @param requestMessage the message to send.
     * @return the response.
     * @deprecated Best to combine sending through {@link #send(AntMessage)} and listening on # instead.
     */
    @Deprecated
    public AntMessage sendBlocking(AntBlockingMessage requestMessage) {
        Flux<AntMessage> response = this.antMessageProcessor
                .filter(responseMessage -> RequestMatcher.isMatchingResponse(requestMessage, responseMessage))
                .concatMap(AntUsbDevice::mapToErrorIfRequired)
                .take(1);

        Mono<Void> messageSender = Mono.fromRunnable(() -> this.antUsbWriter.write(requestMessage));
        return (AntMessage) Flux.merge(response, messageSender).blockFirst(Duration.ofSeconds(10));
    }


    private byte getAvailableChannelNumber() {
        for (int i = 0; i < antChannels.length; i++) {
            if (antChannels[i] == null) {
                return (byte) i;
            }
        }
        throw new IllegalStateException("No available channels found");
    }

    private void initAndroidUsbInterface(android.content.Context androidContext) throws AntException {
        LOG.debug(() -> "<initAndroidUsbInterface>");
        android.hardware.usb.UsbManager usbManager = (android.hardware.usb.UsbManager)
                androidContext.getSystemService(android.content.Context.USB_SERVICE);
        androidConnection = usbManager.openDevice(androidDevice);
        android.hardware.usb.UsbInterface usbInterface = getActiveAndroidUsbInterface();
        if (androidConnection.claimInterface(usbInterface, true)) {
            LOG.debug(() -> "(initAndroidUsbInterface): interface claimed, collect endpoints");
            List<android.hardware.usb.UsbEndpoint> usbEndpoints = new ArrayList<>();
            for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
                usbEndpoints.add(usbInterface.getEndpoint(i));
            }
            LOG.debug(() -> "(initAndroidUsbInterface): initialize and start reader with in endpoint");
            android.hardware.usb.UsbEndpoint inEndpoint = usbEndpoints.stream()
                    .filter(endpoint -> endpoint.getDirection() == android.hardware.usb.UsbConstants.USB_DIR_IN)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not obtain usb input pipe."));
            antUsbReader = new AntUsbReader(androidConnection, inEndpoint);
            new Thread(antUsbReader, "ant-usb-reader").start();
            subscribeTo(antUsbReader);
            LOG.debug(() -> "(initAndroidUsbInterface): initialize writer with out endpoint");
            android.hardware.usb.UsbEndpoint outEndpoint = usbEndpoints.stream()
                    .filter(endpoint -> endpoint.getDirection() == android.hardware.usb.UsbConstants.USB_DIR_OUT)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not obtain usb output pipe."));
            antUsbWriter = new AntUsbWriter(androidConnection, outEndpoint);
        } else {
            throw new AntException("Could not claim usb interface");
        }
    }

    private void initUsbInterface() throws AntException, javax.usb.UsbException {
        javax.usb.UsbInterface usbInterface = getActiveUsbInterface();
        if (usbInterface == null) {
            throw new AntException("active Usb interface not found");
        }
        if (usbInterface.isClaimed()) {
            throw new AntException("Usb device already claimed");
        }
        usbInterface.claim(AntUsbDevice::forceClaim);

        @SuppressWarnings("unchecked")
        List<javax.usb.UsbEndpoint> usbEndpoints = usbInterface.getUsbEndpoints();
        javax.usb.UsbEndpoint inEndpoint = usbEndpoints.stream()
                .filter(endpoint -> endpoint.getDirection() == javax.usb.UsbConst.ENDPOINT_DIRECTION_IN)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Could not obtain usb input pipe."));
        javax.usb.UsbEndpoint outEndpoint = usbEndpoints.stream()
                .filter(endpoint -> endpoint.getDirection() == javax.usb.UsbConst.ENDPOINT_DIRECTION_OUT)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Could not obtain usb output pipe."));

        javax.usb.UsbPipe usbOutPipe = outEndpoint.getUsbPipe();
        usbOutPipe.open();

        javax.usb.UsbPipe inPipe = inEndpoint.getUsbPipe();
        inPipe.open();

        antUsbReader = new AntUsbReader(inPipe);
        new Thread(antUsbReader, "ant-usb-reader").start();
        antUsbWriter = new AntUsbWriter(usbOutPipe);
        subscribeTo(antUsbReader);
    }

    private void subscribeTo(AntUsbReader antUsbMessageReader) {
        antUsbMessageReader
                .antMessages()
                .publishOn(Schedulers.elastic())
                .doOnNext(this.antMessageSink::next)
                .doOnError(this.errorSink::next)
                .subscribe();

        this.errorProcessor.doOnNext(
                        error -> LOG.error(() -> "TODO Handle error " + error))
                .subscribe();
    }

    private void initAntDevice() throws AntException, InterruptedException {
        resetUsbDevice();

        CapabilitiesResponseMessage capabilitiesResponseMessage = (CapabilitiesResponseMessage) sendBlocking(RequestMessage.forMessageId(CapabilitiesResponseMessage.MSG_ID));
        this.capabilities = new AntUsbDeviceCapabilities(capabilitiesResponseMessage);
        this.antChannels = new AntChannel[capabilities.getMaxChannels()];

        AntVersionMessage antVersionMessage = (AntVersionMessage) sendBlocking(RequestMessage.forMessageId(AntVersionMessage.MSG_ID));
        this.antVersion = antVersionMessage.getAntVersion();

        SerialNumberMessage serialNumberMessage = (SerialNumberMessage) sendBlocking(RequestMessage.forMessageId(SerialNumberMessage.MSG_ID));
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

    private javax.usb.UsbInterface getActiveUsbInterface() {
        if (this.device != null) {
            return (javax.usb.UsbInterface) this.device.getActiveUsbConfiguration().getUsbInterfaces().getFirst();
        }
        return null;
    }

    private android.hardware.usb.UsbInterface getActiveAndroidUsbInterface() {
        return this.androidDevice.getInterface(0);
    }

    private void closeChannel(byte channelNumber) throws AntException {
        UnassignChannelMessage unassignChannelMessage = new UnassignChannelMessage(channelNumber);
        ChannelEventOrResponseMessage unassignResponseMsg = (ChannelEventOrResponseMessage) sendBlocking(unassignChannelMessage);
        if (unassignResponseMsg.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            LOG.debug(() -> format("Received unexpected message %s. Halting program.", unassignResponseMsg));
            throw new AntException("Could not close channel " + channelNumber);
        }
        LOG.debug(() -> format("Successfully unassigned channel %s.", channelNumber));
    }
}
