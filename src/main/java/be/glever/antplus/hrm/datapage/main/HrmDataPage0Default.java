package be.glever.antplus.hrm.datapage.main;

import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractHRMDataPage}
 */
public class HrmDataPage0Default extends AbstractHRMDataPage {


    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0;

    /**
     * Constructs a new {@link HrmDataPage0Default}.
     *
     * @param dataPageBytes the data page bytes
     */
    public HrmDataPage0Default(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
