package be.glever.antplus.power.datapage;

import java.util.Arrays;

/**
 * Abstract super class for all power sub-pages.
 */
public abstract class AbstractPowerDataSubPage {

    final private byte[] dataSubPageBytes;

    public AbstractPowerDataSubPage(byte[] dataSubPageBytes) {
        this.dataSubPageBytes = dataSubPageBytes;
    }

    public byte[] getDataSubPageBytes() {
        return dataSubPageBytes;
    }

    public abstract byte getSubPageNumber();

    protected byte[] dataSubPageSubArray(int from, int to) {
        return Arrays.copyOfRange(getDataSubPageBytes(), from, to);
    }
}
