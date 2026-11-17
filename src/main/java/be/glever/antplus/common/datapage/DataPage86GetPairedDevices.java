package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 86: Get Paired Devices.
 */
public class DataPage86GetPairedDevices extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 86;

    /**
     * Constructs a new {@link DataPage86GetPairedDevices}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage86GetPairedDevices(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    /**
     * Returns the peripheral device index.
     *
     * @return the peripheral device index.
     */
    public byte getPeripheralDeviceIndex() {
        return getDataPageBytes()[1];
    }


    /**
     * Returns the total number of connected devices.
     *
     * @return the total number of connected devices.
     */
    public byte getTotalNumberOfConnectedDevices() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the channel state.
     *
     * @return the channel state.
     */
    public byte getChannelState() {
        return getDataPageBytes()[3];
    }


    /**
     * Returns the peripheral device id device number.
     *
     * @return the peripheral device id device number.
     */
    public byte[] getPeripheralDeviceIdDeviceNumber() {
        return super.dataPageSubArray(4, 6);
    }

    /**
     * Returns the peripheral device id transmission type.
     *
     * @return the peripheral device id transmission type.
     */
    public byte getPeripheralDeviceIdTransmissionType() {
        return getDataPageBytes()[6];
    }

    /**
     * Returns the peripheral device id device type.
     *
     * @return the peripheral device id device type.
     */
    public byte getPeripheralDeviceIdDeviceType() {
        return getDataPageBytes()[7];
    }
}
