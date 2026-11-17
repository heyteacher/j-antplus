package be.glever.ant.message.requestedresponse;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

/**
 * ANT message: Ant Version.
 */
public class AntVersionMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x3e;
    private byte[] bytes;

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    /**
     * Constructs a new {@link AntVersionMessage}.
     *
     * @param versionMessage the version message
     */
    public AntVersionMessage(byte[] versionMessage) {
        this.bytes = versionMessage;
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
     * Returns the ant version.
     *
     * @return the ant version.
     */
    public byte[] getAntVersion() {
        return bytes;
    }

}
