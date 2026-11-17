package be.glever.antplus.speedcadence.datapage;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.common.datapage.AbstractAntPlusDataPage;

import java.util.Arrays;

/**
 * Abstract base class for Abstract Speed Cadence Data Page.
 */
public abstract class AbstractSpeedCadenceDataPage extends AbstractAntPlusDataPage {

    /**
     * Constructs a new {@link AbstractSpeedCadenceDataPage}.
     *
     * @param dataPageBytes the data page bytes
     */
    public AbstractSpeedCadenceDataPage(byte[] dataPageBytes) {
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
     * Time of last valid speed or cadence event in milliseconds since "epoch".
     * Note that the ANT+ field size for this is 16 bit and resolution is 1/1024 seconds,
     * so the  value rolls over at 63.999 seconds ( ((1/1024)*(2^17)) - (1/1024)) ).
     * @return the event time.
     */
    public long getEventTime() {
        return calculateSpeedEventTime(getDataPageBytes()[4], getDataPageBytes()[5]);
    }

    /**
     * Calculate speed event time.
     *
     * @param bite1 the bite1
     * @param bite2 the bite2
     * @return the calculate speed event time.
     */
    protected long calculateSpeedEventTime(byte bite1, byte bite2) {
        double timeAnt = ByteUtils.fromUShort(bite1, bite2);
        return (long) ((timeAnt / 1024) * 1000);
    }

    /**
     * Total number of wheel or crank revolutions
     * Rollover 65536 (2 bytes)
     * @return the cumulative revolutions.
     */
    public int getCumulativeRevolutions() {
        return ByteUtils.fromUShort(getDataPageBytes()[6], getDataPageBytes()[7]);
    }

    /**
     * Returns the to string.
     *
     * @return the to string.
     */
    protected String getToString() {
        return "eventTime=" + getEventTime()
                + ",cumulativeRevolutions=" + getCumulativeRevolutions();
    }


}
