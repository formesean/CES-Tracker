import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String USER_DATA_FILE = "user_data.txt";
    private static ArrayList<User> users = new ArrayList<>();

    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
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

    private static void signUp(Scanner scanner) {
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your ID number: ");
        int idNumber = scanner.nextInt();

        System.out.print("Enter your user type (Student/Admin): ");
        String type = scanner.next();

        // Assume cesPoints is 0 during signup
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
                System.out.println("Login successful! Welcome, " + user.getFullName());
                return;
            }
        }

        System.out.println("Login failed. Please check your email and password.");
    }

    private static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
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

    private static int generateUniqueID() {
        return users.size() + 1;
    }

    public static void main(String[] args) {
        loadUsersFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    saveUsersToFile();
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
