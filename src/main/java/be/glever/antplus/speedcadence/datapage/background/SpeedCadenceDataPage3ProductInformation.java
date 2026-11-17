package be.glever.antplus.speedcadence.datapage.background;

import be.glever.antplus.speedcadence.datapage.AbstractSpeedCadenceDataPage;

/**
 * Speed Cadence Data Page3 Product Information.
 */
public class SpeedCadenceDataPage3ProductInformation extends AbstractSpeedCadenceDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 0x3;

    /**
     * Constructs a new {@link SpeedCadenceDataPage3ProductInformation}.
     *
     * @param dataPageBytes the data page bytes
     */
    public SpeedCadenceDataPage3ProductInformation(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Returns the hardware version.
     *
     * @return the hardware version.
     */
    public byte getHardwareVersion() {
        return super.getPageSpecificBytes()[0];
    }

    /**
     * Returns the software version.
     *
     * @return the software version.
     */
    public byte getSoftwareVersion() {
        return super.getPageSpecificBytes()[1];
    }

    /**
     * Returns the model number.
     *
     * @return the model number.
     */
    public byte getModelNumber() {
        return super.getPageSpecificBytes()[2];
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    @Override
    public String toString() {
        return String.format("%s, {%s, HardwareVersion=%s, SoftwareVersion=%s, ModelNumber=%s}", getClass().getSimpleName(), super.getToString(), getHardwareVersion(), getSoftwareVersion(), getModelNumber());
    }
}
