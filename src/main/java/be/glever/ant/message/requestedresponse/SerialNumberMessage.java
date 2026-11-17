package be.glever.ant.message.requestedresponse;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

/**
 * ANT message: Serial Number.
 */
public class SerialNumberMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x61;
    private byte[] bytes;

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return bytes;
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        this.bytes = messageContentBytes;
    }

    /**
     * Constructs a new {@link SerialNumberMessage}.
     *
     * @param serialNumber the serial number
     */
    public SerialNumberMessage(byte[] serialNumber) {
        this.bytes = serialNumber;
    }

    /**
     * Returns the serial number.
     *
     * @return the serial number.
     */
    public byte[] getSerialNumber() {
        return this.bytes;
    }

}
