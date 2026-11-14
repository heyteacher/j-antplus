package be.glever.ant.message;

import java.util.Arrays;

import be.glever.ant.AntException;
import be.glever.ant.util.ByteUtils;

/**
 * Represents an unknown message not recognized and not supported
 */
public class UnknownMessage extends AbstractAntMessage implements AntMessage {

    private final byte[] bytes;

    UnknownMessage(byte[] fullByteArray) {
        this.bytes = fullByteArray;
    }

    @Override
    public byte getMessageId() {
        return bytes[2];
    }

    @Override
    public byte[] getMessageContent() {
        return Arrays.copyOfRange(bytes, 4, 4 + bytes[1]);
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        throw new UnsupportedOperationException("Unsupported, only constructor should be used");
    }

    @Override
    public String toString() {
        return "UnknownMessage [msgId=" + ByteUtils.hexString(getMessageId()) + ", bytes=" + ByteUtils.hexString(bytes) + "]";
    }
}
