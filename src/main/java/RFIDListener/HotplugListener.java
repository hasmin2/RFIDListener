//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import javax.usb.UsbDevice;
import javax.usb.event.UsbServicesEvent;
import javax.usb.event.UsbServicesListener;

public class HotplugListener implements UsbServicesListener {
    private RFIDListenerInit myRfid;

    HotplugListener() {
        try {
            SerialPortHandler serialPortHandler = new SerialPortHandler();
            SaveFileHandler saveFileHandler = new SaveFileHandler();
            new RFIDListenerConstants();
            this.myRfid = new RFIDListenerInit(serialPortHandler, saveFileHandler);
            Thread.sleep(1000L);
            this.myRfid.startTask();
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    public void usbDeviceAttached(UsbServicesEvent event) {
        UsbDevice device = event.getUsbDevice();
        this.myRfid.startTask();
    }

    public void usbDeviceDetached(UsbServicesEvent event) {
        UsbDevice device = event.getUsbDevice();
        this.myRfid.disconnectRFIDListener();
    }

    private boolean isRfidDevice(UsbDevice device) {
        try {
            String deviceStr = device.toString();
            return deviceStr.contains("1a86") && deviceStr.contains("7523");
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }
}
