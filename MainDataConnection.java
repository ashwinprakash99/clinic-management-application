import java.sql.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class MainDataConnection {
    static public Connection connection = null;
    
    public static void init() throws Exception {
        JSONObject jsonObject = (JSONObject) (new JSONParser().parse(new FileReader("connection.json")));
        String host = (String) jsonObject.get("host");
        String port = (String) jsonObject.get("port");
        String username = (String) jsonObject.get("username");
        String password = (String) jsonObject.get("password");
        String database = (String) jsonObject.get("database");
        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
        connection = DriverManager.getConnection(connectionString, username, password);
        System.out.println("Connection Established Successfully...");
    }

    public static void main(String[] args) throws Exception {
        MainDataConnection.init();
    }
}