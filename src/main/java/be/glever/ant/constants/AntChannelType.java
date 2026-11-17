package be.glever.ant.constants;

/**
 * Enumeration representing Ant Channel Type.
 */
public enum AntChannelType {
    /**
     * Constant representing {@code BIDIRECTIONAL_SLAVE}.
     */
    BIDIRECTIONAL_SLAVE(0x00),
    /**
     * Constant representing {@code BIDIRECTIONAL_MASTER}.
     */
    BIDIRECTIONAL_MASTER(0x10),
    /**
     * Constant representing {@code SHARED_BIDIRECTIONAL_SLAVE}.
     */
    SHARED_BIDIRECTIONAL_SLAVE(0x20),
    /**
     * Constant representing {@code SHARED_BIDIRECTIONAL_MASTER}.
     */
    SHARED_BIDIRECTIONAL_MASTER(0x30),
    /**
     * Constant representing {@code SLAVE_RECEIVE_ONLY}.
     */
    SLAVE_RECEIVE_ONLY(0x40),
    /**
     * Constant representing {@code MASTER_TRANSMIT_ONLY}.
     */
    MASTER_TRANSMIT_ONLY(0x50);

    private final byte value;

    AntChannelType(int value) {
        this.value = (byte) value;
    }

    /**
     * Returns the enum constant for the given raw value.
     *
     * @param value the value
     * @return the matching constant.
     */
    @SuppressWarnings("unused")
    public AntChannelType fromValue(byte value) {
        for (AntChannelType ct : AntChannelType.values()) {
            if (ct.value == value) {
                return ct;
            }
        }
        throw new IllegalArgumentException("Unknown channel type for value [" + value + "]");
    }

    /**
     * Returns the value.
     *
     * @return the value.
     */
    public byte getValue() {
        return value;
    }
}
