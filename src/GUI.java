import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

public class GUI {
    private JPanel MainWindow;
    private JPanel MainPanel;
    private JPanel SidePanel;
    private JTextField fullNameTextField;
    private JPasswordField passwordPasswordField;
    private JButton signUpButton;
    private JButton goToLogIn;
    private JTextField emailTextField;
    private JTextField idNumberTextField;
    private JPanel SignUp;
    private JPanel LogIn;
    private JLabel SignUpTitle;
    private JLabel LogInTitle;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton goToSignUp;
    private JPanel Profile;
    private JTable table1;
    private JPanel OnBoardingSP;
    private JPanel StudentSP;
    private JButton button1;
    private JButton button2;
    private JButton logInBtn;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JPanel AdminSP;
    private JButton StudentLogout;
    private JButton AdminLogout;

    private DatabaseManager databaseManager;
    private Controller controller;

    GUI() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();
        performAutoLogin();

        JFrame frame = new JFrame("CES TRACKER");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 650);
        frame.setResizable(false);

        frame.add(MainWindow);

        LogIn.setVisible(false);
        SignUpTitle.setVisible(false);
        OnBoardingSP.setVisible(false);
        frame.setVisible(true);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fullName = fullNameTextField.getText();
                String email = emailTextField.getText();
                String password = String.valueOf(passwordPasswordField.getPassword());
                int idNumber = Integer.parseInt(idNumberTextField.getText());
                String type = databaseManager.isAdminEmail(email) ? "Admin" : "Student";

                databaseManager.insertUserData(fullName, email, password, idNumber, type, 0);
                clearTextFields();
            }
        });

        goToLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextFields();
                SignUp.setVisible(false);
                LogIn.setVisible(true);
                SignUpTitle.setVisible(false);
                LogInTitle.setVisible(true);
            }
        });

        goToSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextFields();
                SignUp.setVisible(true);
                LogIn.setVisible(false);
                SignUpTitle.setVisible(true);
                LogInTitle.setVisible(false);
            }
        });

        logInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                try {
                    if (databaseManager.isEmailExists(email)) {
                        // Email exists, now check the password
                        if (databaseManager.authenticateUser(email, password)) {
                            String userType = databaseManager.getUserType(email);

                            if ("Admin".equals(userType)) {
                                OnBoardingSP.setVisible(false);
                                StudentSP.setVisible(false);
                                AdminSP.setVisible(true);
                                LogIn.setVisible(false);
                                Profile.setVisible(true);

                                JOptionPane.showMessageDialog(null, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                controller.writeLoggedUserToFile(email);
                            } else if ("Student".equals(userType)) {
                                OnBoardingSP.setVisible(false);
                                AdminSP.setVisible(false);
                                StudentSP.setVisible(true);
                                LogIn.setVisible(false);
                                Profile.setVisible(true);

                                JOptionPane.showMessageDialog(null, "Student login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                controller.writeLoggedUserToFile(email);
                            } else {
                                JOptionPane.showMessageDialog(null, "Unknown user type. Please contact support.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Email not found. Please sign up or try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to authenticate user.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                clearTextFields();
            }
        });

        ActionListener logoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpTitle.setVisible(false);
                OnBoardingSP.setVisible(true);
                AdminSP.setVisible(false);
                StudentSP.setVisible(false);
                LogIn.setVisible(true);
                Profile.setVisible(false);

                try {
                    FileWriter writer = new FileWriter("loggedUser.txt");
                    writer.write("");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        AdminLogout.addActionListener(logoutListener);
        StudentLogout.addActionListener(logoutListener);
    }

    private void performAutoLogin() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("loggedUser.txt"));
            String email = reader.readLine();
            reader.close();

            if (email != null && !email.isEmpty()) {
                if (databaseManager.isEmailExists(email)) {
                    String userType = databaseManager.getUserType(email);

                    if ("Admin".equals(userType)) {
                        OnBoardingSP.setVisible(false);
                        StudentSP.setVisible(false);
                        AdminSP.setVisible(true);
                        SignUp.setVisible(false);
                        Profile.setVisible(true);
                    } else if ("Student".equals(userType)) {
                        OnBoardingSP.setVisible(false);
                        AdminSP.setVisible(false);
                        StudentSP.setVisible(true);
                        SignUp.setVisible(false);
                        Profile.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Unknown user type. Please contact support.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearTextFields() {
        fullNameTextField.setText("");
        passwordPasswordField.setText("");
        emailTextField.setText("");
        idNumberTextField.setText("");
        passwordField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
