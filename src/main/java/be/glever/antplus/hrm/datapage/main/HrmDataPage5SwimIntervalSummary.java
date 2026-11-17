package be.glever.antplus.hrm.datapage.main;

import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;

import static be.glever.ant.util.ByteUtils.toInt;

/**
 * ANT+ HRM Data Page 5: Swim Interval Summary.
 */
public class HrmDataPage5SwimIntervalSummary extends AbstractHRMDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 5;

    /**
     * Constructs a new {@link HrmDataPage5SwimIntervalSummary}.
     *
     * @param dataPageBytes the data page bytes
     */
    public HrmDataPage5SwimIntervalSummary(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Returns the interval average heart rate.
     *
     * @return Interval average heart rate in BPM of the interval.
     */
    public int getIntervalAverageHeartRate() {
        return toInt(getPageSpecificBytes()[0]);
    }

    /**
     * Returns the interval maximum heart rate.
     *
     * @return Interval max heart rate in BPM of the interval.
     */
    public int getIntervalMaximumHeartRate() {
        return toInt(getPageSpecificBytes()[1]);
    }

    /**
     * Returns the session average heart rate.
     *
     * @return Session average heart rate in BPM of the interval.
     */
    public int getSessionAverageHeartRate() {
        return toInt(getPageSpecificBytes()[2]);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
