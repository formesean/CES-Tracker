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

    /**
     * Constructs a new DatabaseManager with the specified JDBC URL, username, and password.
     * @param jdbcUrl - The JDBC URL of the database.
     * @param jdbcUsername - The username for connecting to the database.
     * @param jdbcPassword - The password for connecting to the database.
     */
    public DatabaseManager(String jdbcUrl, String jdbcUsername, String jdbcPassword) {
        this.jdbcUrl = jdbcUrl;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    /**
     * Inserts user data into the database.
     * @param id - The user ID.
     * @param fullName - The full name of the user.
     * @param email - The email address of the user.
     * @param password - The password of the user.
     * @param idNumber - The ID number of the user.
     * @param type - The type of user.
     * @param cesPoints - The CES points of the user.
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

    /**
     * Inserts event data into the database.
     * @param eventID - The event ID.
     * @param eventName - The name of the event.
     * @param eventLocation - The location of the event.
     * @param eventDate - The date of the event.
     * @param startTime - The start time of the event.
     * @param endTime - The end time of the event.
     * @param eventType - The type of event.
     * @param eventMode - The mode of the event.
     * @param roles - The roles of the event.
     * @param rolePoints - The points associated with roles in the event.
     */
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

    /**
     * Inserts evaluation form data into the database.
     * @param evalformID - The evaluation form ID.
     * @param userID - The user ID associated with the evaluation form.
     * @param eventID - The event ID associated with the evaluation form.
     * @param qOne - The response to question one.
     * @param qTwo - The response to question two.
     * @param qThree - The response to question three.
     * @param qFour - The response to question four.
     * @param qFive - The response to question five.
     * @param role - The role associated with the evaluation form.
     * @param rolePoints - The points associated with the role in the evaluation form.
     * @param rating - The rating given in the evaluation form.
     * @param beginningImg - The image at the beginning of the event.
     * @param middleImg - The image in the middle of the event.
     * @param endImg - The image at the end of the event.
     */
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

    /**
     * Updates the CES points of a user in the database.
     * @param id - The user ID.
     * @param newCesPoints - The new CES points to be updated.
     */
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

    /**
     * Updates the user password in the database.
     * @param userId - The user ID for which to update the password.
     * @param encryptedPassword - The new encrypted password.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Archives an evaluation form by moving it to the archive table.
     * @param evalformID - The evaluation form ID.
     * @param userID - The user ID associated with the evaluation form.
     * @param eventID - The event ID associated with the evaluation form.
     * @param qOne - The response to question one.
     * @param qTwo - The response to question two.
     * @param qThree - The response to question three.
     * @param qFour - The response to question four.
     * @param qFive - The response to question five.
     * @param role - The role associated with the evaluation form.
     * @param rolePoints - The points associated with the role in the evaluation form.
     * @param rating - The rating given in the evaluation form.
     * @param beginningImg - The image at the beginning of the event.
     * @param middleImg - The image in the middle of the event.
     * @param endImg - The image at the end of the event.
     */
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

    /**
     * Deletes a user from the database.
     * @param id - The user ID to be deleted.
     */
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

    /**
     * Deletes an evaluation form from the database based on the provided evaluation form ID.
     * @param evalformID - The evaluation form ID to be deleted.
     */
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

    /**
     * Checks if an email already exists in the database.
     * @param email - The email address to be checked.
     * @returns True if the email exists; otherwise, false.
     * @throws if a database access error occurs.
     */
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

    /**
     * Checks if an ID number already exists in the database.
     * @param idNumber - The ID number to be checked.
     * @returns True if the ID number exists; otherwise, false.
     * @throws if a database access error occurs.
     */
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

    /**
     * Checks if an email belongs to an admin.
     * @param email - The email address to be checked.
     * @returns True if the email belongs to an admin; otherwise, false.
     */
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

    /**
     * Authenticates a user by comparing the provided password with the stored encrypted password.
     * @param email - The email address of the user.
     * @param password - The password to be authenticated.
     * @returns True if authentication is successful; otherwise, false.
     * @throws if a database access error occurs.
     */
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

    /**
     * Retrieves the user ID associated with the provided email.
     * @param email - The email address of the user.
     * @returns The user ID associated with the email.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves the CES points of a user based on the provided user ID.
     * @param id - The user ID.
     * @returns The CES points of the user.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves a User object representing the user with the provided user ID.
     * @param id - The user ID.
     * @returns A User object representing the user.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Sets the year level of a user based on the provided user ID.
     * @param id - The user ID.
     * @param yearLevel - The new year level to be set.
     * @throws if a database access error occurs.
     */
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

    /**
     * Retrieves a list of all students from the database.
     * @returns A list of Student objects representing all students in the database.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves a list of all users (both admins and students) from the database.
     * @returns A list of User objects representing all users in the database.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves a list of students from the database based on the provided year level.
     * @param yearLevel - The year level to filter the students.
     * @returns A list of Student objects representing students in the specified year level.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves the name of a user based on the provided user ID.
     * @param userID - The user ID for which to retrieve the name.
     * @returns The name of the user.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves an Event object representing an event with the provided event ID.
     * @param eventID - The event ID.
     * @returns An Event object representing the event.
     * @throws if an error occurs during database access.
     */
    public Event getEvent(String eventID) {
        try (Connection dbConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword)) {
            try (PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM events WHERE eventID = ?")) {
                statement.setString(1, eventID);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String eventName = resultSet.getString("eventName");
                    String eventLocation = resultSet.getString("eventLocation");
                    String eventDate = String.valueOf(resultSet.getDate("eventDate"));
                    String startTime = String.valueOf(resultSet.getTime("startTime"));
                    String endTime = String.valueOf(resultSet.getTime("endTime"));
                    String eventType = resultSet.getString("eventType");
                    String eventMode = resultSet.getString("eventMode");
                    String roles = resultSet.getString("roles");
                    String rolePoints = resultSet.getString("rolePoints");

                    return new Event(eventID, eventName, eventLocation, eventDate, startTime, endTime, eventType, eventMode, roles, rolePoints);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Retrieves the role points associated with a specific role in an event.
     * @param eventID - The event ID.
     * @param role - The role for which to retrieve the points.
     * @returns The role points.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves a list of all events from the database.
     * @returns A list of Event objects representing all events in the database.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves a list of all evaluation form data from the database.
     * @returns A list of EvaluationForm objects representing all evaluation form data in the database.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves a list of evaluation form data from the user's archive based on the provided user ID.
     * @param userID - The user ID.
     * @returns A list of EvaluationForm objects representing evaluation form data in the user's archive.
     * @throws if an error occurs during database access.
     */
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

    /**
     * Retrieves evaluation form data for a specific evaluation form ID.
     * @param evalformID - The evaluation form ID.
     * @returns An EvaluationForm object representing the evaluation form data.
     * @throws if an error occurs during database access.
     */
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
}