package be.glever.antplus.common.datapage;

import java.util.Arrays;

/**
 * ANT+ Data Page 68: Ant Fs Host Command Response.
 */
public class DataPage68AntFsHostCommandResponse extends AbstractAntPlusDataPage {

    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 68;

    /**
     * Constructs a new {@link DataPage68AntFsHostCommandResponse}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage68AntFsHostCommandResponse(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the command or response byte.
     *
     * @return the command or response byte.
     */
    public byte getCommandOrResponseByte() {
        return getDataPageBytes()[1];
    }

    /**
     * Returns the parameters.
     *
     * @return the parameters.
     */
    public byte[] getParameters() {
        return Arrays.copyOfRange(getDataPageBytes(), 2, 8);
    }
}
