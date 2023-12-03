import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class MainWindow extends javax.swing.JFrame {
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPanel SignUpPanel;
    private javax.swing.JTextField emailField1;
    private javax.swing.JTextField emailField2;
    private javax.swing.JButton forgotPasswordBtn;
    private javax.swing.JButton goToLoginBtn;
    private javax.swing.JButton goToSignUpBtn;
    private javax.swing.JTextField idNumberField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton loginBtn;
    private javax.swing.JTextField nameField;
    private javax.swing.JPasswordField passwordField2;
    private javax.swing.JPasswordField passwordField1;
    private javax.swing.JButton signUpBtn;

    private DatabaseManager databaseManager;
    private Controller controller;

    private User loggedUser;

    public MainWindow() {
        initComponents();
    }

    private void initComponents() {
        databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/oop", "root", "");
        controller = new Controller();

        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SignUpPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        emailField1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        signUpBtn = new javax.swing.JButton();
        idNumberField = new javax.swing.JTextField();
        goToLoginBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        passwordField1 = new javax.swing.JPasswordField();
        LoginPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        emailField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        goToSignUpBtn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        forgotPasswordBtn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        passwordField2 = new javax.swing.JPasswordField();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pics-removebg-preview.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.getAccessibleContext().setAccessibleParent(jPanel3);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 117, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        show();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(189, 224, 254));
        setTitle("CES Tracker");
        setMaximumSize(new java.awt.Dimension(1350, 750));
        setMinimumSize(new java.awt.Dimension(1350, 750));
        setPreferredSize(new java.awt.Dimension(1350, 750));
        setLocationRelativeTo(null);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(189, 224, 254));
        jPanel1.setPreferredSize(new java.awt.Dimension(1350, 750));

        jPanel3.setBackground(new java.awt.Color(0, 48, 73));
        jPanel3.setPreferredSize(new java.awt.Dimension(540, 750));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/sheeshers (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup().addComponent(jLabel4).addGap(0, 0, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 713, Short.MAX_VALUE).addContainerGap()));

        jPanel2.setLayout(new java.awt.CardLayout());

        SignUpPanel.setBackground(new java.awt.Color(189, 224, 254));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Name:");
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Email:");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Password:");
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("ID Number:");
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel14.setText("Already have an account?");

        signUpBtn.setBackground(new java.awt.Color(0, 48, 73));
        signUpBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        signUpBtn.setForeground(new java.awt.Color(255, 255, 255));
        signUpBtn.setText("Sign Up");
        signUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                signUpBtnActionPerformed(e);
            }
        });

        goToLoginBtn.setBackground(new java.awt.Color(189, 224, 254));
        goToLoginBtn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        goToLoginBtn.setText("Log In");
        goToLoginBtn.setToolTipText("");
        goToLoginBtn.setBorder(null);
        goToLoginBtn.setBorderPainted(false);
        goToLoginBtn.setContentAreaFilled(false);
        goToLoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                goToLoginBtnActionPerformed(e);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon2 (1).png"))); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon3 (1).png"))); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon4 (1).png"))); // NOI18N
        jLabel9.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Welcome to CES Tracker");
        jLabel8.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 90)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Hi there!");

        javax.swing.GroupLayout SignUpPanelLayout = new javax.swing.GroupLayout(SignUpPanel);
        SignUpPanel.setLayout(SignUpPanelLayout);
        SignUpPanelLayout.setHorizontalGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SignUpPanelLayout.createSequentialGroup().addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SignUpPanelLayout.createSequentialGroup().addGap(188, 188, 188).addComponent(jLabel6).addGap(52, 52, 52).addComponent(jLabel1).addGap(50, 50, 50).addComponent(jLabel7)).addGroup(SignUpPanelLayout.createSequentialGroup().addGap(158, 158, 158).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SignUpPanelLayout.createSequentialGroup().addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel10).addComponent(jLabel11).addComponent(jLabel12)).addGap(33, 33, 33).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(emailField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(nameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(SignUpPanelLayout.createSequentialGroup().addComponent(passwordField1).addGap(1, 1, 1)))).addGroup(SignUpPanelLayout.createSequentialGroup().addComponent(jLabel13).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(signUpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE).addGroup(javax.swing.GroupLayout.Alignment.LEADING, SignUpPanelLayout.createSequentialGroup().addGap(69, 69, 69).addComponent(jLabel14).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(goToLoginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))).addGroup(SignUpPanelLayout.createSequentialGroup().addGap(22, 22, 22).addComponent(idNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))))).addGroup(SignUpPanelLayout.createSequentialGroup().addContainerGap().addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        SignUpPanelLayout.setVerticalGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(SignUpPanelLayout.createSequentialGroup().addContainerGap(84, Short.MAX_VALUE).addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(4, 4, 4).addComponent(jLabel9).addGap(55, 55, 55).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel10).addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel11).addComponent(emailField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel12).addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel13).addComponent(idNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(33, 33, 33).addComponent(signUpBtn).addGap(18, 18, 18).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(goToLoginBtn).addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(80, 80, 80).addGroup(SignUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel1).addComponent(jLabel6).addComponent(jLabel7)).addGap(91, 91, 91)));

        jPanel2.add(LoginPanel, "card2");
        jPanel2.add(SignUpPanel, "card3");

        LoginPanel.setBackground(new java.awt.Color(189, 224, 254));
        LoginPanel.setPreferredSize(new java.awt.Dimension(500, 504));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Email:");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Password:");

        loginBtn.setBackground(new java.awt.Color(0, 48, 73));
        loginBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginBtn.setForeground(new java.awt.Color(255, 255, 255));
        loginBtn.setText("Log In");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                loginBtnActionPerformed(e);
            }
        });

        goToSignUpBtn.setBackground(new java.awt.Color(189, 224, 254));
        goToSignUpBtn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        goToSignUpBtn.setText("Sign Up");
        goToSignUpBtn.setToolTipText("");
        goToSignUpBtn.setBorder(null);
        goToSignUpBtn.setBorderPainted(false);
        goToSignUpBtn.setContentAreaFilled(false);
        goToSignUpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                goToSignUpBtnActionPerformed(e);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel15.setText("Don't have an account?");

        forgotPasswordBtn.setBackground(new java.awt.Color(189, 224, 254));
        forgotPasswordBtn.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        forgotPasswordBtn.setText("Forgot Password?");
        forgotPasswordBtn.setToolTipText("");
        forgotPasswordBtn.setBorder(null);
        forgotPasswordBtn.setBorderPainted(false);
        forgotPasswordBtn.setContentAreaFilled(false);
        forgotPasswordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                forgotPasswordBtnActionPerformed(e);
            }
        });

        jLabel21.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 90)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Hi there!");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon2 (1).png"))); // NOI18N
        jLabel23.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Welcome to CES Tracker");
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon3 (1).png"))); // NOI18N
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon4 (1).png"))); // NOI18N

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LoginPanelLayout.createSequentialGroup().addGap(144, 144, 144).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LoginPanelLayout.createSequentialGroup().addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel3).addComponent(jLabel5)).addGap(32, 32, 32).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(emailField2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(forgotPasswordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(passwordField2))).addGroup(LoginPanelLayout.createSequentialGroup().addGap(154, 154, 154).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LoginPanelLayout.createSequentialGroup().addGap(6, 6, 6).addComponent(jLabel15).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(goToSignUpBtn)).addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap(240, Short.MAX_VALUE)).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LoginPanelLayout.createSequentialGroup().addContainerGap().addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LoginPanelLayout.createSequentialGroup().addGap(182, 182, 182).addComponent(jLabel24).addGap(52, 52, 52).addComponent(jLabel22).addGap(50, 50, 50).addComponent(jLabel25)).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
        LoginPanelLayout.setVerticalGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginPanelLayout.createSequentialGroup().addContainerGap(306, Short.MAX_VALUE).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel3).addComponent(emailField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(forgotPasswordBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(22, 22, 22).addComponent(loginBtn).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(goToSignUpBtn)).addGap(281, 281, 281)).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(LoginPanelLayout.createSequentialGroup().addGap(87, 87, 87).addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(4, 4, 4).addComponent(jLabel23).addGap(378, 378, 378).addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel22).addComponent(jLabel24).addComponent(jLabel25)).addContainerGap(88, Short.MAX_VALUE))));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 782, Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1358, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE));

        pack();
    }

    /**
     * Switches to the login panel and hides the sign-up panel.
     * @param e - The action event.
     */
    private void goToLoginBtnActionPerformed(java.awt.event.ActionEvent e) {
        SignUpPanel.setVisible(false);
        LoginPanel.setVisible(true);
    }

    /**
     * Switches to the sign-up panel and hides the login panel.
     * @param e - The action event.
     */
    private void goToSignUpBtnActionPerformed(java.awt.event.ActionEvent e) {
        SignUpPanel.setVisible(true);
        LoginPanel.setVisible(false);
    }

    /**
     * Handles sign-up button click, generates a unique user ID, and inserts user data into the database.
     * @param e - The action event.
     */
    private void signUpBtnActionPerformed(java.awt.event.ActionEvent e) {
        String userID = String.valueOf(UUID.randomUUID());
        String fullName = nameField.getText();
        String email = emailField1.getText();
        String password = String.valueOf(passwordField1.getPassword());
        int idNumber = Integer.parseInt(idNumberField.getText());
        String type = databaseManager.isAdminEmail(email) ? "Admin" : "Student";

        databaseManager.insertUserData(userID, fullName, email, password, idNumber, type, 0);
        clearFields();
    }

    /**
     * Handles login button click, authenticates the user, and redirects to the appropriate window.
     * @param e - The action event.
     */
    private void loginBtnActionPerformed(java.awt.event.ActionEvent e) {
        String email = emailField2.getText();
        String password = String.valueOf(passwordField2.getPassword());
        String userID = databaseManager.getUserID(email);

        try {
            if (databaseManager.isEmailExists(email)) {
                if (databaseManager.authenticateUser(email, password)) {
                    loggedUser = databaseManager.getUser(userID);

                    String userType = loggedUser.getType();
                    controller.writeLoggedUserToFile(loggedUser.getUniqueID());

                    if ("Admin".equals(userType)) {
                        dispose();
                        AdminWindow adminWindow = new AdminWindow(loggedUser);
                        adminWindow.updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
                        JOptionPane.showMessageDialog(null, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else if ("Student".equals(userType)) {
                        dispose();
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

            clearFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to authenticate user.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the action when the "Forgot Password" button is clicked.
     * Displays a prompt to enter the email address, checks if the email exists in the database,
     * and allows the user to reset the password by entering a new password and confirming it.
     * @param e - The action event.
     */
    private void forgotPasswordBtnActionPerformed(java.awt.event.ActionEvent e) {
        String userEmail = JOptionPane.showInputDialog(this, "Enter your email address:");

        if (userEmail != null && !userEmail.isEmpty()) {
            try {
                if (databaseManager.isEmailExists(userEmail)) {
                    String userID = databaseManager.getUserID(userEmail);

                    JPasswordField newPasswordField = new JPasswordField();
                    JPasswordField confirmPasswordField = new JPasswordField();
                    Object[] message = {
                            "New Password:", newPasswordField,
                            "Confirm Password:", confirmPasswordField
                    };

                    int option = JOptionPane.showConfirmDialog(this, message, "Reset Password", JOptionPane.OK_CANCEL_OPTION);

                    if (option == JOptionPane.OK_OPTION) {
                        String newPassword = String.valueOf(newPasswordField.getPassword());
                        String confirmPassword = String.valueOf(confirmPasswordField.getPassword());


                        if (newPassword.equals(confirmPassword)) {
                            String encryptedPassword = Controller.encryptPassword(newPassword);
                            databaseManager.changePassword(userID, encryptedPassword);
                            JOptionPane.showMessageDialog(this, "Password reset successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Email not found. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Failed to reset password.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Clears all fields in the Signup and Login page.
     */
    private void clearFields() {
        nameField.setText("");
        emailField1.setText("");
        emailField2.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
        idNumberField.setText("");
    }

    /**
     * Performs automatic login by reading the user ID from the file and redirecting to the appropriate window.
     */
    private void performAutoLogin() {
        try (BufferedReader reader = new BufferedReader(new FileReader("loggedUser.txt"));) {
            String userID = reader.readLine();

            if (userID != null && !userID.isEmpty()) {
                loggedUser = databaseManager.getUser(userID);

                if (loggedUser != null) {
                    String userType = loggedUser.getType();

                    if ("Admin".equals(userType)) {
                        dispose();
                        AdminWindow adminWindow = new AdminWindow(loggedUser);
                        adminWindow.updateLoggedInUserInfoLabels(loggedUser.getUniqueID());
                    } else if ("Student".equals(userType)) {
                        dispose();
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

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainWindow mainWindow = new MainWindow();

                mainWindow.performAutoLogin();

                if (mainWindow.loggedUser == null) {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            mainWindow.setVisible(true);
                        }
                    });
                }
            }
        });
    }
}
