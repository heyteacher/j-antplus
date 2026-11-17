package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 78: Multi Component System Manufacturers Information.
 */
public class DataPage78MultiComponentSystemManufacturersInformation extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 78;

    /**
     * Constructs a new {@link DataPage78MultiComponentSystemManufacturersInformation}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage78MultiComponentSystemManufacturersInformation(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the component identifier.
     *
     * @return the component identifier.
     */
    @SuppressWarnings("unused")
    public byte getComponentIdentifier() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the hw revision.
     *
     * @return the hw revision.
     */
    @SuppressWarnings("unused")
    public byte getHwRevision() {
        return getDataPageBytes()[3];
    }

    /**
     * Returns the manufacturer id.
     *
     * @return the manufacturer id.
     */
    public byte[] getManufacturerId() {
        return super.dataPageSubArray(4, 6);
    }

    /**
     * Returns the model number.
     *
     * @return the model number.
     */
    public byte[] getModelNumber() {
        return dataPageSubArray(6, 8);
    }
}
