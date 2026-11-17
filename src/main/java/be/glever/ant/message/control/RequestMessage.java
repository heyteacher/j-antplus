package be.glever.ant.message.control;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.message.AntBlockingMessage;
import be.glever.ant.util.ByteArrayBuilder;

/**
 * ANT message: Request.
 */
public class RequestMessage extends AbstractAntMessage implements AntBlockingMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x4d;

    private byte[] bytes;

    /**
     * Constructs a new {@link RequestMessage}.
     *
     * @param bytes the bytes
     */
    public RequestMessage(byte[] bytes) {
        parse(bytes);
    }

    /**
     * Constructs a new {@link RequestMessage}.
     *
     * @param channelNumberOrSubMessageId the channel number or sub message id
     * @param msgIdRequested the msg id requested
     */
    public RequestMessage(byte channelNumberOrSubMessageId, byte msgIdRequested) {
        this(channelNumberOrSubMessageId, msgIdRequested, null, null);
    }

    /**
     * Request message.
     *
     * @param channelNumberOrSubMessageId the channel number or sub message id
     * @param msgIdRequested the message id requested
     * @param address the address (only used when reading NVM message)
     * @param size the size (only used when reading NVM message)
     */
    public RequestMessage(byte channelNumberOrSubMessageId, byte msgIdRequested, Byte address, Byte size) {
        this.bytes = new ByteArrayBuilder().write(channelNumberOrSubMessageId, msgIdRequested).writeIfNotNull(address)
                .writeIfNotNull(size).toByteArray();
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

    /**
     * Returns the msg id requested.
     *
     * @return the msg id requested.
     */
    public byte getMsgIdRequested() {
        return bytes[1];
    }

    @Override
    public byte getResponseMessageId() {
        return getMsgIdRequested();
    }

    /**
     * For message id.
     *
     * @param requestedMsgId the requested msg id
     * @return the for message id.
     */
    public static RequestMessage forMessageId(byte requestedMsgId) {
        return new RequestMessage((byte) 0, requestedMsgId, (byte) 0, (byte) 0);
    }
}
