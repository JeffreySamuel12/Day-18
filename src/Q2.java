import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Q2 {
    public static void main(String[] args) {
        // Database URL
        String url = "jdbc:postgresql://localhost:5432/company_db";
        // Database credentials
        String user = "postgres";
        String password = "jeffrey_12";

        Connection connection = null;

        try {
            // Establishing the connection
            connection = DriverManager.getConnection(url, user, password);

            // SQL query
            String sql = "SELECT * FROM employees";

            // Prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");

                // Print the details
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Position: " + position);
                System.out.println("Salary: " + salary);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            // Handle SQL exception
            System.err.println("SQL Exception: " + e.getMessage());
        } finally {
            // Close the connection
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
