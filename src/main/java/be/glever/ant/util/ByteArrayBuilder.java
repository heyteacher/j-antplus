package be.glever.ant.util;

import java.io.ByteArrayOutputStream;

/**
 * Byte Array Builder.
 */
public class ByteArrayBuilder {

    private final ByteArrayOutputStream byteArrayOutputStream;

    /**
     * Constructs a new {@link ByteArrayBuilder}.
     */
    public ByteArrayBuilder() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    /**
     * Write.
     *
     * @param bytes the bytes
     * @return the write.
     */
    public ByteArrayBuilder write(byte... bytes) {
        for (byte b : bytes) {
            byteArrayOutputStream.write(b);
        }
        return this;
    }

    /**
     * Converts this message to a byte array.
     *
     * @return the byte array representation.
     */
    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Write if not null.
     *
     * @param address the address
     * @return the write if not null.
     */
    public ByteArrayBuilder writeIfNotNull(Byte address) {
        if (address != null) {
            byteArrayOutputStream.write(address);
        }
        return this;
    }

}
