//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;

class RFIDListenerInit {
    private SerialPortHandler serialPortHandler;
    private List comportList;
    private SaveFileHandler saveFileHandler;

    RFIDListenerInit(SerialPortHandler serialPortHandler, SaveFileHandler saveFileHandler) {
        this.serialPortHandler = serialPortHandler;
        this.saveFileHandler = saveFileHandler;
        TrayIconHandler.registerTrayIcon(Toolkit.getDefaultToolkit().getImage("trayIcon.png"), "RFID_Listener", (e) -> {
        });
        this.getCurrentComportList();
        if (this.comportList.size() > 0) {
            this.initializeDropdownMenu();
        } else {
            TrayIconHandler.addItem("RFID_Listener", (e) -> {
            });
            TrayIconHandler.addSeparator();
            TrayIconHandler.displayMessage("RFID_Listener", "No port found for connecting. Check connectivity.", MessageType.INFO);
            TrayIconHandler.addItem("Exit", (e) -> {
                this.exitMenuEvent(e.getSource());
            });
        }

    }

    private void initializeDropdownMenu() {
        this.addConnectMenu();
        this.initComportListMenu();
        this.initComportSpeedListMenu();
        this.initComportDatabitMenu();
        this.initComportParitybitMenu();
        this.initComportStopbitMenu();
        this.initComportFlowControlMenu();
        this.initConfigSettings();
        TrayIconHandler.addItem("Exit", (e) -> {
            this.exitMenuEvent(e.getSource());
        });
        TrayIconHandler.displayMessage("RFID_Listener", "Appliation is running...", MessageType.INFO);
    }

    void exitMenuEvent(Object source) {
        this.serialPortHandler.disConnect();
        if (RFIDListenerMain.lockFile.exists()) {
            RFIDListenerMain.lockFile.delete();
        }

        System.exit(0);
    }

    void disconnectRFIDListener() {
        this.serialPortHandler.disConnect();
        TrayIconHandler.toggleCheckbox("Connect");
        if (RFIDListenerMain.lockFile.exists()) {
            RFIDListenerMain.lockFile.delete();
        }

    }

    public void startTask() {
        try {
            this.serialPortHandler.connect();
            TrayIconHandler.toggleCheckbox("Connect");
            TrayIconHandler.displayMessage("RFID_Listener", "Auto Connection completed. App is ready to go.", MessageType.INFO);
        } catch (Exception var2) {
            TrayIconHandler.displayMessage("RFID_Listener", "Can't auto start the connection. Please check the connection status. and reatart the app.", MessageType.WARNING);
        }

    }

    private void addConnectMenu() {
        TrayIconHandler.addCheckBox("Connect", (e) -> {
            this.addConnectEvent(e.getStateChange());
        });
        TrayIconHandler.addSeparator();
    }

    private void addConnectEvent(Object source) {
        if (source.equals(1)) {
            try {
                this.serialPortHandler.connect();
            } catch (Exception var3) {
                TrayIconHandler.toggleCheckbox("Connect");
            }
        } else {
            this.serialPortHandler.disConnect();
        }

    }

    void initComportListMenu() {
        if (this.comportList.size() > 0) {
            Iterator var1 = this.comportList.iterator();

            while(var1.hasNext()) {
                Object aComportList = var1.next();
                TrayIconHandler.addCheckBoxToMenu("ComPort", aComportList.toString(), (e) -> {
                    this.addComportSelectEvent(e.getItem());
                });
            }
        } else {
            TrayIconHandler.addCheckBoxToMenu("ComPort", "No Port available", this::addComportSelectEvent);
        }

        this.setInitComportValueFromFile();
    }

    private void initComportSpeedListMenu() {
        String[] var1 = RFIDListenerConstants.BAUDRATE_MENUITEM;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String aBAUDRATE_MENUITEM = var1[var3];
            TrayIconHandler.addCheckBoxToMenu("BaudRate", aBAUDRATE_MENUITEM, (e) -> {
                this.addComportSpeedSelectionEvent(e.getItem());
            });
        }

        this.setInitComportSpeedValueFromFile();
    }

    private void initComportDatabitMenu() {
        String[] var1 = RFIDListenerConstants.DATABIT_MENUITEM;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String aDATABIT_MENUITEM = var1[var3];
            TrayIconHandler.addCheckBoxToMenu("Data", aDATABIT_MENUITEM, (e) -> {
                this.addComportDatabitSelectionEvent(e.getItem());
            });
        }

        this.setInitComportDatabitValueFromFile();
    }

    private void initComportParitybitMenu() {
        String[] var1 = RFIDListenerConstants.PARITY_MENUITEM;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String aPARITY_MENUITEM = var1[var3];
            TrayIconHandler.addCheckBoxToMenu("Parity", aPARITY_MENUITEM, (e) -> {
                this.addComportParitySelectionEvent(e.getItem());
            });
        }

        this.setInitComportParitybitValueFromFile();
    }

    private void initComportStopbitMenu() {
        String[] var1 = RFIDListenerConstants.STOP_MENUITEM;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String aSTOP_MENUITEM = var1[var3];
            TrayIconHandler.addCheckBoxToMenu("Stop", aSTOP_MENUITEM, (e) -> {
                this.addComportStopbitSelectionEvent(e.getItem());
            });
        }

        this.setInitComportStopbitValueFromFile();
    }

    private void initComportFlowControlMenu() {
        String[] var1 = RFIDListenerConstants.FLOWCONTROL_MENUITEM;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String aFLOWCONTROL_MENUITEM = var1[var3];
            TrayIconHandler.addCheckBoxToMenu("FlowControl", aFLOWCONTROL_MENUITEM, (e) -> {
                this.addComportFlowControlSelectionEvent(e.getItem());
            });
        }

        this.setInitComportFlowControlValueFromFile();
    }

    private void addComportSelectEvent(Object source) {
        this.saveFileHandler.setComport(source.toString());
        TrayIconHandler.refreshCheckboxMenu("ComPort", source.toString());
    }

    private void addComportSpeedSelectionEvent(Object source) {
        this.saveFileHandler.setBaudRate(source.toString());
        TrayIconHandler.refreshCheckboxMenu("BaudRate", source.toString());
    }

    private void addComportStopbitSelectionEvent(Object source) {
        this.saveFileHandler.setStopbit(source.toString());
        TrayIconHandler.refreshCheckboxMenu("Stop", source.toString());
    }

    private void addComportFlowControlSelectionEvent(Object source) {
        this.saveFileHandler.setFlowControl(source.toString());
        TrayIconHandler.refreshCheckboxMenu("FlowControl", source.toString());
    }

    private void addComportDatabitSelectionEvent(Object source) {
        this.saveFileHandler.setDatabit(source.toString());
        TrayIconHandler.refreshCheckboxMenu("Data", source.toString());
    }

    private void addComportParitySelectionEvent(Object source) {
        this.saveFileHandler.setParityBit(source.toString());
        TrayIconHandler.refreshCheckboxMenu("Parity", source.toString());
    }

    private void setInitComportValueFromFile() {
        TrayIconHandler.refreshCheckboxMenu("ComPort", this.saveFileHandler.getComport());
    }

    private void setInitComportSpeedValueFromFile() {
        TrayIconHandler.refreshCheckboxMenu("BaudRate", this.saveFileHandler.getBaudRate());
    }

    private void setInitComportDatabitValueFromFile() {
        TrayIconHandler.refreshCheckboxMenu("Data", this.saveFileHandler.getDatabit());
    }

    private void setInitComportParitybitValueFromFile() {
        TrayIconHandler.refreshCheckboxMenu("Parity", this.saveFileHandler.getParitybit());
    }

    private void setInitComportStopbitValueFromFile() {
        TrayIconHandler.refreshCheckboxMenu("Stop", this.saveFileHandler.getStopbit());
    }

    private void setInitComportFlowControlValueFromFile() {
        TrayIconHandler.refreshCheckboxMenu("FlowControl", this.saveFileHandler.getFlowControl());
    }

    private void initConfigSettings() {
        this.saveFileHandler.getRESTSvrIP();
        this.saveFileHandler.getServerIP();
        this.saveFileHandler.getServerPort();
        this.saveFileHandler.getServiceString();
        this.saveFileHandler.getConnectionPrefixString();
        this.saveFileHandler.getRESTSvrIP();
        this.saveFileHandler.getRESTSvrCommtype();
        this.saveFileHandler.getRESTSvrAppkey();
        this.saveFileHandler.getRESTSvrThing();
        this.saveFileHandler.getRESTSvrThingService();
    }

    void getCurrentComportList() {
        this.comportList = this.serialPortHandler.getPortList();
    }
}
