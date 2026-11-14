package be.glever.antplus.fec.datapage;

import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;

import java.util.Arrays;

public abstract class AbstractFecDataPage extends AbstractAntPlusDataPage {

    public AbstractFecDataPage(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Subclasses typically parse this byte to useful data.
     */
    protected byte[] getPageSpecificBytes() {
        return Arrays.copyOfRange(getDataPageBytes(), 1, 7);
    }

}
