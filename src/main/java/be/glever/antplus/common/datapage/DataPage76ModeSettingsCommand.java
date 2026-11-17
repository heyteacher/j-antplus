package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 76: Mode Settings Command.
 */
public class DataPage76ModeSettingsCommand extends AbstractAntPlusDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 76;

    /**
     * Enumeration representing SPORT MODE.
     */
    public enum SPORT_MODE {
        /**
         * Constant representing {@code RUNNING}.
         */
        RUNNING,
        /**
         * Constant representing {@code CYCLING}.
         */
        CYCLING,
        /**
         * Constant representing {@code SWIMMING}.
         */
        SWIMMING
    }

    /**
     * Constructs a new {@link DataPage76ModeSettingsCommand}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage76ModeSettingsCommand(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Constructs a new {@link DataPage76ModeSettingsCommand}.
     *
     * @param mode the mode
     */
    public DataPage76ModeSettingsCommand(SPORT_MODE mode) {
        super(createDataPageBytes(mode));
    }

    private static byte[] createDataPageBytes(SPORT_MODE mode) {
        byte[] bytes = new byte[8];

        bytes[0] = 76;
        bytes[1] = (byte) 0xFF;
        bytes[2] = (byte) 0xFF;
        bytes[3] = (byte) 0xFF;
        bytes[4] = (byte) 0xFF;
        bytes[5] = (byte) 0xFF;
        bytes[6] = (byte) 0xFF;
        bytes[7] = switch (mode) {
            case CYCLING -> 0x01;
            case RUNNING -> 0x02;
            case SWIMMING -> 0x05;
        };

        return bytes;
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
