import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String USER_DATA_PATH = "user_data.txt";
    private static final String ADMIN_DATA_PATH = "admins.txt";
    private static final String LOGGED_USERS_FILE_PATH = "loggedUser.txt";
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    private static void signUp(Scanner scanner) {
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your ID number: ");
        int idNumber = scanner.nextInt();
        scanner.nextLine();

        String type = isEmailAdmin(email) ? "Admin" : "Student";

        User newUser = new User(generateUniqueID(), fullName, email, password, idNumber, type, 0);
        users.add(newUser);

        System.out.println("Sign up successful! Your unique ID is: " + newUser.getUniqueID());
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome " + user.getType() + ", " + user.getFullName());
                return;
            }
        }

        System.out.println("Login failed. Please check your email and password.");
    }

    private static void logout() {
        if (loggedInUser != null) {
            System.out.println("Logging out user: " + loggedInUser.getFullName());
            users.remove(loggedInUser);
            removeUserFromLoggedFile(loggedInUser);
            loggedInUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    private static boolean isEmailAdmin(String email) {
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

    private static void removeUserFromLoggedFile(User userToRemove) {
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

    private static void loadUsersFromFile() {
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

    private static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_PATH))) {
            for (User user : users) {
                String userData = String.format("%d,%s,%s,%s,%d,%s,%d",
                        user.getUniqueID(), user.getFullName(), user.getEmail(),
                        user.getPassword(), user.getIDNumber(), user.getType(), user.getCESPoints());
                writer.write(userData);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveLoggedUsersToFile() {
        if (loggedInUser != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGGED_USERS_FILE_PATH, true))) {
                String userData = String.format("%d,%s,%s,%s,%d,%s,%d",
                        loggedInUser.getUniqueID(), loggedInUser.getFullName(), loggedInUser.getEmail(),
                        loggedInUser.getPassword(), loggedInUser.getIDNumber(), loggedInUser.getType(), loggedInUser.getCESPoints());
                writer.write(userData);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception properly in a real-world scenario
            }
        }
    }

    private static int generateUniqueID() {
        return users.size() + 1;
    }

    public static void main(String[] args) {
        loadUsersFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Log Out");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    signUp(scanner);
                    saveUsersToFile();
                    break;
                case 2:
                    login(scanner);
                    saveLoggedUsersToFile();
                    break;
                case 3:
                    logout();
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
