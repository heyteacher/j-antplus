package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Enable Single Channel Encryption.
 */
@SuppressWarnings("unused")
public class EnableSingleChannelEncryptionMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link EnableSingleChannelEncryptionMessage}.
     */
    public EnableSingleChannelEncryptionMessage() {
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
