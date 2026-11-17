package be.glever.ant.message.channel;

/**
 * Enumeration representing Channel Event Response Code.
 */
public enum ChannelEventResponseCode {
    /**
     * Constant representing {@code RESPONSE_NO_ERROR}.
     */
    RESPONSE_NO_ERROR((byte) 0x00),
    /**
     * Constant representing {@code EVENT_RX_SEARCH_TIMEOUT}.
     */
    EVENT_RX_SEARCH_TIMEOUT((byte) 0x01),
    /**
     * Constant representing {@code EVENT_RX_FAIL}.
     */
    EVENT_RX_FAIL((byte) 0x02),
    /**
     * Constant representing {@code EVENT_TX}.
     */
    EVENT_TX((byte) 0x03),
    /**
     * Constant representing {@code EVENT_TRANSFER_RX_FAILED}.
     */
    EVENT_TRANSFER_RX_FAILED((byte) 0x04),
    /**
     * Constant representing {@code EVENT_TRANSFER_TX_COMPLETED}.
     */
    EVENT_TRANSFER_TX_COMPLETED((byte) 0x05),
    /**
     * Constant representing {@code EVENT_TRANSFER_TX_FAILED}.
     */
    EVENT_TRANSFER_TX_FAILED((byte) 0x06),
    /**
     * Constant representing {@code EVENT_CHANNEL_CLOSED}.
     */
    EVENT_CHANNEL_CLOSED((byte) 0x07),
    /**
     * Constant representing {@code EVENT_RX_FAIL_GO_TO_SEARCH}.
     */
    EVENT_RX_FAIL_GO_TO_SEARCH((byte) 0x08),
    /**
     * Constant representing {@code EVENT_CHANNEL_COLLISION}.
     */
    EVENT_CHANNEL_COLLISION((byte) 0x09),
    /**
     * Constant representing {@code EVENT_TRANSFER_TX_START}.
     */
    EVENT_TRANSFER_TX_START((byte) 0x0a),
    /**
     * Constant representing {@code EVENT_TRANSFER_NEXT_DATA_BLOCK}.
     */
    EVENT_TRANSFER_NEXT_DATA_BLOCK((byte) 0x11),
    /**
     * Constant representing {@code CHANNEL_IN_WRONG_STATE}.
     */
    CHANNEL_IN_WRONG_STATE((byte) 0x15),
    /**
     * Constant representing {@code CHANNEL_NOT_OPENED}.
     */
    CHANNEL_NOT_OPENED((byte) 0x16),
    /**
     * Constant representing {@code CHANNEL_ID_NOT_SET}.
     */
    CHANNEL_ID_NOT_SET((byte) 0x18),
    /**
     * Constant representing {@code CLOSE_ALL_CHANNELS}.
     */
    CLOSE_ALL_CHANNELS((byte) 0x19),
    /**
     * Constant representing {@code TRANSFER_IN_PROGRESS}.
     */
    TRANSFER_IN_PROGRESS((byte) 0x1f),
    /**
     * Constant representing {@code TRANSFER_SEQUENCE_NUMBER_ERROR}.
     */
    TRANSFER_SEQUENCE_NUMBER_ERROR((byte) 0x20),
    /**
     * Constant representing {@code TRANSFER_IN_ERROR}.
     */
    TRANSFER_IN_ERROR((byte) 0x21),
    /**
     * Constant representing {@code MESSAGE_SIZE_EXCEEDS_LIMIT}.
     */
    MESSAGE_SIZE_EXCEEDS_LIMIT((byte) 0x27),
    /**
     * Constant representing {@code INVALID_MESSAGE}.
     */
    INVALID_MESSAGE((byte) 0x28),
    /**
     * Constant representing {@code INVALID_NETWORK_NUMBER}.
     */
    INVALID_NETWORK_NUMBER((byte) 0x29),
    /**
     * Constant representing {@code INVALID_LIST_ID}.
     */
    INVALID_LIST_ID((byte) 0x30),
    /**
     * Constant representing {@code INVALID_SCAN_TX_CHANNEL}.
     */
    INVALID_SCAN_TX_CHANNEL((byte) 0x31),
    /**
     * Constant representing {@code INVALID_PARAMETER_PROVIDED}.
     */
    INVALID_PARAMETER_PROVIDED((byte) 0x33),
    /**
     * Constant representing {@code EVENT_SERIAL_QUEUE_OVERFLOW}.
     */
    EVENT_SERIAL_QUEUE_OVERFLOW((byte) 0x34),
    /**
     * Constant representing {@code EVENT_QUEUE_OVERFLOW}.
     */
    EVENT_QUEUE_OVERFLOW((byte) 0x35),
    /**
     * Constant representing {@code ENCRYPT_NEGOTIATION_SUCCESS}.
     */
    ENCRYPT_NEGOTIATION_SUCCESS((byte) 0x38),
    /**
     * Constant representing {@code ENCRYPT_NEGOTIATION_FAIL}.
     */
    ENCRYPT_NEGOTIATION_FAIL((byte) 0x39),
    /**
     * Constant representing {@code NVM_FULL_ERROR}.
     */
    NVM_FULL_ERROR((byte) 0x40),
    /**
     * Constant representing {@code NVM_WRITE_ERROR}.
     */
    NVM_WRITE_ERROR((byte) 0x41),
    /**
     * Constant representing {@code USB_STRING_WRITE_FAIL}.
     */
    USB_STRING_WRITE_FAIL((byte) 0x70),
    /**
     * Constant representing {@code MSG_SERIAL_ERROR_ID}.
     */
    MSG_SERIAL_ERROR_ID((byte) 0xAE),
    /**
     * Constant representing {@code UNDOCUMENTED_ANT_ERROR_CODE}.
     */
    UNDOCUMENTED_ANT_ERROR_CODE((byte) 0xFF); // bug here when ant decides to use 0xFF


    private final byte code;

    ChannelEventResponseCode(byte value) {
        this.code = value;
    }

    /**
     * Returns the enum constant for the given raw value.
     *
     * @param value the value
     * @return the matching constant.
     */
    public static ChannelEventResponseCode fromValue(byte value) {
        for (ChannelEventResponseCode channelEvenResponseCode : values()) {
            if (value == channelEvenResponseCode.code) {
                return channelEvenResponseCode;
            }
        }
        return UNDOCUMENTED_ANT_ERROR_CODE;
    }
}
