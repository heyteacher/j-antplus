package be.glever.antplus.fec.datapage;

import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;

import java.util.Arrays;

/**
 * Abstract base class for Abstract Fec Data Page.
 */
public abstract class AbstractFecDataPage extends AbstractAntPlusDataPage {

    /**
     * Constructs a new {@link AbstractFecDataPage}.
     *
     * @param dataPageBytes the data page bytes
     */
    public AbstractFecDataPage(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Subclasses typically parse this byte to useful data.
     * @return the page specific bytes.
     */
    protected byte[] getPageSpecificBytes() {
        return Arrays.copyOfRange(getDataPageBytes(), 1, 7);
    }

}
