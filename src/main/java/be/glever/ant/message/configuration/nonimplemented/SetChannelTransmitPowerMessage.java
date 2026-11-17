package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Set Channel Transmit Power.
 */
@SuppressWarnings("unused")
public class SetChannelTransmitPowerMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link SetChannelTransmitPowerMessage}.
     */
    public SetChannelTransmitPowerMessage() {
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
