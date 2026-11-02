package be.glever.antplus.common.datapage.constants;

public enum BatteryStatus {
    NEW(1),
    GOOD(2),
    OK(3),
    LOW(4),
    CRITICAL(5),
    INVALID(7);

    final private int value;

    public int getValue() {
        return value;
    }

    BatteryStatus(int value) {
        this.value = value;
    }

    public static BatteryStatus fromValue(int value) {
        for (BatteryStatus batteryStatus : BatteryStatus.values()) {
            if (batteryStatus.value == value) {
                return batteryStatus;
            }
        }
        throw new IllegalArgumentException("Unknown battery status type for value [" + value + "]");
    }
}
