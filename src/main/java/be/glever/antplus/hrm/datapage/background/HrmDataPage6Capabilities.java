package be.glever.antplus.hrm.datapage.background;

import be.glever.antplus.hrm.datapage.AbstractHRMDataPage;
import be.glever.antplus.hrm.datapage.main.HrmDataPage4PreviousHeartBeatEvent;
import be.glever.antplus.hrm.datapage.main.HrmDataPage5SwimIntervalSummary;

/**
 * Lists supported capabilities for running/cycling/swimming and if they are enabled.
 * In case this capability is enabled it's specific datapage is transmitted as main datapage instead of the generic {@link HrmDataPage4PreviousHeartBeatEvent}
 * Currently only swimming seems to be possible ({@link HrmDataPage5SwimIntervalSummary}).
 */
public class HrmDataPage6Capabilities extends AbstractHRMDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 6;

    /**
     * Constructs a new {@link HrmDataPage6Capabilities}.
     *
     * @param dataPageBytes the data page bytes
     */
    public HrmDataPage6Capabilities(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Checks if extended running feature supported.
     *
     * @return {@code true} if extended running feature supported, {@code false} otherwise.
     */
    public boolean isExtendedRunningFeatureSupported() {
        return isBitSet(0b1, getSupportedFeaturesByte());
    }

    /**
     * Checks if extended cycling features supported.
     *
     * @return {@code true} if extended cycling features supported, {@code false} otherwise.
     */
    public boolean isExtendedCyclingFeaturesSupported() {
        return isBitSet(0b01, getSupportedFeaturesByte());
    }

    /**
     * Checks if extended swimming features supported.
     *
     * @return {@code true} if extended swimming features supported, {@code false} otherwise.
     */
    public boolean isExtendedSwimmingFeaturesSupported() {
        return isBitSet(0b001, getSupportedFeaturesByte());
    }


    /**
     * Checks if extended running features enabled.
     *
     * @return {@code true} if extended running features enabled, {@code false} otherwise.
     */
    public boolean isExtendedRunningFeaturesEnabled() {
        return isBitSet(0b1, getEnabledFeaturesByte());
    }

    /**
     * Checks if extended cycling features enabled.
     *
     * @return {@code true} if extended cycling features enabled, {@code false} otherwise.
     */
    public boolean isExtendedCyclingFeaturesEnabled() {
        return isBitSet(0b01, getEnabledFeaturesByte());
    }

    /**
     * Checks if extended swimming features enabled.
     *
     * @return {@code true} if extended swimming features enabled, {@code false} otherwise.
     */
    public boolean isExtendedSwimmingFeaturesEnabled() {
        return isBitSet(0b001, getEnabledFeaturesByte());
    }

    private boolean isBitSet(int mask, byte b) {
        return 1 == (mask & b);
    }

    private byte getSupportedFeaturesByte() {
        return getPageSpecificBytes()[1];
    }

    private byte getEnabledFeaturesByte() {
        return getPageSpecificBytes()[2];
    }
}
