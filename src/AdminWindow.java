import com.raven.datechooser.DateChooser;
import com.raven.swing.TimePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class AdminWindow extends javax.swing.JFrame {
    private javax.swing.JPanel AdminSP;
    private javax.swing.JButton ApprovalBtn;
    private javax.swing.JPanel ApprovalPanel;
    private javax.swing.JButton DashboardBtn;
    private javax.swing.JPanel DashboardPanel;
    private javax.swing.JScrollPane HistoryScrollPane;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel ProfilePanel;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JPanel StudentHistoryPanel;
    private javax.swing.JButton AdminLogout;
    private javax.swing.JButton UserManagementBtn;
    private javax.swing.JButton AdminProfileBtn;
    private javax.swing.JPanel UserCES;
    private javax.swing.JPanel UserEdit;
    private javax.swing.JPanel UserHistory;
    private javax.swing.JPanel UserInfo;
    private javax.swing.JPanel UserManagementPanel;
    private javax.swing.JPanel ViewEvalFormPanel;
    private javax.swing.JPanel Window;
    private javax.swing.JButton allBtn;
    private javax.swing.JRadioButton angryRadioButton;
    private javax.swing.JTable approvalTable;
    private javax.swing.JScrollPane approvalTableScrollPane;
    private javax.swing.JButton approvalViewFormBtn;
    private javax.swing.JButton approveEvalFormBtn;
    private javax.swing.JButton backToApprovalBtn;
    private javax.swing.JButton backToUserManagementBtn;
    private javax.swing.JLabel beginningImg;
    private javax.swing.JButton changePass;
    private javax.swing.JButton createEventBtn;
    private javax.swing.JPanel dashboadHeader;
    private javax.swing.JPanel dashboardAddEvent;
    private javax.swing.JPanel dashboardEventList;
    private javax.swing.JButton deletedBtn;
    private javax.swing.JRadioButton disappointedRadioButton;
    private javax.swing.JButton disapproveEvalFormBtn;
    private javax.swing.JLabel documentationLabel;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel endImg;
    private javax.swing.JButton endTimeBtn;
    private javax.swing.JScrollPane evalFormForApproval;
    private javax.swing.JTextField eventDateField;
    private javax.swing.JTextField eventEndTimeField;
    private javax.swing.JTextField eventLocationField;
    private javax.swing.JTextField eventModeField;
    private javax.swing.JTextField eventNameField;
    private javax.swing.JLabel eventNameTitle;
    private javax.swing.JTextField eventRole1Field;
    private javax.swing.JTextField eventRole2Field;
    private javax.swing.JTextField eventRole3Field;
    private javax.swing.JTextField eventRolePoints1Field;
    private javax.swing.JTextField eventRolePoints2Field;
    private javax.swing.JTextField eventRolePoints3Field;
    private javax.swing.JTextField eventStartTimeField;
    private javax.swing.JTextField eventTypeField;
    private javax.swing.JTable eventsTable;
    private javax.swing.JScrollPane eventsTableScrollPane;
    private javax.swing.JRadioButton goodRadioButton;
    private javax.swing.JLabel greetingsLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane userHistoryTableScrollPane;
    private javax.swing.JTable userHistoryTable;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JRadioButton loveEmojisRadioButton;
    private javax.swing.JLabel middleImg;
    private javax.swing.JPanel name;
    private javax.swing.JRadioButton neutralRadioButton;
    private javax.swing.JTable profileHistoryTable;
    private javax.swing.JTextArea question1Field;
    private javax.swing.JScrollPane question1Pane;
    private javax.swing.JTextArea question2Field;
    private javax.swing.JScrollPane question2Pane;
    private javax.swing.JTextArea question3Field;
    private javax.swing.JScrollPane question3Pane;
    private javax.swing.JTextArea question4Field;
    private javax.swing.JScrollPane question4Pane;
    private javax.swing.JTextArea question5Field;
    private javax.swing.JScrollPane question5Pane;
    private javax.swing.JLabel roleTitle;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton setYrLvlBtn;
    private javax.swing.JButton startTimeBtn;
    private javax.swing.JPanel studentDetails;
    private javax.swing.JTable umTable;
    private javax.swing.JScrollPane umTableScrollPane;
    private javax.swing.JPanel userContainer;
    private javax.swing.JLabel userCourse;
    private javax.swing.JLabel userIDnum;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userPoints;
    private javax.swing.JPanel vieHistoryHeader;
    private javax.swing.JPanel viewHistory;
    private javax.swing.JButton viewHistoryBtn;
    private javax.swing.JButton year1Btn;
    private javax.swing.JButton year2Btn;
    private javax.swing.JButton year3Btn;
    private javax.swing.JButton year4Btn;
    private javax.swing.JPanel yrLevel;
    private javax.swing.JLabel approvalNameField;
    private javax.swing.JLabel approvalYrLvlField;
    private javax.swing.JLabel logo;

    private DefaultTableModel eventsTableModel;
    private DefaultTableModel umTableModel;
    private DefaultTableModel approvalTableModel;
    private DefaultTableModel userHistoryTableModel;
    private DefaultTableModel profileHistoryTableModel;
    private final DateChooser datePicker = new DateChooser();
    private final TimePicker timePicker = new TimePicker();

    private DatabaseManager databaseManager;
    private final User loggedUser;

    private List<User> users;
    private List<Event> events;
    private List<EvaluationForm> evalFormDataList;
    private List<EvaluationForm> evalformArchive;

    private ButtonGroup radioBtnGroup;

    public AdminWindow(User loggedUser) {
        this.loggedUser = loggedUser;
        initComponents();
    }

    private void initComponents() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        Controller controller = new Controller();

        Window = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        AdminSP = new javax.swing.JPanel();
        AdminLogout = new javax.swing.JButton();
        DashboardBtn = new javax.swing.JButton();
        ApprovalBtn = new javax.swing.JButton();
        UserManagementBtn = new javax.swing.JButton();
        AdminProfileBtn = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        ProfilePanel = new javax.swing.JPanel();
        UserInfo = new javax.swing.JPanel();
        userContainer = new javax.swing.JPanel();
        userName = new javax.swing.JLabel();
        userIDnum = new javax.swing.JLabel();
        userCourse = new javax.swing.JLabel();
        UserEdit = new javax.swing.JPanel();
        setYrLvlBtn = new javax.swing.JButton();
        changePass = new javax.swing.JButton();
        UserCES = new javax.swing.JPanel();
        userPoints = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        UserHistory = new javax.swing.JPanel();
        HistoryScrollPane = new javax.swing.JScrollPane();
        profileHistoryTable = new javax.swing.JTable();
        DashboardPanel = new javax.swing.JPanel();
        dashboadHeader = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        dashboardAddEvent = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        eventNameField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        eventDateField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        eventStartTimeField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        eventEndTimeField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        eventTypeField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        eventModeField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        eventRole1Field = new javax.swing.JTextField();
        eventRole2Field = new javax.swing.JTextField();
        eventRolePoints1Field = new javax.swing.JTextField();
        startTimeBtn = new javax.swing.JButton();
        endTimeBtn = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        eventRole3Field = new javax.swing.JTextField();
        eventRolePoints3Field = new javax.swing.JTextField();
        eventRolePoints2Field = new javax.swing.JTextField();
        createEventBtn = new javax.swing.JButton();
        eventLocationField = new javax.swing.JTextField();
        dashboardEventList = new javax.swing.JPanel();
        eventsTableScrollPane = new javax.swing.JScrollPane();
        eventsTable = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        UserManagementPanel = new javax.swing.JPanel();
        viewHistoryBtn = new javax.swing.JButton();
        deletedBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        year4Btn = new javax.swing.JButton();
        year3Btn = new javax.swing.JButton();
        year2Btn = new javax.swing.JButton();
        year1Btn = new javax.swing.JButton();
        allBtn = new javax.swing.JButton();
        umTableScrollPane = new javax.swing.JScrollPane();
        umTable = new javax.swing.JTable();
        ApprovalPanel = new javax.swing.JPanel();
        approvalViewFormBtn = new javax.swing.JButton();
        approvalTableScrollPane = new javax.swing.JScrollPane();
        approvalTable = new javax.swing.JTable();
        backToUserManagementBtn = new javax.swing.JButton();
        ViewEvalFormPanel = new javax.swing.JPanel();
        evalFormForApproval = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        greetingsLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        angryRadioButton = new javax.swing.JRadioButton();
        disappointedRadioButton = new javax.swing.JRadioButton();
        neutralRadioButton = new javax.swing.JRadioButton();
        goodRadioButton = new javax.swing.JRadioButton();
        loveEmojisRadioButton = new javax.swing.JRadioButton();
        documentationLabel = new javax.swing.JLabel();
        question3Pane = new javax.swing.JScrollPane();
        question3Field = new javax.swing.JTextArea();
        question2Pane = new javax.swing.JScrollPane();
        question2Field = new javax.swing.JTextArea();
        question1Pane = new javax.swing.JScrollPane();
        question1Field = new javax.swing.JTextArea();
        question4Pane = new javax.swing.JScrollPane();
        question4Field = new javax.swing.JTextArea();
        question5Pane = new javax.swing.JScrollPane();
        question5Field = new javax.swing.JTextArea();
        backToApprovalBtn = new javax.swing.JButton();
        disapproveEvalFormBtn = new javax.swing.JButton();
        approveEvalFormBtn = new javax.swing.JButton();
        beginningImg = new javax.swing.JLabel();
        middleImg = new javax.swing.JLabel();
        endImg = new javax.swing.JLabel();
        eventNameTitle = new javax.swing.JLabel();
        roleTitle = new javax.swing.JLabel();
        StudentHistoryPanel = new javax.swing.JPanel();
        vieHistoryHeader = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        studentDetails = new javax.swing.JPanel();
        name = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        yrLevel = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        viewHistory = new javax.swing.JPanel();
        userHistoryTableScrollPane = new javax.swing.JScrollPane();
        userHistoryTable = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        approvalNameField = new javax.swing.JLabel();
        approvalYrLvlField = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        ProfilePanel.setVisible(true);
        DashboardPanel.setVisible(false);
        UserManagementPanel.setVisible(false);
        ApprovalPanel.setVisible(false);
        ViewEvalFormPanel.setVisible(false);
        StudentHistoryPanel.setVisible(false);

        datePicker.setLabelCurrentDayVisible(false);
        datePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        datePicker.setTextField(eventDateField);
        eventDateField.setText("");

        allBtn.addActionListener(yearLevelFilterListener);
        year1Btn.addActionListener(yearLevelFilterListener);
        year2Btn.addActionListener(yearLevelFilterListener);
        year3Btn.addActionListener(yearLevelFilterListener);
        year4Btn.addActionListener(yearLevelFilterListener);

        radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(angryRadioButton);
        radioBtnGroup.add(disappointedRadioButton);
        radioBtnGroup.add(neutralRadioButton);
        radioBtnGroup.add(goodRadioButton);
        radioBtnGroup.add(loveEmojisRadioButton);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                searchFieldActionListener(e);
            }
        });

        show();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CES Tracker - Admin");
        setMaximumSize(new java.awt.Dimension(1350, 750));
        setMinimumSize(new java.awt.Dimension(1350, 750));
        setPreferredSize(new java.awt.Dimension(1350, 750));
        setLocationRelativeTo(null);
        setResizable(false);

        Window.setBackground(new java.awt.Color(255, 255, 255));
        SidePanel.setBackground(new java.awt.Color(24, 29, 49));
        AdminSP.setBackground(new java.awt.Color(24, 29, 49));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        AdminLogout.setText("Log Out");
        AdminLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                AdminLogoutActionPerformed(e);
            }
        });

        DashboardBtn.setText("Dashboard");
        DashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                DashboardBtnActionPerformed(e);
            }
        });

        ApprovalBtn.setText("Approval");
        ApprovalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ApprovalBtnActionPerformed(e);
            }
        });

        UserManagementBtn.setText("User Management");
        UserManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                UserManagementBtnActionPerformed(e);
            }
        });

        AdminProfileBtn.setText("Profile");
        AdminProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                AdminProfileBtnActionPerformed(e);
            }
        });

        javax.swing.GroupLayout AdminSPLayout = new javax.swing.GroupLayout(AdminSP);
        AdminSP.setLayout(AdminSPLayout);
        AdminSPLayout.setHorizontalGroup(AdminSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(AdminSPLayout.createSequentialGroup().addContainerGap().addGroup(AdminSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(AdminSPLayout.createSequentialGroup().addGroup(AdminSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(UserManagementBtn, javax.swing.GroupLayout.Alignment.TRAILING).addComponent(DashboardBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(ApprovalBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(AdminProfileBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(AdminLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(0, 0, Short.MAX_VALUE)).addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        AdminSPLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ApprovalBtn, DashboardBtn, AdminLogout, UserManagementBtn, AdminProfileBtn});
        AdminSPLayout.setVerticalGroup(AdminSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminSPLayout.createSequentialGroup().addGap(81, 81, 81).addComponent(logo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(DashboardBtn).addGap(32, 32, 32).addComponent(UserManagementBtn).addGap(32, 32, 32).addComponent(ApprovalBtn).addGap(32, 32, 32).addComponent(AdminProfileBtn).addGap(220, 220, 220).addComponent(AdminLogout).addGap(68, 68, 68)));

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SidePanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(AdminSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE)));
        SidePanelLayout.setVerticalGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(AdminSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.CardLayout());

        ProfilePanel.setBackground(new java.awt.Color(255, 255, 255));

        userName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        userName.setText("User Name");
        userIDnum.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        userIDnum.setText("ID Number");
        userCourse.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        userCourse.setText("User Course");

        javax.swing.GroupLayout userContainerLayout = new javax.swing.GroupLayout(userContainer);
        userContainer.setLayout(userContainerLayout);
        userContainerLayout.setHorizontalGroup(userContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(userContainerLayout.createSequentialGroup().addContainerGap().addGroup(userContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(userName, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE).addComponent(userIDnum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(userCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
        userContainerLayout.setVerticalGroup(userContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(userContainerLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(userName).addGap(18, 18, 18).addComponent(userCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(userIDnum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27)));

        setYrLvlBtn.setText("Set Year Level");
        setYrLvlBtn.setEnabled(false);
        changePass.setText("Change Password");
        changePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                changePassActionPerformed(e);
            }
        });

        javax.swing.GroupLayout UserEditLayout = new javax.swing.GroupLayout(UserEdit);
        UserEdit.setLayout(UserEditLayout);
        UserEditLayout.setHorizontalGroup(UserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserEditLayout.createSequentialGroup().addGap(70, 70, 70).addGroup(UserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(setYrLvlBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(changePass, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)).addContainerGap(70, Short.MAX_VALUE)));
        UserEditLayout.setVerticalGroup(UserEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserEditLayout.createSequentialGroup().addGap(56, 56, 56).addComponent(setYrLvlBtn).addGap(18, 18, 18).addComponent(changePass).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        userPoints.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        userPoints.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userPoints.setText("CES POINTS");

        javax.swing.GroupLayout UserCESLayout = new javax.swing.GroupLayout(UserCES);
        UserCES.setLayout(UserCESLayout);
        UserCESLayout.setHorizontalGroup(UserCESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserCESLayout.createSequentialGroup().addGap(48, 48, 48).addComponent(userPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(52, Short.MAX_VALUE)));
        UserCESLayout.setVerticalGroup(UserCESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserCESLayout.createSequentialGroup().addGap(40, 40, 40).addComponent(userPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Points");

        javax.swing.GroupLayout UserInfoLayout = new javax.swing.GroupLayout(UserInfo);
        UserInfo.setLayout(UserInfoLayout);
        UserInfoLayout.setHorizontalGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserInfoLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(userContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(UserCES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(73, 73, 73).addComponent(UserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(46, 46, 46)));
        UserInfoLayout.setVerticalGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserInfoLayout.createSequentialGroup().addGap(10, 10, 10).addGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserInfoLayout.createSequentialGroup().addComponent(userContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(10, 10, 10)).addGroup(UserInfoLayout.createSequentialGroup().addComponent(UserCES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel9).addGap(0, 41, Short.MAX_VALUE)).addComponent(UserEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));

        UserHistory.setBackground(new java.awt.Color(255, 255, 255));

        profileHistoryTableModel = new DefaultTableModel();
        profileHistoryTableModel.addColumn("Event Name");
        profileHistoryTableModel.addColumn("Role");
        profileHistoryTableModel.addColumn("Points Gained");

        profileHistoryTable.setDefaultEditor(Object.class, null);
        profileHistoryTable.getTableHeader().setReorderingAllowed(false);
        profileHistoryTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        profileHistoryTable.setModel(profileHistoryTableModel);
        HistoryScrollPane.setViewportView(profileHistoryTable);

        javax.swing.GroupLayout UserHistoryLayout = new javax.swing.GroupLayout(UserHistory);
        UserHistory.setLayout(UserHistoryLayout);
        UserHistoryLayout.setHorizontalGroup(UserHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserHistoryLayout.createSequentialGroup().addComponent(HistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 55, Short.MAX_VALUE)));
        UserHistoryLayout.setVerticalGroup(UserHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserHistoryLayout.createSequentialGroup().addComponent(HistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 40, Short.MAX_VALUE)));

        javax.swing.GroupLayout ProfilePanelLayout = new javax.swing.GroupLayout(ProfilePanel);
        ProfilePanel.setLayout(ProfilePanelLayout);
        ProfilePanelLayout.setHorizontalGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(ProfilePanelLayout.createSequentialGroup().addGap(20, 20, 20).addGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(UserHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(UserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(15, Short.MAX_VALUE)));
        ProfilePanelLayout.setVerticalGroup(ProfilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(ProfilePanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(UserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(18, 18, 18).addComponent(UserHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        MainPanel.add(ProfilePanel, "card2");

        dashboadHeader.setBackground(new java.awt.Color(189, 224, 254));
        dashboadHeader.setForeground(new java.awt.Color(189, 224, 254));

        jLabel10.setFont(new java.awt.Font("Impact", 0, 28)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("E V E N T   D A S H B O A R D");

        javax.swing.GroupLayout dashboadHeaderLayout = new javax.swing.GroupLayout(dashboadHeader);
        dashboadHeader.setLayout(dashboadHeaderLayout);
        dashboadHeaderLayout.setHorizontalGroup(dashboadHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(dashboadHeaderLayout.createSequentialGroup().addGap(0, 431, Short.MAX_VALUE).addComponent(jLabel10).addGap(0, 432, Short.MAX_VALUE)));
        dashboadHeaderLayout.setVerticalGroup(dashboadHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(dashboadHeaderLayout.createSequentialGroup().addGap(0, 24, Short.MAX_VALUE).addComponent(jLabel10).addGap(0, 24, Short.MAX_VALUE)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Add Event");
        jLabel15.setText("Name:");
        jLabel16.setText("Location:");
        jLabel17.setText("Date:");
        jLabel18.setText("Start Time:");
        jLabel19.setText("End Time:");
        jLabel20.setText("Type:");
        jLabel21.setText("Mode:");
        jLabel22.setText("Role:");

        startTimeBtn.setText("Set Time");
        eventStartTimeField.setEditable(false);
        startTimeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                startTimeBtnActionPerformed(e);
            }
        });

        endTimeBtn.setText("Set Time");
        eventEndTimeField.setEditable(false);
        endTimeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                endTimeBtnActionPerformed(e);
            }
        });

        jLabel23.setText("Points:");
        createEventBtn.setText("Create Event");
        createEventBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                createEventBtnActionPerformed(e);
            }
        });

        javax.swing.GroupLayout dashboardAddEventLayout = new javax.swing.GroupLayout(dashboardAddEvent);
        dashboardAddEvent.setLayout(dashboardAddEventLayout);
        dashboardAddEventLayout.setHorizontalGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(dashboardAddEventLayout.createSequentialGroup().addGap(64, 64, 64).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventModeField, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(dashboardAddEventLayout.createSequentialGroup().addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventStartTimeField)).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(eventEndTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(startTimeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(endTimeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addGroup(dashboardAddEventLayout.createSequentialGroup().addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(createEventBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(dashboardAddEventLayout.createSequentialGroup().addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(eventRole2Field).addComponent(eventRole1Field).addComponent(eventRole3Field, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(23, 23, 23).addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(eventRolePoints1Field).addComponent(eventRolePoints3Field).addComponent(eventRolePoints2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))).addContainerGap(48, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardAddEventLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(242, 242, 242)));
        dashboardAddEventLayout.setVerticalGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(dashboardAddEventLayout.createSequentialGroup().addContainerGap().addComponent(jLabel11).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15).addComponent(eventNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel16).addComponent(eventLocationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel17).addComponent(eventDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel18).addComponent(eventStartTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(startTimeBtn)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel19).addComponent(eventEndTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(endTimeBtn)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel20).addComponent(eventTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel21).addComponent(eventModeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel22).addComponent(eventRole1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel23).addComponent(eventRolePoints1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(eventRole2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(eventRolePoints2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(dashboardAddEventLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(eventRole3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(eventRolePoints3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(createEventBtn).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        eventsTableModel = new DefaultTableModel();
        eventsTableModel.addColumn("Name");
        eventsTableModel.addColumn("Location");
        eventsTableModel.addColumn("Date");
        eventsTableModel.addColumn("Type");

        eventsTable.setDefaultEditor(Object.class, null);
        eventsTable.getTableHeader().setReorderingAllowed(false);
        eventsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        eventsTable.setModel(eventsTableModel);
        eventsTableScrollPane.setViewportView(eventsTable);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("List of CES Events");

        javax.swing.GroupLayout dashboardEventListLayout = new javax.swing.GroupLayout(dashboardEventList);
        dashboardEventList.setLayout(dashboardEventListLayout);
        dashboardEventListLayout.setHorizontalGroup(dashboardEventListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardEventListLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(176, 176, 176)).addGroup(dashboardEventListLayout.createSequentialGroup().addContainerGap().addComponent(eventsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        dashboardEventListLayout.setVerticalGroup(dashboardEventListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dashboardEventListLayout.createSequentialGroup().addContainerGap().addComponent(jLabel24).addGap(18, 18, 18).addComponent(eventsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(53, Short.MAX_VALUE)));

        javax.swing.GroupLayout DashboardPanelLayout = new javax.swing.GroupLayout(DashboardPanel);
        DashboardPanel.setLayout(DashboardPanelLayout);
        DashboardPanelLayout.setHorizontalGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1180, Short.MAX_VALUE).addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(DashboardPanelLayout.createSequentialGroup().addGap(23, 23, 23).addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(dashboadHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(DashboardPanelLayout.createSequentialGroup().addComponent(dashboardAddEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(dashboardEventList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap(24, Short.MAX_VALUE))));
        DashboardPanelLayout.setVerticalGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 750, Short.MAX_VALUE).addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(DashboardPanelLayout.createSequentialGroup().addGap(31, 31, 31).addComponent(dashboadHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(DashboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(dashboardAddEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(dashboardEventList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(31, Short.MAX_VALUE))));

        MainPanel.add(DashboardPanel, "card2");

        editBtn.setText(" Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                editBtnActionPerformed(e);
            }
        });

        deletedBtn.setText("Delete");
        deletedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                deletedBtnActionPerformed(e);
            }
        });

        viewHistoryBtn.setText("View History");
        viewHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                viewHistoryBtnActionPerformed(e);
            }
        });

        jLabel1.setText("Search");
        year4Btn.setText("Year 4");
        year4Btn.setToolTipText("");

        year3Btn.setText("Year 3");
        year3Btn.setToolTipText("");
        year2Btn.setText("Year 2");
        year2Btn.setToolTipText("");
        year1Btn.setText("Year 1");
        year1Btn.setToolTipText("");
        allBtn.setText("All");

        umTableModel = new DefaultTableModel();
        umTableModel.addColumn("Name");
        umTableModel.addColumn("Email");
        umTableModel.addColumn("ID Number");
        umTableModel.addColumn("Year Level");
        umTableModel.addColumn("Total CES Points");

        umTable.setDefaultEditor(Object.class, null);
        umTable.getTableHeader().setReorderingAllowed(false);
        umTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        umTable.setModel(umTableModel);
        umTableScrollPane.setViewportView(umTable);

        javax.swing.GroupLayout UserManagementPanelLayout = new javax.swing.GroupLayout(UserManagementPanel);
        UserManagementPanel.setLayout(UserManagementPanelLayout);
        UserManagementPanelLayout.setHorizontalGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserManagementPanelLayout.createSequentialGroup().addGap(120, 120, 120).addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(UserManagementPanelLayout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(UserManagementPanelLayout.createSequentialGroup().addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(year1Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(year2Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(year3Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(year4Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(136, 136, 136).addComponent(editBtn).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(deletedBtn).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(viewHistoryBtn)).addComponent(umTableScrollPane)).addContainerGap(120, Short.MAX_VALUE)));
        UserManagementPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {allBtn, deletedBtn, editBtn, viewHistoryBtn, year1Btn, year2Btn, year3Btn, year4Btn});
        UserManagementPanelLayout.setVerticalGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserManagementPanelLayout.createSequentialGroup().addGap(75, 75, 75).addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(allBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(UserManagementPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(year2Btn).addComponent(year3Btn).addComponent(year4Btn).addComponent(editBtn).addComponent(deletedBtn).addComponent(viewHistoryBtn).addComponent(year1Btn))).addGap(18, 18, 18).addComponent(umTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(115, Short.MAX_VALUE)));
        UserManagementPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {allBtn, deletedBtn, editBtn, viewHistoryBtn, year1Btn, year2Btn, year3Btn, year4Btn});

        MainPanel.add(UserManagementPanel, "card3");

        approvalViewFormBtn.setText("View Form");
        approvalViewFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                approvalViewFormBtnActionPerformed(e);
            }
        });

        approvalTableModel = new DefaultTableModel();
        approvalTableModel.addColumn("Student Name");
        approvalTableModel.addColumn("Event Name");
        approvalTableModel.addColumn("Submission Date");

        approvalTable.setDefaultEditor(Object.class, null);
        approvalTable.getTableHeader().setReorderingAllowed(false);
        approvalTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        approvalTable.setModel(approvalTableModel);
        approvalTableScrollPane.setViewportView(approvalTable);

        javax.swing.GroupLayout ApprovalPanelLayout = new javax.swing.GroupLayout(ApprovalPanel);
        ApprovalPanel.setLayout(ApprovalPanelLayout);
        ApprovalPanelLayout.setHorizontalGroup(ApprovalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ApprovalPanelLayout.createSequentialGroup().addGap(100, 100, 100).addGroup(ApprovalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(approvalViewFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(approvalTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(120, 120, 120)));
        ApprovalPanelLayout.setVerticalGroup(ApprovalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ApprovalPanelLayout.createSequentialGroup().addContainerGap(113, Short.MAX_VALUE).addComponent(approvalViewFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(approvalTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(151, 151, 151)));

        MainPanel.add(ApprovalPanel, "card4");

        evalFormForApproval.setBorder(null);
        evalFormForApproval.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        evalFormForApproval.getVerticalScrollBar().setUnitIncrement(20);

        jPanel4.setPreferredSize(new java.awt.Dimension(1654, 850));

        greetingsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        greetingsLabel.setText("Evaluation Form");

        jLabel2.setText("1. I saw that...");
        jLabel3.setText("2. I heard that...");
        jLabel4.setText("4. With what I experienced, I will...");
        jLabel6.setText("3. I felt that...");
        jLabel5.setText("5. The exposure activity made me think that...");
        jLabel7.setText("Role in the Activity");
        jLabel8.setText("Please check your overall rating in the CES exposure activity");

        angryRadioButton.setText("Angry");
        disappointedRadioButton.setText("Disappointed");
        neutralRadioButton.setText("Neutral");
        goodRadioButton.setText("Good");
        loveEmojisRadioButton.setText("Love");
        documentationLabel.setText("Documentation");

        question3Pane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        question3Pane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        question3Pane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        question3Field.setColumns(20);
        question3Field.setRows(5);
        question3Pane.setViewportView(question3Field);

        question2Pane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        question2Pane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        question2Pane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        question2Field.setColumns(20);
        question2Field.setRows(5);
        question2Pane.setViewportView(question2Field);

        question1Pane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        question1Pane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        question1Pane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        question1Field.setColumns(20);
        question1Field.setRows(5);
        question1Pane.setViewportView(question1Field);

        question4Pane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        question4Pane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        question4Pane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        question4Field.setColumns(20);
        question4Field.setRows(5);
        question4Pane.setViewportView(question4Field);

        question5Pane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        question5Pane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        question5Pane.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        question5Field.setColumns(20);
        question5Field.setRows(5);
        question5Pane.setViewportView(question5Field);

        backToApprovalBtn.setText("Back");
        backToApprovalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                backToApprovalBtnActionPerformed(e);
            }
        });
        backToApprovalBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backToApprovalBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backToApprovalBtn.setMaximumSize(new java.awt.Dimension(127, 23));
        backToApprovalBtn.setMinimumSize(new java.awt.Dimension(127, 23));
        backToApprovalBtn.setPreferredSize(new java.awt.Dimension(127, 23));

        disapproveEvalFormBtn.setText("Disapprove");
        disapproveEvalFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                disapproveEvalFormBtnActionPerformed(e);
            }
        });
        disapproveEvalFormBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        disapproveEvalFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        disapproveEvalFormBtn.setMaximumSize(new java.awt.Dimension(127, 23));
        disapproveEvalFormBtn.setMinimumSize(new java.awt.Dimension(127, 23));
        disapproveEvalFormBtn.setPreferredSize(new java.awt.Dimension(127, 23));

        approveEvalFormBtn.setText("Approve");
        approveEvalFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                approveEvalFormBtnActionPerformed(e);
            }
        });
        approveEvalFormBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        approveEvalFormBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        approveEvalFormBtn.setMaximumSize(new java.awt.Dimension(127, 23));
        approveEvalFormBtn.setMinimumSize(new java.awt.Dimension(127, 23));
        approveEvalFormBtn.setPreferredSize(new java.awt.Dimension(127, 23));

        beginningImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        middleImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        endImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        eventNameTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        roleTitle.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(56, 56, 56).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(roleTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup().addComponent(beginningImg, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(28, 28, 28).addComponent(middleImg, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(28, 28, 28).addComponent(endImg, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(221, 221, 221).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(approveEvalFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(disapproveEvalFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(232, 232, 232)).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(documentationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel4Layout.createSequentialGroup().addComponent(angryRadioButton).addGap(18, 18, 18).addComponent(disappointedRadioButton).addGap(18, 18, 18).addComponent(neutralRadioButton).addGap(18, 18, 18).addComponent(goodRadioButton).addGap(18, 18, 18).addComponent(loveEmojisRadioButton)).addGroup(jPanel4Layout.createSequentialGroup().addComponent(greetingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(677, 677, 677).addComponent(backToApprovalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(175, 175, 175)))).addComponent(question1Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(question5Pane, javax.swing.GroupLayout.Alignment.LEADING).addComponent(question4Pane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE).addComponent(question3Pane, javax.swing.GroupLayout.Alignment.LEADING).addComponent(question2Pane, javax.swing.GroupLayout.Alignment.LEADING)).addComponent(eventNameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 1010, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(356, Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(20, 20, 20).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(greetingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(backToApprovalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(eventNameTitle).addGap(30, 30, 30).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question1Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question2Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(5, 5, 5).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question3Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(8, 8, 8).addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question4Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(8, 8, 8).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question5Pane, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(14, 14, 14).addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(roleTitle).addGap(8, 8, 8).addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(angryRadioButton).addComponent(disappointedRadioButton).addComponent(neutralRadioButton).addComponent(goodRadioButton).addComponent(loveEmojisRadioButton)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(documentationLabel).addGap(18, 18, 18).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(approveEvalFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(disapproveEvalFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(beginningImg, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(middleImg, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(endImg, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(279, Short.MAX_VALUE)));

        evalFormForApproval.setViewportView(jPanel4);

        javax.swing.GroupLayout ViewEvalFormPanelLayout = new javax.swing.GroupLayout(ViewEvalFormPanel);
        ViewEvalFormPanel.setLayout(ViewEvalFormPanelLayout);
        ViewEvalFormPanelLayout.setHorizontalGroup(ViewEvalFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 1180, Short.MAX_VALUE).addGroup(ViewEvalFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(ViewEvalFormPanelLayout.createSequentialGroup().addGap(12, 12, 12).addComponent(evalFormForApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(78, Short.MAX_VALUE))));
        ViewEvalFormPanelLayout.setVerticalGroup(ViewEvalFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 750, Short.MAX_VALUE).addGroup(ViewEvalFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(ViewEvalFormPanelLayout.createSequentialGroup().addGap(18, 18, 18).addComponent(evalFormForApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE).addGap(19, 19, 19))));

        MainPanel.add(ViewEvalFormPanel, "card6");

        vieHistoryHeader.setBackground(new java.awt.Color(189, 224, 254));
        vieHistoryHeader.setForeground(new java.awt.Color(189, 224, 254));

        jLabel13.setFont(new java.awt.Font("Impact", 0, 28)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("STUDENT INVOLVEMENT ON CES ACTIVITIES");

        studentDetails.setBackground(new java.awt.Color(189, 224, 254));
        studentDetails.setForeground(new java.awt.Color(189, 224, 254));

        name.setBackground(new java.awt.Color(189, 224, 254));
        name.setForeground(new java.awt.Color(189, 224, 254));

        jLabel12.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Name:");

        approvalNameField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout nameLayout = new javax.swing.GroupLayout(name);
        name.setLayout(nameLayout);
        nameLayout.setHorizontalGroup(nameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(nameLayout.createSequentialGroup().addGap(15, 15, 15).addComponent(jLabel12).addGap(18, 18, 18).addComponent(approvalNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(55, Short.MAX_VALUE)));
        nameLayout.setVerticalGroup(nameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(nameLayout.createSequentialGroup().addContainerGap().addGroup(nameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel12).addComponent(approvalNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(70, Short.MAX_VALUE)));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        yrLevel.setBackground(new java.awt.Color(189, 224, 254));
        yrLevel.setForeground(new java.awt.Color(189, 224, 254));

        jLabel14.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Year Level:");

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane4.setViewportView(jTextArea5);

        approvalYrLvlField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout yrLevelLayout = new javax.swing.GroupLayout(yrLevel);
        yrLevel.setLayout(yrLevelLayout);
        yrLevelLayout.setHorizontalGroup(yrLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(yrLevelLayout.createSequentialGroup().addGap(18, 18, 18).addComponent(jLabel14).addGap(18, 18, 18).addComponent(approvalYrLvlField, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(54, Short.MAX_VALUE)));
        yrLevelLayout.setVerticalGroup(yrLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(yrLevelLayout.createSequentialGroup().addContainerGap().addGroup(yrLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel14).addComponent(approvalYrLvlField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(70, Short.MAX_VALUE)));

        javax.swing.GroupLayout studentDetailsLayout = new javax.swing.GroupLayout(studentDetails);
        studentDetails.setLayout(studentDetailsLayout);
        studentDetailsLayout.setHorizontalGroup(studentDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(studentDetailsLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(yrLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        studentDetailsLayout.setVerticalGroup(studentDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(yrLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        javax.swing.GroupLayout vieHistoryHeaderLayout = new javax.swing.GroupLayout(vieHistoryHeader);
        vieHistoryHeader.setLayout(vieHistoryHeaderLayout);
        vieHistoryHeaderLayout.setHorizontalGroup(vieHistoryHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(studentDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(vieHistoryHeaderLayout.createSequentialGroup().addGap(333, 333, 333).addComponent(jLabel13).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        vieHistoryHeaderLayout.setVerticalGroup(vieHistoryHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vieHistoryHeaderLayout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel13).addGap(18, 18, 18).addComponent(studentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

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
        userHistoryTableScrollPane.setViewportView(userHistoryTable);

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("history");

        javax.swing.GroupLayout viewHistoryLayout = new javax.swing.GroupLayout(viewHistory);
        viewHistory.setLayout(viewHistoryLayout);
        viewHistoryLayout.setHorizontalGroup(viewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(viewHistoryLayout.createSequentialGroup().addGroup(viewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(viewHistoryLayout.createSequentialGroup().addGap(500, 500, 500).addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(viewHistoryLayout.createSequentialGroup().addContainerGap().addComponent(userHistoryTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(47, Short.MAX_VALUE)));
        viewHistoryLayout.setVerticalGroup(viewHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewHistoryLayout.createSequentialGroup().addGap(12, 12, 12).addComponent(jLabel25).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(userHistoryTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE).addContainerGap()));

        backToUserManagementBtn.setText("Back");
        backToUserManagementBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backToUserManagementBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backToUserManagementBtn.setMaximumSize(new java.awt.Dimension(127, 23));
        backToUserManagementBtn.setMinimumSize(new java.awt.Dimension(127, 23));
        backToUserManagementBtn.setPreferredSize(new java.awt.Dimension(127, 23));
        backToUserManagementBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                backToUserManagementBtnActionPerformed(e);
            }
        });

        javax.swing.GroupLayout StudentHistoryPanelLayout = new javax.swing.GroupLayout(StudentHistoryPanel);
        StudentHistoryPanel.setLayout(StudentHistoryPanelLayout);
        StudentHistoryPanelLayout.setHorizontalGroup(StudentHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentHistoryPanelLayout.createSequentialGroup().addContainerGap(933, Short.MAX_VALUE).addComponent(backToUserManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(120, 120, 120)).addGroup(StudentHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(StudentHistoryPanelLayout.createSequentialGroup().addGap(18, 18, 18).addGroup(StudentHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(StudentHistoryPanelLayout.createSequentialGroup().addComponent(viewHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(19, 19, 19)).addGroup(StudentHistoryPanelLayout.createSequentialGroup().addComponent(vieHistoryHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))));
        StudentHistoryPanelLayout.setVerticalGroup(StudentHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(StudentHistoryPanelLayout.createSequentialGroup().addGap(50, 50, 50).addComponent(backToUserManagementBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(677, Short.MAX_VALUE)).addGroup(StudentHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(StudentHistoryPanelLayout.createSequentialGroup().addGap(38, 38, 38).addComponent(vieHistoryHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(viewHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(39, 39, 39))));

        MainPanel.add(StudentHistoryPanel, "card7");

        javax.swing.GroupLayout WindowLayout = new javax.swing.GroupLayout(Window);
        Window.setLayout(WindowLayout);
        WindowLayout.setHorizontalGroup(WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(WindowLayout.createSequentialGroup().addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()));
        WindowLayout.setVerticalGroup(WindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(Window, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(Window, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
        showHistory();

        pack();
    }

    /**
     * Handles the action when the Admin Profile button is clicked.
     * Hides unnecessary panels and displays the profile panel. Also, invokes the method to show the user's history.
     * @param e The ActionEvent object.
     */
    private void AdminProfileBtnActionPerformed(java.awt.event.ActionEvent e) {
        DashboardPanel.setVisible(false);
        UserManagementPanel.setVisible(false);
        ApprovalPanel.setVisible(false);
        ProfilePanel.setVisible(true);
        showHistory();
    }

    /**
     * Displays the user's history in the profile history table.
     */
    private void showHistory() {
        profileHistoryTable.setModel(profileHistoryTableModel);

        evalformArchive = databaseManager.getUserArchive(loggedUser.getUniqueID());
        profileHistoryTableModel.setRowCount(0);

        for (EvaluationForm archive : evalformArchive) {
            Event event = databaseManager.getEvent(archive.getEventID());
            String eventName = event.getName();

            profileHistoryTableModel.addRow(new Object[]{eventName, archive.getRole(), archive.getRolePoints()});
        }

        profileHistoryTableModel.fireTableDataChanged();
        profileHistoryTable.repaint();
    }

    /**
     * Handles the action when the Dashboard button is clicked.
     * Displays the dashboard panel and populates the events table.
     * @param e The ActionEvent object.
     */
    private void DashboardBtnActionPerformed(java.awt.event.ActionEvent e) {
        DashboardPanel.setVisible(true);
        UserManagementPanel.setVisible(false);
        ApprovalPanel.setVisible(false);
        ProfilePanel.setVisible(false);

        eventsTable.setModel(eventsTableModel);
        events = databaseManager.getAllEvents();
        eventsTableModel.setRowCount(0);

        for (Event event :events) {
            LocalDate date = LocalDate.parse(event.getDate());
            String eventDate = date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

            eventsTableModel.addRow(new Object[]{event.getName(), event.getLocation(), eventDate, event.getType()});
        }

        eventsTableModel.fireTableDataChanged();
        eventsTable.repaint();
    }

    /**
     * Handles the action when the start time button is clicked.
     * Shows the time picker popup and sets the display text to the start time field.
     * @param e The ActionEvent object.
     */
    private void startTimeBtnActionPerformed(java.awt.event.ActionEvent e) {
        timePicker.showPopup(null, 100, 100);
        timePicker.setDisplayText(eventStartTimeField);
    }

    /**
     * Handles the action when the end time button is clicked.
     * Shows the time picker popup and sets the display text to the end time field.
     * @param e The ActionEvent object.
     */
    private void endTimeBtnActionPerformed(java.awt.event.ActionEvent e) {
        timePicker.showPopup(null, 100, 100);
        timePicker.setDisplayText(eventEndTimeField);
    }

    /**
     * Handles the action when the create event button is clicked.
     * Collects event data, inserts it into the database, and updates the events table.
     * @param e The ActionEvent object.
     */
    private void createEventBtnActionPerformed(java.awt.event.ActionEvent e) {
        String eventID = String.valueOf(UUID.randomUUID());
        String eventName = eventNameField.getText();
        String eventLocation = eventLocationField.getText();
        String eventDate = eventDateField.getText();
        String startTime = eventStartTimeField.getText();
        String endTime = eventEndTimeField.getText();
        String eventType = eventTypeField.getText();
        String eventMode = eventModeField.getText();
        String roles = eventRole1Field.getText() + "," + eventRole2Field.getText() + "," + eventRole3Field.getText();
        String rolePoints = eventRolePoints1Field.getText() + "," + eventRolePoints2Field.getText() + "," + eventRolePoints3Field.getText();

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

    /**
     * Handles the action when the User Management button is clicked.
     * Displays the user management panel and populates the user management table.
     * @param e The ActionEvent object.
     */
    private void UserManagementBtnActionPerformed(java.awt.event.ActionEvent e) {
        DashboardPanel.setVisible(false);
        UserManagementPanel.setVisible(true);
        ApprovalPanel.setVisible(false);
        ProfilePanel.setVisible(false);

        umTable.setModel(umTableModel);
        users = databaseManager.getAllStudents();
        umTableModel.setRowCount(0);

        for (User user : users) {
            umTableModel.addRow(new Object[]{user.getFullName(), user.getEmail(), user.getIDNumber(), ((Student) user).getYearLevel(), user.getCESPoints()});
        }

        umTableModel.fireTableDataChanged();
        umTable.repaint();
    }

    /**
     * ActionListener for the year level filter buttons in the user management panel.
     * Filters users based on the selected year level and updates the user management table accordingly.
     */
    private final ActionListener yearLevelFilterListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
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

    /**
     * Handles the action when the search field content changes.
     * Filters users based on the search text and updates the user management table accordingly.
     * @param e The ActionEvent object.
     */
    private void searchFieldActionListener(java.awt.event.ActionEvent e) {
        String searchText = searchField.getText().toLowerCase();

        umTable.setModel(umTableModel);
        users = databaseManager.getAllUser();
        umTableModel.setRowCount(0);

        for (User user : users) {
            if ("Student".equals(user.getType())) {
                String fullName = user.getFullName().toLowerCase();
                String email = user.getEmail().toLowerCase();
                String idNumber = String.valueOf(user.getIDNumber()).toLowerCase();

                if (fullName.contains(searchText) || email.contains(searchText) || idNumber.contains(searchText)) {
                    umTableModel.addRow(new Object[]{user.getFullName(), user.getEmail(), user.getIDNumber(), ((Student) user).getYearLevel(), user.getCESPoints()});
                }
            }
        }

        umTableModel.fireTableDataChanged();
        umTable.repaint();
    }

    /**
     * Handles the action when the edit button is clicked.
     * Allows editing of CES points for the selected user.
     * @param e The ActionEvent object.
     */
    private void editBtnActionPerformed(java.awt.event.ActionEvent e) {
        int selectedRow = umTable.getSelectedRow();

        if (selectedRow != -1) {
            User selectedUser = users.get(selectedRow);
            String selectedUserID = selectedUser.getUniqueID();

            String newCESPointsString = JOptionPane.showInputDialog(null, "Enter new CES Points for " + selectedUser.getFullName() + ":");

            if (newCESPointsString != null && newCESPointsString.matches("\\d+")) {
                int newCESPoints = Integer.parseInt(newCESPointsString);

                databaseManager.updateUserCESPoints(selectedUserID, newCESPoints);
                UserManagementBtnActionPerformed(e);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer for CES Points.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the delete button is clicked.
     * Allows deleting the selected user and updates the user management table.
     * @param e The ActionEvent object.
     */
    private void deletedBtnActionPerformed(java.awt.event.ActionEvent e) {
        int selectedRow = umTable.getSelectedRow();

        if (selectedRow != -1) {
            User selectedUser = users.get(selectedRow);

            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                databaseManager.deleteUser(selectedUser.getUniqueID());
                UserManagementBtnActionPerformed(e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the "View History" button is clicked.
     * Displays the user's evaluation history in a separate panel.
     * @param e The ActionEvent object.
     */
    private void viewHistoryBtnActionPerformed(java.awt.event.ActionEvent e) {
        int selectedRow = umTable.getSelectedRow();

        if (selectedRow != -1) {
            User selectedUser = users.get(selectedRow);
            String selectedUserID = selectedUser.getUniqueID();

            approvalNameField.setText(selectedUser.getFullName());

            if (selectedUser instanceof Student student) {
                approvalYrLvlField.setText(student.getYearLevel());
            } else {
                approvalYrLvlField.setText("N/A");
            }

            userHistoryTable.setModel(userHistoryTableModel);

            evalformArchive = databaseManager.getUserArchive(selectedUserID);
            userHistoryTableModel.setRowCount(0);

            for (EvaluationForm archive :evalformArchive) {
                Event event = databaseManager.getEvent(archive.getEventID());

                userHistoryTableModel.addRow(new Object[]{event.getName(), event.getLocation(), event.getDate(), event.getType(), event.getMode(), archive.getRole(), archive.getRolePoints()});
            }

            userHistoryTableModel.fireTableDataChanged();
            userHistoryTable.repaint();

            UserManagementPanel.setVisible(false);
            StudentHistoryPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to view.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the "Back to User Management" button is clicked.
     * Returns to the User Management panel from the Student History panel.
     * @param e The ActionEvent object.
     */
    private void backToUserManagementBtnActionPerformed(java.awt.event.ActionEvent e) {
        StudentHistoryPanel.setVisible(false);
        UserManagementPanel.setVisible(true);
    }

    /**
     * Handles the action when the "Approval" button is clicked.
     * Displays the Approval panel and populates the approval table with evaluation form data.
     * @param e The ActionEvent object.
     */
    private void ApprovalBtnActionPerformed(java.awt.event.ActionEvent e) {
        DashboardPanel.setVisible(false);
        UserManagementPanel.setVisible(false);
        ApprovalPanel.setVisible(true);
        ProfilePanel.setVisible(false);

        approvalTable.setModel(approvalTableModel);
        evalFormDataList = databaseManager.getAllEvalFormData();
        approvalTableModel.setRowCount(0);

        for (EvaluationForm evalFormData : evalFormDataList) {
            Event event = databaseManager.getEvent(evalFormData.getEventID());
            String userName = databaseManager.getUserName(evalFormData.getUserID());
            String eventName = event.getName();
            String submitted_atDate = new SimpleDateFormat("MMMM dd, yyyy").format(evalFormData.getSubmitted_at());

            approvalTableModel.addRow(new Object[]{userName, eventName, submitted_atDate});
        }

        approvalTableModel.fireTableDataChanged();
        approvalTable.repaint();
    }

    /**
     * Handles the action when the "View Form" button is clicked in the Approval panel.
     * Displays the details of the selected evaluation form in a separate panel.
     * @param e The ActionEvent object.
     */
    private void approvalViewFormBtnActionPerformed(java.awt.event.ActionEvent e) {
        int selectedRow = approvalTable.getSelectedRow();

        if (selectedRow != -1) {
            EvaluationForm selectedForm = evalFormDataList.get(selectedRow);
            String selectedFormID = selectedForm.getEvalformID();
            EvaluationForm viewedForm = databaseManager.getEvalFormData(selectedFormID);
            Event event = databaseManager.getEvent(viewedForm.getEventID());

            ApprovalPanel.setVisible(false);
            ViewEvalFormPanel.setVisible(true);

            eventNameTitle.setText(event.getName());
            question1Field.setText(viewedForm.getQOne());
            question2Field.setText(viewedForm.getQTwo());
            question3Field.setText(viewedForm.getQThree());
            question4Field.setText(viewedForm.getQFour());
            question5Field.setText(viewedForm.getQFive());
            setRatingInButtonGroup(viewedForm.getRating());
            roleTitle.setText(viewedForm.getRole());
            beginningImg.setIcon(viewedForm.getBeginningImg());
            middleImg.setIcon(viewedForm.getMiddleImg());
            endImg.setIcon(viewedForm.getEndImg());

            eventNameTitle.setEnabled(false);
            question1Field.setEditable(false);
            question2Field.setEditable(false);
            question3Field.setEditable(false);
            question4Field.setEditable(false);
            question5Field.setEditable(false);
            roleTitle.setEnabled(false);
            angryRadioButton.setEnabled(false);
            disappointedRadioButton.setEnabled(false);
            neutralRadioButton.setEnabled(false);
            goodRadioButton.setEnabled(false);
            loveEmojisRadioButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to view.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the "Back to Approval" button is clicked.
     * Returns to the Approval panel from the View Evaluation Form panel.
     * @param e The ActionEvent object.
     */
    private void backToApprovalBtnActionPerformed(java.awt.event.ActionEvent e) {
        ViewEvalFormPanel.setVisible(false);
        ApprovalPanel.setVisible(true);
    }

    /**
     * Handles the action when the "Approve Evaluation Form" button is clicked.
     * Approves the selected evaluation form, updates user CES points, archives the form, and deletes it.
     * @param e The ActionEvent object.
     */
    private void approveEvalFormBtnActionPerformed(java.awt.event.ActionEvent e) {
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
                String submitted_atDate = new SimpleDateFormat("MMMM dd, yyyy").format(evalFormData.getSubmitted_at());

                approvalTableModel.addRow(new Object[]{userName, eventName, submitted_atDate});
            }

            approvalTableModel.fireTableDataChanged();
            approvalTable.repaint();

            ViewEvalFormPanel.setVisible(false);
            ApprovalPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to approve.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the "Disapprove Evaluation Form" button is clicked.
     * Deletes the selected evaluation form and updates the approval table.
     * @param e The ActionEvent object.
     */
    private void disapproveEvalFormBtnActionPerformed(java.awt.event.ActionEvent e) {
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
                String submitted_atDate = new SimpleDateFormat("MMMM dd, yyyy").format(evalFormData.getSubmitted_at());

                approvalTableModel.addRow(new Object[]{userName, eventName, submitted_atDate});
            }

            approvalTableModel.fireTableDataChanged();
            approvalTable.repaint();

            ViewEvalFormPanel.setVisible(false);
            ApprovalBtn.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to approve.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the "Admin Logout" button is clicked.
     * Clears logged user information, closes the current window, and opens the main window.
     * @param e The ActionEvent object.
     */
    private void AdminLogoutActionPerformed(java.awt.event.ActionEvent e) {
        try {
            FileWriter loggedUserTXT = new FileWriter("loggedUser.txt");
            loggedUserTXT.write("");
            loggedUserTXT.close();

            FileWriter settingsCONFIG = new FileWriter("settings.config");
            settingsCONFIG.write("");
            settingsCONFIG.close();

            dispose();
            MainWindow mainWindow = new MainWindow();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the action when the "Change Password" button is clicked.
     * Displays a dialog for changing the user's password.
     * @param e The ActionEvent object.
     */
    private void changePassActionPerformed(java.awt.event.ActionEvent e) {
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

    /**
     * Sets the selected radio button in the rating button group based on the provided rating.
     * @param rating The rating value.
     */
    private void setRatingInButtonGroup(String rating) {
        switch (rating) {
            case "Angry":
                radioBtnGroup.setSelected(angryRadioButton.getModel(), true);
                break;
            case "Disappointed":
                radioBtnGroup.setSelected(disappointedRadioButton.getModel(), true);
                break;
            case "Neutral":
                radioBtnGroup.setSelected(neutralRadioButton.getModel(), true);
                break;
            case "Good":
                radioBtnGroup.setSelected(goodRadioButton.getModel(), true);
                break;
            case "Love Emojis":
                radioBtnGroup.setSelected(loveEmojisRadioButton.getModel(), true);
                break;
            default:
                break;
        }
    }

    /**
     * Updates the labels displaying user information in the UI.
     * @param id The unique ID of the logged-in user.
     */
    public void updateLoggedInUserInfoLabels(String id) {
        User userProfile = databaseManager.getUser(id);

        if (userProfile != null) {
            String name = userProfile.getFullName();
            String idNumber = String.valueOf(userProfile.getIDNumber());
            int cesPoints = userProfile.getCESPoints();

            userName.setText(name);
            userIDnum.setText(idNumber);
            userCourse.setText("BS Computer Engineering");
            userPoints.setText(String.valueOf(cesPoints));
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
