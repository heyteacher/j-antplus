package be.glever.antplus.power.datapage.main;

import be.glever.antplus.power.datapage.AbstractPowerDataPage;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractPowerDataPage}
 */
public class PowerDataPage13TorqueEffectivenessPedalSmoothness extends AbstractPowerDataPage {


    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0x13;

    /**
     * Constructs a new {@link PowerDataPage13TorqueEffectivenessPedalSmoothness}.
     *
     * @param dataPageBytes the data page bytes
     */
    public PowerDataPage13TorqueEffectivenessPedalSmoothness(byte[] dataPageBytes) {
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
     * Left crank torque effectiveness
     * <p>
     * Unit: 0.5%
     * Range: 0-100%
     * @return the left torque effectiveness.
     */
    public int getLeftTorqueEffectiveness() {
        return getPageSpecificBytes()[1];
    }

    /**
     * Right crank torque effectiveness
     * <p>
     * Unit: 0.5%
     * Range: 0-100%
     * @return the right torque effectiveness.
     */
    public int getRightTorqueEffectiveness() {
        return getPageSpecificBytes()[2];
    }

    /**
     * Torque smoothness of the left crank, invalid if 0xFF
     * <p>
     * Unit: 0.5%
     * Range: 0-100%
     * @return the left pedal smoothness.
     */
    public int getLeftPedalSmoothness() {
        if (getRightPedalSmoothness() == 0xFF)
            return 0xFF;

        return getPageSpecificBytes()[3];
    }

    /**
     * Torque smoothness of the right crank, invalid if 0xFF
     * <p>
     * Unit: 0.5%
     * Range: 0-100%
     * @return the right pedal smoothness.
     */
    public int getRightPedalSmoothness() {
        return getPageSpecificBytes()[4];
    }

    /**
     * Combined torque smoothness of the left and right crank, invalid if 0xFF
     * <p>
     * Unit: 0.5%
     * Range: 0-100%
     * @return the combined pedal smoothness.
     */
    public int getCombinedPedalSmoothness() {
        if (getRightPedalSmoothness() != 0xFF)
            return 0xFF;

        return getPageSpecificBytes()[3];
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
