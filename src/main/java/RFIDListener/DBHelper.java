//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DBHelper {
    private static PropertiesConfiguration rfidConfig;

    DBHelper() {
    }

    public static Connection GetConnection() {
        try {
            rfidConfig = new PropertiesConfiguration("RFIDListener.ini");
        } catch (ConfigurationException var5) {
            var5.printStackTrace();
        }

        String user = "C63A";
        String password = "prodc63a";
        String url = rfidConfig.getProperty("ConnectionPrefix").toString() + rfidConfig.getProperty("ServerIP") + ":" + rfidConfig.getProperty("ServerPort") + ":" + rfidConfig.getProperty("ServiceString");

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static void Close(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }
}
