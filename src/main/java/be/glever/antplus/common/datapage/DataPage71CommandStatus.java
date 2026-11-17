package be.glever.antplus.common.datapage;

import java.util.Arrays;

/**
 * ANT+ Data Page 71: Command Status.
 */
public class DataPage71CommandStatus extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 71;

    /**
     * Constructs a new {@link DataPage71CommandStatus}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage71CommandStatus(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }


    /**
     * Returns the last received command id.
     *
     * @return the last received command id.
     */
    public byte getLastReceivedCommandId() {
        return getDataPageBytes()[1];
    }

    /**
     * Returns the sequence number.
     *
     * @return the sequence number.
     */
    public byte getSequenceNumber() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the command status.
     *
     * @return the command status.
     */
    public byte getCommandStatus() {
        return getDataPageBytes()[3];
    }

    /**
     * Returns the data.
     *
     * @return the data.
     */
    public byte[] getData() {
        return Arrays.copyOfRange(getDataPageBytes(), 4, 8);
    }
}
