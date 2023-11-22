import com.raven.datechooser.DateChooser;
import com.raven.swing.TimePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class AdminWindow {
    private JPanel SidePanel;
    private JPanel MainPanel;
    private JPanel AdminSP;
    private JButton dashboardButton;
    private JButton userManangementButton;
    private JButton approvalButton;
    private JButton AdminProfileBtn;
    private JButton AdminLogout;
    private JPanel Dashboard;
    private JPanel dashboardLeftPanel;
    private JTextField eventNameTxtF;
    private JTextField eventLocTxtF;
    private JTextField eventModeTxtF;
    private JButton addEventButton;
    private JTextField eventTypeTxtF;
    private JTextField startTimeField;
    private JButton startTimeButton;
    private JTextField dateField;
    private JTextField endTimeField;
    private JButton endTimeButton;
    private JPanel role1Panel;
    private JTextField role1Field;
    private JTextField role1Points;
    private JPanel role2Panel;
    private JTextField role2Field;
    private JTextField role2Points;
    private JTable eventsTable;
    private JPanel UserManagement;
    private JTable umTable;
    private JButton allButton;
    private JButton year1Button;
    private JButton year2Button;
    private JButton year3Button;
    private JButton year4Button;
    private JButton batchXButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton viewHistoryButton;
    private JTextField searchField;
    private JPanel Window;
    private JPanel Approval;
    private JTable approvalTable;
    private JButton viewFormButton;
    private JPanel Profile;
    private JPanel UserInfo;
    private JLabel userName;
    private JLabel userType;
    private JLabel userCourse;
    private JButton setYrLvlBtn;
    private JButton changePass;
    private JPanel UserCES;
    private JLabel userPoints;
    private JPanel UserHistory;
    private JTable profileHistoryTable;
    private JPanel ViewEvalForm;
    private JScrollPane veScrollPane;
    private JTextField veQ1;
    private JTextField veQ2;
    private JTextField veQ3;
    private JTextField veQ4;
    private JTextField veQ5;
    private JRadioButton veR1;
    private JRadioButton veR2;
    private JRadioButton veR3;
    private JRadioButton veR4;
    private JRadioButton veR5;
    private JButton goBackApproval;
    private JButton declineButton;
    private JButton approveButton;
    private JLabel eventTitleField;
    private JLabel bImg;
    private JLabel mImg;
    private JLabel eImg;
    private JLabel roleTitleField;
    private JPanel ViewHistory;
    private JTable userHistoryTable;
    private JButton goBackUMButton;

    private DatabaseManager databaseManager;
    private Controller controller;

    private User loggedUser;

    private List<User> users;
    private List<Event> events;
    private List<EvaluationForm> evalFormDataList;
    private List<EvaluationForm> evalformArchive;

    private DefaultTableModel umTableModel;
    private DefaultTableModel eventsTableModel;
    private DefaultTableModel approvalTableModel;
    private DefaultTableModel profileHistoryTableModel;
    private DefaultTableModel userHistoryTableModel;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private DateChooser datePicker = new DateChooser();
    private TimePicker timePicker = new TimePicker();
    private ButtonGroup veBtnGroup;

    AdminWindow(User loggedUser) {
        this.loggedUser = loggedUser;
        initializeWindow();
    }

    AdminWindow() {
        initializeWindow();
    }

    private void initializeWindow() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();

        JFrame frame = new JFrame("CES TRACKER");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1350, 750);
        frame.add(Window);
        frame.setResizable(false);
        frame.setVisible(true);

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

        userHistoryTableModel = new DefaultTableModel();
        userHistoryTableModel.addColumn("Event Name");
        userHistoryTableModel.addColumn("Location");
        userHistoryTableModel.addColumn("Date");
        userHistoryTableModel.addColumn("Type");
        userHistoryTableModel.addColumn("Mode");
        userHistoryTableModel.addColumn("Role");
        userHistoryTableModel.addColumn("Points Gained");

        userHistoryTable.setDefaultEditor(Object.class, null);
        userHistoryTable.getTableHeader().setReorderingAllowed(false);
        userHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userHistoryTable.setModel(userHistoryTableModel);

        approvalTableModel = new DefaultTableModel();
        approvalTableModel.addColumn("Student Name");
        approvalTableModel.addColumn("Event Name");
        approvalTableModel.addColumn("Submission Date");

        approvalTable.setDefaultEditor(Object.class, null);
        approvalTable.getTableHeader().setReorderingAllowed(false);
        approvalTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        approvalTable.setModel(approvalTableModel);

        profileHistoryTableModel = new DefaultTableModel();
        profileHistoryTableModel.addColumn("Event Name");
        profileHistoryTableModel.addColumn("Role");
        profileHistoryTableModel.addColumn("Points Gained");

        profileHistoryTable.setDefaultEditor(Object.class, null);
        profileHistoryTable.getTableHeader().setReorderingAllowed(false);
        profileHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        profileHistoryTable.setModel(profileHistoryTableModel);

        veBtnGroup = new ButtonGroup();
        veBtnGroup.add(veR1);
        veBtnGroup.add(veR2);
        veBtnGroup.add(veR3);
        veBtnGroup.add(veR4);
        veBtnGroup.add(veR5);

//        efScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        veScrollPane.getVerticalScrollBar().setUnitIncrement(20);

        Profile.setVisible(true);
        Dashboard.setVisible(false);
        UserManagement.setVisible(false);
        Approval.setVisible(false);

        updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
        showHistory();

        AdminProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard.setVisible(false);
                UserManagement.setVisible(false);
                Approval.setVisible(false);
                Profile.setVisible(true);
                showHistory();
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

                eventsTableModel.fireTableDataChanged();
                eventsTable.repaint();
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
                String roles = role1Field.getText() + "," + role2Field.getText();
                String rolePoints = role1Points.getText() + "," + role2Points.getText();

                databaseManager.insertEventData(eventID, eventName, eventLocation, eventDate, startTime, endTime, eventType, eventMode, roles, rolePoints);

                eventsTable.setModel(eventsTableModel);
                events = databaseManager.getAllEvents();
                eventsTableModel.setRowCount(0);

                for (Event event :events) {
                    eventsTableModel.addRow(new Object[]{event.getName(), event.getLocation(), event.getDate(), event.getType()});
                }

                eventsTableModel.fireTableDataChanged();
                eventsTable.repaint();
            }
        });

        userManangementButton.addActionListener(new ActionListener() {
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

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = umTable.getSelectedRow();

                if (selectedRow != -1) {
                    User selectedUser = users.get(selectedRow);
                    String selectedUserID = selectedUser.getUniqueID();

                    userHistoryTable.setModel(userHistoryTableModel);

                    evalformArchive = databaseManager.getUserArchive(selectedUserID);
                    userHistoryTableModel.setRowCount(0);

                    for (EvaluationForm archive :evalformArchive) {
                        Event event = databaseManager.getEvent(archive.getEventID());

                        userHistoryTableModel.addRow(new Object[]{event.getName(), event.getLocation(), event.getDate(), event.getType(), event.getMode(), archive.getRole(), archive.getRolePoints()});
                    }

                    userHistoryTableModel.fireTableDataChanged();
                    userHistoryTable.repaint();

                    UserManagement.setVisible(false);
                    ViewHistory.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to view.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goBackUMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewHistory.setVisible(false);
                UserManagement.setVisible(true);
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
                    Event event = databaseManager.getEvent(evalFormData.getEventID());
                    String userName = databaseManager.getUserName(evalFormData.getUserID());
                    String eventName = event.getName();
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
                    Event event = databaseManager.getEvent(viewedForm.getEventID());

                    Approval.setVisible(false);
                    ViewEvalForm.setVisible(true);

                    eventTitleField.setText(event.getName());
                    veQ1.setText(viewedForm.getQOne());
                    veQ2.setText(viewedForm.getQTwo());
                    veQ3.setText(viewedForm.getQThree());
                    veQ4.setText(viewedForm.getQFour());
                    veQ5.setText(viewedForm.getQFive());
                    setRatingInButtonGroup(viewedForm.getRating());
                    roleTitleField.setText(viewedForm.getRole());
                    bImg.setIcon(viewedForm.getBeginningImg());
                    mImg.setIcon(viewedForm.getMiddleImg());
                    eImg.setIcon(viewedForm.getEndImg());

                    eventTitleField.setEnabled(false);
                    veQ1.setEditable(false);
                    veQ2.setEditable(false);
                    veQ3.setEditable(false);
                    veQ4.setEditable(false);
                    veQ5.setEditable(false);
                    roleTitleField.setEnabled(false);
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

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = approvalTable.getSelectedRow();

                if (selectedRow != -1) {
                    EvaluationForm selectedForm = evalFormDataList.get(selectedRow);
                    int pointsGained = Integer.parseInt(selectedForm.getRolePoints());
                    int currentPoints = databaseManager.getUserCESPoints(selectedForm.getUserID());
                    int newCesPoints = currentPoints + pointsGained;

                    databaseManager.updateUserCESPoints(selectedForm.getUserID(), newCesPoints);
                    databaseManager.archiveEvalForm(selectedForm.getEventID(), selectedForm.getUserID(), selectedForm.getEventID(), selectedForm.getQOne(), selectedForm.getQTwo(), selectedForm.getQThree(), selectedForm.getQFour(), selectedForm.getQFive(), selectedForm.getRole(), pointsGained, selectedForm.getRating(), selectedForm.getBeginningImg(), selectedForm.getMiddleImg(), selectedForm.getEndImg());
                    databaseManager.deleteEvalForm(selectedForm.getEvalformID());

                    approvalTable.setModel(approvalTableModel);
                    evalFormDataList = databaseManager.getAllEvalFormData();
                    approvalTableModel.setRowCount(0);

                    for (EvaluationForm evalFormData : evalFormDataList) {
                        Event event = databaseManager.getEvent(evalFormData.getEventID());
                        String userName = databaseManager.getUserName(evalFormData.getUserID());
                        String eventName = event.getName();
                        String submitted_atDate = dateFormat.format(evalFormData.getSubmitted_at());

                        approvalTableModel.addRow(new Object[]{userName, eventName, submitted_atDate});
                    }

                    approvalTableModel.fireTableDataChanged();
                    approvalTable.repaint();

                    ViewEvalForm.setVisible(false);
                    Approval.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to approve.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = approvalTable.getSelectedRow();

                if (selectedRow != -1) {
                    EvaluationForm selectedForm = evalFormDataList.get(selectedRow);

                    databaseManager.deleteEvalForm(selectedForm.getEvalformID());

                    approvalTable.setModel(approvalTableModel);
                    evalFormDataList = databaseManager.getAllEvalFormData();
                    approvalTableModel.setRowCount(0);

                    for (EvaluationForm evalFormData : evalFormDataList) {
                        Event event = databaseManager.getEvent(evalFormData.getEventID());
                        String userName = databaseManager.getUserName(evalFormData.getUserID());
                        String eventName = event.getName();
                        String submitted_atDate = dateFormat.format(evalFormData.getSubmitted_at());

                        approvalTableModel.addRow(new Object[]{userName, eventName, submitted_atDate});
                    }

                    approvalTableModel.fireTableDataChanged();
                    approvalTable.repaint();

                    ViewEvalForm.setVisible(false);
                    Approval.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to approve.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        goBackApproval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewEvalForm.setVisible(false);
                Approval.setVisible(true);
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
                        updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
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

        AdminLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("loggedUser.txt");
                    writer.write("");
                    writer.close();

                    frame.dispose();
                    MainWindow mainWindow = new MainWindow();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        ActionListener yearLevelFilterListener = new ActionListener() {
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

        allButton.addActionListener(yearLevelFilterListener);
        year1Button.addActionListener(yearLevelFilterListener);
        year2Button.addActionListener(yearLevelFilterListener);
        year3Button.addActionListener(yearLevelFilterListener);
        year4Button.addActionListener(yearLevelFilterListener);
        batchXButton.addActionListener(yearLevelFilterListener);
    }

    public void updateLoggedInUserInfoLabels(String id) {
        User userProfile = databaseManager.getUser(id);

        if (userProfile != null) {
            String name = userProfile.getFullName();
            String type = userProfile.getType();
            int cesPoints = userProfile.getCESPoints();

            userName.setText(name);
            userType.setText(type);
            userCourse.setText("BS Computer Engineering");
            userPoints.setText(String.valueOf(cesPoints));
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    private void showHistory() {
        profileHistoryTable.setModel(profileHistoryTableModel);

        evalformArchive = databaseManager.getUserArchive(loggedUser.getUniqueID());
        profileHistoryTableModel.setRowCount(0);

        for (EvaluationForm archive :evalformArchive) {
            Event event = databaseManager.getEvent(archive.getEventID());
            String eventName = event.getName();

            profileHistoryTableModel.addRow(new Object[]{eventName, archive.getRole(), archive.getRolePoints()});
        }

        profileHistoryTableModel.fireTableDataChanged();
        profileHistoryTable.repaint();
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(AdminWindow::new);
//    }
}
