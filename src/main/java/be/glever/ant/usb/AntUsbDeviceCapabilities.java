package be.glever.ant.usb;

import be.glever.ant.message.requestedresponse.CapabilitiesResponseMessage;

/**
 * Lists capabilities of an ant usb device.
 *
 * @author glen
 */
public class AntUsbDeviceCapabilities {

    private final CapabilitiesResponseMessage crMsg;

    public AntUsbDeviceCapabilities(CapabilitiesResponseMessage crMsg) {
        this.crMsg = crMsg;
    }

    public byte getMessageId() {
        return crMsg.getMessageId();
    }

    public byte[] getMessageContent() {
        return crMsg.getMessageContent();
    }

    public byte getMaxChannels() {
        return crMsg.getMaxChannels();
    }

    @SuppressWarnings("unused")
    public short getMaxNetworks() {
        return crMsg.getMaxNetworks();
    }

    @SuppressWarnings("unused")
    public boolean getReceiveChannels() {
        return crMsg.getReceiveChannels();
    }

    @SuppressWarnings("unused")
    public boolean getTransmitChannels() {
        return crMsg.getTransmitChannels();
    }

    @SuppressWarnings("unused")
    public boolean getReceiveMessages() {
        return crMsg.getReceiveMessages();
    }

    @SuppressWarnings("unused")
    public boolean getTransmitMessages() {
        return crMsg.getTransmitMessages();
    }

    @SuppressWarnings("unused")
    public boolean getAckMessages() {
        return crMsg.getAckMessages();
    }

    @SuppressWarnings("unused")
    public boolean getBurstMessages() {
        return crMsg.getBurstMessages();
    }

    @SuppressWarnings("unused")
    public boolean getNetworkEnabled() {
        return crMsg.getNetworkEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getSerialNumberEnabled() {
        return crMsg.getSerialNumberEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getPerChannelTxPowerEnabled() {
        return crMsg.getPerChannelTxPowerEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getLowPrioritySearchEnabled() {
        return crMsg.getLowPrioritySearchEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getScriptEnabled() {
        return crMsg.getScriptEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getSearchListEnabled() {
        return crMsg.getSearchListEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getLedEnabled() {
        return crMsg.getLedEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getExtMessageEnabled() {
        return crMsg.getExtMessageEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getScanModeEnabled() {
        return crMsg.getScanModeEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getProxSearchEnabled() {
        return crMsg.getProxSearchEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getExtAssignEnabled() {
        return crMsg.getExtAssignEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getFsAntFsEnabled() {
        return crMsg.getFsAntFsEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getFit1Enabled() {
        return crMsg.getFit1Enabled();
    }

    @SuppressWarnings("unused")
    public short getMaxSensCoreChannels() {
        return crMsg.getMaxSensCoreChannels();
    }

    @SuppressWarnings("unused")
    public short getMaxSensorChannels() {
        return crMsg.getMaxSensCoreChannels();
    }

    @SuppressWarnings("unused")
    public boolean getAdvancedBurstEnabled() {
        return crMsg.getAdvancedBurstEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getEventBufferingEnabled() {
        return crMsg.getEventBufferingEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getEventFilteringEnabled() {
        return crMsg.getEventFilteringEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getHighDutySearchEnabled() {
        return crMsg.getHighDutySearchEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getSearchSharingEnabled() {
        return crMsg.getSearchSharingEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getSelectiveDataUpdatesEnabled() {
        return crMsg.getSelectiveDataUpdatesEnabled();
    }

    @SuppressWarnings("unused")
    public boolean getEncryptedChannelEnabled() {
        return crMsg.getEncryptedChannelEnabled();
    }

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
