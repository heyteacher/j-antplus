package be.glever.antplus.common.datapage;

/**
 * ANT+ Data Page 83: Time And Date.
 */
public class DataPage83TimeAndDate extends AbstractAntPlusDataPage {
    /**
     * Data page number.
     */
    public static final byte PAGE_NR = 83;

    /**
     * Constructs a new {@link DataPage83TimeAndDate}.
     *
     * @param dataPageBytes the data page bytes
     */
    public DataPage83TimeAndDate(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    /**
     * Returns the seconds.
     *
     * @return the seconds.
     */
    public byte getSeconds() {
        return getDataPageBytes()[2];
    }

    /**
     * Returns the minutes.
     *
     * @return the minutes.
     */
    public byte getMinutes() {
        return getDataPageBytes()[3];
    }

    /**
     * Returns the hours.
     *
     * @return the hours.
     */
    public byte getHours() {
        return getDataPageBytes()[4];
    }

    /**
     * Returns the day of week.
     *
     * @return the day of week.
     */
    public byte getDayOfWeek() {
        return (byte) (getDataPageBytes()[5] & 0b00000111);
    }


    /**
     * Returns the day of month.
     *
     * @return the day of month.
     */
    public byte getDayOfMonth() {
        return (byte) (getDataPageBytes()[5] & 0b11111000);
    }

    /**
     * Returns the month.
     *
     * @return the month.
     */
    public byte getMonth() {
        return getDataPageBytes()[6];
    }


    /**
     * Returns the year.
     *
     * @return the year.
     */
    public byte getYear() {
        return getDataPageBytes()[7];
    }
}
