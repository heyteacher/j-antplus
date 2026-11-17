package be.glever.ant.message.configuration;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.util.ByteArrayBuilder;

/**
 * ANT message: Search Timeout.
 */
public class SearchTimeoutMessage extends AbstractAntMessage implements AntBlockingMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x44;

    private byte channelNumber;
    private byte searchTimeout;

    /**
     * Constructs a new {@link SearchTimeoutMessage}.
     *
     * @param channelNumber the channel number
     * @param searchTimeout the search timeout
     */
    @SuppressWarnings("unused")
    public SearchTimeoutMessage(byte channelNumber, byte searchTimeout) {
        this.channelNumber = channelNumber;
        this.searchTimeout = searchTimeout;
    }

    /**
     * Constructs a new {@link SearchTimeoutMessage}.
     *
     * @param bytes the bytes
     */
    public SearchTimeoutMessage(byte[] bytes) {
        parse(bytes);
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return new ByteArrayBuilder()
                .write(channelNumber)
                .write(searchTimeout)
                .toByteArray();
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        // TODO Auto-generated method stub

    }

    @Override
    public byte getResponseMessageId() {
        return ChannelEventOrResponseMessage.MSG_ID;
    }
}
