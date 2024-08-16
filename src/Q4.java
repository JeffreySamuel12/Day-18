import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q4 {
    public static void main(String[] args) {
        // Database URL
        String url = "jdbc:postgresql://localhost:5432/bank_db";
        // Database credentials
        String user = "postgres";
        String password = "jeffrey_12";

        Connection connection = null;

        try {
            // Establishing the connection
            connection = DriverManager.getConnection(url, user, password);
            // Disable auto-commit mode
            connection.setAutoCommit(false);

            // SQL statements for the transaction
            String withdrawSQL = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            String depositSQL = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

            // Prepared statements
            PreparedStatement withdrawStmt = connection.prepareStatement(withdrawSQL);
            PreparedStatement depositStmt = connection.prepareStatement(depositSQL);

            // Transaction details
            int amount = 100; // Amount to transfer
            int fromAccountId = 1; // Source account ID
            int toAccountId = 2; // Destination account ID

            // Withdraw from source account
            withdrawStmt.setInt(1, amount);
            withdrawStmt.setInt(2, fromAccountId);
            withdrawStmt.executeUpdate();

            // Deposit to destination account
            depositStmt.setInt(1, amount);
            depositStmt.setInt(2, toAccountId);
            depositStmt.executeUpdate();

            // Commit the transaction
            connection.commit();
            System.out.println("Transaction completed successfully!");

        } catch (SQLException e) {
            // Handle SQL exception and roll back the transaction
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Transaction failed. Rolled back.");
                } catch (SQLException ex) {
                    System.err.println("Failed to roll back the transaction: " + ex.getMessage());
                }
            }
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
