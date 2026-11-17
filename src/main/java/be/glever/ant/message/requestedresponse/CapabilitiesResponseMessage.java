package be.glever.ant.message.requestedresponse;

import be.glever.ant.AntException;
import be.glever.ant.message.AbstractAntMessage;

import java.util.Arrays;

import static be.glever.ant.util.ByteUtils.hasBitSet;

/**
 * ANT message: Capabilities Response.
 */
public class CapabilitiesResponseMessage extends AbstractAntMessage {

    /**
     * Message ID.
     */
    public static final byte MSG_ID = 0x54;
    private byte[] messageBytes;

    /**
     * Constructs a new {@link CapabilitiesResponseMessage}.
     */
    public CapabilitiesResponseMessage() {
    }

    /**
     * Constructs a new {@link CapabilitiesResponseMessage}.
     *
     * @param bytes the bytes
     */
    public CapabilitiesResponseMessage(byte[] bytes) {
        parse(bytes);
    }

    @Override
    public byte getMessageId() {
        return MSG_ID;
    }

    @Override
    public byte[] getMessageContent() {
        return messageBytes;
    }

    @Override
    public void setMessageBytes(byte[] messageContentBytes) throws AntException {
        if (messageContentBytes.length != 8 && messageContentBytes.length != 6) {
            throw new AntException(
                    String.format("Incorrect message length. Given: %s, expected: 8 or 6", messageContentBytes.length));
        }
        this.messageBytes = messageContentBytes;
    }

    /**
     * Returns the max channels.
     *
     * @return the max channels.
     */
    public byte getMaxChannels() {
        return messageBytes[0];
    }

    /**
     * Returns the max networks.
     *
     * @return the max networks.
     */
    public short getMaxNetworks() {
        return messageBytes[1];
    }

    private short getStandardOptionsByte() {
        return messageBytes[2];
    }

    /**
     * Returns whether receive channels is enabled or active.
     *
     * @return {@code true} if receive channels, {@code false} otherwise.
     */
    public boolean getReceiveChannels() {
        return !hasBitSet(getStandardOptionsByte(), 0);
    }

    /**
     * Returns whether transmit channels is enabled or active.
     *
     * @return {@code true} if transmit channels, {@code false} otherwise.
     */
    public boolean getTransmitChannels() {
        return !hasBitSet(getStandardOptionsByte(), 1);
    }

    /**
     * Returns whether receive messages is enabled or active.
     *
     * @return {@code true} if receive messages, {@code false} otherwise.
     */
    public boolean getReceiveMessages() {
        return !hasBitSet(getStandardOptionsByte(), 2);
    }

    /**
     * Returns whether transmit messages is enabled or active.
     *
     * @return {@code true} if transmit messages, {@code false} otherwise.
     */
    public boolean getTransmitMessages() {
        return !hasBitSet(getStandardOptionsByte(), 3);
    }

    /**
     * Returns whether ack messages is enabled or active.
     *
     * @return {@code true} if ack messages, {@code false} otherwise.
     */
    public boolean getAckMessages() {
        return !hasBitSet(getStandardOptionsByte(), 4);
    }

    /**
     * Returns whether burst messages is enabled or active.
     *
     * @return {@code true} if burst messages, {@code false} otherwise.
     */
    public boolean getBurstMessages() {
        return !hasBitSet(getStandardOptionsByte(), 5);
    }

    private byte getAdvancedOptionsByte() {
        return messageBytes[3];
    }

    /**
     * Returns whether network enabled is enabled or active.
     *
     * @return {@code true} if network enabled, {@code false} otherwise.
     */
    public boolean getNetworkEnabled() {
        return hasBitSet(getAdvancedOptionsByte(), 1);
    }

    /**
     * Returns whether serial number enabled is enabled or active.
     *
     * @return {@code true} if serial number enabled, {@code false} otherwise.
     */
    public boolean getSerialNumberEnabled() {
        return hasBitSet(getAdvancedOptionsByte(), 3);
    }

    /**
     * Returns whether per channel tx power enabled is enabled or active.
     *
     * @return {@code true} if per channel tx power enabled, {@code false} otherwise.
     */
    public boolean getPerChannelTxPowerEnabled() {
        return hasBitSet(getAdvancedOptionsByte(), 4);
    }

    /**
     * Returns whether low priority search enabled is enabled or active.
     *
     * @return {@code true} if low priority search enabled, {@code false} otherwise.
     */
    public boolean getLowPrioritySearchEnabled() {
        return hasBitSet(getAdvancedOptionsByte(), 5);
    }

    /**
     * Returns whether script enabled is enabled or active.
     *
     * @return {@code true} if script enabled, {@code false} otherwise.
     */
    public boolean getScriptEnabled() {
        return hasBitSet(getAdvancedOptionsByte(), 6);
    }

    /**
     * Returns whether search list enabled is enabled or active.
     *
     * @return {@code true} if search list enabled, {@code false} otherwise.
     */
    public boolean getSearchListEnabled() {
        return hasBitSet(getAdvancedOptionsByte(), 7);
    }

    private byte getAdvancedOptions2Byte() {
        return messageBytes[4];
    }

    /**
     * Returns whether led enabled is enabled or active.
     *
     * @return {@code true} if led enabled, {@code false} otherwise.
     */
    public boolean getLedEnabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 0);
    }

    /**
     * Returns whether ext message enabled is enabled or active.
     *
     * @return {@code true} if ext message enabled, {@code false} otherwise.
     */
    public boolean getExtMessageEnabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 1);
    }

    /**
     * Returns whether scan mode enabled is enabled or active.
     *
     * @return {@code true} if scan mode enabled, {@code false} otherwise.
     */
    public boolean getScanModeEnabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 2);
    }

    /**
     * Returns whether prox search enabled is enabled or active.
     *
     * @return {@code true} if prox search enabled, {@code false} otherwise.
     */
    public boolean getProxSearchEnabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 4);
    }

    /**
     * Returns whether ext assign enabled is enabled or active.
     *
     * @return {@code true} if ext assign enabled, {@code false} otherwise.
     */
    public boolean getExtAssignEnabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 5);
    }

    /**
     * Returns whether fs ant fs enabled is enabled or active.
     *
     * @return {@code true} if fs ant fs enabled, {@code false} otherwise.
     */
    public boolean getFsAntFsEnabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 6);
    }

    /**
     * Returns whether fit1 enabled is enabled or active.
     *
     * @return {@code true} if fit1 enabled, {@code false} otherwise.
     */
    public boolean getFit1Enabled() {
        return hasBitSet(getAdvancedOptions2Byte(), 7);
    }

    /**
     * Returns the max sens core channels.
     *
     * @return the max sens core channels.
     */
    public short getMaxSensCoreChannels() {
        return messageBytes[5];
    }

    private byte getAdvancedOptions3Byte() {
        return messageBytes.length == 8? messageBytes[6]: 0;
    }

    /**
     * Returns whether advanced burst enabled is enabled or active.
     *
     * @return {@code true} if advanced burst enabled, {@code false} otherwise.
     */
    public boolean getAdvancedBurstEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 0);
    }

    /**
     * Returns whether event buffering enabled is enabled or active.
     *
     * @return {@code true} if event buffering enabled, {@code false} otherwise.
     */
    public boolean getEventBufferingEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 1);
    }

    /**
     * Returns whether event filtering enabled is enabled or active.
     *
     * @return {@code true} if event filtering enabled, {@code false} otherwise.
     */
    public boolean getEventFilteringEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 2);
    }

    /**
     * Returns whether high duty search enabled is enabled or active.
     *
     * @return {@code true} if high duty search enabled, {@code false} otherwise.
     */
    public boolean getHighDutySearchEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 3);
    }

    /**
     * Returns whether search sharing enabled is enabled or active.
     *
     * @return {@code true} if search sharing enabled, {@code false} otherwise.
     */
    public boolean getSearchSharingEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 4);
    }

    /**
     * Returns whether selective data updates enabled is enabled or active.
     *
     * @return {@code true} if selective data updates enabled, {@code false} otherwise.
     */
    public boolean getSelectiveDataUpdatesEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 6);
    }

    /**
     * Returns whether encrypted channel enabled is enabled or active.
     *
     * @return {@code true} if encrypted channel enabled, {@code false} otherwise.
     */
    public boolean getEncryptedChannelEnabled() {
        return hasBitSet(getAdvancedOptions3Byte(), 7);
    }

    private byte getAdvancedOptions4Byte() {
        return messageBytes.length == 8? messageBytes[7]: 0;
    }

    /**
     * Returns whether rf active notification enabled is enabled or active.
     *
     * @return {@code true} if rf active notification enabled, {@code false} otherwise.
     */
    public boolean getRfActiveNotificationEnabled() {
        return hasBitSet(getAdvancedOptions4Byte(), 0);
    }

    @Override
    public String toString() {
        return "CapabilitiesResponseMessage [getMessageId()=" + getMessageId() + ", getMessageContent()="
                + Arrays.toString(getMessageContent()) + ", getMaxChannels()=" + getMaxChannels()
                + ", getMaxNetworks()=" + getMaxNetworks() + ", getReceiveChannels()=" + getReceiveChannels()
                + ", getTransmitChannels()=" + getTransmitChannels() + ", getReceiveMessages()=" + getReceiveMessages()
                + ", getTransmitMessages()=" + getTransmitMessages() + ", getAckdMessages()=" + getAckMessages()
                + ", getBurstMessages()=" + getBurstMessages() + ", getNetworkEnabled()=" + getNetworkEnabled()
                + ", getSerialNumberEnabled()=" + getSerialNumberEnabled() + ", getPerChannelTxPowerEnabled()="
                + getPerChannelTxPowerEnabled() + ", getLowPrioritySearchEnabled()=" + getLowPrioritySearchEnabled()
                + ", getScriptEnabled()=" + getScriptEnabled() + ", getSearchListEnabled()=" + getSearchListEnabled()
                + ", getLedEnabled()=" + getLedEnabled() + ", getExtMessageEnabled()=" + getExtMessageEnabled()
                + ", getScanModeEnabled()=" + getScanModeEnabled() + ", getProxSearchEnabled()="
                + getProxSearchEnabled() + ", getExtAssignEnabled()=" + getExtAssignEnabled() + ", getFsAntFsEnabled()="
                + getFsAntFsEnabled() + ", getFit1Enabled()=" + getFit1Enabled() + ", getMaxSensRcoreChannels()="
                + getMaxSensCoreChannels() + ", getAdvancedBurstEnabled()=" + getAdvancedBurstEnabled()
                + ", getEventBuffereingEnabled()=" + getEventBufferingEnabled() + ", getEventFilteringEnabled()="
                + getEventFilteringEnabled() + ", getHighDutySearchEnabled()=" + getHighDutySearchEnabled()
                + ", getSearchSharingEnabled()=" + getSearchSharingEnabled() + ", getSelectiveDataUpdatesEnabled()="
                + getSelectiveDataUpdatesEnabled() + ", getEncryptedChannelEnabled()=" + getEncryptedChannelEnabled()
                + ", getRfActiveNotificationEnabled()=" + getRfActiveNotificationEnabled() + "]";
    }

}
