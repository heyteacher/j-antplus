package be.glever.antplus.common.datapage.constants;

/**
 * Enumeration representing Battery Status.
 */
public enum BatteryStatus {
    /**
     * Constant representing {@code NEW}.
     */
    NEW(1),
    /**
     * Constant representing {@code GOOD}.
     */
    GOOD(2),
    /**
     * Constant representing {@code OK}.
     */
    OK(3),
    /**
     * Constant representing {@code LOW}.
     */
    LOW(4),
    /**
     * Constant representing {@code CRITICAL}.
     */
    CRITICAL(5),
    /**
     * Constant representing {@code INVALID}.
     */
    INVALID(7);

    final private int value;

    /**
     * Returns the value.
     *
     * @return the value.
     */
    public int getValue() {
        return value;
    }

    BatteryStatus(int value) {
        this.value = value;
    }

    /**
     * Returns the enum constant for the given raw value.
     *
     * @param value the value
     * @return the matching constant.
     */
    public static BatteryStatus fromValue(int value) {
        for (BatteryStatus batteryStatus : BatteryStatus.values()) {
            if (batteryStatus.value == value) {
                return batteryStatus;
            }
        }
        throw new IllegalArgumentException("Unknown battery status type for value [" + value + "]");
    }
}
