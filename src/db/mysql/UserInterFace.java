package db.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInterFace {

    private static MySQL mySQL;


    public static void setup(MySQL mySQL) throws SQLException {
        UserInterFace.mySQL = mySQL;
        mySQL.update("CREATE TABLE IF NOT EXISTS Users (Email VARCHAR(100), Username VARCHAR(100), ID VARCHAR(100))");
    }

    public static boolean existsEmail(String email) throws SQLException {

        PreparedStatement ps = mySQL.connect.prepareStatement("SELECT * FROM users WHERE Email=?");

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {
            return false;

        } else {

            return true;
        }
    }

    public static boolean existsUsername(String username) throws SQLException {

        PreparedStatement ps = mySQL.connect.prepareStatement("SELECT * FROM users WHERE Username=?");

        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (!rs.next()) {

            return false;
        } else {
            return true;
        }
    }


}
