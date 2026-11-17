package be.glever.antplus.fec.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.fec.EquipmentType;
import be.glever.antplus.fec.FecState;
import be.glever.antplus.fec.HeartRateDataSource;
import be.glever.antplus.power.datapage.AbstractPowerDataPage;

import java.util.Optional;

/**
 * Default or unknown data page.
 * Used in transmission control patterns.
 * Contains no useful extra info over the generic {@link AbstractPowerDataPage}
 */
public class FecDataPage16GeneralFeData extends AbstractPowerDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 16;

    /**
     * Constructs a new {@link FecDataPage16GeneralFeData}.
     *
     * @param dataPageBytes the data page bytes
     */
    public FecDataPage16GeneralFeData(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Returns the equipment type.
     *
     * @return the equipment type.
     */
    public Optional<EquipmentType> getEquipmentType() {
        byte equipmentTypeBitField = getPageSpecificBytes()[0];
        byte mask = (byte) 0b00001111;  // bits 5-7 are reserved
        int masked = equipmentTypeBitField & mask;
        return EquipmentType.valueOf(masked);
    }

    /**
     * Accumulated time since the start of the workout
     * <p>
     * Unit: Seconds
     * Resolution: 1/4s
     * Rollover: 64s
     * @return the elapsed time.
     */
    public int getElapsedTime() {
        return 4 * getPageSpecificBytes()[1];
    }

    /**
     * Accumulated time since the start of the workout
     * <p>
     * Available for at least rowers, cycle trainers, treadmills and nordic skiers
     * <p>
     * Resolution: 1m
     * Unit: Meters
     * Rollover: 256m
     * @return the distance travelled.
     */
    public int getDistanceTravelled() {
        return getPageSpecificBytes()[2];
    }

    /**
     * Current speed, 0xFFFF is invalid
     * Unit: m/s
     * Resolution 1/1000 m/s
     * Range: 0 - 65.534 m/s
     * @return the speed.
     */
    public double getSpeed() {
        return ByteUtils.fromUShort(getPageSpecificBytes()[3], getPageSpecificBytes()[4]) / 1000.0;
    }

    /**
     * Heart rate from HRM or hand contact sensor
     * <p>
     * Unit: bpm
     * Resolution 1bpm
     * Range: 0-254 bpm
     * @return the heart rate.
     */
    public int getHeartRate() {
        return getPageSpecificBytes()[5];
    }

    /**
     * Returns the heart rate source.
     *
     * @return the heart rate source.
     */
    public Optional<HeartRateDataSource> getHeartRateSource() {
        byte capabilities = getPageSpecificBytes()[6];
        return HeartRateDataSource.valueOf(capabilities & 0b00000011);
    }

    /**
     * Checks if distance traveled enabled.
     *
     * @return {@code true} if distance traveled enabled, {@code false} otherwise.
     */
    public boolean isDistanceTraveledEnabled() {
        byte capabilities = getPageSpecificBytes()[6];
        return ByteUtils.hasBitSet(capabilities, 2);
    }

    /**
     * Checks if virtual speed.
     *
     * @return {@code true} if virtual speed, {@code false} otherwise.
     */
    public boolean isVirtualSpeed() {
        byte capabilities = (byte) (getPageSpecificBytes()[6] & 0x0F);
        return ByteUtils.hasBitSet(capabilities, 3);
    }

    /**
     * Returns the fec state.
     *
     * @return the fec state.
     */
    public Optional<FecState> getFecState() {
        byte fecState = (byte) (getPageSpecificBytes()[6] >> 4);
        return FecState.valueOf(fecState & 0b0000011);
    }

    /**
     * Returns whether lap toggle is enabled or active.
     *
     * @return {@code true} if lap toggle, {@code false} otherwise.
     */
    public boolean getLapToggle() {
        return ByteUtils.hasBitSet(getPageSpecificBytes()[6] >> 4, 3);
    }


    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
