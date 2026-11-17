package be.glever.antplus.power.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.power.datapage.AbstractPowerDataPage;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractPowerDataPage}
 */
public class PowerDataPage20CrankTorqueFrequency extends AbstractPowerDataPage {


    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0x20;

    /**
     * Constructs a new {@link PowerDataPage20CrankTorqueFrequency}.
     *
     * @param dataPageBytes the data page bytes
     */
    public PowerDataPage20CrankTorqueFrequency(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Returns the update event count.
     *
     * @return the update event count.
     */
    public int getUpdateEventCount() {
        return getPageSpecificBytes()[0];
    }

    /**
     * Slope/variation of the output frequency
     * <p>
     * Unit: 1/10 Nm/Hz
     * Range: 100-256
     * @return the slope.
     */
    public int getSlope() {
        return ByteUtils.fromUShort(getPageSpecificBytes()[2], getPageSpecificBytes()[1]);
    }

    /**
     * Time of the latest torque rotation event
     * <p>
     * Unit: 1/2000s
     * Rollover: 32.7
     * @return the time stamp.
     */
    public double getTimeStamp() {
        // TODO: Convert to double
        return ByteUtils.fromUShort(getPageSpecificBytes()[4], getPageSpecificBytes()[3]);
    }

    /**
     * 65536 ticks
     * @return the torque ticks stamp.
     */
    public double torqueTicksStamp() {
        return ByteUtils.fromUShort(getPageSpecificBytes()[6], getPageSpecificBytes()[5]);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
