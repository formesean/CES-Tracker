import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class DatabaseManager {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;

    /**
     * Creates a new DatabaseManager instance with the given database connection details.
     *
     * @param jdbcUrl      The JDBC URL of the database.
     * @param jdbcUsername The username for the database connection.
     * @param jdbcPassword The password for the database connection.
     */
    public DatabaseManager(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    /**
     * Fetches user data from the database and returns the result set.
     *
     * @return The result set containing user data.
     */
    public ResultSet fetchUserData() {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            Statement statement = dbConnection.createStatement();
            return statement.executeQuery("SELECT * FROM users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}