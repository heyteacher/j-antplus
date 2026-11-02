package be.glever.antplus.common.datapage;

import be.glever.antplus.common.datapage.constants.BatteryStatus;

public class DataPage82BatteryStatus extends AbstractAntPlusDataPage {
    public static final byte PAGE_NR = 82;

    public DataPage82BatteryStatus(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    public int getBatteryIdentifier() {
        return (getDataPageBytes()[2]>> 4) & 0b00000111;
    }

    public int getNumberOfBatteries() {
        return getDataPageBytes()[2] & 0b00000111;
    }

    public BatteryStatus getBatteryStatus() {
        int batteryStatusValue = (getDataPageBytes()[7] >> 4) & 0b00000111;
        return BatteryStatus.fromValue(batteryStatusValue);
    }

    public byte getFractionalBatteryVoltage() {
        return getDataPageBytes()[6];
    }
}
