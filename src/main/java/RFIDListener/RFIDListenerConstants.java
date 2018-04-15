//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

public final class RFIDListenerConstants {
    static final String CONNECT_MENU = "Connect";
    static final String COMPORT_MENU = "ComPort";
    static final String BAUDRATE_MENU = "BaudRate";
    static final String DATA_BIT_MENU = "Data";
    static final String PARITY_BIT_MENU = "Parity";
    static final String STOP_BIT_MENU = "Stop";
    static final String FLOWCONTROL_MENU = "FlowControl";
    static final String SERVER_IP_MENU = "ServerIP";
    static final String REST_SERVER_IP_MENU = "RESTServerIP";
    static final String REST_SERVER_APPKEY_MENU = "RESTSvrAppKey";
    static final String REST_SERVER_THING_MENU = "RESTSvrThing";
    static final String REST_SERVER_THING_SERVICE_MENU = "RESTSvrThingService";
    static final String REST_SERVER_COMM_MENU = "RESTSvrComm";
    static final String SERVER_PORT_MENU = "ServerPort";
    static final String SERVICE_STRING_MENU = "ServiceString";
    static final String CONNECTION_PREFIX_STRING_MENU = "ConnectionPrefix";
    static final String EXIT_MENU = "Exit";
    static final int RFID_STRING_LENGTH = 8;
    static final String[] BAUDRATE_MENUITEM = new String[]{"2400", "4800", "9600", "14400", "19200", "28800", "57600", "115200", "231400", "921600", "1500000"};
    static final String[] DATABIT_MENUITEM = new String[]{"7", "8"};
    static final String[] PARITY_MENUITEM = new String[]{"1", "2", "0"};
    static final String[] STOP_MENUITEM = new String[]{"1", "1.5", "2"};
    static final String[] FLOWCONTROL_MENUITEM = new String[]{"Xon/Xoff", "None", "Hardware"};
    static final String TOOLTIP = "RFID_Listener";
    static final String STATUS_MESSAGE = "Appliation is running...";
    static final String CONNECTED_MESSAGE = "Application Successfully Connected!";
    static final String DISCONNECTED_MESSAGE = "Application Successfully disconnected!";
    static final String NO_PORT_AVAILABLE_MESSAGE = "No port found for connecting. Check connectivity.";
    static final String CONFIG_FILE_MISSING_MESSAGE = "Can't Find 'RFIDListener.ini' file, trying restart the Application";
    static final String FAILED_TO_SERVER_CONNECTED_MESSAGE = "Failed to connect Server. RestServer is offline";
    static final String SEND_FAILED_WITH_API_MESSAGE = "Failed to send message. Rest API not available.";
    static final String APP_ALREADY_RUN_MESSAGE = "RFID Listener is already running";
    static final String NO_CONNECT_MENU = "No Port available";
    static final String FAILED_TO_AUTOSTART_MESSAGE = "Can't auto start the connection. Please check the connection status. and reatart the app.";
    static final String AUTOSTART_CONNECT_MESSAGE = "Auto Connection completed. App is ready to go.";
    static final String PORT_IN_USE_MESSAGE = "The PORT currently selected is already in use, try restart the application!";
    static final String PORT_IN_USE_TOOLTIP = "RFID_Listener: Current port is using";
    static final String PORT_NOT_EXIST_MESSAGE = "The PORT currently selected is not Exist, try other Serial port or restart the application";
    static final String PORT_NOT_EXIST_TOOLTIP = "RFID_Listener: Port not exist.";
    static final String RECEIVED_MSG = "Query Successfully Received";
    static final String SEND_SUCCESSFUL_MESSAGE = "Message Successfully sent to Server.";
    static final String SEND_FAILED_MESSAGE = "Failed to send Message with Client Cause, Please restart the Application";
    static final String AUTH_FAILED_MESSAGE = "Failed to send message. Authorization has failed.";
    static final String DISCONNECTED_FAILED_MESSAGE = "Can't disconnect from Serial PORT, restart the Application";
    static final String TRAY_ICON = "trayIcon.png";
    static final String CONFIG_FILE = "RFIDListener.ini";

    public RFIDListenerConstants() {
    }
}
