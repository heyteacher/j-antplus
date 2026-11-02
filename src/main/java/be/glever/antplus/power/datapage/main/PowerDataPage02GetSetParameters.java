package be.glever.antplus.power.datapage.main;

import static java.lang.String.format;

import java.util.Arrays;

import be.glever.ant.util.ByteArrayBuilder;
import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;
import be.glever.antplus.power.datapage.AbstractPowerDataSubPage;

public class PowerDataPage02GetSetParameters extends AbstractAntPlusDataPage {

    public static final byte PAGE_NR = 2;

    /**
     * Get parameters page for a specific subpage.
     *
     * @param dataPageBytes the data page bytes of page
     */
    public PowerDataPage02GetSetParameters
            (byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Set parameters for a subpage.
     *
     * @param subPage  the subpage to set
     */
    public PowerDataPage02GetSetParameters(AbstractPowerDataSubPage subPage) {
        super(new ByteArrayBuilder().write(PAGE_NR).write(subPage.getDataSubPageBytes()).toByteArray());
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Get the subpage number.
     *
     * @return the subpage number
     */
    public byte getSubPageNumber() {
        return getDataPageBytes()[1];
    }

    /**
     * Get the subpage based on the subpage number.
     *
     * @return the subpage based on the subpage number
     */
    public AbstractPowerDataSubPage getSubPage() {
        byte[] subPagePayload =  Arrays.copyOfRange(getDataPageBytes(), 1, getDataPageBytes().length);
        switch (getSubPageNumber()) {
            case PowerDataSubPage01CrankParameters.SUB_PAGE_NR -> {
                return new PowerDataSubPage01CrankParameters(subPagePayload);
            }
            case PowerDataSubPage254AdvancedCapabilities2.SUB_PAGE_NR -> {
                return new PowerDataSubPage254AdvancedCapabilities2(subPagePayload);
            }
            default -> {
                throw new IllegalStateException("Unknown subpage number: " + getSubPageNumber());
            }
        }
    }

    @Override
    public String toString() {
        return format("PageNumber: 0x%02X, %s" , getPageNumber() , getSubPage());
    }
}
