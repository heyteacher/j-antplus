package be.glever.ant.usb;

import be.glever.ant.message.requestedresponse.CapabilitiesResponseMessage;

/**
 * Lists capabilities of an ant usb device.
 *
 * @author glen
 */
public class AntUsbDeviceCapabilities {

    private final CapabilitiesResponseMessage crMsg;

    /**
     * Constructs a new {@link AntUsbDeviceCapabilities}.
     *
     * @param crMsg the cr msg
     */
    public AntUsbDeviceCapabilities(CapabilitiesResponseMessage crMsg) {
        this.crMsg = crMsg;
    }

    /**
     * Returns the message id.
     *
     * @return the message id.
     */
    public byte getMessageId() {
        return crMsg.getMessageId();
    }

    /**
     * Returns the message content.
     *
     * @return the message content.
     */
    public byte[] getMessageContent() {
        return crMsg.getMessageContent();
    }

    /**
     * Returns the max channels.
     *
     * @return the max channels.
     */
    public byte getMaxChannels() {
        return crMsg.getMaxChannels();
    }

    /**
     * Returns the max networks.
     *
     * @return the max networks.
     */
    @SuppressWarnings("unused")
    public short getMaxNetworks() {
        return crMsg.getMaxNetworks();
    }

    /**
     * Returns whether receive channels is enabled or active.
     *
     * @return {@code true} if receive channels, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getReceiveChannels() {
        return crMsg.getReceiveChannels();
    }

    /**
     * Returns whether transmit channels is enabled or active.
     *
     * @return {@code true} if transmit channels, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getTransmitChannels() {
        return crMsg.getTransmitChannels();
    }

    /**
     * Returns whether receive messages is enabled or active.
     *
     * @return {@code true} if receive messages, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getReceiveMessages() {
        return crMsg.getReceiveMessages();
    }

    /**
     * Returns whether transmit messages is enabled or active.
     *
     * @return {@code true} if transmit messages, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getTransmitMessages() {
        return crMsg.getTransmitMessages();
    }

    /**
     * Returns whether ack messages is enabled or active.
     *
     * @return {@code true} if ack messages, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getAckMessages() {
        return crMsg.getAckMessages();
    }

    /**
     * Returns whether burst messages is enabled or active.
     *
     * @return {@code true} if burst messages, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getBurstMessages() {
        return crMsg.getBurstMessages();
    }

    /**
     * Returns whether network enabled is enabled or active.
     *
     * @return {@code true} if network enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getNetworkEnabled() {
        return crMsg.getNetworkEnabled();
    }

    /**
     * Returns whether serial number enabled is enabled or active.
     *
     * @return {@code true} if serial number enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getSerialNumberEnabled() {
        return crMsg.getSerialNumberEnabled();
    }

    /**
     * Returns whether per channel tx power enabled is enabled or active.
     *
     * @return {@code true} if per channel tx power enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getPerChannelTxPowerEnabled() {
        return crMsg.getPerChannelTxPowerEnabled();
    }

    /**
     * Returns whether low priority search enabled is enabled or active.
     *
     * @return {@code true} if low priority search enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getLowPrioritySearchEnabled() {
        return crMsg.getLowPrioritySearchEnabled();
    }

    /**
     * Returns whether script enabled is enabled or active.
     *
     * @return {@code true} if script enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getScriptEnabled() {
        return crMsg.getScriptEnabled();
    }

    /**
     * Returns whether search list enabled is enabled or active.
     *
     * @return {@code true} if search list enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getSearchListEnabled() {
        return crMsg.getSearchListEnabled();
    }

    /**
     * Returns whether led enabled is enabled or active.
     *
     * @return {@code true} if led enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getLedEnabled() {
        return crMsg.getLedEnabled();
    }

    /**
     * Returns whether ext message enabled is enabled or active.
     *
     * @return {@code true} if ext message enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getExtMessageEnabled() {
        return crMsg.getExtMessageEnabled();
    }

    /**
     * Returns whether scan mode enabled is enabled or active.
     *
     * @return {@code true} if scan mode enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getScanModeEnabled() {
        return crMsg.getScanModeEnabled();
    }

    /**
     * Returns whether prox search enabled is enabled or active.
     *
     * @return {@code true} if prox search enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getProxSearchEnabled() {
        return crMsg.getProxSearchEnabled();
    }

    /**
     * Returns whether ext assign enabled is enabled or active.
     *
     * @return {@code true} if ext assign enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getExtAssignEnabled() {
        return crMsg.getExtAssignEnabled();
    }

    /**
     * Returns whether fs ant fs enabled is enabled or active.
     *
     * @return {@code true} if fs ant fs enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getFsAntFsEnabled() {
        return crMsg.getFsAntFsEnabled();
    }

    /**
     * Returns whether fit1 enabled is enabled or active.
     *
     * @return {@code true} if fit1 enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getFit1Enabled() {
        return crMsg.getFit1Enabled();
    }

    /**
     * Returns the max sens core channels.
     *
     * @return the max sens core channels.
     */
    @SuppressWarnings("unused")
    public short getMaxSensCoreChannels() {
        return crMsg.getMaxSensCoreChannels();
    }

    /**
     * Returns the max sensor channels.
     *
     * @return the max sensor channels.
     */
    @SuppressWarnings("unused")
    public short getMaxSensorChannels() {
        return crMsg.getMaxSensCoreChannels();
    }

    /**
     * Returns whether advanced burst enabled is enabled or active.
     *
     * @return {@code true} if advanced burst enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getAdvancedBurstEnabled() {
        return crMsg.getAdvancedBurstEnabled();
    }

    /**
     * Returns whether event buffering enabled is enabled or active.
     *
     * @return {@code true} if event buffering enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getEventBufferingEnabled() {
        return crMsg.getEventBufferingEnabled();
    }

    /**
     * Returns whether event filtering enabled is enabled or active.
     *
     * @return {@code true} if event filtering enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getEventFilteringEnabled() {
        return crMsg.getEventFilteringEnabled();
    }

    /**
     * Returns whether high duty search enabled is enabled or active.
     *
     * @return {@code true} if high duty search enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getHighDutySearchEnabled() {
        return crMsg.getHighDutySearchEnabled();
    }

    /**
     * Returns whether search sharing enabled is enabled or active.
     *
     * @return {@code true} if search sharing enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getSearchSharingEnabled() {
        return crMsg.getSearchSharingEnabled();
    }

    /**
     * Returns whether selective data updates enabled is enabled or active.
     *
     * @return {@code true} if selective data updates enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getSelectiveDataUpdatesEnabled() {
        return crMsg.getSelectiveDataUpdatesEnabled();
    }

    /**
     * Returns whether encrypted channel enabled is enabled or active.
     *
     * @return {@code true} if encrypted channel enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getEncryptedChannelEnabled() {
        return crMsg.getEncryptedChannelEnabled();
    }

    /**
     * Returns whether rf active notification enabled is enabled or active.
     *
     * @return {@code true} if rf active notification enabled, {@code false} otherwise.
     */
    @SuppressWarnings("unused")
    public boolean getRfActiveNotificationEnabled() {
        return crMsg.getRfActiveNotificationEnabled();
    }

    @Override
    public String toString() {
        return "AntUsbDeviceCapabilities{" +
                "crMsg=" + crMsg +
                '}';
    }
}
