import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class MainWindow extends JFrame {
    private JFrame frame;
    private JPanel SidePanel;
    private JLabel SignUpTitle;
    private JLabel LogInTitle;
    private JPanel MainPanel;
    private JPanel SignUp;
    private JTextField fullNameTextField;
    private JTextField emailTextField;
    private JTextField idNumberTextField;
    private JPasswordField passwordPasswordField;
    private JButton signUpButton;
    private JButton goToLogIn;
    private JPanel LogIn;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton logInBtn;
    private JButton goToSignUp;
    private JPanel Window;
    private JLabel picLabel;
    private JLabel picLabelLogIn;

    private DatabaseManager databaseManager;
    private Controller controller;

    private User loggedUser;
    private ImageIcon currentIcon;
    private Image currentImage;
    private Image resizedImage;
    private ImageIcon resizedIcon;
    MainWindow() {
        initializeWindow();
    }

    private void initializeWindow() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();
        int newWidth = 150;
        int newHeight = 150;

        currentIcon = (ImageIcon) picLabel.getIcon();
        currentImage = currentIcon.getImage();


        resizedImage = currentImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        picLabel.setIcon(resizedIcon);

        currentIcon = (ImageIcon) picLabelLogIn.getIcon();
        currentImage = currentIcon.getImage();

        resizedImage = currentImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        resizedIcon = new ImageIcon(resizedImage);
        picLabelLogIn.setIcon(resizedIcon);

        frame = new JFrame("CES TRACKER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350, 750);
        frame.add(Window);
        frame.setResizable(false);
        frame.setVisible(true);

        performAutoLogin();
        windowActions();
    }

    private void windowActions() {
        goToLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp.setVisible(false);
                LogIn.setVisible(true);
                SignUpTitle.setVisible(false);
                LogInTitle.setVisible(true);
            }
        });

        goToSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp.setVisible(true);
                LogIn.setVisible(false);
                SignUpTitle.setVisible(true);
                LogInTitle.setVisible(false);
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UUID uuid = UUID.randomUUID();

                String id = String.valueOf(uuid);
                String fullName = fullNameTextField.getText();
                String email = emailTextField.getText();
                String password = String.valueOf(passwordPasswordField.getPassword());
                int idNumber = Integer.parseInt(idNumberTextField.getText());
                String type = databaseManager.isAdminEmail(email) ? "Admin" : "Student";

                databaseManager.insertUserData(id, fullName, email, password, idNumber, type, 0);
            }
        });

        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String userID = databaseManager.getUserID(email);

                try {
                    if (databaseManager.isEmailExists(email)) {
                        if (databaseManager.authenticateUser(email, password)) {
                            loggedUser = databaseManager.getUser(userID);

                            String userType = loggedUser.getType();
                            controller.writeLoggedUserToFile(loggedUser.getUniqueID());

                            if ("Admin".equals(userType)) {
                                frame.dispose();
                                AdminWindow adminWindow = new AdminWindow(loggedUser);
                                adminWindow.updateLoggedInUserInfoLabels(loggedUser.getUniqueID());

                                JOptionPane.showMessageDialog(null, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else if ("Student".equals(userType)) {
                                frame.dispose();
                                StudentWindow studentWindow = new StudentWindow(loggedUser);
                                studentWindow.updateLoggedInUserInfoLabels(loggedUser.getUniqueID());

                                JOptionPane.showMessageDialog(null, "Student login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Unknown user type. Please contact support.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Email not found. Please sign up or try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
//                    clearTextFields();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to authenticate user.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void performAutoLogin() {
        try (BufferedReader reader = new BufferedReader(new FileReader("loggedUser.txt"));) {
            String userID = reader.readLine();

            if (userID != null && !userID.isEmpty()) {
                loggedUser = databaseManager.getUser(userID);

                if (loggedUser != null) {
                    String userType = loggedUser.getType();

                    if ("Admin".equals(userType)) {
                        frame.dispose();
                        AdminWindow adminWindow = new AdminWindow(loggedUser);
                        adminWindow.updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
                    } else if ("Student".equals(userType)) {
                        frame.dispose();
                        StudentWindow studentWindow = new StudentWindow(loggedUser);
                        studentWindow.updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
                    } else {
                        JOptionPane.showMessageDialog(null, "Unknown user type. Please contact support.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
