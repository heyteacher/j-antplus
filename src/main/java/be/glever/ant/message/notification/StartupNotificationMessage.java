package be.glever.ant.message.notification;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;
import be.glever.ant.util.ByteUtils;

/**
 * ANT message: Startup Notification.
 */
public class StartupNotificationMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x6f;
    private byte[] bytes;

    /**
     * Constructs a new {@link StartupNotificationMessage}.
     *
     * @param bytes the bytes
     */
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

    /**
     * Checks if power on reset.
     *
     * @return {@code true} if power on reset, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isPowerOnReset() {
        return ByteUtils.hasMask(bytes[0], 0);
    }

    /**
     * Checks if hardware reset line.
     *
     * @return {@code true} if hardware reset line, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isHardwareResetLine() {
        return ByteUtils.hasBitSet(bytes[0], 0);
    }

    /**
     * Checks if watch dog reset.
     *
     * @return {@code true} if watch dog reset, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isWatchDogReset() {
        return ByteUtils.hasBitSet(bytes[0], 1);
    }

    /**
     * Checks if command reset.
     *
     * @return {@code true} if command reset, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean isCommandReset() {
        return ByteUtils.hasBitSet(bytes[0], 5);
    }

    /**
     * I synchronous reset.
     *
     * @return the i synchronous reset.
     */
    @SuppressWarnings("unused")
    public boolean iSynchronousReset() {
        return ByteUtils.hasBitSet(bytes[0], 6);
    }

    /**
     * I suspend reset.
     *
     * @return the i suspend reset.
     */
    @SuppressWarnings("unused")
    public boolean iSuspendReset() {
        return ByteUtils.hasBitSet(bytes[0], 7);
    }
}
