package be.glever.ant.util;

import java.io.ByteArrayOutputStream;

public class ByteArrayBuilder {

    private final ByteArrayOutputStream byteArrayOutputStream;

    public ByteArrayBuilder() {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    public ByteArrayBuilder write(byte... bytes) {
        for (byte b : bytes) {
            byteArrayOutputStream.write(b);
        }
        return this;
    }

    public byte[] toByteArray() {
        return byteArrayOutputStream.toByteArray();
    }

    public ByteArrayBuilder writeIfNotNull(Byte address) {
        if (address != null) {
            byteArrayOutputStream.write(address);
        }
        return this;
    }

}
