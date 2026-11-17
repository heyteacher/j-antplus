package be.glever.ant.message.data;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.util.ByteUtils;

import java.util.Arrays;

/**
 * Base class for all broadcast messages (ie data payloads received from ant+ devices).
 * TODO when needed, extend this class further to account for extended data behavior as defined in 7.1.1
 */
public class BroadcastDataMessage extends AbstractAntMessage {
    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x4e;
    private byte[] messageContentBytes;

    /**
     * Constructs a new {@link BroadcastDataMessage}.
     *
     * @param bytes the bytes
     */
    public BroadcastDataMessage(byte[] bytes) {
        parse(bytes);
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
