package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Configure Selective Data Updates.
 */
@SuppressWarnings("unused")
public class ConfigureSelectiveDataUpdatesMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link ConfigureSelectiveDataUpdatesMessage}.
     */
    public ConfigureSelectiveDataUpdatesMessage() {
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
