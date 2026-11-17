package be.glever.ant.message.configuration;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;

/**
 * ANT message: Channel Id.
 */
public class ChannelIdMessage extends AbstractAntMessage implements AntBlockingMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x51;
    private byte[] bytes;

    /**
     * Constructs a new {@link ChannelIdMessage}.
     *
     * @param bytes the bytes
     */
    public ChannelIdMessage(byte[] bytes) {
        parse(bytes);
    }

    /**
     * Constructs a new {@link ChannelIdMessage}.
     *
     * @param channelNr the channel nr
     * @param deviceNr the device nr
     * @param deviceTypeID the device type id
     * @param transmissionType the transmission type
     */
    public ChannelIdMessage(byte channelNr, byte[] deviceNr, byte deviceTypeID, byte transmissionType) {
        this.bytes = new byte[]{channelNr, deviceNr[0], deviceNr[1], deviceTypeID, transmissionType};
    }

    /**
     * Returns the channel nr.
     *
     * @return the channel nr.
     */
    @SuppressWarnings("unused")
    public byte getChannelNr() {
        return bytes[0];
    }

    /**
     * Returns the device nr.
     *
     * @return the device nr.
     */
    @SuppressWarnings("unused")
    public byte[] getDeviceNr() {
        return new byte[]{bytes[1], bytes[2]};
    }

    /**
     * Returns the device type id.
     *
     * @return the device type id.
     */
    @SuppressWarnings("unused")
    public byte getDeviceTypeId() {
        return bytes[3];
    }

    /**
     * Returns the transmission type.
     *
     * @return the transmission type.
     */
    @SuppressWarnings("unused")
    public byte getTransmissionType() {
        return bytes[4];
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return bytes;
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        this.bytes = messageContentBytes;
    }

    @Override
    public byte getResponseMessageId() {
        return ChannelEventOrResponseMessage.MSG_ID;
    }
}
