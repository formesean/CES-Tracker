import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class DatabaseManager {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    private Controller controller = new Controller();

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
     * Inserts a new user into the database.
     *
     * @param fullName = The full name of the user.
     * @param email = The email of the user.
     * @param password = The password of the user.
     * @param idNumber = The ID number of the user.
     * @param type = The user type.
     * @param cesPoints = The CES points of the user.
     */
    public void insertUserData(String id, String fullName, String email, String password, int idNumber, String type, int cesPoints) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            if (isEmailExists(email) && isIDNumberExists(idNumber)) {
                JOptionPane.showMessageDialog(null, "Email and ID Number already exists! Please try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String encryptedPassword = Controller.encryptPassword(password);

            PreparedStatement preparedStatement = dbConnection.prepareStatement("INSERT INTO users (userID, userName, userEmail, userPassword, userIDNumber, userType, userCESPoints) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, encryptedPassword);
            preparedStatement.setInt(5, idNumber);
            preparedStatement.setString(6, type);
            preparedStatement.setInt(7, cesPoints);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new SQLException("Failed to insert user into the database.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to insert user into the database.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isUUIDExists(String id) throws SQLException {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM users WHERE userID = ?");
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean isEmailExists(String email) throws SQLException {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM users WHERE userEmail = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean isIDNumberExists(int idNumber) throws SQLException {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM users WHERE userIDNumber = ?");
            statement.setInt(1, idNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    public boolean isAdminEmail(String email) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM admins WHERE adminEmail = ?");
            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean authenticateUser(String email, String password) throws SQLException {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT userPassword FROM users WHERE userEmail = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String encryptedPassword = resultSet.getString("userPassword");

                StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
                return passwordEncryptor.checkPassword(password, encryptedPassword);
            }
        }
        return false;
    }

    public String getUserID(String email) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT userID FROM users WHERE userEmail = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("userID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public User getUser(String id) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM users WHERE userID = ?")) {
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String userType = resultSet.getString("userType");

                    if ("Admin".equals(userType)) {
                        return new Admin(
                                resultSet.getString("userID"),
                                resultSet.getString("userName"),
                                resultSet.getString("userEmail"),
                                resultSet.getInt("userIDNumber"),
                                userType,
                                resultSet.getInt("userCESPoints")
                        );
                    } else if ("Student".equals(userType)) {
                        return new Student(
                                resultSet.getString("userID"),
                                resultSet.getString("userName"),
                                resultSet.getString("userEmail"),
                                resultSet.getInt("userIDNumber"),
                                userType,
                                resultSet.getInt("userCESPoints"),
                                resultSet.getString("userYearLevel")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setYearLevel(String id, String yearLevel) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("UPDATE users SET userYearLevel = ? WHERE userID = ?");
            preparedStatement.setString(1, yearLevel);
            preparedStatement.setString(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new SQLException("Failed to update user in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String userName = resultSet.getString("userName");
                String userEmail = resultSet.getString("userEmail");
                int userIDNumber = resultSet.getInt("userIDNumber");
                String userType = resultSet.getString("userType");
                int userCESPoints = resultSet.getInt("userCESPoints");

                users.add(new User(userID, userName, userEmail, userIDNumber, userType, userCESPoints));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<EvaluationForm> getAllEvalFormData() {
        List<EvaluationForm> evalFormDataList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM evalform")) {

            while (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String qOne = resultSet.getString("qOne");
                String qTwo = resultSet.getString("qTwo");
                byte[] beginningImgBytes = resultSet.getBytes("beginningImg");
                byte[] middleImgBytes = resultSet.getBytes("middleImg");
                byte[] endImgBytes = resultSet.getBytes("endImg");

                ImageIcon beginningImg = controller.convertBytesToImageIcon(beginningImgBytes);
                ImageIcon middleImg = controller.convertBytesToImageIcon(middleImgBytes);
                ImageIcon endImg = controller.convertBytesToImageIcon(endImgBytes);

                EvaluationForm evaluationForm = new EvaluationForm(userID, qOne, qTwo, beginningImg, middleImg, endImg);
                evalFormDataList.add(evaluationForm);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return evalFormDataList;
    }

    public void insertEvalForm(String evalformID, String userID, String eventID, String qOne, String qTwo, ImageIcon beginningImg, ImageIcon middleImg, ImageIcon endImg) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO evalform (evalformID, userID, eventID, qOne, qTwo, beginningImg, middleImg, endImg, submitted_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            Timestamp submitted_at = new Timestamp(new Date().getTime());
            byte[] beginningImgBytes = controller.convertImageIconToBytes(beginningImg);
            byte[] middleImgBytes = controller.convertImageIconToBytes(middleImg);
            byte[] endImgBytes = controller.convertImageIconToBytes(endImg);

            statement.setString(1, evalformID);
            statement.setString(2, userID);
            statement.setString(3, eventID);
            statement.setString(4, qOne);
            statement.setString(5, qTwo);
            statement.setBytes(6, beginningImgBytes);
            statement.setBytes(7, middleImgBytes);
            statement.setBytes(8, endImgBytes);
            statement.setTimestamp(9, submitted_at);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Evaluation Form submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to submit evaluation form!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword(String userId, String encryptedPassword) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("UPDATE users SET userPassword = ? WHERE userID = ?");
            preparedStatement.setString(1, encryptedPassword);
            preparedStatement.setString(2, userId);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new SQLException("Failed to update password in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}