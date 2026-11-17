package be.glever.antplus.common.datapage;

import be.glever.antplus.common.datapage.constants.BatteryStatus;

/**
 * ANT+ Data Page 82: Battery Status.
 */
public class DataPage82BatteryStatus extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 82;

    /**
     * Constructs a new {@link DataPage82BatteryStatus}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage82BatteryStatus(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the battery identifier.
     *
     * @return the battery identifier.
     */
    public int getBatteryIdentifier() {
        return (getDataPageBytes()[2]>> 4) & 0b00000111;
    }

    /**
     * Returns the number of batteries.
     *
     * @return the number of batteries.
     */
    public int getNumberOfBatteries() {
        return getDataPageBytes()[2] & 0b00000111;
    }

    /**
     * Returns the battery status.
     *
     * @return the battery status.
     */
    public BatteryStatus getBatteryStatus() {
        int batteryStatusValue = (getDataPageBytes()[7] >> 4) & 0b00000111;
        return BatteryStatus.fromValue(batteryStatusValue);
    }

    /**
     * Returns the fractional battery voltage.
     *
     * @return the fractional battery voltage.
     */
    public byte getFractionalBatteryVoltage() {
        return getDataPageBytes()[6];
    }
}
