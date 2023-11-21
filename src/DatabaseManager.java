import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class DatabaseManager {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    private Controller controller = new Controller();

    public DatabaseManager(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

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

    public void insertEventData(String eventID, String eventName, String eventLocation, String eventDate, String startTime, String endTime, String eventType, String eventMode, String roles, String rolePoints) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
            Time startTimeValue = new Time(timeFormat.parse(startTime).getTime());
            Time endTimeValue = new Time(timeFormat.parse(endTime).getTime());

            PreparedStatement preparedStatement = dbConnection.prepareStatement("INSERT INTO events (eventID, eventName, eventLocation, eventDate, startTime, endTime, eventType, eventMode, roles, rolePoints) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, eventID);
            preparedStatement.setString(2, eventName);
            preparedStatement.setString(3, eventLocation);
            preparedStatement.setDate(4, java.sql.Date.valueOf(eventDate));
            preparedStatement.setTime(5, startTimeValue);
            preparedStatement.setTime(6, endTimeValue);
            preparedStatement.setString(7, eventType);
            preparedStatement.setString(8, eventMode);
            preparedStatement.setString(9, roles);
            preparedStatement.setString(10, rolePoints);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Event added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to insert event into the database.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertEvalForm(String evalformID, String userID, String eventID, String qOne, String qTwo, String qThree, String qFour, String qFive, String role, int rolePoints, String rating, ImageIcon beginningImg, ImageIcon middleImg, ImageIcon endImg) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO evalform (evalformID, userID, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg, submitted_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            Timestamp submitted_at = new Timestamp(new Date().getTime());
            byte[] beginningImgBytes = controller.convertImageIconToBytes(beginningImg);
            byte[] middleImgBytes = controller.convertImageIconToBytes(middleImg);
            byte[] endImgBytes = controller.convertImageIconToBytes(endImg);

            statement.setString(1, evalformID);
            statement.setString(2, userID);
            statement.setString(3, eventID);
            statement.setString(4, qOne);
            statement.setString(5, qTwo);
            statement.setString(6, qThree);
            statement.setString(7, qFour);
            statement.setString(8, qFive);
            statement.setString(9, role);
            statement.setInt(10, rolePoints);
            statement.setString(11, rating);
            statement.setBytes(12, beginningImgBytes);
            statement.setBytes(13, middleImgBytes);
            statement.setBytes(14, endImgBytes);
            statement.setTimestamp(15, submitted_at);
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

    public void updateUserCESPoints(String id, int newCesPoints) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement preparedStatement = dbConnection.prepareStatement("UPDATE users SET userCESPoints = ? WHERE userID = ?");
            preparedStatement.setInt(1, newCesPoints);
            preparedStatement.setString(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "CES Points updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                throw new SQLException("Failed to update CES Points in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void archiveEvalForm(String evalformID, String userID, String eventID, String qOne, String qTwo, String qThree, String qFour, String qFive, String role, int rolePoints, String rating, ImageIcon beginningImg, ImageIcon middleImg, ImageIcon endImg) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("INSERT INTO archive (evalformID, userID, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg, submitted_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            Timestamp submitted_at = new Timestamp(new Date().getTime());
            byte[] beginningImgBytes = controller.convertImageIconToBytes(beginningImg);
            byte[] middleImgBytes = controller.convertImageIconToBytes(middleImg);
            byte[] endImgBytes = controller.convertImageIconToBytes(endImg);

            statement.setString(1, evalformID);
            statement.setString(2, userID);
            statement.setString(3, eventID);
            statement.setString(4, qOne);
            statement.setString(5, qTwo);
            statement.setString(6, qThree);
            statement.setString(7, qFour);
            statement.setString(8, qFive);
            statement.setString(9, role);
            statement.setInt(10, rolePoints);
            statement.setString(11, rating);
            statement.setBytes(12, beginningImgBytes);
            statement.setBytes(13, middleImgBytes);
            statement.setBytes(14, endImgBytes);
            statement.setTimestamp(15, submitted_at);
            statement.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(String id) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement deleteUserStatement = dbConnection.prepareStatement("DELETE FROM users WHERE userID = ?")) {
                deleteUserStatement.setString(1, id);
                int rowsDeleted = deleteUserStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEvalForm(String evalformID) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement deleteEvalFormStatement = dbConnection.prepareStatement("DELETE FROM evalform WHERE evalformID = ?")) {
                deleteEvalFormStatement.setString(1, evalformID);
                deleteEvalFormStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean isUUIDExists(String id) throws SQLException {
//        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
//            PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM users WHERE userID = ?");
//            statement.setString(1, id);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            if (resultSet.next()) {
//                return resultSet.getInt(1) > 0;
//            }
//        }
//        return false;
//    }

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

    public int getUserCESPoints(String id) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM users WHERE userID = ?")) {
                statement.setString(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt("userCESPoints");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
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

    public List<User> getAllStudents() {
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

                if ("Student".equals(userType)) {
                    String userYearLevel = resultSet.getString("userYearLevel");
                    users.add(new Student(userID, userName, userEmail, userIDNumber, userType, userCESPoints, userYearLevel));
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
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

                if ("Admin".equals(userType)) {
                    users.add(new Admin(userID, userName, userEmail, userIDNumber, userType, userCESPoints));
                } else if ("Student".equals(userType)) {
                    String userYearLevel = resultSet.getString("userYearLevel");
                    users.add(new Student(userID, userName, userEmail, userIDNumber, userType, userCESPoints, userYearLevel));
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<User> getStudentsByYearLevel(String yearLevel) {
        List<User> students = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM users WHERE userType = 'Student' AND userYearLevel = ?");
            statement.setString(1, yearLevel);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String userName = resultSet.getString("userName");
                String userEmail = resultSet.getString("userEmail");
                int userIDNumber = resultSet.getInt("userIDNumber");
                String userType = resultSet.getString("userType");
                int userCESPoints = resultSet.getInt("userCESPoints");

                students.add(new Student(userID, userName, userEmail, userIDNumber, userType, userCESPoints, yearLevel));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public String getUserName(String userID) {
        String userName = null;

        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT userName FROM users WHERE userID = ?")) {
                preparedStatement.setString(1, userID);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    userName = resultSet.getString("userName");
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userName;
    }

    public String getEventName(String eventID) {
        String eventName = null;

        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT eventName FROM events WHERE eventID = ?")) {
                preparedStatement.setString(1, eventID);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    eventName = resultSet.getString("eventName");
                }

                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eventName;
    }

    public int getRolePoints(String eventID, String role) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement preparedStatement = dbConnection.prepareStatement("SELECT roles, rolePoints FROM events WHERE eventID = ?")) {
                preparedStatement.setString(1, eventID);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String[] rolesArray = resultSet.getString("roles").split(",");
                    String[] rolePointsArray = resultSet.getString("rolePoints").split(",");

                    int roleIndex = Arrays.asList(rolesArray).indexOf(role);

                    if (roleIndex != -1 && roleIndex < rolePointsArray.length) {
                        return Integer.parseInt(rolePointsArray[roleIndex]);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM events");

            while (resultSet.next()) {
                String eventID = resultSet.getString("eventID");
                String eventName = resultSet.getString("eventName");
                String eventLocation = resultSet.getString("eventLocation");
                Date eventDate = resultSet.getDate("eventDate");
                Time startTime = resultSet.getTime("startTime");
                Time endTime = resultSet.getTime("endTime");
                String eventType = resultSet.getString("eventType");
                String eventMode = resultSet.getString("eventMode");
                String roles = resultSet.getString("roles");
                String rolePoints = resultSet.getString("rolePoints");

                events.add(new Event(eventID, eventName, eventLocation, eventDate.toString(), startTime.toString(), endTime.toString(), eventType, eventMode, roles, rolePoints));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return events;
    }

    public List<EvaluationForm> getAllEvalFormData() {
        List<EvaluationForm> evalFormDataList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM evalform")) {

            while (resultSet.next()) {
                String evalformID = resultSet.getString("evalformID");
                String userID = resultSet.getString("userID");
                String eventID = resultSet.getString("eventID");
                String qOne = resultSet.getString("qOne");
                String qTwo = resultSet.getString("qTwo");
                String qThree = resultSet.getString("qThree");
                String qFour = resultSet.getString("qFour");
                String qFive = resultSet.getString("qFive");
                String role = resultSet.getString("role");
                String rolePoints = resultSet.getString("rolePoints");
                String rating = resultSet.getString("rating");
                byte[] beginningImgBytes = resultSet.getBytes("beginningImg");
                byte[] middleImgBytes = resultSet.getBytes("middleImg");
                byte[] endImgBytes = resultSet.getBytes("endImg");
                Timestamp submitted_at = resultSet.getTimestamp("submitted_at");

                ImageIcon beginningImg = controller.convertBytesToImageIcon(beginningImgBytes);
                ImageIcon middleImg = controller.convertBytesToImageIcon(middleImgBytes);
                ImageIcon endImg = controller.convertBytesToImageIcon(endImgBytes);

                evalFormDataList.add(new EvaluationForm(evalformID, userID, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg, submitted_at));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return evalFormDataList;
    }

    public List<EvaluationForm> getUserArchive(String userID) {
        List<EvaluationForm> evalformArchive = new ArrayList<>();

        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM archive WHERE userID = ?");
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String evalformID = resultSet.getString("evalformID");
                String eventID = resultSet.getString("eventID");
                String qOne = resultSet.getString("qOne");
                String qTwo = resultSet.getString("qTwo");
                String qThree = resultSet.getString("qThree");
                String qFour = resultSet.getString("qFour");
                String qFive = resultSet.getString("qFive");
                String role = resultSet.getString("role");
                String rolePoints = resultSet.getString("rolePoints");
                String rating = resultSet.getString("rating");
                byte[] beginningImgBytes = resultSet.getBytes("beginningImg");
                byte[] middleImgBytes = resultSet.getBytes("middleImg");
                byte[] endImgBytes = resultSet.getBytes("endImg");
                Timestamp submitted_at = resultSet.getTimestamp("submitted_at");

                ImageIcon beginningImg = controller.convertBytesToImageIcon(beginningImgBytes);
                ImageIcon middleImg = controller.convertBytesToImageIcon(middleImgBytes);
                ImageIcon endImg = controller.convertBytesToImageIcon(endImgBytes);

                evalformArchive.add(new EvaluationForm(evalformID, userID, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg, submitted_at));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return evalformArchive;
    }

    public EvaluationForm getEvalFormData(String evalformID) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM evalform WHERE evalformID = ?")) {
                statement.setString(1, evalformID);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String userID = resultSet.getString("userID");
                    String eventID = resultSet.getString("eventID");
                    String qOne = resultSet.getString("qOne");
                    String qTwo = resultSet.getString("qTwo");
                    String qThree = resultSet.getString("qThree");
                    String qFour = resultSet.getString("qFour");
                    String qFive = resultSet.getString("qFive");
                    String role = resultSet.getString("role");
                    String rolePoints = resultSet.getString("rolePoints");
                    String rating = resultSet.getString("rating");
                    byte[] beginningImgBytes = resultSet.getBytes("beginningImg");
                    byte[] middleImgBytes = resultSet.getBytes("middleImg");
                    byte[] endImgBytes = resultSet.getBytes("endImg");
                    Timestamp submitted_at = resultSet.getTimestamp("submitted_at");

                    ImageIcon beginningImg = controller.convertBytesToImageIcon(beginningImgBytes);
                    ImageIcon middleImg = controller.convertBytesToImageIcon(middleImgBytes);
                    ImageIcon endImg = controller.convertBytesToImageIcon(endImgBytes);

                    return new EvaluationForm(evalformID, userID, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg, submitted_at);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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