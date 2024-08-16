import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Q1 {
    public static void main(String[] args) {
        // Database URL
        String url = "jdbc:postgresql://localhost:5432/test_db";
        // Database credentials
        String user = "postgres";
        String password = "jeffrey_12";

        Connection connection = null;

        try {
            // Establishing the connection
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connection to the database established successfully!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            // Handling SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.err.println("Failed to close the connection: " + ex.getMessage());
            }
        }
    }
}
