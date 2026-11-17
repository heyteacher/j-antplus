package be.glever.ant.usb.impl;

import static java.lang.String.format;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import be.glever.ant.AntException;
import be.glever.ant.message.AntMessage;
import be.glever.ant.usb.AbstractAntUsbWriter;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;

/**
 * Android Ant Usb Writer.
 */
public class AndroidAntUsbWriter extends AbstractAntUsbWriter {

    private static final Log LOG = Log.getLogger(AbstractAntUsbWriter.class);
    private final UsbDeviceConnection connection;
    private final UsbEndpoint outEndpoint;
    /**
     * Constructs a new {@link AndroidAntUsbWriter}.
     *
     * @param connection the connection
     * @param outEndpoint the out endpoint
     */
    public AndroidAntUsbWriter(UsbDeviceConnection connection, UsbEndpoint outEndpoint) {
        this.connection = connection;
        this.outEndpoint = outEndpoint;
    }

    public synchronized void write(AntMessage message) throws AntException {
        try {
            byte[] messageBytes = message.toByteArray();
            LOG.debug(() -> format("Sending %s with bytes %s.", message.getClass().getSimpleName(), ByteUtils.hexString(messageBytes)));
            connection.bulkTransfer(outEndpoint, messageBytes, messageBytes.length, 5000);
        } catch (Throwable t) {
            throw new AntException(t);
        }
    }

}
