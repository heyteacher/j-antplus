package be.glever.antplus.common.datapage;

import java.util.Arrays;

/**
 * ANT+ Data Page 67: Ant Fs Client Beacon.
 */
public class DataPage67AntFsClientBeacon extends AbstractAntPlusDataPage {


    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 67;

    /**
     * Constructs a new {@link DataPage67AntFsClientBeacon}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage67AntFsClientBeacon(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the status byte1.
     *
     * @return the status byte1.
     */
    public byte getStatusByte1() {
        return super.getDataPageBytes()[1];
    }

    /**
     * Returns the status byte2.
     *
     * @return the status byte2.
     */
    public byte getStatusByte2() {
        return super.getDataPageBytes()[2];
    }

    /**
     * Returns the authentication type.
     *
     * @return the authentication type.
     */
    public byte getAuthenticationType() {
        return super.getDataPageBytes()[3];
    }

    /**
     * Returns the ant fs device descriptor or host serial number.
     *
     * @return the ant fs device descriptor or host serial number.
     */
    public byte[] getAntFsDeviceDescriptorOrHostSerialNumber() {
        return Arrays.copyOfRange(getDataPageBytes(), 4, 8);
    }
}
