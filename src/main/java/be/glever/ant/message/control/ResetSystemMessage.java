package be.glever.ant.message.control;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

public class ResetSystemMessage extends AbstractAntMessage {

    public static final byte MSG_ID = 0x4a;

    public ResetSystemMessage() {
    }
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
