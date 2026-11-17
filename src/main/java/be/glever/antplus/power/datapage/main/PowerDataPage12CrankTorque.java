package be.glever.antplus.power.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.power.datapage.AbstractPowerDataPage;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractPowerDataPage}
 */
public class PowerDataPage12CrankTorque extends AbstractPowerDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0x12;

    /**
     * Constructs a new {@link PowerDataPage12CrankTorque}.
     *
     * @param dataPageBytes the data page bytes
     */
    public PowerDataPage12CrankTorque(byte[] dataPageBytes) {
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
     * Rollover: 256
     * @return the crank revolutions.
     */
    public int getCrankRevolutions() {
        return getPageSpecificBytes()[1];
    }

    /**
     * Instantaneous crank cadence. 255 if unavailable
     * Unit: RPM
     * Range: 0 - 254 RPM
     * @return the instantaneous cadence.
     */
    public int getInstantaneousCadence() {
        return getPageSpecificBytes()[2];
    }

    /**
     * Rollower: 32s
     * Unit: 1/2048s
     * @return the crank period.
     */
    public double getCrankPeriod() {
        byte[] pageBytes = getPageSpecificBytes();
        return ByteUtils.fromUShort(pageBytes[3], pageBytes[4]);
    }

    /**
     * Rollover: 2048Nm
     * Unit: 1/32Nm
     * @return the accumulated torque.
     */
    public double getAccumulatedTorque() {
        byte[] pageBytes = getPageSpecificBytes();
        return ByteUtils.fromUShort(pageBytes[5], pageBytes[6]);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
