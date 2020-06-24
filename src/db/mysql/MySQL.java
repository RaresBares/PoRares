package db.mysql;

import java.sql.*;

public class MySQL {
    Connection connect;

    public MySQL(String db, String name, String password, String host, int port) throws SQLException, ClassNotFoundException {


        connect = DriverManager
                .getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=true", name, password);

    }

    ;

    public void close() throws SQLException {
        connect.close();
    }

    public void setup() {


    }


    public void update(String qur) throws SQLException {
        connect.createStatement().executeUpdate(qur);
    }


}
