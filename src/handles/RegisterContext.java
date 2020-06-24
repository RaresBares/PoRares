package handles;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.mysql.UserInterFace;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class RegisterContext implements HttpHandler {


    @Override
    public void handle(HttpExchange he) throws IOException {

        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);

        int b;
        String buf = "";
        while ((b = br.read()) != -1) {

            buf += ((char) b);

        }


        String msg = buf;
        JSONObject js = new JSONObject(msg);
        String email = js.getString("email");
        String pass = js.getString("passwort");
        String username = js.getString("username");


        String response = "nexists";


        try {
            if (UserInterFace.existsEmail(email)) {
                response = "eexists";
            }
            if (UserInterFace.existsUsername(username)) {
                response = "uexists";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.getResponseHeaders().add("Access-Control-Allow-Headers", "origin, x-requested-with, content-type");
        he.getResponseHeaders().add("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");


        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();

        os.write(response.getBytes());
        os.close();


        System.out.println(response.length() + "  " + response);


        br.close();
        isr.close();


    }
}
