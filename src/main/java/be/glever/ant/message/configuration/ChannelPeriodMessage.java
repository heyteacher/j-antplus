package be.glever.ant.message.configuration;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.util.ByteArrayBuilder;

/**
 * ANT message: Channel Period.
 */
public class ChannelPeriodMessage extends AbstractAntMessage implements AntBlockingMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x43;
    private byte channelNumber;
    private byte[] period;

    /**
     * Constructs a new {@link ChannelPeriodMessage}.
     *
     * @param channelNumber the channel number
     * @param period the period
     */
    public ChannelPeriodMessage(byte channelNumber, byte[] period) {
        this.channelNumber = channelNumber;
        this.period = period;
    }

    /**
     * Constructs a new {@link ChannelPeriodMessage}.
     *
     * @param bytes the bytes
     */
    public ChannelPeriodMessage(byte[] bytes) {
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
                .write(period)
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
