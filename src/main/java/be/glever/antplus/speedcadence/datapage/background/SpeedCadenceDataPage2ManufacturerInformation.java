package be.glever.antplus.speedcadence.datapage.background;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;
import be.glever.antplus.speedcadence.datapage.AbstractSpeedCadenceDataPage;

public class SpeedCadenceDataPage2ManufacturerInformation extends AbstractSpeedCadenceDataPage {

    public static final byte PAGE_NR = 0x2;

    public SpeedCadenceDataPage2ManufacturerInformation(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    // TODO: Is there a method to convert manufacturer ID to a string? We'd need a database of the official registry.
    public int getManufacturerId() {
        return super.getPageSpecificBytes()[0];
    }

    /**
     * Gets the device number, the upper 16 bits of the 4-byte serial number
     * @return the device number
     */
    public long getDeviceNumber() {
        return ByteUtils.fromUShort(getDataPageBytes()[2], getDataPageBytes()[3]);
    }

    /**
     * Gets the device number hexadecimal string representation
     * @return the device number hexadecimal string representation
     */
    public String getDeviceNumberAsString() {
        return ByteUtils.hexString(super.dataPageSubArray(2, 4)).replace("-","");
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, ManufacturerId=%s, DeviceNumber %s, DeviceNumberAsString %s",
                getClass().getSimpleName(),
                super.getToString(),
                getManufacturerId(),
                getDeviceNumber(),
                getDeviceNumberAsString());
    }
}
