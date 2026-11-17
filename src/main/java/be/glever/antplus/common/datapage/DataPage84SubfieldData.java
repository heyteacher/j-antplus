package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 84: Subfield Data.
 */
public class DataPage84SubfieldData extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 84;

    /**
     * Constructs a new {@link DataPage84SubfieldData}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage84SubfieldData(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    /**
     * Returns the sub page1.
     *
     * @return the sub page1.
     */
    public byte getSubPage1() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the sub page2.
     *
     * @return the sub page2.
     */
    public byte getSubPage2() {
        return getDataPageBytes()[3];
    }

    /**
     * Returns the data field1.
     *
     * @return the data field1.
     */
    public byte[] getDataField1() {
        return super.dataPageSubArray(4, 6);
    }

    /**
     * Returns the data field2.
     *
     * @return the data field2.
     */
    public byte[] getDataField2() {
        return super.dataPageSubArray(6, 8);
    }

    // TODO implement subpages as per spec 6-17
}
