package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 80: Manufacturers Information.
 */
public class DataPage80ManufacturersInformation extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 80;

    /**
     * Constructs a new {@link DataPage80ManufacturersInformation}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage80ManufacturersInformation(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    /**
     * Returns the hw revision.
     *
     * @return the hw revision.
     */
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
        return super.dataPageSubArray(6, 8);
    }
}
