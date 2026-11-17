package be.glever.ant.message.control;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;

/**
 * ANT message: Open Rx Scan Mode.
 */
public class OpenRxScanModeMessage extends AbstractAntMessage implements AntBlockingMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x5b;

    private byte[] bytes;

    /**
     * Constructs a new {@link OpenRxScanModeMessage}.
     *
     * @param bytes the bytes
     */
    public OpenRxScanModeMessage(byte[] bytes) {
        parse(bytes);
    }

    /**
     * Constructs a new {@link OpenRxScanModeMessage}.
     *
     * @param synchronousPacketsOnly the synchronous packets only
     */
    public OpenRxScanModeMessage(boolean synchronousPacketsOnly) {
        this.bytes = new byte[]{0, (byte) (synchronousPacketsOnly ? 1 : 0)};
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return this.bytes;
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        this.bytes = messageContentBytes;
    }

    @Override
    public byte getResponseMessageId() {
        return ChannelEventOrResponseMessage.MSG_ID;
    }
}
