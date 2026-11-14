package be.glever.ant.message.configuration;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.util.ByteArrayBuilder;

public class ChannelRfFrequencyMessage extends AbstractAntMessage implements AntBlockingMessage {

    public static final byte MSG_ID = 0x45;
    private byte channelNumber;
    private byte channelRfFrequency;

    public ChannelRfFrequencyMessage(byte channelNumber, byte channelRfFrequency) {
        this.channelNumber = channelNumber;
        this.channelRfFrequency = channelRfFrequency;
    }

    public ChannelRfFrequencyMessage(byte[] bytes) {
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
                .write(channelRfFrequency)
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
