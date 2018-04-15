//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package RFIDListener;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Request.Builder;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class RFIDListenerConnector {
    private PropertiesConfiguration rfidConfig;

    RFIDListenerConnector() {
        try {
            this.rfidConfig = new PropertiesConfiguration("RFIDListener.ini");
        } catch (ConfigurationException var2) {
            var2.printStackTrace();
        }

    }

    void send(String id) {
        Connection conn = DBHelper.GetConnection();
        ResultSet rs = null;
        if (conn != null) {
            PreparedStatement pstmt = null;

            try {
                StringBuilder sql = new StringBuilder();
                StringBuilder hex = new StringBuilder(Long.toHexString(Long.valueOf(id)).toUpperCase());

                while(hex.length() < 8) {
                    hex.insert(0, "0");
                }

                sql.append("SELECT * FROM support.all_amstm_view EMP inner join support.all_asgm_view DEPT on emp.COMPANY = dept.COMPANY and emp.ASGN_CD = dept.ASGN_CD WHERE EMP_NO in (SELECT EMP_NO FROM XP05.SUPPORT_IDCARD_VW WHERE CARD_NO = ?)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, String.valueOf(hex));
                rs = pstmt.executeQuery();
                StringBuilder resultStr = new StringBuilder();
                InetAddress local = InetAddress.getLocalHost();
                String ip = local.getHostAddress();
                resultStr.append("%7B");

                while(rs.next()) {
                    TrayIconHandler.displayMessage(rs.getString("EMP_NO") + "__" + rs.getString("KOR_NM"), "Query Successfully Received", MessageType.INFO);
                    resultStr.append("\"kor_nm\" :");
                    resultStr.append("\"").append(rs.getString("KOR_NM")).append("\"");
                    resultStr.append(",");
                    resultStr.append("\"emp_no\" :");
                    resultStr.append("\"").append(rs.getString("EMP_NO")).append("\"");
                    resultStr.append(",");
                    resultStr.append("\"asgn_cd\" : ");
                    resultStr.append("\"").append(rs.getString("ASGN_CD")).append("\"");
                    resultStr.append(",");
                    resultStr.append("\"asgn_full_nm\" : ");
                    resultStr.append("\"").append(rs.getString("ASGN_FULL_NM")).append("\"");
                    resultStr.append(",");
                    resultStr.append("\"job_tit_nm\" : ");
                    resultStr.append("\"").append(rs.getString("JOB_TIT_NM")).append("\"");
                }

                resultStr.append(",");
                resultStr.append("\"ip\" : ");
                resultStr.append("\"").append(ip).append("\"");
                resultStr.append("%7D");
                this.sendtoRESTServer(resultStr);
            } catch (Exception var18) {
                var18.printStackTrace();
            } finally {
                try {
                    assert rs != null;

                    rs.close();
                    pstmt.close();
                    conn.close();
                } catch (SQLException var17) {
                    System.out.println("Oracle Connection Already Closed");
                }

            }

            DBHelper.Close(conn);
        }
    }

    private void sendtoRESTServer(StringBuilder resultStr) {
        OkHttpClient client = new OkHttpClient();
        Request request = (new Builder()).url("http://" + this.rfidConfig.getProperty("RESTServerIP").toString() + "/" + this.rfidConfig.getProperty("RESTSvrThing") + "/" + this.rfidConfig.getProperty("RESTSvrThingService") + "?method=" + this.rfidConfig.getProperty("RESTSvrComm") + "&appKey=" + this.rfidConfig.getProperty("RESTSvrAppKey") + "&inputData=" + resultStr).get().addHeader("Cache-Control", "no-cache").build();

        try {
            Thread.sleep(1000L);
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                TrayIconHandler.displayMessage("RFID_Listener", "Message Successfully sent to Server.", MessageType.INFO);
            } else if (response.code() == 401) {
                TrayIconHandler.displayMessage("RFID_Listener", "Failed to send message. Authorization has failed.", MessageType.WARNING);
            } else if (response.code() == 404) {
                TrayIconHandler.displayMessage("RFID_Listener", "Failed to connect Server. RestServer is offline", MessageType.WARNING);
            } else if (response.code() == 503) {
                TrayIconHandler.displayMessage("RFID_Listener", "Failed to send message. Rest API not available.", MessageType.WARNING);
            } else {
                TrayIconHandler.displayMessage(response.code() + " HTTP error has occurred", String.valueOf(response.body()), MessageType.WARNING);
            }

            response.body().close();
        } catch (InterruptedException | IOException var5) {
            TrayIconHandler.displayMessage("RFID_Listener", "Failed to send Message with Client Cause, Please restart the Application", MessageType.WARNING);
        }

    }
}
