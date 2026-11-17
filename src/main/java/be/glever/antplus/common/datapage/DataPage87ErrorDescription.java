package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 87: Error Description.
 */
public class DataPage87ErrorDescription extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 87;

    /**
     * Constructs a new {@link DataPage87ErrorDescription}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage87ErrorDescription(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    // TODO extract meaningful value from bits 0:3
    /**
     * Returns the system component index.
     *
     * @return the system component index.
     */
    public byte getSystemComponentIndex() {
        return getDataPageBytes()[2];
    }

    // TODO extract meaningful value from bits 6:7
    /**
     * Returns the error level.
     *
     * @return the error level.
     */
    public byte getErrorLevel() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the profile specific error codes.
     *
     * @return the profile specific error codes.
     */
    public byte getProfileSpecificErrorCodes() {
        return getDataPageBytes()[3];
    }

    /**
     * Returns the manufacturer specific error codes.
     *
     * @return the manufacturer specific error codes.
     */
    public byte[] getManufacturerSpecificErrorCodes() {
        return super.dataPageSubArray(4, 8);
    }


}
