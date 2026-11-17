package be.glever.ant.message.requestedresponse;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

/**
 * ANT message: Channel Status.
 */
public class ChannelStatusMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x52;

    private byte[] bytes;

    /**
     * Constructs a new {@link ChannelStatusMessage}.
     */
    @SuppressWarnings("unused")
    public ChannelStatusMessage() {
    }
    /**
     * Constructs a new {@link ChannelStatusMessage}.
     *
     * @param bytes the bytes
     */
    public ChannelStatusMessage(byte[] bytes) {
        parse(bytes);
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

    /**
     * Returns the channel number.
     *
     * @return the channel number.
     */
    public byte getChannelNumber() {
        return this.bytes[0];
    }

    /**
     * Returns the channel status.
     *
     * @return the channel status.
     */
    public CHANNEL_STATUS getChannelStatus() {
        return CHANNEL_STATUS.fromValue(bytes[1]);
    }

    /**
     * Enumeration representing CHANNEL STATUS.
     */
    public enum CHANNEL_STATUS {
        /**
         * Constant representing {@code UnAssigned}.
         */
        UnAssigned,
        /**
         * Constant representing {@code Assigned}.
         */
        Assigned,
        /**
         * Constant representing {@code Searching}.
         */
        Searching,
        /**
         * Constant representing {@code Tracking}.
         */
        Tracking;

        /**
         * Returns the enum constant for the given raw value.
         *
         * @param value the value
         * @return the matching constant.
         */
        public static CHANNEL_STATUS fromValue(byte value) {
            return switch (value & 0b11) {
                case 0 -> UnAssigned;
                case 1 -> Assigned;
                case 2 -> Searching;
                case 3 -> Tracking;
                default -> throw new IllegalArgumentException("Bad value passed");
            };
        }
    }

    @Override
    public String toString() {
        return "ChannelStatusMessage{" +
                "channelStatus=" + getChannelStatus() +
                ", channelStatus=" + getChannelStatus() +
                '}';
    }
}
