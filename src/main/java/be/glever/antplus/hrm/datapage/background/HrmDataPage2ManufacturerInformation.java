package be.glever.antplus.hrm.datapage.background;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;

public class HrmDataPage2ManufacturerInformation extends AbstractHRMDataPage {

    public static final byte PAGE_NR = 0x2;

    public HrmDataPage2ManufacturerInformation(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
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

    /**
     * Gets the manufacturer id
     * @return the manufacturer id
     */
    public int getManufacturerId() {
        return getDataPageBytes()[1];
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, DeviceNumber %s, ManufacturerId, %s",
                getClass().getSimpleName(),
                super.getToString(),
                getDeviceNumber(),
                getManufacturerId()
                );
    }
}
