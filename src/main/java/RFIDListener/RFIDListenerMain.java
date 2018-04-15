//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import java.awt.Component;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import javax.swing.JOptionPane;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbServices;

class RFIDListenerMain {
    public static File lockFile;

    RFIDListenerMain() {
    }

    public static void main(String[] args) {
        if (isRunnable()) {
            try {
                UsbServices services = UsbHostManager.getUsbServices();
                services.addUsbServicesListener(new HotplugListener());
            } catch (UsbException var2) {
                var2.printStackTrace();
            }
        }

    }

    private static boolean isRunnable() {
        try {
            String path = "RFIDScanner.lock";
            lockFile = new File(path);
            if (lockFile.exists()) {
                lockFile.delete();
            }

            FileChannel channel = (new RandomAccessFile(lockFile, "rw")).getChannel();
            FileLock lock = channel.tryLock();
            if (lock == null) {
                channel.close();
                throw new Exception();
            } else {
                return true;
            }
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "RFID Listener is already running");
            return false;
        }
    }
}
