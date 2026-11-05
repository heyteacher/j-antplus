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

    public byte getSupplementalSwRevision() {
        return getDataPageBytes()[2];
    }

    public byte getMainSwRevision() {
        return getDataPageBytes()[3];
    }

    public static byte[] getByteArraySerialNumber(long serialNumber) {
        return new byte[] {
                (byte)  serialNumber,
                (byte) ( serialNumber >> 8)
        };
    }

    public long getSerialNumber() {
        byte[] lowest32BitsOfSerialNumber = super.dataPageSubArray(6, 8);
        return (lowest32BitsOfSerialNumber[0] & 0xFF) + ((lowest32BitsOfSerialNumber[1]  & 0xFF) * 256) ;
    }

    public String getName() {
        return ByteUtils.hexString(super.dataPageSubArray(6, 8));
    }
}
