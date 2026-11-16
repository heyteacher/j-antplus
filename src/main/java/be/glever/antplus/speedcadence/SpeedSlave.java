package be.glever.antplus.speedcadence;

import be.glever.ant.AntException;
import be.glever.ant.channel.AntChannel;
import be.glever.ant.constants.AntPlusDeviceType;
import be.glever.ant.message.channel.ChannelEventOrResponseMessage;
import be.glever.ant.message.channel.ChannelEventResponseCode;
import be.glever.ant.message.configuration.*;
import be.glever.ant.message.control.OpenChannelMessage;
import be.glever.ant.usb.AbstractAntUsbDevice;

public class SpeedSlave {

    private static final byte ANTPLUS_SPEED_CADENCE_FREQUENCY = (byte) 57; // 2457Mhz
    private final AbstractAntUsbDevice abstractAntUsbDevice;

    public SpeedSlave(AbstractAntUsbDevice abstractAntUsbDevice) {
        this.abstractAntUsbDevice = abstractAntUsbDevice;
    }

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
        ChannelPeriodMessage channelPeriodMessage = new ChannelPeriodMessage(channelNumber, SpeedChannel.CHANNEL_PERIOD);
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
