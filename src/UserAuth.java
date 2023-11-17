import java.io.*;
import java.util.ArrayList;

public class UserAuth {
    protected final String USER_DATA_PATH = "user_data.txt";
    protected final String ADMIN_DATA_PATH = "admins.txt";
    protected final String LOGGED_USERS_FILE_PATH = "loggedUser.txt";
    protected ArrayList<User> users = new ArrayList<>();
    protected User loggedInUser = null;
    protected boolean isLoggedIn = false;

    public boolean isEmailAdmin(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_DATA_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeUserFromLoggedFile(User userToRemove) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOGGED_USERS_FILE_PATH));
             BufferedWriter writer = new BufferedWriter(new FileWriter(LOGGED_USERS_FILE_PATH, false))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                int uniqueID = Integer.parseInt(userData[0]);

                if (uniqueID != userToRemove.getUniqueID()) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                int uniqueID = Integer.parseInt(userData[0]);
                String fullName = userData[1];
                String email = userData[2];
                String password = userData[3];
                int idNumber = Integer.parseInt(userData[4]);
                String type = userData[5];
                int cesPoints = Integer.parseInt(userData[6]);

                User user = new User(uniqueID, fullName, email, password, idNumber, type, cesPoints);
                users.add(user);
            }
        } catch (IOException e) {

        }
    }

    public boolean saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_PATH))) {
            for (User user : users) {
                String userData = String.format("%d,%s,%s,%s,%d,%s,%d",
                        user.getUniqueID(), user.getFullName(), user.getEmail(),
                        user.getPassword(), user.getIDNumber(), user.getType(), user.getCESPoints());
                writer.write(userData);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void saveLoggedUsersToFile() {
        if (loggedInUser != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGGED_USERS_FILE_PATH, true))) {
                String userData = String.format("%d,%s,%s,%s,%d,%s,%d",
                        loggedInUser.getUniqueID(), loggedInUser.getFullName(), loggedInUser.getEmail(),
                        loggedInUser.getPassword(), loggedInUser.getIDNumber(), loggedInUser.getType(), loggedInUser.getCESPoints());
                writer.write(userData);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int generateUniqueID() {
        return users.size() + 1;
    }
}
