package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 85: Memory Level.
 */
public class DataPage85MemoryLevel extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 85;

    /**
     * Constructs a new {@link DataPage85MemoryLevel}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage85MemoryLevel(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    /**
     * Returns the percent used.
     *
     * @return the percent used.
     */
    public byte getPercentUsed() {
        return getDataPageBytes()[4];
    }

    /**
     * Returns the total size.
     *
     * @return the total size.
     */
    public byte[] getTotalSize() {
        return super.dataPageSubArray(5, 7);
    }


    /**
     * Returns the total size unit.
     *
     * @return the total size unit.
     */
    public byte getTotalSizeUnit() {
        return getDataPageBytes()[7];
    }
}
