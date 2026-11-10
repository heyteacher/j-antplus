package be.glever.ant.usb;

import be.glever.ant.message.AntMessage;
import be.glever.ant.message.AntMessageRegistry;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

import static java.lang.String.format;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import java.util.Arrays;

/**
 * Reads an {@link UsbDeviceConnection} and parses the byte stream to {@link AntMessage}s
 * which can then be retrieved through {@link #antMessages()}().
 *
 * @author glen
 */
public class AntUsbReader implements Runnable {
    public static final byte SYNC = (byte) 0xa4;
    private static final Log LOG = Log.getLogger(AntUsbReader.class);
    private final FluxProcessor<AntMessage, AntMessage> usbMessageProcessor = DirectProcessor.<AntMessage>create().serialize();
    private final FluxSink<AntMessage> usbMessageSink = usbMessageProcessor.sink();
    private UsbDeviceConnection connection;
    private UsbEndpoint inEndpoint;
    private javax.usb.UsbPipe inPipe;
    private boolean stop = false;

    public AntUsbReader(UsbDeviceConnection connection, UsbEndpoint inEndpoint) {
        this.connection = connection;
        this.inEndpoint = inEndpoint;
    }

    public AntUsbReader(javax.usb.UsbPipe inPipe) {
        this.inPipe = inPipe;
    }

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
                if (connection != null && inEndpoint != null) {
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
            }
        } catch (Throwable t) {
            LOG.error(() -> "Fatal error reading from usb device " + t.getMessage(), t);
        }
        usbMessageSink.complete();
    }

    public void stop() {
        this.stop = true;
        this.usbMessageSink.complete();
    }

    public Flux<AntMessage> antMessages() {
        return this.usbMessageProcessor;
    }
}
