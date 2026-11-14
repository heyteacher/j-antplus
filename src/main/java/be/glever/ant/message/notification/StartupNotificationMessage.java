package be.glever.ant.message.notification;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.util.ByteUtils;

public class StartupNotificationMessage extends AbstractAntMessage {

    public static final byte MSG_ID = 0x6f;
    private byte[] bytes;

    public StartupNotificationMessage(byte[] bytes) {
        parse(bytes);
    }

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

    @SuppressWarnings("unused")
    public boolean isPowerOnReset() {
        return ByteUtils.hasMask(bytes[0], 0);
    }

    @SuppressWarnings("unused")
    public boolean isHardwareResetLine() {
        return ByteUtils.hasBitSet(bytes[0], 0);
    }

    @SuppressWarnings("unused")
    public boolean isWatchDogReset() {
        return ByteUtils.hasBitSet(bytes[0], 1);
    }

    @SuppressWarnings("unused")
    public boolean isCommandReset() {
        return ByteUtils.hasBitSet(bytes[0], 5);
    }

    @SuppressWarnings("unused")
    public boolean iSynchronousReset() {
        return ByteUtils.hasBitSet(bytes[0], 6);
    }

    @SuppressWarnings("unused")
    public boolean iSuspendReset() {
        return ByteUtils.hasBitSet(bytes[0], 7);
    }
}
