package be.glever.antplus.hrm.datapage.background;

import be.glever.antplus.common.datapage.constants.BatteryStatus;
import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;

import static be.glever.ant.util.ByteUtils.toInt;

/**
 * ANT+ HRM Data Page 7: Battery Status.
 */
public class HrmDataPage7BatteryStatus extends AbstractHRMDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 7;

    /**
     * Constructs a new {@link HrmDataPage7BatteryStatus}.
     *
     * @param dataPageBytes the data page bytes
     */
    public HrmDataPage7BatteryStatus(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the battery level percentage.
     *
     * @return Battery level in percentage or -1 if not used.
     */
    @SuppressWarnings("unused")
    public int getBatteryLevelPercentage() {
        byte batteryLevelByte = getPageSpecificBytes()[0];

        return batteryLevelByte == (byte) 0xFF ? -1 : toInt(batteryLevelByte);
    }

    /**
     * Returns the fractional battery voltage.
     *
     * @return Fractional battery voltage. Not sure what this is, perhaps just a better granularity (1/256V) than percentage? At the moment just return this byte toInt
     */
    @SuppressWarnings("unused")
    public int getFractionalBatteryVoltage() {
        return toInt(getPageSpecificBytes()[1]);
    }

    /**
     * Returns the battery voltage description.
     *
     * @return the battery voltage description.
     */
    @SuppressWarnings("unused")
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

