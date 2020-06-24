package register;

import com.sun.net.httpserver.HttpServer;
import db.mysql.UserInterFace;
import handles.RegisterContext;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Register {


    public Register() throws Exception {
        int port = 2003;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server : " + port);


        while (true) {

            Socket clientSocket = serverSocket.accept();
            System.err.println("Neue Verbindung");


            InputStream in = clientSocket.getInputStream();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


            String s = "";

            Scanner sc = new Scanner(in);
            int ch;
            boolean pass = true;
            while ((ch = in.read()) > -1 && pass) {

                s += ((char)ch);
                if(s.endsWith(", \"end\": \"true\"}")){
                    System.err.println("end");
                    pass = false;
                    break;

                }
            }
            String msg = "good";
            String data = s.substring(s.length() - Integer.parseInt(s.split("Content-Length")[1].trim().split("\n")[0].trim().substring(2).trim()));
            JSONObject jsonObject = new JSONObject(data);

            if(UserInterFace.existsUsername(jsonObject.getString("username"))){
                msg = "uexists";
            }
            if(UserInterFace.existsEmail(jsonObject.getString("email"))){
                msg = "eexists";
            }
            //  System.out.println(s);
         //  System.out.println( s.split("\n").length);



            out.write("HTTP/1.0 200 OK\r\n");
            out.write("Server: Apache/0.8.4\r\n");
            out.write("Access-Control-Allow-Origin: *\r\n");

            out.write("Content-Type: text/html\r\n");
            out.write("Content-Length: " + msg.length() + "\r\n");
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            out.write("\r\n");
            out.write(msg);


            out.close();
            in.close();
            clientSocket.close();
        }
    }






    public int getStatus(){
        return 0;
    }

}
