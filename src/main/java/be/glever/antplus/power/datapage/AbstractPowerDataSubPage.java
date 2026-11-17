package be.glever.antplus.power.datapage;

import java.util.Arrays;

/**
 * Abstract super class for all power sub-pages.
 */
public abstract class AbstractPowerDataSubPage {

    final private byte[] dataSubPageBytes;

    /**
     * Constructs a new {@link AbstractPowerDataSubPage}.
     *
     * @param dataSubPageBytes the data sub page bytes
     */
    public AbstractPowerDataSubPage(byte[] dataSubPageBytes) {
        this.dataSubPageBytes = dataSubPageBytes;
    }

    /**
     * Returns the data sub page bytes.
     *
     * @return the data sub page bytes.
     */
    public byte[] getDataSubPageBytes() {
        return dataSubPageBytes;
    }

    /**
     * Returns the sub page number.
     *
     * @return the sub page number.
     */
    public abstract byte getSubPageNumber();

    /**
     * Data sub page sub array.
     *
     * @param from the from
     * @param to the to
     * @return the data sub page sub array.
     */
    protected byte[] dataSubPageSubArray(int from, int to) {
        return Arrays.copyOfRange(getDataSubPageBytes(), from, to);
    }
}
