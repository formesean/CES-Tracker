import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

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
    private JPanel OnBoardingSP;
    private JPanel StudentSP;
    private JButton StudentProfileBtn;
    private JButton evaluationFormButton;
    private JButton logInBtn;
    private JButton dashboardButton;
    private JButton userManangementButton;
    private JButton approvalButton;
    private JButton AdminProfileBtn;
    private JPanel AdminSP;
    private JButton StudentLogout;
    private JButton AdminLogout;
    private JPanel UserInfo;
    private JPanel UserCES;
    private JButton changePass;
    private JLabel userName;
    private JLabel userType;
    private JLabel userCourse;
    private JLabel userPoints;
    private JPanel EvalForm;
    private JComboBox comboBox1;
    private JTextField qOneTF;
    private JTextField qTwoTF;
    private JButton beginningButton;
    private JButton middleButton;
    private JButton endButton;
    private JLabel beginningImage;
    private JLabel middleImage;
    private JLabel endImage;
    private JButton submitEvalFormBtn;
    private JLabel bImg;
    private JLabel mImg;
    private JLabel eImg;
    private JPanel UserManagement;
    private JTable umTable;
    private JButton setYrLvlBtn;

    private DefaultTableModel umTableModel;
    private ScheduledExecutorService dataUpdater;

    private DatabaseManager databaseManager;
    private Controller controller;
    private List<EvaluationForm> evalFormDataList;
    private List<User> users;
    private User loggedUser;

    GUI() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();

        JFrame frame = new JFrame("CES TRACKER");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 650);
        frame.setResizable(false);

        frame.add(MainWindow);

        umTableModel = new DefaultTableModel();
        umTableModel.addColumn("Full Name");
        umTableModel.addColumn("Email");
        umTableModel.addColumn("ID Number");
        umTableModel.addColumn("CES Points");

        umTable.setDefaultEditor(Object.class, null);
        umTable.getTableHeader().setReorderingAllowed(false);
        umTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        umTable.setModel(umTableModel);

        SignUpTitle.setVisible(false);
        OnBoardingSP.setVisible(false);
        LogIn.setVisible(false);
        Profile.setVisible(false);
        EvalForm.setVisible(false);
        UserManagement.setVisible(false);
        frame.setVisible(true);

        performAutoLogin();

        // fetch eval form
//        evalFormDataList = databaseManager.getAllEvalFormData();
//        if (!evalFormDataList.isEmpty()) {
//            EvaluationForm firstEvalFormData = evalFormDataList.get(0);
//            bImg.setIcon(firstEvalFormData.getBeginningImg());
//        } else {
//            bImg.setIcon(null);
//        }

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
                String userID = databaseManager.getUserID(email);

                try {
                    if (databaseManager.isEmailExists(email)) {
                        if (databaseManager.authenticateUser(email, password)) {
                            loggedUser = databaseManager.getUser(userID);
                            updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
                            String userType = loggedUser.getType();

                            if ("Admin".equals(userType)) {
                                OnBoardingSP.setVisible(false);
                                StudentSP.setVisible(false);
                                AdminSP.setVisible(true);
                                LogIn.setVisible(false);
                                Profile.setVisible(true);

                                JOptionPane.showMessageDialog(null, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else if ("Student".equals(userType)) {
                                OnBoardingSP.setVisible(false);
                                AdminSP.setVisible(false);
                                StudentSP.setVisible(true);
                                LogIn.setVisible(false);
                                EvalForm.setVisible(false);
                                Profile.setVisible(true);

                                JOptionPane.showMessageDialog(null, "Student login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Unknown user type. Please contact support.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            controller.writeLoggedUserToFile(loggedUser.getUniqueID());
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Email not found. Please sign up or try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    clearTextFields();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to authenticate user.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        StudentProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(true);
                EvalForm.setVisible(false);
            }
        });

        AdminProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManagement.setVisible(false);

                Profile.setVisible(true);
            }
        });

        evaluationFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EvalForm.setVisible(true);
                Profile.setVisible(false);
            }
        });

        submitEvalFormBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("loggedUser.txt"));
                    String id = reader.readLine();
                    reader.close();

                    UUID uuid = UUID.randomUUID();

                    String evalformID = String.valueOf(uuid);
                    String qOne = qOneTF.getText();
                    String qTwo = qTwoTF.getText();
                    ImageIcon beginningImg = (ImageIcon) beginningImage.getIcon();
                    ImageIcon middleImg = (ImageIcon) middleImage.getIcon();
                    ImageIcon endImg = (ImageIcon) endImage.getIcon();
                    if (beginningImg != null && middleImg != null && endImg != null) {
                        databaseManager.insertEvalForm(evalformID, id, evalformID, qOne, qTwo, beginningImg, middleImg, endImg);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select images for all three categories.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        });

        userManangementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(false);

                UserManagement.setVisible(true);

                umTable.setModel(umTableModel);
                users = databaseManager.getAllUser();
                umTableModel.setRowCount(0);

                for (User user : users) {
                    umTableModel.addRow(new Object[]{user.getFullName(), user.getEmail(), user.getIDNumber(), user.getCESPoints()});
                }
            }
        });

        setYrLvlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedUser != null && "Student".equals(loggedUser.getType())) {
                    String[] yearOptions = {"Year 1", "Year 2", "Year 3", "Year 4", "Batch X"};

                    String selectedOption = (String) JOptionPane.showInputDialog(null, "Select Year Level", "Year Level Selection",
                            JOptionPane.QUESTION_MESSAGE, null, yearOptions, yearOptions[0]);

                    if (selectedOption != null) {
                        ((Student) loggedUser).setYearLevel(selectedOption);
                        databaseManager.setYearLevel(loggedUser.getUniqueID(), selectedOption);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Year level can only be set for students.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField passwordField = new JPasswordField();
                JComponent[] inputs = new JComponent[] {
                        new JLabel("Enter your new password:"),
                        passwordField
                };

                int result = JOptionPane.showConfirmDialog(null, inputs, "Change Password", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    char[] passwordChars = passwordField.getPassword();
                    String newPassword = new String(passwordChars);

                    if (!newPassword.isEmpty()) {
                        String encryptedPassword = Controller.encryptPassword(newPassword);
                        databaseManager.changePassword(loggedUser.getUniqueID(), encryptedPassword);
                    } else {
                        JOptionPane.showMessageDialog(null, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Password change canceled.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        ActionListener logoutListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpTitle.setVisible(false);
                AdminSP.setVisible(false);
                StudentSP.setVisible(false);
                Profile.setVisible(false);

                OnBoardingSP.setVisible(true);
                EvalForm.setVisible(false);
                LogIn.setVisible(true);

                try {
                    FileWriter writer = new FileWriter("loggedUser.txt");
                    writer.write("");
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };

        ActionListener selectImage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceBtn = (JButton) e.getSource();

                JFileChooser chooser = new JFileChooser(".");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int response = chooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();

                    try {
                        BufferedImage img = ImageIO.read(selectedFile);
                        Image resizedImage = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(resizedImage);

                        if (sourceBtn == beginningButton) {
                            beginningImage.setIcon(icon);
                        } else if (sourceBtn == middleButton) {
                            middleImage.setIcon(icon);
                        } else if (sourceBtn == endButton) {
                            endImage.setIcon(icon);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        AdminLogout.addActionListener(logoutListener);
        StudentLogout.addActionListener(logoutListener);
        beginningButton.addActionListener(selectImage);
        middleButton.addActionListener(selectImage);
        endButton.addActionListener(selectImage);
    }

    private void performAutoLogin() {
        try (BufferedReader reader = new BufferedReader(new FileReader("loggedUser.txt"));) {
            String userID = reader.readLine();

            if (userID != null && !userID.isEmpty()) {
                loggedUser = databaseManager.getUser(userID);

                if (loggedUser != null) {
                    updateLoggedInUserInfoLabels(userID);
                    String userType = loggedUser.getType();

                    SignUp.setVisible(false);
                    LogIn.setVisible(false);
                    EvalForm.setVisible(false);
                    OnBoardingSP.setVisible(false);
                    Profile.setVisible(true);

                    if ("Admin".equals(userType)) {
                        StudentSP.setVisible(false);
                        AdminSP.setVisible(true);
                    } else if ("Student".equals(userType)) {
                        AdminSP.setVisible(false);
                        StudentSP.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Unknown user type. Please contact support.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (IOException e) {
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

    private void updateLoggedInUserInfoLabels(String id) {
        User userProfile = databaseManager.getUser(id);

        if (userProfile != null) {
            String name = userProfile.getFullName();
            String type = userProfile.getType();
            int cesPoints = userProfile.getCESPoints();

            userName.setText(name);
            userType.setText(type);

            if ("Student".equals(userProfile.getType())) {
                userCourse.setText("BS Computer Engineering - " + ((Student) userProfile).getYearLevel());
            } else {
                userCourse.setText("BS Computer Engineering");
            }

            userPoints.setText(String.valueOf(cesPoints));
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
            clearTextFields();
        }
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
