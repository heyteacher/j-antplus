package be.glever.antplus.fec;

import java.util.Arrays;
import java.util.Optional;

/**
 * Where the FE-C equipment obtains the heart rate measurement from
 */
public enum HeartRateDataSource {
    // Unknown source
    /**
     * Constant representing {@code UNKNOWN}.
     */
    UNKNOWN((byte) 0),
    // ANT+ sensor connected to the FE-C equipment
    /**
     * Constant representing {@code ANT_PLUS}.
     */
    ANT_PLUS((byte) 1),
    // 5 kHz wireless HRM worn by the athlete
    /**
     * Constant representing {@code EM}.
     */
    EM((byte) 3),
    // Hand contact sensor on the FE-C equipment
    /**
     * Constant representing {@code HAND_CONTACTS}.
     */
    HAND_CONTACTS((byte) 3);

    private final byte value;

    HeartRateDataSource(byte value) {
        this.value = value;
    }

    /**
     * Returns the enum constant for the given value.
     *
     * @param value the value
     * @return the matching constant.
     */
    public static Optional<HeartRateDataSource> valueOf(int value) {
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
