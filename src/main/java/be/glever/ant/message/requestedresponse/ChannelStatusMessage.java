package be.glever.ant.message.requestedresponse;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

public class ChannelStatusMessage extends AbstractAntMessage {

    public static final byte MSG_ID = 0x52;

    private byte[] bytes;

    @SuppressWarnings("unused")
    public ChannelStatusMessage() {
    }
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

    public byte getChannelNumber() {
        return this.bytes[0];
    }

    public CHANNEL_STATUS getChannelStatus() {
        return CHANNEL_STATUS.fromValue(bytes[1]);
    }

    public enum CHANNEL_STATUS {
        UnAssigned, Assigned, Searching, Tracking;

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
