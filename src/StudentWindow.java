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
import java.util.List;
import java.util.UUID;

public class StudentWindow extends JFrame {
    private JPanel SidePanel;
    private JPanel StudentSP;
    private JButton StudentProfileBtn;
    private JButton evaluationFormButton;
    private JButton StudentLogout;
    private JPanel MainPanel;
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
    private JPanel EvalForm;
    private JScrollPane efScrollPane;
    private JComboBox eventsBox;
    private JTextField q1TxtF;
    private JTextField q2TxtF;
    private JButton submitEvalFormBtn;
    private JTextField q3TxtF;
    private JTextField q4TxtF;
    private JTextField q5TxtF;
    private JRadioButton angryRadioButton;
    private JRadioButton disappointedRadioButton;
    private JRadioButton neutralRadioButton;
    private JRadioButton goodRadioButton;
    private JRadioButton loveEmojisRadioButton;
    private JButton beginningButton;
    private JButton middleButton;
    private JButton endButton;
    private JLabel beginningImage;
    private JLabel middleImage;
    private JLabel endImage;
    private JComboBox rolesBox;
    private JPanel Window;

    private DatabaseManager databaseManager;
    private Controller controller;

    private User loggedUser;

    private List<Event> events;
    private List<EvaluationForm> evalformArchive;

    private DefaultTableModel profileHistoryTableModel;
    private ButtonGroup radioBtnGroup;

    StudentWindow(User loggedUser) {
        this.loggedUser = loggedUser;
        initializeWindow();
    }

    StudentWindow() {
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

        radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(angryRadioButton);
        radioBtnGroup.add(disappointedRadioButton);
        radioBtnGroup.add(neutralRadioButton);
        radioBtnGroup.add(goodRadioButton);
        radioBtnGroup.add(loveEmojisRadioButton);

        profileHistoryTableModel = new DefaultTableModel();
        profileHistoryTableModel.addColumn("Event Name");
        profileHistoryTableModel.addColumn("Role");
        profileHistoryTableModel.addColumn("Points Gained");

        profileHistoryTable.setDefaultEditor(Object.class, null);
        profileHistoryTable.getTableHeader().setReorderingAllowed(false);
        profileHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        profileHistoryTable.setModel(profileHistoryTableModel);

        Profile.setVisible(true);
        EvalForm.setVisible(false);

        updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
        showHistory();

        StudentProfileBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(true);
                EvalForm.setVisible(false);
                showHistory();
            }
        });

        evaluationFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.setVisible(false);
                EvalForm.setVisible(true);

                events = databaseManager.getAllEvents();
                eventsBox.removeAllItems();
                eventsBox.addItem("Select Event Name");

                rolesBox.removeAllItems();
                rolesBox.addItem("Select Role");

                for (Event event : events) {
                    eventsBox.addItem(event.getName());
                }

                eventsBox.setSelectedIndex(0);

                eventsBox.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            rolesBox.removeAllItems();
                            rolesBox.addItem("Select Role");

                            String selectedEventName = (String) eventsBox.getSelectedItem();
                            Event selectedEvent = null;

                            for (Event event : events) {
                                if (event.getName().equals(selectedEventName)) {
                                    selectedEvent = event;
                                    break;
                                }
                            }

                            if (selectedEvent != null) {
                                String[] eventRoles = selectedEvent.getRoles().split(",");
                                for (String role : eventRoles) {
                                    rolesBox.addItem(role.trim());
                                }
                            }
                        }
                    }
                });
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
                    String selectedEventName = (String) eventsBox.getSelectedItem();

                    String evalformID = String.valueOf(uuid);
                    String eventID = getEventIDFromName(selectedEventName);
                    String qOne = q1TxtF.getText();
                    String qTwo = q2TxtF.getText();
                    String qThree = q3TxtF.getText();
                    String qFour = q4TxtF.getText();
                    String qFive = q5TxtF.getText();
                    String role = (String) rolesBox.getSelectedItem();
                    int rolePoints = databaseManager.getRolePoints(eventID, role);
                    String rating = getSelectedRadioValue();
                    ImageIcon beginningImg = (ImageIcon) beginningImage.getIcon();
                    ImageIcon middleImg = (ImageIcon) middleImage.getIcon();
                    ImageIcon endImg = (ImageIcon) endImage.getIcon();

                    if (beginningImg != null && middleImg != null && endImg != null) {
                        databaseManager.insertEvalForm(evalformID, id, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select images for all three categories.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | RuntimeException ex) {
                    ex.printStackTrace();
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

        StudentLogout.addActionListener(new ActionListener() {
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

        beginningButton.addActionListener(selectImage);
        middleButton.addActionListener(selectImage);
        endButton.addActionListener(selectImage);
    }

    public void updateLoggedInUserInfoLabels(String id) {
        User userProfile = databaseManager.getUser(id);

        if (userProfile != null) {
            String name = userProfile.getFullName();
            String type = userProfile.getType();
            int cesPoints = userProfile.getCESPoints();

            userName.setText(name);
            userType.setText(type);
            userCourse.setText("BS Computer Engineering - " + ((Student) userProfile).getYearLevel());
            userPoints.setText(String.valueOf(cesPoints));
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
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
//        SwingUtilities.invokeLater(StudentWindow::new);
//    }
}
