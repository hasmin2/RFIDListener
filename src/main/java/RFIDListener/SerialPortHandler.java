//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.awt.TrayIcon.MessageType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

class SerialPortHandler {
    private PropertiesConfiguration rfidConfig;
    private SerialPort serialPort;
    private boolean listenPort;

    SerialPortHandler() {
        try {
            this.rfidConfig = new PropertiesConfiguration("RFIDListener.ini");
        } catch (ConfigurationException var2) {
            var2.printStackTrace();
        }

    }

    List getPortList() {
        List comportList = new ArrayList();
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        while(portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier)portList.nextElement();
            comportList.add(portId.getName());
        }

        return comportList;
    }

    void disConnect() {
        this.listenPort = false;

        try {
            this.serialPort.close();
            TrayIconHandler.displayMessage("RFID_Listener", "Application Successfully disconnected!", MessageType.INFO);
        } catch (Exception var2) {
            TrayIconHandler.displayMessage("RFID_Listener", "Can't disconnect from Serial PORT, restart the Application", MessageType.WARNING);
        }

    }

    void connect() throws Exception {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(String.valueOf(this.rfidConfig.getProperty("ComPort")));
            if (portIdentifier.isCurrentlyOwned()) {
                TrayIconHandler.displayMessage("RFID_Listener", "The PORT currently selected is already in use, try restart the application!", MessageType.WARNING);
            } else {
                try {
                    CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
                    this.serialPort = (SerialPort)commPort;
                    this.serialPort.setSerialPortParams(Integer.valueOf(this.rfidConfig.getProperty("BaudRate").toString()), Integer.valueOf(this.rfidConfig.getProperty("Data").toString()), Integer.valueOf(this.rfidConfig.getProperty("Stop").toString()), Integer.valueOf(this.rfidConfig.getProperty("Parity").toString()));
                    TrayIconHandler.displayMessage("RFID_Listener", "Application Successfully Connected!", MessageType.INFO);
                    this.listenPort = true;
                    (new Thread(new SerialPortHandler.SerialReader(this.serialPort))).start();
                } catch (PortInUseException var3) {
                    TrayIconHandler.displayMessage("RFID_Listener: Current port is using", "The PORT currently selected is already in use, try restart the application!", MessageType.WARNING);
                    throw var3;
                } catch (UnsupportedCommOperationException var4) {
                    var4.printStackTrace();
                }
            }

        } catch (NoSuchPortException var5) {
            TrayIconHandler.displayMessage("RFID_Listener: Port not exist.", "The PORT currently selected is not Exist, try other Serial port or restart the application", MessageType.WARNING);
            throw var5;
        }
    }

    private class SerialReader implements Runnable {
        SerialPort cardReader;

        SerialReader(SerialPort port) {
            this.cardReader = port;
        }

        public void run() {
            InputStream in = null;

            try {
                in = this.cardReader.getInputStream();
                BufferedReader bufferStream = new BufferedReader(new InputStreamReader(in));
                String rfidMessage = "";

                while(SerialPortHandler.this.listenPort) {
                    Thread.sleep(1000L);

                    try {
                        rfidMessage = bufferStream.readLine();
                    } catch (IOException var6) {
                        continue;
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }

                    if (rfidMessage != null && rfidMessage.length() >= 8) {
                        RFIDListenerConnector rfidListenerConnector = new RFIDListenerConnector();
                        rfidListenerConnector.send(rfidMessage);
                    }
                }
            } catch (InterruptedException | IOException var8) {
                var8.printStackTrace();

                try {
                    assert in != null;

                    if (RFIDListenerMain.lockFile.exists()) {
                        RFIDListenerMain.lockFile.delete();
                    }

                    in.close();
                } catch (IOException var5) {
                    ;
                }
            }

        }
    }
}
