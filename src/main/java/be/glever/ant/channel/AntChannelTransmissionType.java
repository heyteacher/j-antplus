package be.glever.ant.channel;

/**
 * Ant Channel Transmission Type.
 */
public class AntChannelTransmissionType {
    private final byte value;

    /**
     * Use this one for searching devices. It is the Master device that decides the TransmissionType, which slaves must use when opening a channel.
     */
    public static final AntChannelTransmissionType PAIRING_TRANSMISSION_TYPE = new AntChannelTransmissionType((byte) 0, (byte) 0, (byte) 0);

    /**
     * Constructs a new {@link AntChannelTransmissionType}.
     *
     * @param sharedAddressIndicator the shared address indicator
     * @param globalDataPagesUsage the global data pages usage
     * @param deviceNumberExtension the device number extension
     */
    public AntChannelTransmissionType(byte sharedAddressIndicator, byte globalDataPagesUsage, byte deviceNumberExtension) {
        sharedAddressIndicator = (byte) (0b11 & sharedAddressIndicator);
        globalDataPagesUsage = (byte) (0b100 & (globalDataPagesUsage << 2));
        // 4th bit unused
        deviceNumberExtension = (byte) (deviceNumberExtension << 4);

        this.value = (byte) (sharedAddressIndicator | globalDataPagesUsage | deviceNumberExtension);
    }

    /**
     * Returns the shared address indicator.
     *
     * @return the shared address indicator.
     */
    @SuppressWarnings("unused")
    public byte getSharedAddressIndicator() {
        return (byte) (value & 0b11);
    }

    /**
     * Returns the global data pages usage.
     *
     * @return the global data pages usage.
     */
    @SuppressWarnings("unused")
    public byte getGlobalDataPagesUsage() {
        return (byte) ((value & 0b100) >> 2);
    }

    /**
     * Returns the optional device number extension.
     *
     * @return the optional device number extension.
     */
    @SuppressWarnings("unused")
    public byte getOptionalDeviceNumberExtension() {
        return (byte) (value >> 4);
    }

    /**
     * Interface for Shared Address Indicator.
     */
    @SuppressWarnings("unused")

    public interface SharedAddressIndicator {
        /**
         * Reserved value.
         */
        byte RESERVED = 0;
        /**
         * Independent value.
         */
        byte INDEPENDENT = 1;
        /**
         * Shared address setting: with 1 byte address.
         */
        byte SHARED_WITH_1_BYTE_ADDRESS = 0b10;
        /**
         * Shared address setting: with 2 byte address.
         */
        byte SHARED_WITH_2_BYTE_ADDRESS = 0b11;
    }

    /**
     * Interface for Global Data Pages Usage.
     */
    @SuppressWarnings("unused")
    public interface GlobalDataPagesUsage {
        /**
         * NOT USED.
         */
        byte NOT_USED = 0;
        /**
         * USED.
         */
        byte USED = 1;
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
