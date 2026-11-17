package be.glever.ant.message.data;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.util.ByteArrayBuilder;
import be.glever.ant.util.ByteUtils;

import java.util.Arrays;

/**
 * ANT message: Acknowledge Data.
 */
public class AcknowledgeDataMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final int MSG_ID = 0x4f;
    private byte[] messageContentBytes;

    /**
     * Constructs a new {@link AcknowledgeDataMessage}.
     *
     * @param channelNumber the channel number
     * @param payload the payload
     */
    public AcknowledgeDataMessage(byte channelNumber, byte[] payload) {
        setMessageBytes(new ByteArrayBuilder().write(channelNumber).write(payload).toByteArray());
    }

    @Override
    public byte[] getMessageContent() {
        return messageContentBytes;
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        this.messageContentBytes = messageContentBytes;
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    /**
     * Returns the channel number.
     *
     * @return the channel number.
     */
    public byte getChannelNumber() {
        return getMessageContent()[0];
    }

    /**
     * Returns the pay load.
     *
     * @return the pay load.
     */
    public byte[] getPayLoad() {
        return Arrays.copyOfRange(messageContentBytes, 1, messageContentBytes.length);
    }

    @Override
    public String toString() {
        return "BroadcastDataMessage{" +
                "messageContentBytes=" + ByteUtils.hexString(messageContentBytes) +
                '}';
    }
}
