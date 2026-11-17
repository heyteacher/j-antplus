package be.glever.antplus.speedcadence.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;

import java.util.Arrays;

/**
 * Speed and cadence data page 0.
 */
public class SpeedAndCadenceDataPage0Default extends AbstractAntPlusDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0;

    /**
     * Constructs a new {@link SpeedAndCadenceDataPage0Default}.
     *
     * @param dataPageBytes the data page bytes
     */
    public SpeedAndCadenceDataPage0Default(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Subclasses typically parse this byte to useful data.
     * @return the page specific bytes.
     */
    protected byte[] getPageSpecificBytes() {
        return Arrays.copyOfRange(getDataPageBytes(), 1, 4);
    }

    /**
     * Time of last valid speed event in milliseconds since "epoch".
     * Note that the ANT+ field size for this is 16 bit and resolution is 1/1024 seconds,
     * so the  value rolls over at 63.999 seconds ( ((1/1024)*(2^17)) - (1/1024)) ).
     * @return the speed event time.
     */
    public long getSpeedEventTime() {
        return calculateEventTime(getDataPageBytes()[4], getDataPageBytes()[5]);
    }

    /**
     * Time of last valid speed event in milliseconds since "epoch".
     * Note that the ANT+ field size for this is 16 bit and resolution is 1/1024 seconds,
     * so the  value rolls over at 63.999 seconds ( ((1/1024)*(2^17)) - (1/1024)) ).
     * @return the cadence event time.
     */
    public long getCadenceEventTime() {
        return calculateEventTime(getDataPageBytes()[0], getDataPageBytes()[1]);
    }

    /**
     * Calculate event time.
     *
     * @param bite1 the bite1
     * @param bite2 the bite2
     * @return the calculate event time.
     */
    protected long calculateEventTime(byte bite1, byte bite2) {
        double timeAnt = ByteUtils.fromUShort(bite1, bite2);
        return (long) ((timeAnt / 1024) * 1000);
    }

    /**
     * Total number of wheel revolutions
     * Rollover 2^16
     * @return the cumulative speed revolutions.
     */
    public int getCumulativeSpeedRevolutions() {
        return ByteUtils.fromUShort(getDataPageBytes()[6], getDataPageBytes()[7]);
    }

    /**
     * Total number of crank revolutions
     * Rollover 2^16
     * @return the cumulative cadence revolutions.
     */
    public int getCumulativeCadenceRevolutions() {
        return ByteUtils.fromUShort(getDataPageBytes()[2], getDataPageBytes()[3]);
    }

    /**
     * Returns the to string.
     *
     * @return the to string.
     */
    protected String getToString() {
        return "speedEventTime=" + getSpeedEventTime()
                + ",cumulativeSpeedRevolutions=" + getCumulativeSpeedRevolutions()
                + ",cadenceEventTime=" + getCumulativeCadenceRevolutions()
                + ",cumulativeCadenceRevolutions=" + getCumulativeCadenceRevolutions();
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

}
