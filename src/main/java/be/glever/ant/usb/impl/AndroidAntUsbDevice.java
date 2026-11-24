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

/**
 * Android Ant Usb Device.
 */
public class AndroidAntUsbDevice extends AbstractAntUsbDevice {

    private static final Log LOG = Log.getLogger(AndroidAntUsbDevice.class);

    final private Context context;

    final private UsbDevice androidDevice;
    private UsbDeviceConnection androidConnection;

    /**
     * Constructs a new {@link AndroidAntUsbDevice}.
     *
     * @param context       the context
     * @param androidDevice the android device
     * @param androidLogger the android logger
     */
    public AndroidAntUsbDevice(Context context, UsbDevice androidDevice, Logger androidLogger) {
        this.androidDevice = androidDevice;
        this.context = context;
        Log.setAndroidLogger(androidLogger);
    }

    /**
     * Returns the usb device in Android platform.
     * 
     * @return the usb device in Android platform.
     */
    public UsbDevice getAndroidDevice() {
        return androidDevice;
    }

    @Override
    protected void closeInterface() {
        LOG.debug(() -> "<closeInterface>");
        UsbInterface androidUsbInterface = getActiveAndroidUsbInterface();
        if (androidConnection != null && androidUsbInterface != null) {
            LOG.debug(() -> "(closeInterface): release interface");
            androidConnection.releaseInterface(getActiveAndroidUsbInterface());
            androidConnection.close();
        } else if (androidConnection != null) {
            LOG.debug(() -> "(closeInterface): close connection");
            androidConnection.close();
        } else {
            LOG.warn(() -> "(closeInterface): no connection or no interface to close");
        }
    }

    @Override
    protected void initUsbInterface() throws AntException {
        LOG.debug(() -> "<initUsbInterface>");
        UsbManager usbManager = (UsbManager) context.getSystemService(android.content.Context.USB_SERVICE);
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

    /**
     * Returns the active android usb interface.
     *
     * @return the active android usb interface.
     */
    protected UsbInterface getActiveAndroidUsbInterface() {
        LOG.debug(() -> "<getActiveAnd+roidUsbInterface>");
        if (this.androidDevice != null) {
            UsbInterface usbInterface = this.androidDevice.getInterface(0);
            if (usbInterface != null) {
                LOG.debug(() -> "(getActiveAndroidUsbInterface): interface 0 found");
                return usbInterface;
            }
        }
        LOG.warn(() -> "(getActiveAndroidUsbInterface): interface 0 not found, returns null");
        return null;
    }
}
