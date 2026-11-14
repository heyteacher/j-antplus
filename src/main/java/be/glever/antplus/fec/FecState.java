package be.glever.antplus.fec;

import java.util.Arrays;
import java.util.Optional;

public enum FecState {
    ASLEEP_OFF((byte) 1),
    READY((byte) 2),
    IN_USE((byte) 3),
    FINISHED_PAUSED((byte) 4);

    private final byte value;

    FecState(byte i) {
        value = i;
    }

    public static Optional<FecState> valueOf(int value) {
        return Arrays.stream(values())
                .filter(legNo -> legNo.value == value)
                .findFirst();
    }

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

