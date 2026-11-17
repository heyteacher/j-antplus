package be.glever.ant.util;

import be.glever.util.logging.Log;

import static java.lang.String.format;

/**
 * Utility methods for byte manipulation.
 */
public class ByteUtils {
    private ByteUtils() {
    }
    private static final Log LOG = Log.getLogger(ByteUtils.class);

    /**
     * Binary string.
     *
     * @param bytes the bytes
     * @return the binary string.
     */
    @SuppressWarnings("unused")
    public static String binaryString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(8 * bytes.length);
        for (byte bite : bytes) {
            sb.append(Integer.toBinaryString(bite & 0xFF));
        }
        return sb.toString();
    }

    /**
     * Hex string.
     *
     * @param bytes the bytes
     * @return the hex string.
     */
    public static String hexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(3 * bytes.length);

        boolean first = true;
        for (byte bite : bytes) {
            if (first) {
                first = false;
            } else {
                sb.append('-');
            }
            sb.append(format("%02X", (0xFF & bite)));
        }
        return sb.toString();
    }

    /**
     * Checks if mask.
     *
     * @param value the value
     * @param mask the mask
     * @return {@code true} if mask, {@code false} otherwise.
     */
    public static boolean hasMask(int value, int mask) {
        return (value & mask) == mask;
    }

    /**
     * Checks if bit set.
     *
     * @param value the value
     * @param bitPos the bit pos
     * @return {@code true} if bit set, {@code false} otherwise.
     */
    public static boolean hasBitSet(int value, int bitPos) {
        return hasMask(value, 1 << bitPos);
    }

    /**
     * Hex string.
     *
     * @param bite the bite
     * @return the hex string.
     */
    public static String hexString(byte bite) {
        return hexString(new byte[]{bite});
    }

    /**
     * To int.
     *
     * @param byteArray the byte array
     * @return the to int.
     */
    public static int toInt(byte... byteArray) {
        if (byteArray.length > 4) {
            throw new IllegalArgumentException("ByteArray too large to convert to int. Length was: " + byteArray.length);
        }

        int val = 0;
        for (byte b : byteArray) {
            val = val << 8;
            val |= b;
        }
        return val;
    }

    /**
     * returns 2 byte array representing the unsigned value
     *
     * @param val the value to convert
     * @return the to u short.
     */
    public static byte[] toUShort(int val) {
        byte[] ret = new byte[2];
        ret[0] = (byte) val;
        ret[1] = (byte) (val >> 8);
        return ret;
    }

    /**
     * From u short.
     *
     * @param byte0 the byte0
     * @param byte1 the byte1
     * @return the from u short.
     */
    public static int fromUShort(byte byte0, byte byte1) {
        return (0xFF & byte0) | ((0xFF & byte1) << 8);
    }

    /**
     * From unsigned bytes.
     *
     * @param bytes the bytes
     * @return the from unsigned bytes.
     */
    public static int fromUnsignedBytes(byte[] bytes) {
        //         return 2 * (0xFF & pageSpecificBytes[0]) | ((0xFF & pageSpecificBytes[1]) << 8 | ((0xFF & pageSpecificBytes[2]) << 16));
        int val = 0x00;
        boolean first = true;
        for (int i = bytes.length; i > 0; i--) {
            if (!first) {
                val <<= 8;
            } else {
                first = false;
            }
            val |= bytes[i - 1] & 0xff;
        }
        int finalVal = val;
        LOG.debug(() -> format("decoded %s to unsigned int %s", ByteUtils.hexString(bytes), finalVal));
        return val;
    }
}
