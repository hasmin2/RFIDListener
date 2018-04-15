//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.io.IOException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

class SaveFileHandler {
    private PropertiesConfiguration rfidConfig;

    SaveFileHandler() {
        try {
            File f = new File("RFIDListener.ini");
            if (f.exists()) {
                this.rfidConfig = new PropertiesConfiguration("RFIDListener.ini");
            } else {
                f.createNewFile();
                this.rfidConfig = new PropertiesConfiguration("RFIDListener.ini");
            }
        } catch (ConfigurationException var2) {
            var2.printStackTrace();
        } catch (IOException var3) {
            TrayIconHandler.displayMessage("RFID_Listener", "Can't Find 'RFIDListener.ini' file, trying restart the Application", MessageType.WARNING);
        }

    }

    public void setComport(String port) {
        try {
            this.rfidConfig.setProperty("ComPort", port);
            this.rfidConfig.save();
        } catch (ConfigurationException var3) {
            var3.printStackTrace();
        }

    }

    public void setBaudRate(String baud) {
        try {
            this.rfidConfig.setProperty("BaudRate", baud);
            this.rfidConfig.save();
        } catch (ConfigurationException var3) {
            var3.printStackTrace();
        }

    }

    public void setStopbit(String stop) {
        try {
            this.rfidConfig.setProperty("Stop", stop);
            this.rfidConfig.save();
        } catch (ConfigurationException var3) {
            var3.printStackTrace();
        }

    }

    public void setFlowControl(String flow) {
        try {
            this.rfidConfig.setProperty("FlowControl", flow);
            this.rfidConfig.save();
        } catch (ConfigurationException var3) {
            var3.printStackTrace();
        }

    }

    public void setDatabit(String data) {
        try {
            this.rfidConfig.setProperty("Data", data);
            this.rfidConfig.save();
        } catch (ConfigurationException var3) {
            var3.printStackTrace();
        }

    }

    public void setParityBit(String parity) {
        try {
            this.rfidConfig.setProperty("Parity", parity);
            this.rfidConfig.save();
        } catch (ConfigurationException var3) {
            var3.printStackTrace();
        }

    }

    private void saveCall() {
        try {
            this.rfidConfig.save();
        } catch (ConfigurationException var2) {
            var2.printStackTrace();
        }

    }

    public String getComport() {
        try {
            return this.rfidConfig.getProperty("ComPort").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("ComPort", "COM3");
            this.saveCall();
            return this.rfidConfig.getProperty("ComPort").toString();
        }
    }

    public String getBaudRate() {
        try {
            return this.rfidConfig.getProperty("BaudRate").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("BaudRate", "9600");
            this.saveCall();
            return this.rfidConfig.getProperty("BaudRate").toString();
        }
    }

    public String getDatabit() {
        try {
            return this.rfidConfig.getProperty("Data").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("Data", "8");
            this.saveCall();
            return this.rfidConfig.getProperty("Data").toString();
        }
    }

    public String getParitybit() {
        try {
            return this.rfidConfig.getProperty("Parity").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("Parity", "0");
            this.saveCall();
            return this.rfidConfig.getProperty("Parity").toString();
        }
    }

    public String getFlowControl() {
        try {
            return this.rfidConfig.getProperty("FlowControl").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("FlowControl", "None");
            this.saveCall();
            return this.rfidConfig.getProperty("FlowControl").toString();
        }
    }

    public String getStopbit() {
        try {
            return this.rfidConfig.getProperty("Stop").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("Stop", "1");
            this.saveCall();
            return this.rfidConfig.getProperty("Stop").toString();
        }
    }

    public String getConnectionPrefixString() {
        try {
            return this.rfidConfig.getProperty("ConnectionPrefix").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("ConnectionPrefix", "jdbc:oracle:thin:@");
            this.saveCall();
            return this.rfidConfig.getProperty("ConnectionPrefix").toString();
        }
    }

    public String getServiceString() {
        try {
            return this.rfidConfig.getProperty("ServiceString").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("ServiceString", "HIDB1");
            this.saveCall();
            return this.rfidConfig.getProperty("ServiceString").toString();
        }
    }

    public String getServerIP() {
        try {
            return this.rfidConfig.getProperty("ServerIP").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("ServerIP", "10.100.17.11");
            this.saveCall();
            return this.rfidConfig.getProperty("ServerIP").toString();
        }
    }

    public String getServerPort() {
        try {
            return this.rfidConfig.getProperty("ServerPort").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("ServerPort", "1521");
            this.saveCall();
            return this.rfidConfig.getProperty("ServerPort").toString();
        }
    }

    public String getRESTSvrIP() {
        try {
            return this.rfidConfig.getProperty("RESTServerIP").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("RESTServerIP", "10.100.48.230");
            this.saveCall();
            return this.rfidConfig.getProperty("RESTServerIP").toString();
        }
    }

    public String getRESTSvrThing() {
        try {
            return this.rfidConfig.getProperty("RESTSvrThing").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("RESTSvrThing", "Thingworx/Things/SF.BLT.Thing.RFIDInterface");
            this.saveCall();
            return this.rfidConfig.getProperty("RESTSvrThing").toString();
        }
    }

    public String getRESTSvrThingService() {
        try {
            return this.rfidConfig.getProperty("RESTSvrThingService").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("RESTSvrThingService", "Services/receiveService");
            this.saveCall();
            return this.rfidConfig.getProperty("RESTSvrThingService").toString();
        }
    }

    public String getRESTSvrAppkey() {
        try {
            return this.rfidConfig.getProperty("RESTSvrAppKey").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("RESTSvrAppKey", "409a03cc-eadf-4b4d-a186-7f4ee06a39ae");
            this.saveCall();
            return this.rfidConfig.getProperty("RESTSvrAppKey").toString();
        }
    }

    public String getRESTSvrCommtype() {
        try {
            return this.rfidConfig.getProperty("RESTSvrComm").toString();
        } catch (NullPointerException var2) {
            this.rfidConfig.setProperty("RESTSvrComm", "POST");
            this.saveCall();
            return this.rfidConfig.getProperty("RESTSvrComm").toString();
        }
    }
}
