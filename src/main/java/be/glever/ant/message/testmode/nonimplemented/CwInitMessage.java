package be.glever.ant.message.testmode.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Cw Init.
 */
public class CwInitMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link CwInitMessage}.
     */
    public CwInitMessage() {
    }
    @Override
    public byte getMessageId() {
        return 0;
    }

    @Override
    public void parse(byte[] bytes) throws AntException {

    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }


    @Override
    public byte getResponseMessageId() {
        return 0;
    }
}
