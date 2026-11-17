package be.glever.antplus.common.datapage;

import java.util.Arrays;

/**
 * Abstract super class for all DataPages.
 */
public abstract class AbstractAntPlusDataPage {

    final private byte[] dataPageBytes;

    /**
     * Create a new DataPage from the given byte array.
     * @param dataPageBytes the byte array
     */
    public AbstractAntPlusDataPage(byte[] dataPageBytes) {
        this.dataPageBytes = dataPageBytes;
    }

    /**
     * Returns the data page bytes.
     *
     * @return the data page bytes.
     */
    public byte[] getDataPageBytes() {
        return dataPageBytes;
    }

    /**
     * Returns the page number.
     *
     * @return the page number.
     */
    public abstract byte getPageNumber();

    // TODO may be HRM  or main/background datapage specific. Pull down if necessary.
    /**
     * Returns whether page change toggle is enabled or active.
     *
     * @return {@code true} if page change toggle, {@code false} otherwise.
     */
    public boolean getPageChangeToggle() {
        return (1 & getDataPageBytes()[1]) == 1;
    }

    /**
     * Data page sub array.
     *
     * @param from the from
     * @param to the to
     * @return the data page sub array.
     */
    protected byte[] dataPageSubArray(int from, int to) {
        return Arrays.copyOfRange(getDataPageBytes(), from, to);
    }
}
