package be.glever.ant.message.notification;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.util.ByteUtils;

/**
 * ANT message: Serial Error.
 */
public class SerialErrorMessage extends AbstractAntMessage {
    /**
     * Message ID.
     */
    public static final byte MSG_ID = (byte) 0xae;
    private byte[] bytes;


    /**
     * Constructs a new {@link SerialErrorMessage}.
     *
     * @param bytes the bytes
     */
    public SerialErrorMessage(byte[] bytes) {
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
     * Checks if first byte not sync.
     *
     * @return {@code true} if first byte not sync, {@code false} otherwise.
     */
    @SuppressWarnings("unused")

    public boolean isFirstByteNotSync() {
        return ByteUtils.hasMask(bytes[0], 0);
    }

    /**
     * Checks if check sum incorrect.
     *
     * @return {@code true} if check sum incorrect, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isCheckSumIncorrect() {
        return ByteUtils.hasMask(bytes[0], 2);
    }

    /**
     * Checks if ant message too large.
     *
     * @return {@code true} if ant message too large, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isAntMessageTooLarge() {
        return ByteUtils.hasMask(bytes[0], 3);
    }
}
