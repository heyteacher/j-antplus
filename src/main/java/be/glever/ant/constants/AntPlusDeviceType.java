package be.glever.ant.constants;

/**
 * Enumeration representing Ant Plus Device Type.
 */
public enum AntPlusDeviceType {
    /**
     * Constant representing {@code Fec}.
     */
    Fec((byte) 0x11),
    /**
     * Constant representing {@code Power}.
     */
    Power((byte) 0x0B),
    /**
     * Constant representing {@code HRM}.
     */
    HRM((byte) 0x78),
    /**
     * Constant representing {@code SpeedCadence}.
     */
    SpeedCadence((byte) 0x79),
    /**
     * Constant representing {@code Cadence}.
     */
    Cadence((byte) 0x7A),
    /**
     * Constant representing {@code Speed}.
     */
    Speed((byte) 0x7B);

    private final byte value;

    AntPlusDeviceType(byte i) {
        value = i;
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
