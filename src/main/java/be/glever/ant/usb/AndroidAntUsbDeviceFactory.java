package be.glever.ant.usb;

import static java.lang.String.format;

import android.content.Context;
import android.hardware.usb.UsbManager;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.glever.ant.AntException;
import be.glever.ant.usb.impl.AndroidAntUsbDevice;
import be.glever.util.logging.Log;

/**
 * Android Ant Usb Device Factory.
 */
@SuppressWarnings("unused")
public class AndroidAntUsbDeviceFactory {

    private AndroidAntUsbDeviceFactory() {
    }
    private static final Log LOG = Log.getLogger(AndroidAntUsbDeviceFactory.class);
    /**
     * Product ID for DYNASTREAM M USB.
     */
    public static final int PRODUCT_DYNASTREAM_M_USB = 0x1009;
    /**
     * Product ID for DYNASTREAM USB2.
     */
    public static final int PRODUCT_DYNASTREAM_USB2 = 0x1008;
    /**
     * Vendor ID for DYNASTREAM.
     */
    public static final int VENDOR_DYNASTREAM = 0x0fcf;

    /**
     * Keep track of AntUsbDevices, so that the same instance is reused for the same UsbDevice
     */

    final private static List<AndroidAntUsbDevice> ANDROID_ANT_USB_DEVICES = new ArrayList<>();

    /**
     * Returns a list of <b>uninitialized</b> {@link AbstractAntUsbDevice}s that are found on the system.
     * @param context the application context.
     * @param androidLogger the android logger.
     * @return a list of {@link AbstractAntUsbDevice}s.
     * @throws AntException if an error occurs while accessing the usb device.
     */
    @SuppressWarnings("unused")
    public static List<AndroidAntUsbDevice> getAvailableAntDevices(Context context, Logger androidLogger) throws AntException {
        try {
            List<AndroidAntUsbDevice> usbDevices = new ArrayList<>();
            UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
            HashMap<String, android.hardware.usb.UsbDevice> deviceList = usbManager.getDeviceList();
            for (android.hardware.usb.UsbDevice device : deviceList.values()) {
                if (device.getVendorId() == VENDOR_DYNASTREAM && (device.getProductId() == PRODUCT_DYNASTREAM_M_USB) || device.getProductId() == PRODUCT_DYNASTREAM_USB2) {
                    usbDevices.add(reuseOrCreateAntUsbDevice(context, device, androidLogger));
                }
            }
            LOG.debug(() -> format("Found %s devices", usbDevices.size()));
            return usbDevices;
        } catch (SecurityException e) {
            throw new AntException(e);
        }
    }

    private static AndroidAntUsbDevice reuseOrCreateAntUsbDevice(
            Context context,
            android.hardware.usb.UsbDevice device,
            Logger androidLogger) {
        AndroidAntUsbDevice foundDevice = null;
        for (AndroidAntUsbDevice antDev : ANDROID_ANT_USB_DEVICES) {

            if ( antDev.getAndroidDevice() == device)
                foundDevice = antDev;
        }
        if (foundDevice == null) {
            foundDevice = new AndroidAntUsbDevice(context, device, androidLogger);
            ANDROID_ANT_USB_DEVICES.add(foundDevice);
        }
        return foundDevice;
    }
}