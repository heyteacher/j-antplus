package be.glever.antplus.power.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.power.PedalPower;
import be.glever.antplus.power.datapage.AbstractPowerDataPage;
import be.glever.util.logging.Log;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractPowerDataPage}
 */
public class PowerDataPage10PowerOnly extends AbstractPowerDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0x10;

    private static final Log LOG = Log.getLogger(PowerDataPage10PowerOnly.class);

    /**
     * Constructs a new {@link PowerDataPage10PowerOnly}.
     *
     * @param dataPageBytes the data page bytes
     */
    public PowerDataPage10PowerOnly(byte[] dataPageBytes) {
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
     * How the power is distributed between left and right pedal
     *
     *
     * @return the pedal power distribution.
     */
    public PedalPower getPedalPowerDistribution() {
        int pedalPower = getPageSpecificBytes()[1];
        if (pedalPower == -1) {
            LOG.error(() -> "Invalid pedal power distribution");
        }

        boolean zeroIsLeft = ByteUtils.hasBitSet(pedalPower, 7);
        int powerPercentage = pedalPower & 0b01111111;
        return new PedalPower(zeroIsLeft, powerPercentage);
    }

    /**
     * Crank cadence. 255 if unavailable
     * Unit: RPM
     * Range: 0 - 254 RPM
     * @return the instantaneous cadence.
     */
    public int getInstantaneousCadence() {
        return getPageSpecificBytes()[2];
    }

    /**
     * Rollower: 65.536 kW
     * Unit: Watt
     * @return the accumulated power.
     */
    public double getAccumulatedPower() {
        byte[] pageBytes = getPageSpecificBytes();
        return ByteUtils.fromUShort(pageBytes[3], pageBytes[4]);
    }

    /**
     * Range: 0 - 65.535 kW
     * Unit: Watt
     * @return the instantaneous power.
     */
    public double getInstantaneousPower() {
        byte[] pageBytes = getPageSpecificBytes();
        return ByteUtils.fromUShort(pageBytes[5], pageBytes[6]);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
