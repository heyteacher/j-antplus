package be.glever.ant.message.control;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

/**
 * ANT message: Reset System.
 */
public class ResetSystemMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x4a;

    /**
     * Constructs a new {@link ResetSystemMessage}.
     */
    public ResetSystemMessage() {
    }
    /**
     * Constructs a new {@link ResetSystemMessage}.
     *
     * @param bytes the bytes
     */
    public ResetSystemMessage(byte[] bytes) {
        parse(bytes);
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return new byte[]{0};
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        // empty
    }

}
