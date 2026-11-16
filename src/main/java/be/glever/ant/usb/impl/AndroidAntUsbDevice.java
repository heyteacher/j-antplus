package be.glever.ant.usb.impl;

import android.content.Context;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import be.glever.ant.AntException;
import be.glever.ant.usb.AbstractAntUsbDevice;
import be.glever.util.logging.Log;

public class AndroidAntUsbDevice extends AbstractAntUsbDevice {

    private static final Log LOG = Log.getLogger(AndroidAntUsbDevice.class);

    final private Context context;

    final private UsbDevice androidDevice;
    private UsbDeviceConnection androidConnection;

    public AndroidAntUsbDevice(Context context, UsbDevice androidDevice, Logger androidLogger) {
        this.androidDevice = androidDevice;
        this.context = context;
        Log.setAndroidLogger(androidLogger);
    }

    /**
     * Returns the usb device in Android platform.
     * @return the usb device in Android platform.
     */
    public UsbDevice getAndroidDevice() {
        return androidDevice;
    }

    @Override
    protected void closeInterface() {
        UsbInterface androidUsbInterface = getActiveAndroidUsbInterface();
        if (androidConnection != null && androidUsbInterface != null) {
            androidConnection.releaseInterface(getActiveAndroidUsbInterface());
            androidConnection.close();
        }
    }

    @Override
    protected void initUsbInterface() throws AntException {
        LOG.debug(() -> "<initUsbInterface>");
        UsbManager usbManager = (UsbManager)
                context.getSystemService(android.content.Context.USB_SERVICE);
        androidConnection = usbManager.openDevice(androidDevice);
        UsbInterface usbInterface = getActiveAndroidUsbInterface();
        if (usbInterface != null && androidConnection != null && androidConnection.claimInterface(usbInterface, true)) {
            LOG.debug(() -> "(initUsbInterface): interface claimed, collect endpoints");
            List<UsbEndpoint> usbEndpoints = new ArrayList<>();
            for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
                usbEndpoints.add(usbInterface.getEndpoint(i));
            }
            LOG.debug(() -> "(initUsbInterface): initialize and start reader with in endpoint");
            UsbEndpoint inEndpoint = usbEndpoints.stream()
                    .filter(endpoint -> endpoint.getDirection() == UsbConstants.USB_DIR_IN)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not obtain usb input pipe."));
            antUsbReader = new AndroidAntUsbReader(androidConnection, inEndpoint);
            new Thread(antUsbReader, "ant-usb-reader").start();
            subscribeTo(antUsbReader);
            LOG.debug(() -> "(initUsbInterface): initialize writer with out endpoint");
            UsbEndpoint outEndpoint = usbEndpoints.stream()
                    .filter(endpoint -> endpoint.getDirection() == UsbConstants.USB_DIR_OUT)
                    .findAny()
                    .orElseThrow(() -> new IllegalStateException("Could not obtain usb output pipe."));
            antUsbWriter = new AndroidAntUsbWriter(androidConnection, outEndpoint);
        } else {
            throw new AntException("Could not claim usb interface");
        }
    }

    protected UsbInterface getActiveAndroidUsbInterface() {
        if (this.androidDevice != null) {
            return this.androidDevice.getInterface(0);
        }
        return null;
    }
}
