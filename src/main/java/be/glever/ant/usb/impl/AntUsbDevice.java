package be.glever.ant.usb.impl;

import java.util.List;

import javax.usb.UsbConst;
import javax.usb.UsbDevice;
import javax.usb.UsbEndpoint;
import javax.usb.UsbException;
import javax.usb.UsbInterface;
import javax.usb.UsbPipe;

import be.glever.ant.AntException;
import be.glever.ant.usb.AbstractAntUsbDevice;
import be.glever.util.logging.Log;

public class AntUsbDevice extends AbstractAntUsbDevice {

    private static final Log LOG = Log.getLogger(AntUsbDevice.class);

    private final UsbDevice device;

    public AntUsbDevice(UsbDevice device) {
        this.device = device;
    }
    /**
     * Returns the usb device.
     * @return the usb device.
     */
    public UsbDevice getUsbDevice() {
        return device;
    }

    @Override
    protected void closeInterface()  throws AntException {
        try {
            if (this.device != null ) {
                UsbInterface activeUsbInterface = getActiveUsbInterface();
                if (activeUsbInterface != null && activeUsbInterface.isClaimed()) {
                        activeUsbInterface.release();
                }
            }
        } catch (UsbException e) {
            throw new AntException(e);
        }
    }

    private UsbInterface getActiveUsbInterface() {
        if (this.device != null) {
            return (UsbInterface) this.device.getActiveUsbConfiguration().getUsbInterfaces().getFirst();
        }
        return null;
    }

    protected void initUsbInterface() throws AntException {
        LOG.debug(() -> "<initUsbInterface>");
        UsbInterface usbInterface = getActiveUsbInterface();
        if (usbInterface == null) {
            throw new AntException("active Usb interface not found");
        }
        if (usbInterface.isClaimed()) {
            throw new AntException("Usb device already claimed");
        }
        UsbPipe usbOutPipe;
        UsbPipe inPipe;
        try {
            usbInterface.claim(AntUsbDevice::forceClaim);

            @SuppressWarnings("unchecked")
            List<UsbEndpoint> usbEndpoints = usbInterface.getUsbEndpoints();
            UsbEndpoint inEndpoint = usbEndpoints.stream()
                    .filter(endpoint -> endpoint.getDirection() == UsbConst.ENDPOINT_DIRECTION_IN)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not obtain usb input pipe."));
            UsbEndpoint outEndpoint = usbEndpoints.stream()
                    .filter(endpoint -> endpoint.getDirection() == UsbConst.ENDPOINT_DIRECTION_OUT)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not obtain usb output pipe."));

            usbOutPipe = outEndpoint.getUsbPipe();
            usbOutPipe.open();

            inPipe = inEndpoint.getUsbPipe();
            inPipe.open();
        } catch (UsbException e) {
            throw new AntException(e);
        }

        antUsbReader = new AntUsbReader(inPipe);
        new Thread(antUsbReader, "ant-usb-reader").start();
        antUsbWriter = new AntUsbWriter(usbOutPipe);
        subscribeTo(antUsbReader);
    }

    private static boolean forceClaim(UsbInterface usbInterface) {
        return true;
    }
}
