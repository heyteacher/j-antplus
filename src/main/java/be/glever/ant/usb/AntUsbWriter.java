package be.glever.ant.usb;

import be.glever.ant.AntException;
import be.glever.ant.message.AntMessage;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;

import static java.lang.String.format;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import javax.usb.UsbPipe;

public class AntUsbWriter {
    private static final Log LOG = Log.getLogger(AntUsbWriter.class);
    private UsbDeviceConnection connection;
    private UsbEndpoint outEndpoint;
    private UsbPipe usbOutPipe;
    public AntUsbWriter(UsbDeviceConnection connection, UsbEndpoint outEndpoint) {
        this.connection = connection;
        this.outEndpoint = outEndpoint;
    }

    public AntUsbWriter(UsbPipe usbOutPipe) {
        this.usbOutPipe = usbOutPipe;
    }

    public synchronized void write(AntMessage message) throws AntException {
        try {
            byte[] messageBytes = message.toByteArray();
            LOG.debug(() -> format("Sending %s with bytes %s.", message.getClass().getSimpleName(), ByteUtils.hexString(messageBytes)));
            if (usbOutPipe != null) {
                usbOutPipe.syncSubmit(messageBytes);
            }
            if (connection!= null && outEndpoint != null) {
                connection.bulkTransfer(outEndpoint, messageBytes, messageBytes.length, 5000);
            }
        } catch (Throwable t) {
            throw new AntException(t);
        }
    }
}
