package be.glever.ant.channel;

import be.glever.ant.constants.AntChannelType;
import be.glever.ant.message.AntMessage;
import reactor.core.publisher.Flux;

/**
 * Abstract ANT channel.
 */
public abstract class AntChannel {

    /**
     * Wildcard device number.
     */
    public static final Long DEVICE_NUMBER_WILDCARD = 0L;

    /**
     * Constructs a new {@link AntChannel}.
     */
    protected AntChannel() {
    }
    private AntChannelId channelId;
    private AntChannelType channelType;
    private byte rfFrequency;
    private byte[] channelPeriod;
    private AntChannelNetwork network;
    private byte channelNumber;
    private Long deviceNumber;

    /**
     * subscribe to the message flux
     * @param messageFlux the message flux
     */
    public abstract void subscribeTo(Flux<AntMessage> messageFlux);

    /**
     * Returns the events.
     *
     * @return the events.
     */
    public abstract Flux<AntMessage> getEvents();

    /**
     * Returns the channel type.
     *
     * @return the channel type.
     */
    public AntChannelType getChannelType() {
        return channelType;
    }


    /**
     * Sets the channel type.
     *
     * @param channelType the channel type
     */
    public void setChannelType(AntChannelType channelType) {
        this.channelType = channelType;
    }


    /**
     * Returns the rf frequency.
     *
     * @return the rf frequency.
     */
    public byte getRfFrequency() {
        return rfFrequency;
    }


    /**
     * Sets the rf frequency.
     *
     * @param rfFrequency the rf frequency
     */
    public void setRfFrequency(byte rfFrequency) {
        if (rfFrequency < 0 || rfFrequency > 124) {
            throw new IllegalArgumentException(
                    "Invalid frequency value: [" + rfFrequency + "]. Must lie within 0-124 (inclusive)");
        }
        this.rfFrequency = rfFrequency;
    }


    /**
     * Returns the channel id.
     *
     * @return the channel id.
     */
    public AntChannelId getChannelId() {
        return channelId;
    }


    /**
     * Sets the channel id.
     *
     * @param channelId the channel id
     */
    public void setChannelId(AntChannelId channelId) {
        this.channelId = channelId;
    }


    /**
     * Returns the channel period.
     *
     * @return the channel period.
     */
    public byte[] getChannelPeriod() {
        return channelPeriod;
    }


    /**
     * Sets the channel period.
     *
     * @param channelPeriod the channel period
     */
    public void setChannelPeriod(byte[] channelPeriod) {
        this.channelPeriod = channelPeriod;
    }


    /**
     * Returns the network.
     *
     * @return the network.
     */
    public AntChannelNetwork getNetwork() {
        return network;
    }


    /**
     * set the network
     * @param network the network
     */
    public void setNetwork(AntChannelNetwork network) {
        this.network = network;
    }

    /**
     * Returns the channel number.
     *
     * @return the channel number.
     */
    public byte getChannelNumber() {
        return this.channelNumber;
    }

    /**
     * Sets the channel number.
     *
     * @param channelNumber the channel number
     */
    public void setChannelNumber(byte channelNumber) {
        this.channelNumber = channelNumber;
    }

    /**
     * Gets the device number
     * @return the device number
     */
    public Long getDeviceNumber() { return this.deviceNumber;}

    /**
     * Sets the device number
     * @param deviceNumber the device number
     */
    public void setDeviceNumber(Long deviceNumber) { this.deviceNumber = deviceNumber;}
}
