import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {

    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/new_schema";
        String username = "root";
        String password = "12345";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Welcome");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
