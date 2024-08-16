import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q3 {
    public static void main(String[] args) {
        // Database URL
        String url = "jdbc:postgresql://localhost:5432/inventory_db";
        // Database credentials
        String user = "postgres";
        String password = "jeffrey_12";

        Connection connection = null;

        try {
            // Establishing the connection
            connection = DriverManager.getConnection(url, user, password);

            // SQL insert statement
            String sql = "INSERT INTO products (product_id, name, price, quantity) VALUES (?, ?, ?, ?)";

            // Prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Sample data
            int productId = 1;
            String productName = "Sample Product";
            double productPrice = 19.99;
            int productQuantity = 100;

            // Setting the parameters
            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, productName);
            preparedStatement.setDouble(3, productPrice);
            preparedStatement.setInt(4, productQuantity);

            // Execute the insert
            int rowsAffected = preparedStatement.executeUpdate();

            // Print confirmation message
            if (rowsAffected > 0) {
                System.out.println("A new product has been inserted successfully!");
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
