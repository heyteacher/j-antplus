package be.glever.ant.usb;


import java.util.ArrayList;
import java.util.List;

import javax.usb.UsbDevice;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;

import be.glever.ant.AntException;
import be.glever.ant.usb.impl.AntUsbDevice;

public class AntUsbDeviceFactory {
    public static final int PRODUCT_DYNASTREAM_M_USB = 0x1009;
    public static final int PRODUCT_DYNASTREAM_USB2 = 0x1008;
    public static final int VENDOR_DYNASTREAM = 0x0fcf;

    /**
     * Keep track of AntUsbDevices, so that the same instance is reused for the same UsbDevice
     */
    final private static List<AntUsbDevice> ANT_USB_DEVICES = new ArrayList<>();

    /**
     * Returns a list of <b>uninitialized</b> {@link AbstractAntUsbDevice}s that are found on the system.
     *
     * @return a list of {@link AbstractAntUsbDevice}s.
     * @throws AntException if an error occurs while accessing the usb device.
     */
    public static List<AntUsbDevice> getAvailableAntDevices() throws AntException {
        try {
            List<AntUsbDevice> usbDevices = new ArrayList<>();
            usbDevices.addAll(findDevices(UsbHostManager.getUsbServices().getRootUsbHub(), VENDOR_DYNASTREAM, PRODUCT_DYNASTREAM_M_USB));
            usbDevices.addAll(findDevices(UsbHostManager.getUsbServices().getRootUsbHub(), VENDOR_DYNASTREAM, PRODUCT_DYNASTREAM_USB2));
            return usbDevices;
        } catch (SecurityException | UsbException e) {
            throw new AntException(e);
        }
    }

    private static List<AntUsbDevice> findDevices(UsbHub usbHub, int vendorId, int productId) {
        List<AntUsbDevice> resultList = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<UsbDevice> attachedUsbDevices = usbHub.getAttachedUsbDevices();
        attachedUsbDevices.forEach(device -> {
            if (device instanceof UsbHub) {
                resultList.addAll(findDevices((UsbHub) device, vendorId, productId));
            } else {
                if (device.getUsbDeviceDescriptor().idVendor() == vendorId && device.getUsbDeviceDescriptor().idProduct() == productId) {
                    resultList.add(reuseOrCreateAntUsbDevice(device));
                }
            }
        });
        return resultList;
    }

    private static AntUsbDevice reuseOrCreateAntUsbDevice(UsbDevice device) {
        AntUsbDevice foundDevice = null;
        for (AntUsbDevice antDev : ANT_USB_DEVICES) {
            if (antDev.getUsbDevice() == device)
                foundDevice = antDev;
        }
        if (foundDevice == null) {
            foundDevice = new AntUsbDevice(device);
            ANT_USB_DEVICES.add(foundDevice);
        }
        return foundDevice;
    }
}