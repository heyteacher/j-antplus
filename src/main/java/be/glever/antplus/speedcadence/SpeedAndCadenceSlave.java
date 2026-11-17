package be.glever.antplus.speedcadence;

import be.glever.ant.AntException;
import be.glever.ant.channel.AntChannel;
import be.glever.ant.constants.AntPlusDeviceType;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.message.channel.ChannelEventResponseCode;
import be.glever.ant.message.configuration.*;
import be.glever.ant.message.control.OpenChannelMessage;
import be.glever.ant.usb.AbstractAntUsbDevice;

/**
 * ANT+ slave device handler for Speed And Cadence.
 */
public class SpeedAndCadenceSlave {

    private static final byte ANTPLUS_SPEED_CADENCE_FREQUENCY = (byte) 57; // 2457Mhz
    private final AbstractAntUsbDevice abstractAntUsbDevice;

    /**
     * Constructs a new {@link SpeedAndCadenceSlave}.
     *
     * @param abstractAntUsbDevice the abstract ant usb device
     */
    public SpeedAndCadenceSlave(AbstractAntUsbDevice abstractAntUsbDevice) {
        this.abstractAntUsbDevice = abstractAntUsbDevice;
    }

    /**
     * List devices.
     *
     * @return the list devices.
     * @throws AntException if an error occurs
     */
    public AntChannel[] listDevices() throws AntException {
        SpeedChannel channel = new SpeedChannel(abstractAntUsbDevice);

        byte channelNumber = 0x00;

        // ASSIGN CHANNEL
        NetworkKeyMessage networkKeyMessage = new NetworkKeyMessage(channelNumber, channel.getNetwork().getNetworkKey());
        ChannelEventOrResponseMessage setNetworkKeyResponse = (ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(networkKeyMessage);
        if (setNetworkKeyResponse.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could not set network key");
        }

        AssignChannelMessage assignChannelMessage = new AssignChannelMessage(channelNumber, (byte) 0x40, (byte) 0x00);
        ChannelEventOrResponseMessage response = (ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(assignChannelMessage);
        if (response.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could not assign channel");
        }

        ChannelIdMessage channelIdMessage = new ChannelIdMessage(channelNumber, new byte[]{0x00, 0x00}, AntPlusDeviceType.HRM.value(), (byte) 0x00);
        ChannelEventOrResponseMessage channelIdResponse = (ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(channelIdMessage);
        if (channelIdResponse.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could set channelId");
        }
        ChannelPeriodMessage channelPeriodMessage = new ChannelPeriodMessage(channelNumber, SpeedAndCadenceChannel.CHANNEL_PERIOD);
        ChannelEventOrResponseMessage channelPeriodResponseMessage = (ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(channelPeriodMessage);
        if (channelPeriodResponseMessage.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could set channel period");
        }

        ChannelRfFrequencyMessage channelRfFrequencyMessage = new ChannelRfFrequencyMessage(channelNumber, (byte) 0x39);
        ChannelEventOrResponseMessage channelRfFrequencyResponse = (ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(channelRfFrequencyMessage);
        if (channelRfFrequencyResponse.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could not set rf frequency");
        }

        response = (ChannelEventOrResponseMessage) abstractAntUsbDevice.sendBlocking(new OpenChannelMessage(response.getChannelNumber()));
        if (response.getResponseCode() != ChannelEventResponseCode.RESPONSE_NO_ERROR) {
            throw new RuntimeException("Could not open channel");
        }

        // OPEN CHANNEL
        return null;
    }

}
