package be.glever.ant.usb;

import be.glever.ant.message.AntMessage;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;
import android.hardware.usb.UsbDeviceConnection;

/**
 * Reads an {@link UsbDeviceConnection} and parses the byte stream to {@link AntMessage}s
 * which can then be retrieved through {@link #antMessages()}().
 *
 * @author glen
 */
abstract public class AbstractAntUsbReader implements Runnable {

    /**
     * Constructs a new {@link AbstractAntUsbReader}.
     */
    protected AbstractAntUsbReader() {
    }
    /**
     * Sync byte.
     */
    public static final byte SYNC = (byte) 0xa4;
    private final FluxProcessor<AntMessage, AntMessage> usbMessageProcessor = DirectProcessor.<AntMessage>create().serialize();
    /**
     * usb Message Sink.
     */
    protected final FluxSink<AntMessage> usbMessageSink = usbMessageProcessor.sink();
    /**
     * Flag indicating whether reader is stopped.
     */
    protected boolean stop = false;


    /**
     * Stops the reader.
     */
    public void stop() {
        this.stop = true;
        this.usbMessageSink.complete();
    }

    /**
     * Returns the flux of received ANT messages.
     *
     * @return the flux of received ANT messages.
     */
    public Flux<AntMessage> antMessages() {
        return this.usbMessageProcessor;
    }
}
