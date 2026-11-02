package be.glever.antplus.common.datapage;

import static java.lang.String.format;

public class DataPage70Request extends AbstractAntPlusDataPage {

    public static final byte PAGE_NR = 70;

    public DataPage70Request(byte[] dataPageBytes) {
        super(dataPageBytes);
    }

    /**
     * DataPage to request specific datapage from device so as not to have to wait until device sends it itself.
     * Meant for faster display of info to user.
     *
     * @param timesToTransmitRequestedPage times to transmit requested page asked sensor (1-128)
     * @param useAcknowledgementMessages if true, ask sensor to send acknowledgement message and ignore timesToTransmitRequestedPage.
     * @param transmitUntilAcknowledgeReceived If true, timesToTransmitRequestedPage and useAcknowledgementMessages are ignored.
     * @param pageNumber  The data page to request
     * @param subPageNumber the sub-page to request
     */
    public DataPage70Request(
            int timesToTransmitRequestedPage,
            boolean useAcknowledgementMessages,
            boolean transmitUntilAcknowledgeReceived,
            byte pageNumber,
            byte subPageNumber
    ) {
        this(createDataPageBytes(
                timesToTransmitRequestedPage,
                useAcknowledgementMessages,
                transmitUntilAcknowledgeReceived,
                pageNumber,
                subPageNumber));
    }

    private static byte[] createDataPageBytes(
            int timesToTransmitRequestedPage,
            boolean useAcknowledgementMessages,
            boolean transmitUntilAcknowledgeReceived,
            byte pageNumber,
            byte subPageNumber) {
        byte[] dataPageBytes = new byte[8];
        dataPageBytes[0] = PAGE_NR;
        dataPageBytes[1] = (byte) 0xFF;
        dataPageBytes[2] = (byte) 0xFF;
        dataPageBytes[3] = subPageNumber;
        dataPageBytes[4] = (byte) 0xFF;
        if (transmitUntilAcknowledgeReceived) {
            dataPageBytes[5] = (byte) 0x80;
        } else {
            byte timesToTransmitMask = 0b01111111;
            dataPageBytes[5] = (byte) (0b01111111 & timesToTransmitRequestedPage);
            byte useAckMsgMask = useAcknowledgementMessages ? (byte) ~timesToTransmitMask : 0;
            dataPageBytes[5] = (byte) (dataPageBytes[5] | useAckMsgMask);
        }
        dataPageBytes[6] = pageNumber;
        dataPageBytes[7] = 1;
        return dataPageBytes;
    }

    @Override
    public byte getPageNumber() {
        return PAGE_NR;
    }

    @Override
    public String toString() {
        return format("PageNumber: 0x%02X" , getPageNumber());
    }
}