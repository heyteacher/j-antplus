package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message: Serial Number Set Channel Id.
 */
@SuppressWarnings("unused")
public class SerialNumberSetChannelIdMessage implements AntBlockingMessage {

    /**
     * Constructs a new {@link SerialNumberSetChannelIdMessage}.
     */
    public SerialNumberSetChannelIdMessage() {
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
