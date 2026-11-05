package be.glever.antplus.power.datapage.main;

import static java.lang.String.format;

import be.glever.ant.util.ByteArrayBuilder;
import be.glever.antplus.power.datapage.AbstractPowerDataSubPage;

public class PowerDataSubPage01CrankParameters extends AbstractPowerDataSubPage {

    public static final byte SUB_PAGE_NR = 1;

    public PowerDataSubPage01CrankParameters(byte[] dataSubPageBytes) {
        super(dataSubPageBytes);
    }

    /**
     * Subpage crank parameters.
     *
     * @param crankLengthInMillimeters  the crank length in millimeters
     */
    public PowerDataSubPage01CrankParameters(double crankLengthInMillimeters) {
        super(new ByteArrayBuilder().
                                    // 0 Page Number (ignored)
                write(SUB_PAGE_NR). // 1 Subpage number
                write((byte) 0xFF). // 2 Reserved
                write((byte) 0xFF). // 3 Reserved
                write((byte) (int) ((crankLengthInMillimeters - 110) / 0.5d)). // 4 Crank Length Value
                write((byte) 0x00). // 5 From Display: Read only, set to 0x00
                write((byte) 0x00). // 6 From Display: Read only, set to 0x00
                write((byte) 0xFF). // 7 Reserved
                toByteArray());
    }

    @Override
    public byte getSubPageNumber() {
        return SUB_PAGE_NR;
    }

    /**
     * Crank length in millimeters
     * @return Crank length in millimeters.
     */
    public double getCrankLengthInMillimeters() {
        return getDataSubPageBytes()[3] * 0.5 + 110d;
    }

    public String toString() {
        return format(
                "SubPageNumber: 0x%02X Crank Length: %s mm",
                getSubPageNumber(),
                getCrankLengthInMillimeters());
    }
}
