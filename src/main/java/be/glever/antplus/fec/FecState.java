package be.glever.antplus.fec;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enumeration representing Fec State.
 */
public enum FecState {
    /**
     * Constant representing {@code ASLEEP_OFF}.
     */
    ASLEEP_OFF((byte) 1),
    /**
     * Constant representing {@code READY}.
     */
    READY((byte) 2),
    /**
     * Constant representing {@code IN_USE}.
     */
    IN_USE((byte) 3),
    /**
     * Constant representing {@code FINISHED_PAUSED}.
     */
    FINISHED_PAUSED((byte) 4);

    private final byte value;

    FecState(byte i) {
        value = i;
    }

    /**
     * Returns the enum constant for the given value.
     *
     * @param value the value
     * @return the matching constant.
     */
    public static Optional<FecState> valueOf(int value) {
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


    @Override
    public String toString() {
        return switch (this) {
            case ASLEEP_OFF -> "Asleep or Off";
            case READY -> "Ready";
            case IN_USE -> "In use";
            case FINISHED_PAUSED -> "Finished or Paused";
            };
    }
}

