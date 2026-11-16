package be.glever.ant.usb;

import be.glever.ant.AntException;
import be.glever.ant.message.AntMessage;
import be.glever.ant.util.ByteUtils;
import be.glever.util.logging.Log;

import static java.lang.String.format;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;

import javax.usb.UsbPipe;

abstract public class AbstractAntUsbWriter {
    private static final Log LOG = Log.getLogger(AbstractAntUsbWriter.class);

    abstract public void write(AntMessage message) throws AntException;
}
