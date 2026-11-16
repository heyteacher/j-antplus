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
abstract public class AbstractAntUsbReader implements Runnable {
    public static final byte SYNC = (byte) 0xa4;
    private static final Log LOG = Log.getLogger(AbstractAntUsbReader.class);
    private final FluxProcessor<AntMessage, AntMessage> usbMessageProcessor = DirectProcessor.<AntMessage>create().serialize();
    protected final FluxSink<AntMessage> usbMessageSink = usbMessageProcessor.sink();
    protected boolean stop = false;


    public void stop() {
        this.stop = true;
        this.usbMessageSink.complete();
    }

    public Flux<AntMessage> antMessages() {
        return this.usbMessageProcessor;
    }
}
