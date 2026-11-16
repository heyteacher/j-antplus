package be.glever.ant.usb.impl;

import static java.lang.String.format;

import java.util.Arrays;

import be.glever.ant.message.AntMessageRegistry;
import be.glever.ant.usb.AbstractAntUsbReader;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;

public class AntUsbReader extends AbstractAntUsbReader {

    private static final Log LOG = Log.getLogger(AntUsbReader.class);

    private final javax.usb.UsbPipe inPipe;

    public AntUsbReader(javax.usb.UsbPipe inPipe) {
        this.inPipe = inPipe;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        byte[] buffer = new byte[128];
        try {
            while (!stop) {
                if (inPipe != null) {
                    javax.usb.util.DefaultUsbIrp irp = new javax.usb.util.DefaultUsbIrp(buffer);
                    irp.waitUntilComplete(100);
                    inPipe.syncSubmit(irp);
                    if (irp.getData().length == 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ie) {
                            boolean ignored = Thread.interrupted();
                        }
                    } else {
                        LOG.debug(() -> format("Read %s bytes", ByteUtils.hexString(Arrays.copyOfRange(buffer, 0, 15))));
                        if (buffer[0] == SYNC) {
                            usbMessageSink.next(AntMessageRegistry.from(buffer));
                        } else {
                            // if this happens too much, treat buffer as 'sliding window' instead of only relying on first byte
                            LOG.warn(() -> format("Buffer %s didn't start with sync byte. Ignoring...", ByteUtils.hexString(buffer)));
                        }
                    }

                }
            }
        } catch (Throwable t) {
            LOG.error(() -> "Fatal error reading from usb device " + t.getMessage(), t);
        }
        usbMessageSink.complete();
    }

}
