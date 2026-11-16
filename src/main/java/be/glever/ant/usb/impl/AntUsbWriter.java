package be.glever.ant.usb.impl;

import static java.lang.String.format;

import javax.usb.UsbPipe;

import be.glever.ant.AntException;
import be.glever.ant.message.AntMessage;
import be.glever.ant.usb.AbstractAntUsbWriter;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;

public class AntUsbWriter extends AbstractAntUsbWriter {

    private static final Log LOG = Log.getLogger(AbstractAntUsbWriter.class);

    private final UsbPipe usbOutPipe;

    public AntUsbWriter(UsbPipe usbOutPipe) {
        this.usbOutPipe = usbOutPipe;
    }

    public synchronized void write(AntMessage message) throws AntException {
        try {
            byte[] messageBytes = message.toByteArray();
            LOG.debug(() -> format("Sending %s with bytes %s.", message.getClass().getSimpleName(), ByteUtils.hexString(messageBytes)));
            usbOutPipe.syncSubmit(messageBytes);
        } catch (Throwable t) {
            throw new AntException(t);
        }
    }

}
