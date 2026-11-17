package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 79: Multi Component System Product Information.
 */
public class DataPage79MultiComponentSystemProductInformation extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 79;

    /**
     * Constructs a new {@link DataPage79MultiComponentSystemProductInformation}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage79MultiComponentSystemProductInformation(byte[] dataPageBytes) {
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
    public byte getComponentIdentifier() {
        return getDataPageBytes()[1];
    }

    /**
     * Returns the supplemental sw revision.
     *
     * @return the supplemental sw revision.
     */
    public byte getSupplementalSwRevision() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the main sw revision.
     *
     * @return the main sw revision.
     */
    public byte getMainSwRevision() {
        return getDataPageBytes()[3];
    }

    /**
     * Returns the lowest32 bits of serial number.
     *
     * @return the lowest32 bits of serial number.
     */
    public byte[] getLowest32BitsOfSerialNumber() {
        return super.dataPageSubArray(4, 8);
    }
}
