package be.glever.ant.usb.impl;

import static java.lang.String.format;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import java.util.Arrays;

import be.glever.ant.message.AntMessageRegistry;
import be.glever.ant.usb.AbstractAntUsbReader;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;

/**
 * Android Ant Usb Reader.
 */
public class AndroidAntUsbReader extends AbstractAntUsbReader {
    private static final Log LOG = Log.getLogger(AndroidAntUsbReader.class);

    private final UsbDeviceConnection connection;
    private final UsbEndpoint inEndpoint;

    /**
     * Constructs a new {@link AndroidAntUsbReader}.
     *
     * @param connection the connection
     * @param inEndpoint the in endpoint
     */
    public AndroidAntUsbReader(UsbDeviceConnection connection, UsbEndpoint inEndpoint) {
        this.connection = connection;
        this.inEndpoint = inEndpoint;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        byte[] buffer = new byte[128];
        try {
            while (!stop) {
                    int receivedBytes = connection.bulkTransfer(inEndpoint, buffer, buffer.length, 100);
                    if (receivedBytes == 0) {
                        LOG.debug(() -> "no byte received, waiting 1 second.");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ie) {
                            boolean ignoredInterrupted = Thread.interrupted();
                        }
                    } else {
                        if (buffer[0] == SYNC) {
                            LOG.debug(() -> format("Read %s bytes", ByteUtils.hexString(Arrays.copyOfRange(buffer, 0, 15))));
                            usbMessageSink.next(AntMessageRegistry.from(buffer));
                        } else {
                            // if this happens too much, treat buffer as 'sliding window' instead of
                            // only relying on first byte
                            LOG.debug(() -> format(
                                            "Buffer %s didn't start with sync byte. Ignoring...",
                                            ByteUtils.hexString(buffer)
                                    )
                            );
                        }
                    }
            }
        } catch (Throwable t) {
            LOG.error(() -> "Fatal error reading from usb device " + t.getMessage(), t);
        }
        usbMessageSink.complete();
    }
}
