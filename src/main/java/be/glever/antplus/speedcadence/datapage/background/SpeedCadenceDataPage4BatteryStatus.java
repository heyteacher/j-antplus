package be.glever.antplus.speedcadence.datapage.background;

import be.glever.antplus.common.datapage.constants.BatteryStatus;
import be.glever.antplus.speedcadence.datapage.AbstractSpeedCadenceDataPage;

import static be.glever.ant.util.ByteUtils.toInt;

/**
 * Speed Cadence Data Page4 Battery Status.
 */
public class SpeedCadenceDataPage4BatteryStatus extends AbstractSpeedCadenceDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 4;

    /**
     * Constructs a new {@link SpeedCadenceDataPage4BatteryStatus}.
     *
     * @param dataPageBytes the data page bytes
     */
    public SpeedCadenceDataPage4BatteryStatus(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Range: 0-255
     * Unit: 1/256 V
     * @return fractional battery voltage.
     */
    public int getFractionalBatteryVoltage() {
        // TODO: Convert into something useful
        byte batteryLevelByte = getPageSpecificBytes()[0];

        return batteryLevelByte == (byte) 0xFF ? -1 : toInt(batteryLevelByte);
    }

    /**
     * Returns the battery voltage description.
     *
     * @return the battery voltage description.
     */
    public BatteryStatus getBatteryVoltageDescription() {
        return switch (toInt(getPageSpecificBytes()[2])) {
            case 1 -> BatteryStatus.NEW;
            case 2 -> BatteryStatus.GOOD;
            case 3 -> BatteryStatus.OK;
            case 4 -> BatteryStatus.LOW;
            case 5 -> BatteryStatus.CRITICAL;
            default -> BatteryStatus.INVALID;
        };
    }
}
