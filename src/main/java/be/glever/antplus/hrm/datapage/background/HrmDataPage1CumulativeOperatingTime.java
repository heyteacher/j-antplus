package be.glever.antplus.hrm.datapage.background;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;
import be.glever.util.logging.Log;

import java.time.Duration;

/**
 * ANT+ HRM Data Page 1: Cumulative Operating Time.
 */
public class HrmDataPage1CumulativeOperatingTime extends AbstractHRMDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0x1;
    private static final Log LOG = Log.getLogger(HrmDataPage1CumulativeOperatingTime.class);

    /**
     * Constructs a new {@link HrmDataPage1CumulativeOperatingTime}.
     *
     * @param dataPageBytes the data page bytes
     */
    public HrmDataPage1CumulativeOperatingTime(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Returns the cumulative operating time.
     *
     * @return The time in seconds since the last reset (= battery replace) of the HRM. This value rolls over at 33554430 seconds ( 9320 hours or 388.x days)
     */
    public int getCumulativeOperatingTime() {
        return 2 * ByteUtils.fromUnsignedBytes(getPageSpecificBytes());
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    @Override
    public String toString() {
        return String.format("%s, {%s, CumulativeOperatingTime=%s (%s)}", getClass().getSimpleName(), super.getToString(), getCumulativeOperatingTime(), Duration.ofSeconds(getCumulativeOperatingTime()));
    }
}
