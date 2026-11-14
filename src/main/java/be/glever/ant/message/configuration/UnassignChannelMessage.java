package be.glever.ant.message.configuration;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;

public class UnassignChannelMessage extends AbstractAntMessage implements AntBlockingMessage {

    public static final byte MSG_ID = 0x41;

    private byte channelNumber;

    public UnassignChannelMessage(byte channelNumber) {
        this.channelNumber = channelNumber;
    }

    public UnassignChannelMessage(byte[] bytes) {
        parse(bytes);
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return new byte[]{channelNumber};
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
