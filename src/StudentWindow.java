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

public class StudentWindow extends javax.swing.JFrame {
    private javax.swing.JButton beginningBtn;
    private javax.swing.JPanel EvalForm;
    private javax.swing.JScrollPane HistoryScrollPane;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton middleBtn;
    private javax.swing.JPanel Profile;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JButton StudentLogout;
    private javax.swing.JButton StudentProfileBtn;
    private javax.swing.JPanel StudentSP;
    private javax.swing.JPanel UserCES;
    private javax.swing.JPanel UserEdit;
    private javax.swing.JPanel UserHistory;
    private javax.swing.JPanel UserInfo;
    private javax.swing.JPanel Window;
    private javax.swing.JRadioButton angryRadioButton;
    private javax.swing.JLabel beginningImage;
    private javax.swing.JButton changePass;
    private javax.swing.JRadioButton disappointedRadioButton;
    private javax.swing.JLabel endImage;
    private javax.swing.JButton evaluationForm;
    private javax.swing.JComboBox<String> eventsBox;
    private javax.swing.JRadioButton goodRadioButton;
    private javax.swing.JLabel greetingsLabel;
    private javax.swing.JButton endBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton loveEmojisRadioButton;
    private javax.swing.JLabel middleImage;
    private javax.swing.JRadioButton neutralRadioButton;
    private javax.swing.JTable profileHistoryTable;
    private javax.swing.JTextField question1Field;
    private javax.swing.JTextField question2Field;
    private javax.swing.JTextField question3Field;
    private javax.swing.JTextField question4Field;
    private javax.swing.JTextField question5Field;
    private javax.swing.JComboBox<String> rolesBox;
    private javax.swing.JButton setYrLvlBtn;
    private javax.swing.JButton submitEvalFormBtn;
    private javax.swing.JPanel userContainer;
    private javax.swing.JLabel userCourse;
    private javax.swing.JLabel userIDnum;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel userPoints;
    private javax.swing.JLabel logo;

    private DefaultTableModel profileHistoryTableModel;

    private DatabaseManager databaseManager;
    private Controller controller;

    private User loggedUser;
    private List<Event> events;
    private List<EvaluationForm> evalformArchive;

    private ButtonGroup radioBtnGroup;

    StudentWindow(User loggedUser) {
        this.loggedUser = loggedUser;
        initComponents();
    }

    private void initComponents() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();

        Window = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        StudentSP = new javax.swing.JPanel();
        StudentLogout = new javax.swing.JButton();
        StudentProfileBtn = new javax.swing.JButton();
        evaluationForm = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        Profile = new javax.swing.JPanel();
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
        EvalForm = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        greetingsLabel = new javax.swing.JLabel();
        eventsBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        question1Field = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        question2Field = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        question3Field = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        question4Field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        question5Field = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rolesBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        angryRadioButton = new javax.swing.JRadioButton();
        disappointedRadioButton = new javax.swing.JRadioButton();
        neutralRadioButton = new javax.swing.JRadioButton();
        goodRadioButton = new javax.swing.JRadioButton();
        loveEmojisRadioButton = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        beginningBtn = new javax.swing.JButton();
        middleBtn = new javax.swing.JButton();
        endBtn = new javax.swing.JButton();
        submitEvalFormBtn = new javax.swing.JButton();
        beginningImage = new javax.swing.JLabel();
        middleImage = new javax.swing.JLabel();
        endImage = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(angryRadioButton);
        radioBtnGroup.add(disappointedRadioButton);
        radioBtnGroup.add(neutralRadioButton);
        radioBtnGroup.add(goodRadioButton);
        radioBtnGroup.add(loveEmojisRadioButton);

        Profile.setVisible(true);
        EvalForm.setVisible(false);

        controller.checkButtonState(setYrLvlBtn);
        beginningBtn.addActionListener(selectImageListener);
        middleBtn.addActionListener(selectImageListener);
        endBtn.addActionListener(selectImageListener);
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);

        show();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CES Tracker - Student");
        setMaximumSize(new java.awt.Dimension(1350, 750));
        setMinimumSize(new java.awt.Dimension(1350, 750));
        setPreferredSize(new java.awt.Dimension(1350, 750));
        setLocationRelativeTo(null);
        setResizable(false);

        Window.setBackground(new java.awt.Color(255, 255, 255));
        SidePanel.setBackground(new java.awt.Color(24, 29, 49));
        StudentSP.setBackground(new java.awt.Color(24, 29, 49));

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        StudentLogout.setText("Log Out");
        StudentLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StudentLogoutActionPerformed(e);
            }
        });

        StudentProfileBtn.setText("Profile");
        StudentProfileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                StudentProfileBtnActionPerformed(e);
            }
        });

        evaluationForm.setText("Evaluation Form");
        evaluationForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                evaluationFormActionPerformed(e);
            }
        });

        javax.swing.GroupLayout StudentSPLayout = new javax.swing.GroupLayout(StudentSP);
        StudentSP.setLayout(StudentSPLayout);
        StudentSPLayout.setHorizontalGroup(StudentSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(evaluationForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(StudentProfileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(StudentLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentSPLayout.createSequentialGroup().addContainerGap().addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
        StudentSPLayout.setVerticalGroup(StudentSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentSPLayout.createSequentialGroup().addGap(81, 81, 81).addComponent(logo).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(StudentProfileBtn).addGap(32, 32, 32).addComponent(evaluationForm).addGap(244, 244, 244).addComponent(StudentLogout).addGap(68, 68, 68)));

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SidePanelLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(StudentSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE)));
        SidePanelLayout.setVerticalGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(StudentSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainPanel.setLayout(new java.awt.CardLayout());

        Profile.setBackground(new java.awt.Color(255, 255, 255));
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
        setYrLvlBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setYrLvlBtnActionPerformed(e);
            }
        });

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
        UserInfoLayout.setHorizontalGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserInfoLayout.createSequentialGroup().addContainerGap(20, Short.MAX_VALUE).addComponent(userContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(UserCES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(100, 100, 100).addComponent(UserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(20, 20, 20)));
        UserInfoLayout.setVerticalGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserInfoLayout.createSequentialGroup().addGap(10, 10, 10).addGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserInfoLayout.createSequentialGroup().addGroup(UserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(userContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(UserEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGap(10, 10, 10)).addGroup(UserInfoLayout.createSequentialGroup().addComponent(UserCES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel9).addGap(0, 15, Short.MAX_VALUE)))));

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
        UserHistoryLayout.setHorizontalGroup(UserHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(HistoryScrollPane));
        UserHistoryLayout.setVerticalGroup(UserHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(UserHistoryLayout.createSequentialGroup().addComponent(HistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 20, Short.MAX_VALUE)));

        javax.swing.GroupLayout ProfileLayout = new javax.swing.GroupLayout(Profile);
        Profile.setLayout(ProfileLayout);
        ProfileLayout.setHorizontalGroup(ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(ProfileLayout.createSequentialGroup().addGap(20, 20, 20).addGroup(ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(UserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(UserHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(14, Short.MAX_VALUE)));
        ProfileLayout.setVerticalGroup(ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(ProfileLayout.createSequentialGroup().addGap(20, 20, 20).addComponent(UserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(18, 18, 18).addComponent(UserHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));

        MainPanel.add(Profile, "card2");

        EvalForm.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        greetingsLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        greetingsLabel.setText("Evaluation Form");
        eventsBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLabel1.setText("1. I saw that...");
        jLabel2.setText("2. I heard that...");
        jLabel3.setText("4. With what I experienced, I will...");
        jLabel4.setText("5. The exposure activity made me think that...");
        jLabel5.setText("3. I felt that...");
        jLabel6.setText("Role in the Activity");
        rolesBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jLabel7.setText("Please check your overall rating in the CES exposure activity");
        angryRadioButton.setText("Angry");
        disappointedRadioButton.setText("Disappointed");
        neutralRadioButton.setText("Neutral");
        goodRadioButton.setText("Good");
        loveEmojisRadioButton.setText("Love");
        jLabel8.setText("Documentation");
        beginningBtn.setText("Beginning");
        middleBtn.setText("Middle");
        endBtn.setText("End");

        submitEvalFormBtn.setText("Submit Evaluation");
        submitEvalFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                submitEvalFormBtnActionPerformed(e);
            }
        });

        beginningImage.setMaximumSize(new java.awt.Dimension(200, 200));
        beginningImage.setMinimumSize(new java.awt.Dimension(200, 200));

        middleImage.setMaximumSize(new java.awt.Dimension(200, 200));
        middleImage.setMinimumSize(new java.awt.Dimension(200, 200));

        endImage.setMaximumSize(new java.awt.Dimension(200, 200));
        endImage.setMinimumSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(56, 56, 56).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(jPanel2Layout.createSequentialGroup().addComponent(angryRadioButton).addGap(18, 18, 18).addComponent(disappointedRadioButton).addGap(18, 18, 18).addComponent(neutralRadioButton).addGap(18, 18, 18).addComponent(goodRadioButton).addGap(18, 18, 18).addComponent(loveEmojisRadioButton)).addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(greetingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE).addComponent(eventsBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE).addComponent(rolesBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(question1Field).addComponent(question2Field).addComponent(question3Field).addComponent(question4Field).addComponent(question5Field, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE).addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(beginningBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(beginningImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(middleBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(middleImage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(endImage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(endBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))).addComponent(submitEvalFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)).addContainerGap(292, Short.MAX_VALUE)));
        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {beginningBtn, middleBtn, endBtn, beginningImage, endImage, middleImage});
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(greetingsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(eventsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question3Field, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question4Field, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(question5Field, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel6).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(rolesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel7).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(angryRadioButton).addComponent(disappointedRadioButton).addComponent(neutralRadioButton).addComponent(goodRadioButton).addComponent(loveEmojisRadioButton)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel8).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(beginningBtn).addComponent(middleBtn).addComponent(endBtn)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(beginningImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(middleImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(endImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(submitEvalFormBtn).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1134, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(20, 20, 20).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE).addContainerGap()));

        javax.swing.GroupLayout EvalFormLayout = new javax.swing.GroupLayout(EvalForm);
        EvalForm.setLayout(EvalFormLayout);
        EvalFormLayout.setHorizontalGroup(EvalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(EvalFormLayout.createSequentialGroup().addContainerGap().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        EvalFormLayout.setVerticalGroup(EvalFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(EvalFormLayout.createSequentialGroup().addContainerGap().addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));

        MainPanel.add(EvalForm, "card3");

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
     * Displays the student profile, hides the evaluation form, and shows the CES Points/Events history.
     * @param e - The action event.
     */
    private void StudentProfileBtnActionPerformed(java.awt.event.ActionEvent e) {
        Profile.setVisible(true);
        EvalForm.setVisible(false);
        showHistory();
    }

    /**
     * Displays the CES Points/Events history in the profile history table.
     */
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

    /**
     * Handles the evaluation form button action, switches to the evaluation form, and populates event and role dropdowns.
     * @param e - The action event.
     */
    private void evaluationFormActionPerformed(java.awt.event.ActionEvent e) {
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

    /**
     * Handles the submit evaluation form button action, validates form input, and inserts data into the database.
     * @param e - The action event.
     */
    private void submitEvalFormBtnActionPerformed(java.awt.event.ActionEvent e) {
        try {
            String userID = loggedUser.getUniqueID();

            String selectedEventName = (String) eventsBox.getSelectedItem();

            String evalformID = String.valueOf(UUID.randomUUID());
            String eventID = getEventIDFromName(selectedEventName);
            String qOne = question1Field.getText();
            String qTwo = question2Field.getText();
            String qThree = question3Field.getText();
            String qFour = question4Field.getText();
            String qFive = question5Field.getText();
            String role = (String) rolesBox.getSelectedItem();
            int rolePoints = databaseManager.getRolePoints(eventID, role);
            String rating = getSelectedRadioValue();
            ImageIcon beginningImg = (ImageIcon) beginningImage.getIcon();
            ImageIcon middleImg = (ImageIcon) middleImage.getIcon();
            ImageIcon endImg = (ImageIcon) endImage.getIcon();

            if (beginningImg != null && middleImg != null && endImg != null) {
                databaseManager.insertEvalForm(evalformID, userID, eventID, qOne, qTwo, qThree, qFour, qFive, role, rolePoints, rating, beginningImg, middleImg, endImg);
                clearEvalFormFields();
            } else {
                JOptionPane.showMessageDialog(null, "Please select images for all three categories.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the student logout action, clears user session files, and opens the main window.
     * @param e - The action event.
     */
    private void StudentLogoutActionPerformed(java.awt.event.ActionEvent e) {
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
     * Handles the set year level button action, allows a student to set their year level, and updates UI.
     * @param e - The action event.
     */
    private void setYrLvlBtnActionPerformed(java.awt.event.ActionEvent e) {
        if (loggedUser != null && "Student".equals(loggedUser.getType())) {
            String[] yearOptions = {"Year 1", "Year 2", "Year 3", "Year 4", "Batch X"};

            String selectedOption = (String) JOptionPane.showInputDialog(null, "Select Year Level", "Year Level Selection",
                    JOptionPane.QUESTION_MESSAGE, null, yearOptions, yearOptions[0]);

            if (selectedOption != null) {
                ((Student) loggedUser).setYearLevel(selectedOption);
                databaseManager.setYearLevel(loggedUser.getUniqueID(), selectedOption);
                updateLoggedInUserInfoLabels(loggedUser.getUniqueID());

                setYrLvlBtn.setEnabled(false);

                try (BufferedWriter bw = new BufferedWriter(new FileWriter("settings.config"))) {
                    bw.write("disabled");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Year level can only be set for students.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the change password action, prompts the user to enter a new password, and updates it in the database.
     * @param e - The action event.
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
     * Clears all fields in the evaluation form.
     */
    private void clearEvalFormFields() {
        eventsBox.setSelectedIndex(0);
        rolesBox.setSelectedIndex(0);
        question1Field.setText("");
        question2Field.setText("");
        question3Field.setText("");
        question4Field.setText("");
        question5Field.setText("");
        beginningImage.setIcon(null);
        middleImage.setIcon(null);
        endImage.setIcon(null);
        angryRadioButton.setSelected(false);
        disappointedRadioButton.setSelected(false);
        neutralRadioButton.setSelected(false);
        goodRadioButton.setSelected(false);
        loveEmojisRadioButton.setSelected(false);
    }

    /**
     * Selects an image for a specific image category based on the source button.
     * @param sourceBtn - The source button triggering the action.
     */
    private void selectImage(JButton sourceBtn) {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int response = chooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            try {
                BufferedImage img = ImageIO.read(selectedFile);
                Image resizedImage = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(resizedImage);

                if (sourceBtn == beginningBtn) {
                    beginningImage.setIcon(icon);
                } else if (sourceBtn == middleBtn) {
                    middleImage.setIcon(icon);
                } else if (sourceBtn == endBtn) {
                    endImage.setIcon(icon);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Action listener for selecting an image from file chooser.
     */
    private final ActionListener selectImageListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceBtn = (JButton) e.getSource();
            selectImage(sourceBtn);
        }
    };

    /**
     * Retrieves the event ID based on the event name.
     * @param eventName - The name of the event.
     * @returns The unique ID of the event.
     */
    private String getEventIDFromName(String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event.getUniqueID();
            }
        }
        return null;
    }

    /**
     * Retrieves the value of the selected radio button for the rating.
     * @returns The selected rating value.
     */
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

    /**
     * Updates the labels displaying the user information in the logged-in state.
     * @param id - The unique ID of the logged-in user.
     */
    public void updateLoggedInUserInfoLabels(String id) {
        User userProfile = databaseManager.getUser(id);

        if (userProfile != null) {
            String name = userProfile.getFullName();
            String idNumber = String.valueOf(userProfile.getIDNumber());
            int cesPoints = userProfile.getCESPoints();

            userName.setText(name);
            userIDnum.setText(idNumber);
            userCourse.setText("BS Computer Engineering - " + ((Student) userProfile).getYearLevel());
            userPoints.setText(String.valueOf(cesPoints));
        } else {
            JOptionPane.showMessageDialog(null, "User not found. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
