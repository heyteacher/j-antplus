package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Set Usb Descriptor String.
 */
@SuppressWarnings("unused")
public class SetUsbDescriptorStringMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link SetUsbDescriptorStringMessage}.
     */
    public SetUsbDescriptorStringMessage() {
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
