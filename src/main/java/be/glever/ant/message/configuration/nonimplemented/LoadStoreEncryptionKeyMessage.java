package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Load Store Encryption Key.
 */
@SuppressWarnings("unused")
public class LoadStoreEncryptionKeyMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link LoadStoreEncryptionKeyMessage}.
     */
    public LoadStoreEncryptionKeyMessage() {
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
