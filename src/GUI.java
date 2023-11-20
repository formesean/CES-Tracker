import com.raven.datechooser.DateChooser;
import com.raven.swing.TimePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

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
    private JComboBox eventsBox;
    private JTextField q1TxtF;
    private JTextField q2TxtF;
    private JButton beginningButton;
    private JButton middleButton;
    private JButton endButton;
    private JLabel beginningImage;
    private JLabel middleImage;
    private JLabel endImage;
    private JButton submitEvalFormBtn;
    private JPanel UserManagement;
    private JTable umTable;
    private JButton setYrLvlBtn;
    private JButton year1Button;
    private JButton year2Button;
    private JButton year3Button;
    private JButton year4Button;
    private JButton batchXButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton allButton;
    private JTextField searchField;
    private JPanel Dashboard;
    private JTextField eventNameTxtF;
    private JTextField eventLocTxtF;
    private JTextField eventTypeTxtF;
    private JTextField eventModeTxtF;
    private JButton addEventButton;
    private JTextField dateField;
    private JTextField startTimeField;
    private JButton startTimeButton;
    private JTextField endTimeField;
    private JButton endTimeButton;
    private JTable eventsTable;
    private JTextField q3TxtF;
    private JTextField q4TxtF;
    private JTextField q5TxtF;
    private JRadioButton angryRadioButton;
    private JRadioButton disappointedRadioButton;
    private JRadioButton neutralRadioButton;
    private JRadioButton goodRadioButton;
    private JRadioButton loveEmojisRadioButton;
    private JScrollPane efScrollPane;
    private JPanel Approval;
    private JTable approvalTable;
    private JButton viewFormButton;
    private JPanel ViewEvalForm;
    private JButton goBackButton;
    private JButton approveButton;
    private JButton declineButton;
    private JTextField veQ1;
    private JTextField veQ2;
    private JTextField veQ3;
    private JTextField veQ4;
    private JTextField veQ5;
    private JLabel eventTitleField;
    private JLabel bImg;
    private JLabel mImg;
    private JLabel eImg;
    private JRadioButton veR1;
    private JRadioButton veR2;
    private JRadioButton veR3;
    private JRadioButton veR4;
    private JRadioButton veR5;
    private JScrollPane veScrollPane;

    private DefaultTableModel umTableModel;
    private DefaultTableModel eventsTableModel;
    private DefaultTableModel approvalTableModel;
    private DateChooser datePicker = new DateChooser();
    private TimePicker timePicker = new TimePicker();
    private ButtonGroup radioBtnGroup;
    private ButtonGroup veBtnGroup;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DatabaseManager databaseManager;
    private Controller controller;
    private List<User> users;
    private List<Event> events;
    private List<EvaluationForm> evalFormDataList;
    private User loggedUser;

    GUI() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();

        JFrame frame = new JFrame("CES TRACKER");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1050, 650);
        frame.setResizable(false);

        frame.add(MainWindow);

        datePicker.setLabelCurrentDayVisible(false);
        datePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        datePicker.setTextField(dateField);

        umTableModel = new DefaultTableModel();
        umTableModel.addColumn("Full Name");
        umTableModel.addColumn("Email");
        umTableModel.addColumn("Year Level");
        umTableModel.addColumn("ID Number");
        umTableModel.addColumn("CES Points");

        umTable.setDefaultEditor(Object.class, null);
        umTable.getTableHeader().setReorderingAllowed(false);
        umTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        umTable.setModel(umTableModel);

        eventsTableModel = new DefaultTableModel();
        eventsTableModel.addColumn("Name");
        eventsTableModel.addColumn("Location");
        eventsTableModel.addColumn("Date");
        eventsTableModel.addColumn("Type");

        eventsTable.setDefaultEditor(Object.class, null);
        eventsTable.getTableHeader().setReorderingAllowed(false);
        eventsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        eventsTable.setModel(eventsTableModel);

        approvalTableModel = new DefaultTableModel();
        approvalTableModel.addColumn("Student Name");
        approvalTableModel.addColumn("Event Name");
        approvalTableModel.addColumn("Submission Date");

        approvalTable.setDefaultEditor(Object.class, null);
        approvalTable.getTableHeader().setReorderingAllowed(false);
        approvalTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        approvalTable.setModel(approvalTableModel);

        radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(angryRadioButton);
        radioBtnGroup.add(disappointedRadioButton);
        radioBtnGroup.add(neutralRadioButton);
        radioBtnGroup.add(goodRadioButton);
        radioBtnGroup.add(loveEmojisRadioButton);

        veBtnGroup = new ButtonGroup();
        veBtnGroup.add(veR1);
        veBtnGroup.add(veR2);
        veBtnGroup.add(veR3);
        veBtnGroup.add(veR4);
        veBtnGroup.add(veR5);

        efScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        veScrollPane.getVerticalScrollBar().setUnitIncrement(20);

        SignUpTitle.setVisible(false);
        OnBoardingSP.setVisible(false);
        LogIn.setVisible(false);
        Profile.setVisible(false);
        EvalForm.setVisible(false);
        Dashboard.setVisible(false);
        UserManagement.setVisible(false);
        Approval.setVisible(false);
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
            /**
             * Performs user account creation when the button is clicked.
             * Generates a unique ID, collects user input, and inserts data into the database.
             *
             * @param e The ActionEvent triggered by clicking the "Sign Up" button.
             */
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
            /**
             * Switches to the login panel when the button is clicked.
             * Clears input fields for a fresh login attempt.
             *
             * @param e The ActionEvent triggered by clicking the button.
             */
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
            /**
             * Switches to the signup panel when the button is clicked.
             * Clears input fields for a fresh registration attempt.
             *
             * @param e The ActionEvent triggered by clicking the button.
             */
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
            /**
             * Handles user login when the "Log In" button is clicked.
             * Authenticates user credentials, switches to the appropriate panel,
             * and displays success or error messages.
             *
             * @param e The ActionEvent triggered by clicking the "Log In" button.
             */
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
                                LogIn.setVisible(false);
                                EvalForm.setVisible(false);
                                Dashboard.setVisible(false);
                                UserManagement.setVisible(false);

                                AdminSP.setVisible(true);
                                Profile.setVisible(true);

                                JOptionPane.showMessageDialog(null, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else if ("Student".equals(userType)) {
                                OnBoardingSP.setVisible(false);
                                AdminSP.setVisible(false);
                                LogIn.setVisible(false);
                                EvalForm.setVisible(false);
                                Dashboard.setVisible(false);
                                UserManagement.setVisible(false);

                                StudentSP.setVisible(true);
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

                events = databaseManager.getAllEvents();
                eventsBox.removeAllItems();
                eventsBox.addItem("Select Event Name");

                for (Event event : events) {
                    eventsBox.addItem(event.getName());
                }

                eventsBox.setSelectedIndex(0);
            }
        });

        submitEvalFormBtn.addActionListener(new ActionListener() {
            /**
             * Handles the submission of the evaluation form when the "Submit" button is clicked.
             * Reads user input, retrieves logged-in user information, and inserts the evaluation form data into the database.
             * Displays an error message if any required information is missing.
             *
             * @param e The ActionEvent triggered by clicking the "Submit" button.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("loggedUser.txt"));
                    String id = reader.readLine();
                    reader.close();

                    UUID uuid = UUID.randomUUID();
                    String selectedEventName = (String) eventsBox.getSelectedItem();

                    String evalformID = String.valueOf(uuid);
                    String eventID = getEventIDFromName(selectedEventName);
                    String qOne = q1TxtF.getText();
                    String qTwo = q2TxtF.getText();
                    String qThree = q3TxtF.getText();
                    String qFour = q4TxtF.getText();
                    String qFive = q5TxtF.getText();
                    String rating = getSelectedRadioValue();
                    ImageIcon beginningImg = (ImageIcon) beginningImage.getIcon();
                    ImageIcon middleImg = (ImageIcon) middleImage.getIcon();
                    ImageIcon endImg = (ImageIcon) endImage.getIcon();

                    if (beginningImg != null && middleImg != null && endImg != null) {
                        databaseManager.insertEvalForm(evalformID, id, eventID, qOne, qTwo, qThree, qFour, qFive, rating, beginningImg, middleImg, endImg);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select images for all three categories.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | RuntimeException ex) {
                    ex.printStackTrace();
                }
            }
        });

        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(false);
                UserManagement.setVisible(false);
                Approval.setVisible(false);
                Dashboard.setVisible(true);

                eventsTable.setModel(eventsTableModel);
                events = databaseManager.getAllEvents();
                eventsTableModel.setRowCount(0);

                for (Event event :events) {
                    eventsTableModel.addRow(new Object[]{event.getName(), event.getLocation(), event.getDate(), event.getType()});
                }
            }
        });

        startTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePicker.showPopup(null, 100, 100);
                timePicker.setDisplayText(startTimeField);
            }
        });

        endTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timePicker.showPopup(null, 100, 100);
                timePicker.setDisplayText(endTimeField);
            }
        });

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UUID uuid = UUID.randomUUID();

                String eventID = String.valueOf(uuid);
                String eventName = eventNameTxtF.getText();
                String eventLocation = eventLocTxtF.getText();
                String eventDate = dateField.getText();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String eventType = eventTypeTxtF.getText();
                String eventMode = eventModeTxtF.getText();

                databaseManager.insertEventData(eventID, eventName, eventLocation, eventDate, startTime, endTime, eventType, eventMode);
            }
        });

        userManangementButton.addActionListener(new ActionListener() {
            /**
             * Handles the navigation to the User Management panel when the "User Management" button is clicked.
             * Retrieves user data from the database and populates the User Management table.
             *
             * @param e The ActionEvent triggered by clicking the "User Management" button.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(false);
                Dashboard.setVisible(false);
                Approval.setVisible(false);
                UserManagement.setVisible(true);

                umTable.setModel(umTableModel);
                users = databaseManager.getAllStudents();
                umTableModel.setRowCount(0);

                for (User user : users) {
                    umTableModel.addRow(new Object[]{user.getFullName(), user.getEmail(), user.getIDNumber(), ((Student) user).getYearLevel(), user.getCESPoints()});
                }

                umTableModel.fireTableDataChanged();
                umTable.repaint();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = umTable.getSelectedRow();

                if (selectedRow != -1) {
                    User selectedUser = users.get(selectedRow);
                    String selectedUserID = selectedUser.getUniqueID();

                    String newCESPointsString = JOptionPane.showInputDialog(null, "Enter new CES Points for " + selectedUser.getFullName() + ":");

                    if (newCESPointsString != null && newCESPointsString.matches("\\d+")) {
                        int newCESPoints = Integer.parseInt(newCESPointsString);

                        databaseManager.updateUserCESPoints(selectedUserID, newCESPoints);

                        umTableModel.setValueAt(newCESPoints, selectedRow, umTableModel.findColumn("CES Points"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer for CES Points.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = umTable.getSelectedRow();

                if (selectedRow != -1) {
                    User selectedUser = users.get(selectedRow);

                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (dialogResult == JOptionPane.YES_OPTION) {
                        databaseManager.deleteUser(selectedUser.getUniqueID());

                        umTableModel.removeRow(selectedRow);
                        umTable.clearSelection();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();

                umTable.setModel(umTableModel);
                users = databaseManager.getAllUser();
                umTableModel.setRowCount(0);

                for (User user : users) {
                    if ("Student".equals(user.getType())) {
                        String fullName = user.getFullName();
                        String email = user.getEmail();
                        String idNumber = String.valueOf(user.getIDNumber());

                        if (fullName.contains(searchText) || email.contains(searchText) || idNumber.contains(searchText)) {
                            umTableModel.addRow(new Object[]{user.getFullName(), user.getEmail(), user.getIDNumber(), ((Student) user).getYearLevel(), user.getCESPoints()});
                        }
                    }
                }

                umTableModel.fireTableDataChanged();
                umTable.repaint();
            }
        });

        approvalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(false);
                Dashboard.setVisible(false);
                UserManagement.setVisible(false);
                Approval.setVisible(true);

                approvalTable.setModel(approvalTableModel);
                evalFormDataList = databaseManager.getAllEvalFormData();
                approvalTableModel.setRowCount(0);

                for (EvaluationForm evalFormData : evalFormDataList) {
                    String userName = databaseManager.getUserName(evalFormData.getUserID());
                    String eventName = databaseManager.getEventName(evalFormData.getEventID());
                    String submitted_atDate = dateFormat.format(evalFormData.getSubmitted_at());

                    approvalTableModel.addRow(new Object[]{userName, eventName, submitted_atDate});
                }

                approvalTableModel.fireTableDataChanged();
                approvalTable.repaint();
            }
        });

        viewFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = approvalTable.getSelectedRow();

                if (selectedRow != -1) {
                    EvaluationForm selectedForm = evalFormDataList.get(selectedRow);
                    String selectedFormID = selectedForm.getEvalformID();
                    EvaluationForm viewedForm = databaseManager.getEvalFormData(selectedFormID);

                    Approval.setVisible(false);
                    ViewEvalForm.setVisible(true);

                    eventTitleField.setText(databaseManager.getEventName(viewedForm.getEventID()));
                    veQ1.setText(viewedForm.getQOne());
                    veQ2.setText(viewedForm.getQTwo());
                    veQ3.setText(viewedForm.getQThree());
                    veQ4.setText(viewedForm.getQFour());
                    veQ5.setText(viewedForm.getQFive());
                    setRatingInButtonGroup(viewedForm.getRating());
                    bImg.setIcon(viewedForm.getBeginningImg());
                    mImg.setIcon(viewedForm.getMiddleImg());
                    eImg.setIcon(viewedForm.getEndImg());

                    eventTitleField.setEnabled(false);
                    veQ1.setEditable(false);
                    veQ2.setEditable(false);
                    veQ3.setEditable(false);
                    veQ4.setEditable(false);
                    veQ5.setEditable(false);

                    veR1.setEnabled(false);
                    veR2.setEnabled(false);
                    veR3.setEnabled(false);
                    veR4.setEnabled(false);
                    veR5.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to view.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEvalForm.setVisible(false);
                Approval.setVisible(true);
            }
        });

        setYrLvlBtn.addActionListener(new ActionListener() {
            /**
             * Handles setting the year level for a student when the "Set Year Level" button is clicked.
             * Displays a dialog for year level selection and updates the user's year level in the database.
             *
             * @param e The ActionEvent triggered by clicking the "Set Year Level" button.
             */
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
            /**
             * Handles the change password functionality when the "Change Password" button is clicked.
             * Prompts the user for a new password and updates the password in the database.
             *
             * @param e The ActionEvent triggered by clicking the "Change Password" button.
             */
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
            /**
             * Handles user logout when the "Logout" button is clicked.
             * Switches panels to the login screen, clears the logged-in user data, and resets the stored user file.
             *
             * @param e The ActionEvent triggered by clicking the "Logout" button.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpTitle.setVisible(false);
                AdminSP.setVisible(false);
                StudentSP.setVisible(false);
                Profile.setVisible(false);
                EvalForm.setVisible(false);
                Dashboard.setVisible(false);
                UserManagement.setVisible(false);
                Approval.setVisible(false);

                OnBoardingSP.setVisible(true);
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
            /**
             * Handles image selection when one of the image selection buttons (Beginning, Middle, End) is clicked.
             * Displays a file chooser, allowing the user to select an image file.
             * Resizes the selected image and sets it as an icon for the corresponding image label.
             *
             * @param e The ActionEvent triggered by clicking one of the image selection buttons.
             */
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

                        if (sourceBtn == beginningButton && beginningImage != null) {
                            beginningImage.setIcon(icon);
                        } else if (sourceBtn == middleButton && middleImage != null) {
                            middleImage.setIcon(icon);
                        } else if (sourceBtn == endButton && endImage != null) {
                            endImage.setIcon(icon);
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };

        ActionListener yearLevelFilterListener = new ActionListener() {
            /**
             * Handles filtering the User Management table to show only users with a specific userYearLevel
             * when a year level button is clicked. If the "All" button is clicked, it shows all students.
             *
             * @param e The ActionEvent triggered by clicking a year level button.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(false);
                UserManagement.setVisible(true);

                JButton sourceButton = (JButton) e.getSource();
                String yearLevel = sourceButton.getText();

                umTable.setModel(umTableModel);
                users = databaseManager.getAllStudents();
                umTableModel.setRowCount(0);

                if ("All".equals(yearLevel)) {
                    users = databaseManager.getAllStudents();
                } else {
                    users = databaseManager.getStudentsByYearLevel(yearLevel);
                }

                umTableModel.setRowCount(0);

                for (User user : users) {
                    umTableModel.addRow(new Object[]{user.getFullName(), user.getEmail(), user.getIDNumber(), ((Student) user).getYearLevel(), user.getCESPoints()});
                }

                umTableModel.fireTableDataChanged();
                umTable.repaint();
            }
        };

        AdminLogout.addActionListener(logoutListener);
        StudentLogout.addActionListener(logoutListener);
        beginningButton.addActionListener(selectImage);
        middleButton.addActionListener(selectImage);
        endButton.addActionListener(selectImage);
        allButton.addActionListener(yearLevelFilterListener);
        year1Button.addActionListener(yearLevelFilterListener);
        year2Button.addActionListener(yearLevelFilterListener);
        year3Button.addActionListener(yearLevelFilterListener);
        year4Button.addActionListener(yearLevelFilterListener);
        batchXButton.addActionListener(yearLevelFilterListener);
    }

    /**
     * Performs automatic login if a user is logged in.
     * Reads the stored user file, retrieves the user's ID, and displays the appropriate panels based on the user type.
     * Handles the transition to the main profile screen for Admin or Student users.
     */
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

    /**
     * Updates the labels in the profile with the logged-in user's information.
     *
     * @param id The unique ID of the logged-in user.
     */
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

    private String getEventIDFromName(String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event.getUniqueID();
            }
        }
        return null;
    }

    private String getSelectedRadioValue() {
        if (angryRadioButton.isSelected()) {
            return "Angry";
        } else if (disappointedRadioButton.isSelected()) {
            return "Disappointed";
        } else if (neutralRadioButton.isSelected()) {
            return "Neutral";
        } else if (goodRadioButton.isSelected()) {
            return "Good";
        } else if (loveEmojisRadioButton.isSelected()) {
            return "Love Emojis";
        }
        return null;
    }

    private void setRatingInButtonGroup(String rating) {
        switch (rating) {
            case "Angry":
                veBtnGroup.setSelected(veR1.getModel(), true);
                break;
            case "Disappointed":
                veBtnGroup.setSelected(veR2.getModel(), true);
                break;
            case "Neutral":
                veBtnGroup.setSelected(veR3.getModel(), true);
                break;
            case "Good":
                veBtnGroup.setSelected(veR4.getModel(), true);
                break;
            case "Love Emojis":
                veBtnGroup.setSelected(veR5.getModel(), true);
                break;
            default:
                break;
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
