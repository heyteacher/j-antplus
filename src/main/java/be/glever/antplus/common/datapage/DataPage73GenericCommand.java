package be.glever.antplus.common.datapage;

import java.util.Arrays;

/**
 * ANT+ Data Page 73: Generic Command.
 */
public class DataPage73GenericCommand extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 73;

    /**
     * Constructs a new {@link DataPage73GenericCommand}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage73GenericCommand(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    /**
     * Returns the slave serial number.
     *
     * @return the slave serial number.
     */
    public byte[] getSlaveSerialNumber() {
        return Arrays.copyOfRange(getDataPageBytes(), 1, 3);
    }

    /**
     * Returns the slave manufacturer id.
     *
     * @return the slave manufacturer id.
     */
    public byte[] getSlaveManufacturerId() {
        return super.dataPageSubArray(3, 5);

    }

    /**
     * Returns the sequence nr.
     *
     * @return the sequence nr.
     */
    public byte getSequenceNr() {
        return getDataPageBytes()[5];
    }

    /**
     * Returns the command number.
     *
     * @return the command number.
     */
    public byte[] getCommandNumber() {
        return super.dataPageSubArray(6, 8);
    }

}
