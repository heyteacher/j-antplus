package be.glever.anttest;

import static java.lang.String.format;

import java.io.IOException;

import be.glever.ant.message.AntMessage;
import be.glever.ant.message.data.AcknowledgeDataMessage;
import be.glever.ant.message.data.BroadcastDataMessage;
import be.glever.ant.usb.AntUsbDevice;
import be.glever.ant.usb.AntUsbDeviceFactory;
import be.glever.ant.util.ByteUtils;
import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;
import be.glever.antplus.common.datapage.DataPage80ManufacturersInformation;
import be.glever.antplus.common.datapage.DataPage81ProductInformation;
import be.glever.antplus.common.datapage.DataPage82BatteryStatus;
import be.glever.antplus.power.PowerChannel;
import be.glever.antplus.power.datapage.PowerDataPageRegistry;
import be.glever.antplus.power.datapage.main.PowerDataPage02GetSetParameters;
import be.glever.antplus.power.datapage.main.PowerDataPage10PowerOnly;
import be.glever.antplus.power.datapage.main.PowerDataPage11WheelTorque;
import be.glever.antplus.power.datapage.main.PowerDataPage12CrankTorque;
import be.glever.antplus.power.datapage.main.PowerDataPage13TorqueEffectivenessPedalSmoothness;
import be.glever.antplus.power.datapage.main.PowerDataPage20CrankTorqueFrequency;
import be.glever.util.logging.Log;

public class PowerTest_Main {
    private static final Log LOG = Log.getLogger(HrmTest_Main.class);
    final private PowerDataPageRegistry registry = new PowerDataPageRegistry();
    private int heartbeatCount;
    private PowerTest_Main() throws IOException, InterruptedException {

        try (AntUsbDevice device = AntUsbDeviceFactory.getAvailableAntDevices().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("No devices found"))) {
            device.initialize();
            device.closeAllChannels(); // channels stay open on usb dongle even if program shuts down.
            PowerChannel channel = new PowerChannel(device);
            channel.getEvents().doOnNext(this::handle).subscribe();
            /*
            // request Advanced Capabilities 2
            sendMessage(connectedDevice, channelNumber,
                    new DataPage70Request(
                            2,
                            false,
                            false,
                            (byte) 0x02,
                            (byte) 0xFE
                    )
            );
            // request Crank Parameters
            sendMessage(connectedDevice, channelNumber,
                   new DataPage70Request(
                           2,
                           false,
                           false,
                           (byte) 0x02,
                           (byte) 0x01
                 )
            );
            // set crank length to 172.5 mm
            sendMessage(
                    connectedDevice,
                    channelNumber,
                    new PowerDataPage02GetSetParameters(
                            new PowerDataSubPage01CrankParameters(172.5)
                    )
             );
             */
            int ignoredResult = System.in.read();
        }
    }

    static void main(String[] args) throws Exception {
        new PowerTest_Main();
    }

    private static String getHexSubPageNumber(PowerDataPage02GetSetParameters powerPage) {
        return String.format("0x%02X", powerPage.getSubPageNumber());
    }

    private static void sendMessage(AntUsbDevice device, byte channelNumber, AbstractAntPlusDataPage dataPage) {
        LOG.debug(() -> format("Send page %s", dataPage));
        device.send(new AcknowledgeDataMessage(channelNumber, dataPage.getDataPageBytes()));
    }

    private void handle(AntMessage antMessage) {
        if (antMessage instanceof BroadcastDataMessage msg) {
            byte[] payLoad = msg.getPayLoad();
            removeToggleBit(payLoad);
            AbstractAntPlusDataPage dataPage = registry.constructDataPage(payLoad);
            if (dataPage == null) {
                LOG.debug(() -> format(
                        "Page Number: Unknow, Payload %s",
                        ByteUtils.hexString(msg.getPayLoad())));
                return;
            }
            switch (dataPage) {
                case PowerDataPage10PowerOnly powerPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Power Only, Instantaneous power: %s, "+
                                    "Instantaneous cadence: %s, Power Distribution Percentage: %s",
                            powerPage.getPageNumber(),
                            powerPage.getInstantaneousPower(),
                            powerPage.getInstantaneousCadence(),
                            powerPage.getPedalPowerDistribution().getPercentage()));
                }
                case PowerDataPage13TorqueEffectivenessPedalSmoothness powerPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X TE PS, LeftTorqueEffectiveness %s, RightTorqueEffectiveness %s, " +
                                    "LeftTorqueEffectiveness %s, LeftPedalSmoothness %s",
                            powerPage.getPageNumber(),
                            powerPage.getLeftTorqueEffectiveness(),
                            powerPage.getRightTorqueEffectiveness(),
                            powerPage.getLeftTorqueEffectiveness(),
                            powerPage.getLeftPedalSmoothness()));
                }
                case PowerDataPage02GetSetParameters getSetParametersPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Get/Set Parameters, %s, Payload %s",
                            getSetParametersPage.getPageNumber(),
                            getSetParametersPage.getSubPage(),
                            ByteUtils.hexString(payLoad)));
                }
                case DataPage80ManufacturersInformation commonPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Manufacturers Information",
                            dataPage.getPageNumber()));
                }
                case DataPage81ProductInformation commonPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Product Information",
                            dataPage.getPageNumber()));
                }
                case DataPage82BatteryStatus batteryStatusPage -> {
                    double fractionalVoltage = ((int) (batteryStatusPage.getFractionalBatteryVoltage() / 2.56d)) / 100d;
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Battery Status, Identifier: %s, Number of Batteries: %s, Status %s ",
                            dataPage.getPageNumber(),
                            batteryStatusPage.getBatteryIdentifier(),
                            batteryStatusPage.getNumberOfBatteries(),
                            batteryStatusPage.getBatteryStatus()));
                }
                case PowerDataPage11WheelTorque powerPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Wheel Torque",
                            dataPage.getPageNumber()));
                }
                case PowerDataPage12CrankTorque powerPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Crank Torque",
                            dataPage.getPageNumber()));
                }
                case PowerDataPage20CrankTorqueFrequency powerPage -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Crank Torque Frequency",
                            dataPage.getPageNumber()));
                }
                default -> {
                    LOG.debug(() -> format(
                            "PageNumber: 0x%02X Not Handled, payload: %s",
                            dataPage.getPageNumber(),
                            ByteUtils.hexString(msg.getMessageContent())));
                }
            }
        } else if (antMessage instanceof AcknowledgeDataMessage msg) {
            byte[] payLoad = msg.getPayLoad();
            removeToggleBit(payLoad);
            AbstractAntPlusDataPage dataPage = registry.constructDataPage(payLoad);
            LOG.debug(() -> format("Page Number: 0x%02X ACK", dataPage.getPageNumber()));
        } else {
            byte[] payLoad = antMessage.toByteArray();
            //LOG.debug(() -> format("Unknow message %s", ByteUtils.hexString(payLoad)));
        }
    }

    /**
     * For the moment not taking the legacy hrm devices into account.
     * Non-legacy devices swap the first bit of the pageNumber every 4 messages.
     *
     * @param payLoad the payload
     */
    private void removeToggleBit(byte[] payLoad) {
        payLoad[0] = (byte) (0b01111111 & payLoad[0]);
    }
}
