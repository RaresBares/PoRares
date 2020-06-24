import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import db.mysql.MySQL;
import db.mysql.UserInterFace;
import register.Register;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

public class PoRares {
    private static UserInterFace uif;
    private static HttpServer server;
    public static Register register;

    public static void main(String[] args) throws Exception {

        UserInterFace.setup(new MySQL("users", "root", "", "localhost", 3306));
        register = new Register();
        System.out.println("RegisterServer: " + register.getStatus());


    }

}
