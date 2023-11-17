import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

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
    private JButton signUpButton1;
    private JPanel Profile;
    private JTable table1;
    private JPanel OnBoardingSP;
    private JPanel StudentSP;
    private JButton button1;
    private JButton button2;
    private JButton logInBtn;

    private UserAuth userAuth = new UserAuth();

    GUI() {
        userAuth.loadUsersFromFile();
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
                int uniqueID = userAuth.generateUniqueID();
                String fullName = fullNameTextField.getText();
                String email = emailTextField.getText();
                String password = String.valueOf(passwordPasswordField.getPassword());
                int idNumber = Integer.parseInt(idNumberTextField.getText());
                String type = userAuth.isEmailAdmin(email) ? "Admin" : "Student";

                User newUser = new User(uniqueID, fullName, email, password, idNumber, type, 0);
                userAuth.users.add(newUser);

                if (userAuth.saveUsersToFile()) {
                    JOptionPane.showMessageDialog(null, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error adding user. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goToLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUp.setVisible(false);
                LogIn.setVisible(true);
                SignUpTitle.setVisible(false);
                LogInTitle.setVisible(true);
            }
        });

        signUpButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                for (User user : userAuth.users) {
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        userAuth.loggedInUser = user;
                        userAuth.saveLoggedUsersToFile();
                        userAuth.isLoggedIn = true;
                        break;
                    }
                }

                if (userAuth.isLoggedIn) {
                    StudentSP.setVisible(true);
                    Profile.setVisible(true);
                    OnBoardingSP.setVisible(false);
                    SignUp.setVisible(false);
                    LogIn.setVisible(false);
                    SignUpTitle.setVisible(false);
                    LogInTitle.setVisible(false);

                    JOptionPane.showMessageDialog(null, "Logged In successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error logging in. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
