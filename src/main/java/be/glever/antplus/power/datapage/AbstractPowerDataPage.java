package be.glever.antplus.power.datapage;

import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;

import java.util.Arrays;

public abstract class AbstractPowerDataPage extends AbstractAntPlusDataPage {

    public AbstractPowerDataPage(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Subclasses typically parse this byte to useful data.
     */
    protected byte[] getPageSpecificBytes() {
        return Arrays.copyOfRange(getDataPageBytes(), 1, 8);
    }

}
