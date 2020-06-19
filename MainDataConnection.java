import java.sql.*;

public class MainDataConnection {
    static public Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_management_application", "root", "root");
            System.out.println("Connection successfull Static connection...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}