package be.glever.ant.message.configuration.nonimplemented;

import be.glever.ant.AntException;
import be.glever.ant.message.AntBlockingMessage;

/**
 * ANT message to add channel ID to list (not implemented).
 */
@SuppressWarnings("unused")
public class AddChannelIdToListMessage implements AntBlockingMessage {
    /**
     * Constructs a new {@link AddChannelIdToListMessage}.
     */
    public AddChannelIdToListMessage() {
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
