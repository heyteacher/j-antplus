package be.glever.antplus.power.datapage;

import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;

import java.util.Arrays;

/**
 * Abstract base class for Abstract Power Data Page.
 */
public abstract class AbstractPowerDataPage extends AbstractAntPlusDataPage {

    /**
     * Constructs a new {@link AbstractPowerDataPage}.
     *
     * @param dataPageBytes the data page bytes
     */
    public AbstractPowerDataPage(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Subclasses typically parse this byte to useful data.
     * @return the page specific bytes.
     */
    protected byte[] getPageSpecificBytes() {
        return Arrays.copyOfRange(getDataPageBytes(), 1, 8);
    }

}
