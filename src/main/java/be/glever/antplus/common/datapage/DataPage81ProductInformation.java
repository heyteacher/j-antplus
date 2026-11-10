package be.glever.antplus.common.datapage;

import be.glever.ant.util.ByteUtils;

public class DataPage81ProductInformation extends AbstractAntPlusDataPage {
    public static final byte PAGE_NR = 81;

    public DataPage81ProductInformation(byte[] dataPageBytes) {
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
        return ByteUtils.fromUShort(getDataPageBytes()[6], getDataPageBytes()[7]);
    }

    /**
     * Gets the device number hexadecimal string representation
     * @return the device number hexadecimal string representation
     */
    public String getDeviceNumberAsString() {
        return ByteUtils.hexString(super.dataPageSubArray(6, 8)).replace("-","");
    }

    /**
     * Gets the main software revision
     * @return the main software revision
     */
    public byte getMainSwRevision() {
        return getDataPageBytes()[3];
    }

    /**
     * Gets the supplemental software revision
     * @return the supplemental software revision
     */
    public byte getSupplementalSwRevision() {
        return getDataPageBytes()[2];
    }

    /**
     * Gets a device number in 2 bytes
     * @param deviceNumber the device number to convert
     * @return the byte array of 2 bytes
     */
    @SuppressWarnings("unused")
    public static byte[] getDeviceNumberAsBytes(long deviceNumber) {
        return new byte[] {
                (byte)  deviceNumber,
                (byte) ( deviceNumber >> 8)
        };
    }
}
