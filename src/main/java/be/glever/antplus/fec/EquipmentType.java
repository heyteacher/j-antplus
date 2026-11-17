package be.glever.antplus.fec;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enumeration representing Equipment Type.
 */
public enum EquipmentType {
    /**
     * Constant representing {@code Treadmill}.
     */
    Treadmill((byte) 19),
    /**
     * Constant representing {@code Elliptical}.
     */
    Elliptical((byte) 20),
    /**
     * Constant representing {@code Rower}.
     */
    Rower((byte) 22),
    /**
     * Constant representing {@code Climber}.
     */
    Climber((byte) 23),
    /**
     * Constant representing {@code NordicSkier}.
     */
    NordicSkier((byte) 24),
    /**
     * Constant representing {@code StationaryBike}.
     */
    StationaryBike((byte) 25);

    private final byte value;

    EquipmentType(byte i) {
        value = i;
    }

    /**
     * Returns the enum constant for the given value.
     *
     * @param value the value
     * @return the matching constant.
     */
    public static Optional<EquipmentType> valueOf(int value) {
        return Arrays.stream(values())
                .filter(legNo -> legNo.value == value)
                .findFirst();
    }

    /**
     * Returns the raw value.
     *
     * @return the raw value.
     */
    public byte value() {
        return value;
    }
}
