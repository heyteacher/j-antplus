package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 74: Open Channel Command.
 */
public class DataPage74OpenChannelCommand extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 74;

    /**
     * Constructs a new {@link DataPage74OpenChannelCommand}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage74OpenChannelCommand(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the lower3 bytes of serial number.
     *
     * @return the lower3 bytes of serial number.
     */
    public byte[] getLower3BytesOfSerialNumber() {
        return super.dataPageSubArray(1, 4);
    }

    /**
     * Returns the device type.
     *
     * @return the device type.
     */
    public byte getDeviceType() {
        return getDataPageBytes()[4];
    }

    /**
     * Returns the rf frequency.
     *
     * @return the rf frequency.
     */
    public byte getRfFrequency() {
        return getDataPageBytes()[5];
    }

    /**
     * Returns the channel period.
     *
     * @return the channel period.
     */
    public byte[] getChannelPeriod() {
        return super.dataPageSubArray(6, 8);
    }
}
