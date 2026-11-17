package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Configure Advanced Burst.
 */
@SuppressWarnings("unused")
public class ConfigureAdvancedBurstMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link ConfigureAdvancedBurstMessage}.
     */
    public ConfigureAdvancedBurstMessage() {
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
