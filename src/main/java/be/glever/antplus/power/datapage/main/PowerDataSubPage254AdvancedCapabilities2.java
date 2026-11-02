package be.glever.antplus.power.datapage.main;

import static java.lang.String.format;

import be.glever.ant.util.ByteArrayBuilder;
import be.glever.antplus.power.datapage.AbstractPowerDataSubPage;

public class PowerDataSubPage254AdvancedCapabilities2 extends AbstractPowerDataSubPage {

    public static final byte SUB_PAGE_NR = (byte) 0xFE;

    public PowerDataSubPage254AdvancedCapabilities2(byte[] dataSubPageBytes) {
        super(dataSubPageBytes);
    }

    /**
     * Subpage crank parameters.
     *
     * @param interoperableCapabilitiesMask  the interoperable capabilities mask
     * @param interoperableCapabilitiesValue the interoperable capabilities value
     */
    private PowerDataSubPage254AdvancedCapabilities2(byte interoperableCapabilitiesMask, byte interoperableCapabilitiesValue) {
        super(new ByteArrayBuilder().
                                    // 0 Page Number (ignored)
                write(SUB_PAGE_NR). // 1 Subpage number
                write((byte) 0xFF). // 2 Reserved
                write((byte) 0xFF). // 3 Reserved
                write((byte) interoperableCapabilitiesMask). // 4 Crank Length Value
                write((byte) 0x00). // 5 From Display: Read only, set to 0x00
                write((byte) interoperableCapabilitiesValue). // 6 From Display: Read only, set to 0x00
                write((byte) 0xFF). // 7 Reserved
                toByteArray());
    }

    /**
     * Creates sub-page for setting Hertz capabilities (4Hz or 8Hz).
     * @param enable8Hertz if true enable 8Hz and disable 4Hz capabilities, otherwise enable 4Hz
     *                     and disable 8Hz capabilities.
     * @return the sub-page for setting Hertz capabilities  (4Hz or 8Hz).
     */
    public static PowerDataSubPage254AdvancedCapabilities2 createHertzSetter(boolean enable8Hertz) {
        return new PowerDataSubPage254AdvancedCapabilities2((byte) 2, (byte) (enable8Hertz ? 2 : 1));
    }

    /**
     * Creates sub-page for setting power phase capabilities.
     * @param enablePowerPhase if true enable power phase capabilities, otherwise disable power phase capabilities.
     * @return the sub-page for setting power phase capabilities.
     */
    public static PowerDataSubPage254AdvancedCapabilities2 createPowerPhaseSetter(boolean enablePowerPhase) {
        return new PowerDataSubPage254AdvancedCapabilities2((byte) 8, (byte) (enablePowerPhase ? 8 : 0));
    }

    /**
     * Creates sub-page for setting PCO capabilities.
     * @param enablePCO if true enable PCO capabilities, otherwise disable PCO capabilities.
     * @return the sub-page for setting PCO capabilities.
     */
    public static PowerDataSubPage254AdvancedCapabilities2 createPCOSetter(boolean enablePCO) {
        return new PowerDataSubPage254AdvancedCapabilities2((byte) 16, (byte) (enablePCO ? 16 : 0));
    }

    /**
     * Creates sub-page for setting rider position capabilities.
     * @param enableRiderPosition if true enable rider position capabilities, otherwise disable rider position capabilities.
     * @return the sub-page for setting rider position capabilities.
     */
    public static PowerDataSubPage254AdvancedCapabilities2 createRiderPositionSetter(boolean enableRiderPosition) {
        return new PowerDataSubPage254AdvancedCapabilities2((byte) 32, (byte) (enableRiderPosition ? 32 : 0));
    }

    /**
     * Creates sub-page for setting torque barycenter capabilities.
     * @param enableTorqueBarycenter if true enable torque barycenter capabilities, otherwise disable torque barycenter capabilities.
     * @return the sub-page for setting torque barycenter capabilities.
     */
    public static PowerDataSubPage254AdvancedCapabilities2 createTorqueBarycenterSetter(boolean enableTorqueBarycenter) {
        return new PowerDataSubPage254AdvancedCapabilities2((byte) 64, (byte) (enableTorqueBarycenter? 64 : 0));
    }

    @Override
    public byte getSubPageNumber() {
        return SUB_PAGE_NR;
    }

    private byte getInteroperableCapabilitiesMask() {
        return getDataSubPageBytes()[3];
    }

    private byte getInteroperableCapabilitiesValue() {
        return getDataSubPageBytes()[5];
    }

    public boolean is4HertzSupported() {
        return ((getInteroperableCapabilitiesMask()) & 1) == 0;
    }
    public boolean is4HertzEnabled() {
        return (getInteroperableCapabilitiesValue() & 1) == 0;
    }
    public boolean is8HertzSupported() {
        return ((getInteroperableCapabilitiesMask() >> 1) & 1) == 0;
    }
    public boolean is8HertzEnabled() {
        return ((getInteroperableCapabilitiesValue() >> 1) & 1) == 0;
    }

    public boolean isPowerPhaseSupported() {
        return ((getInteroperableCapabilitiesMask() >> 3) & 1) == 0;
    }
    public boolean isPowerPhaseEnabled() {
        return ((getInteroperableCapabilitiesValue() >> 3) & 1) == 0;
    }

    public boolean isPCOSupported() {
        return ((getInteroperableCapabilitiesMask() >> 4) & 1) == 0;
    }
    public boolean isPCOEnabled() {
        return ((getInteroperableCapabilitiesValue() >> 4) & 1) == 0;
    }

    public boolean isRiderPositionSupported() {
        return ((getInteroperableCapabilitiesMask() >> 5) & 1) == 0;
    }
    public boolean isRiderPositionEnabled() {
        return ((getInteroperableCapabilitiesValue() >> 5) & 1) == 0;
    }

    public boolean isTorqueBarycenterSupported() {
        return ((getInteroperableCapabilitiesMask() >> 6) & 1) == 0;
    }
    public boolean isTorqueBarycenterEnabled() {
        return ((getInteroperableCapabilitiesValue() >> 6) & 1) == 0;
    }

    @Override
    public String toString() {
        return format(
                "SubPageNumber: 0x%02X 4Hz: (supported: %s - enabled: %s), " +
                        "8Hz: (supported: %s - enabled: %s), " +
                        "PowerPhase: (supported: %s - enabled: %s), " +
                        "PCO: (supported: %s - enabled: %s), " +
                        "RiderPosition: (supported: %s - enabled: %s), " +
                        "TorqueBarycenter: (supported: %s - enabled: %s)",
                getSubPageNumber(),
                is4HertzSupported(),
                is4HertzEnabled(),
                is8HertzSupported(),
                is8HertzEnabled(),
                isPowerPhaseSupported(),
                isPowerPhaseEnabled(),
                isPCOSupported(),
                isPCOEnabled(),
                isRiderPositionSupported(),
                isRiderPositionEnabled(),
                isTorqueBarycenterSupported(),
                isTorqueBarycenterEnabled()
        );
    }

}