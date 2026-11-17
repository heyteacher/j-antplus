package be.glever.antplus.fec.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.power.datapage.AbstractPowerDataPage;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractPowerDataPage}
 */
public class FecDataPage25Bike extends AbstractPowerDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 25;

    /**
     * Constructs a new {@link FecDataPage25Bike}.
     *
     * @param dataPageBytes the data page bytes
     */
    public FecDataPage25Bike(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Rollover: 256
     * @return the upddate event count.
     */
    public int getUpddateEventCount() {
        return getPageSpecificBytes()[0];
    }

    /**
     * Unit: RPM
     * Range: 0-254, 255 invalid
     * @return the instantaneous cadence.
     */
    public int getInstantaneousCadence() {
        return getPageSpecificBytes()[1];
    }

    /**
     * Unit: Watt
     * Rollover: 65536W
     * 0xFFF invalid
     * @return the accumulated power.
     */
    public int getAccumulatedPower() {
        if (getInstantaneousPower() == 0xFFF)
            return 0xFFF;

        return ByteUtils.fromUShort(getPageSpecificBytes()[2], getPageSpecificBytes()[3]);
    }

    /**
     * Unit: Watt
     * Range: 0-4094, 0xFFF invalid
     * @return the instantaneous power.
     */
    public int getInstantaneousPower() {
        return ByteUtils.fromUShort(getPageSpecificBytes()[4], (byte) (getPageSpecificBytes()[5] & 0b00001111));
    }

    //public boolean getPowerCalibrationRequired() {

    //}

    //public boolean getResistanceCalibrationRequired() {

    //}

    //public boolean getUserConfigurationRequired() {

    //}

    // TODO: Flags bit field
    // TODO: FE State bit field

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
