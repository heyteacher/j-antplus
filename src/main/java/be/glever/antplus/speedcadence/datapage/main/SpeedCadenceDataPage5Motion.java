package be.glever.antplus.speedcadence.datapage.main;

import be.glever.ant.util.ByteUtils;
import be.glever.antplus.speedcadence.datapage.AbstractSpeedCadenceDataPage;

/**
 * Speed Cadence Data Page5 Motion.
 */
public class SpeedCadenceDataPage5Motion extends AbstractSpeedCadenceDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 5;

    /**
     * Constructs a new {@link SpeedCadenceDataPage5Motion}.
     *
     * @param dataPageBytes the data page bytes
     */
    public SpeedCadenceDataPage5Motion(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * Whether the wheel or crank is moving
     *
     * @return whether the wheel or crank is moving
     */
    public boolean isMoving() {
        //return 0 == (getPageSpecificBytes()[0] & 0b0000001);
        return !ByteUtils.hasBitSet(getPageSpecificBytes()[0], 0);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }
}
