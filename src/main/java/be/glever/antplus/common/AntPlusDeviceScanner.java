package be.glever.antplus.common;

import be.glever.ant.AntException;
import be.glever.ant.channel.AntChannel;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.message.channel.ChannelEventResponseCode;
import be.glever.ant.message.configuration.AssignChannelMessage;
import be.glever.ant.message.control.OpenRxScanModeMessage;
import be.glever.ant.message.control.RequestMessage;
import be.glever.ant.message.requestedresponse.ChannelStatusMessage;
import be.glever.ant.usb.AbstractAntUsbDevice;
import be.glever.ant.usb.AntUsbDeviceCapabilities;
import be.glever.antplus.hrm.HRMChannel;
import be.glever.util.logging.Log;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


public class AntPlusDeviceScanner {
    private static final Log LOG = Log.getLogger(AntPlusDeviceScanner.class);

    private final AbstractAntUsbDevice abstractAntUsbDevice;

    public AntPlusDeviceScanner(AbstractAntUsbDevice abstractAntUsbDevice) {
        this.abstractAntUsbDevice = abstractAntUsbDevice;
    }

    /**
     * Scans the Ant+ Network for all Ant+ devices.
     * For each found device, an AntChannel will be  assigned, until no more devices found or the max number of channels reached (see {@link AbstractAntUsbDevice#getCapabilities()}.
     * If you need to support more devices than {@link AntUsbDeviceCapabilities#getMaxChannels()}, use continuous scanning (unsupported at time of writing).
     * Downside is that all RF bandwith is spent scanning and no channels can be formed so you are limited to receiving only..
     *
     * @return List of assigned {@link AntChannel}s. Client should open the channel if he wishes to receive all messages, or unassign if he is not interested.
     * @throws AntException if an error occurs.
     */
    public List<AntChannel> scanAvailableDevices() throws AntException {
        AntUsbDeviceCapabilities capabilities = this.abstractAntUsbDevice.getCapabilities();
        short maxChannels = capabilities.getMaxChannels();
        for (int i = 0; i < maxChannels; i++) {
            RequestMessage requestMessage = new RequestMessage((byte) i, ChannelStatusMessage.MSG_ID);
            ChannelStatusMessage responseMessage = (ChannelStatusMessage) abstractAntUsbDevice.sendBlocking(requestMessage);

            if (responseMessage.getChannelStatus() == ChannelStatusMessage.CHANNEL_STATUS.UnAssigned) {
                LOG.debug(() -> format("Channel %s is in status Unassigned. scanning.", responseMessage.getChannelStatus()));
            } else {
                LOG.debug(() -> format("Channel %s is in status %s. Not scanning.", responseMessage.getChannelNumber(), responseMessage.getChannelStatus()));
            }
        }

        return new ArrayList<>();
    }

    public void openRxScanMode() throws AntException {
        HRMChannel channel = new HRMChannel(abstractAntUsbDevice);
        AssignChannelMessage assignChannelMessage = new AssignChannelMessage((byte) 0x00, channel.getChannelType().getValue(), channel.getNetwork().getNetworkNumber());
        if (((ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(assignChannelMessage)).getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could not assign channel");
        }

        OpenRxScanModeMessage rxScanModeMessage = new OpenRxScanModeMessage(false);
        abstractAntUsbDevice.send(rxScanModeMessage);
    }
}
