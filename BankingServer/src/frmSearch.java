


import javax.swing.JOptionPane;
import java.sql.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmSearch.java
 *
 * Created on Jul 8, 2011, 5:46:58 PM
 */
/**
 *
 * @author MIchael Pauk Sa
 */
public class frmSearch extends javax.swing.JFrame implements Runnable{
    private ResultSet rs;
    private Thread t;
    private CheckBlock cb;
    /**
     *
     */
    public static int blockID;
    /**
     *
     */
    public frmSearch() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSAccno = new javax.swing.JTextField();
        txtSCustname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtUserID = new javax.swing.JTextField();
        txtAcctype = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCustname = new javax.swing.JTextField();
        txtDOB = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAddr = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnShowDetail = new javax.swing.JButton();
        btnMakeTran = new javax.swing.JButton();
        btnSeeTran = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnBlockAcc = new javax.swing.JButton();
        btnCloseAcc = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtAmt = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        btnUnblock = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrevious = new javax.swing.JButton();
        txtCdate = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnMale = new javax.swing.JRadioButton();
        btnFemale = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        txtAccno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Blah Blah                                          Bank Information System");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 0), 1, true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 0)));

        jLabel2.setText("Search User ID");

        txtSCustname.setEnabled(false);

        jLabel3.setText("Search Customer Name");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Search By UserID");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Search By Name");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(119, 119, 119)
                        .addComponent(txtSAccno, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSCustname, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(46, 46, 46))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSAccno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSCustname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btnSearch)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 0)));

        jLabel4.setText("User ID");

        txtUserID.setEnabled(false);

        txtAcctype.setEnabled(false);

        jLabel5.setText("Account Type");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("<html><u>Searching Form</u></html>");

        jLabel7.setText("Customer Name");

        txtCustname.setEnabled(false);

        txtDOB.setEnabled(false);

        jLabel8.setText("Date of Birth(DOB)");

        jLabel9.setText("Gender");

        txtAddr.setEnabled(false);

        jLabel10.setText("Address");

        txtPhone.setEnabled(false);

        jLabel11.setText("Phone");

        jLabel12.setText("E-mail");

        txtEmail.setEnabled(false);

        btnShowDetail.setText("Show Detail");
        btnShowDetail.setEnabled(false);
        btnShowDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDetailActionPerformed(evt);
            }
        });

        btnMakeTran.setText("Make Transaction");
        btnMakeTran.setEnabled(false);
        btnMakeTran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeTranActionPerformed(evt);
            }
        });

        btnSeeTran.setText("See Transaction");
        btnSeeTran.setToolTipText("");
        btnSeeTran.setEnabled(false);
        btnSeeTran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeTranActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.setEnabled(false);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setEnabled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBlockAcc.setText("Block Acc");
        btnBlockAcc.setEnabled(false);
        btnBlockAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlockAccActionPerformed(evt);
            }
        });

        btnCloseAcc.setText("Close Acc");
        btnCloseAcc.setEnabled(false);
        btnCloseAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseAccActionPerformed(evt);
            }
        });

        jLabel13.setText("Amount");

        txtAmt.setEnabled(false);

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnUnblock.setText("Unblock");
        btnUnblock.setEnabled(false);
        btnUnblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnblockActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.setEnabled(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrevious.setText("Previous");
        btnPrevious.setEnabled(false);
        btnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousActionPerformed(evt);
            }
        });

        txtCdate.setEnabled(false);

        jLabel14.setText("Creation Date");

        buttonGroup2.add(btnMale);
        btnMale.setText("Male");
        btnMale.setEnabled(false);

        buttonGroup2.add(btnFemale);
        btnFemale.setText("Female");
        btnFemale.setEnabled(false);

        jLabel15.setText("Account No");

        txtAccno.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(119, 119, 119)
                        .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtAcctype, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCustname, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(btnPrevious))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDOB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnMale)
                                .addGap(18, 18, 18)
                                .addComponent(btnFemale))
                            .addComponent(txtAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCdate)
                            .addComponent(txtAmt, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(txtAccno, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUnblock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCloseAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBlockAcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSeeTran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMakeTran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShowDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowDetail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAcctype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeeTran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCustname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMakeTran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnSave)
                    .addComponent(btnMale)
                    .addComponent(btnFemale))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBlockAcc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCloseAcc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUnblock))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(jLabel15)
                    .addComponent(txtAccno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPrevious)
                    .addComponent(btnNext))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        if(txtSAccno.getText().isEmpty()&&txtSCustname.getText().isEmpty())  {
            JOptionPane.showMessageDialog(null,"Enter Required Information To Search");
            return;
        }
        try {
            String[] str;
            if(jRadioButton1.isSelected())  {
                str=MiscTools.showDetail(txtSAccno.getText());
                if(str==null)  {
                    JOptionPane.showMessageDialog(null,"There is no such user with AccID"+txtSAccno.getText()+"!");
                    txtSAccno.setText(""); 
                }
            } else {
                rs=MiscTools.sta.executeQuery("SELECT * FROM ACCOUNT WHERE CUSTNAME='"+txtSCustname.getText()+"';");
                if(!rs.next())  {
                    JOptionPane.showMessageDialog(null,"There is no account with the name "+txtSCustname.getText());
                    txtSCustname.setText("");
                    return;
                }
                str=MiscTools.showDetail(String.valueOf(rs.getString("UserID"))); 
                if(rs.next())  {
                    btnNext.setEnabled(true);
                    rs.previous();
                }
            }
            try {
            if(t.isAlive())
                t.interrupt();
                t.join();
            }catch(NullPointerException e)  {}
            catch(InterruptedException e)  {}
            try {
                if(cb.t.isAlive())
                    cb.t.interrupt();
                    cb.t.join();
            }catch(NullPointerException e)  {}
            catch(InterruptedException e)  {}
            txtUserID.setText(str[10]);
            txtCustname.setText(str[2]);
            if(str[4].equals("male"))
                btnMale.setSelected(true);
            else
                btnFemale.setSelected(true);
            txtDOB.setText(str[3]);//
            txtAddr.setText(str[5]);
            txtPhone.setText(str[6]);
            txtEmail.setText(str[7]);
            txtAcctype.setText(str[1]);
            txtAmt.setText(str[8]);
            txtCdate.setText(str[9]);
            txtAccno.setText(str[0]); 
            btnShowDetail.setEnabled(true);
            btnSeeTran.setEnabled(true);
            btnCloseAcc.setEnabled(true);
            CheckBlock cb=new CheckBlock(txtUserID.getText()) {

                @Override
                public void blockProcess() {
                    btnMakeTran.setEnabled(flag);
                    btnBlockAcc.setEnabled(flag);
                    btnEdit.setEnabled(flag);
                    btnUnblock.setEnabled(!flag);
                    if(btnSave.isEnabled())  {
                        cancelAction();
                    }
                }
            };
            t=new Thread(this);
            t.start();
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        txtSCustname.setEnabled(false); 
        txtSAccno.setEnabled(true); 
        txtSAccno.setText("");
        txtSCustname.setText("");
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        txtSAccno.setEnabled(false); 
        txtSCustname.setEnabled(true); 
        txtSCustname.setText("");
        txtSAccno.setText("");
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void btnUnblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnblockActionPerformed
        try {
            ResultSet rs=MiscTools.sta.executeQuery("SELECT PASSWORD FROM ADMININFO WHERE USERNAME='"+PasswordField.adminName+"';");
            rs.next();
            try {
            if(!MiscTools.isAdminAcc(rs.getString("Password"),JOptionPane.showInputDialog("Enter Your Admin Password")))  {
                JOptionPane.showMessageDialog(null,"Oops, Wrong Password!");
                return;
            }
            }catch(NullPointerException e)  {
                return;
            }
            rs=MiscTools.sta.executeQuery("SELECT BLOCKID FROM BLOCK WHERE ACCID="+txtAccno.getText()+";");
            rs.next();
            MiscTools.sta.executeUpdate("INSERT INTO BLOCKRECYCLE(ID) VALUES("+rs.getInt("BlockID")+");"); 
            MiscTools.sta.executeUpdate("DELETE * FROM BLOCK WHERE ACCID="+txtAccno.getText()+";");
            JOptionPane.showMessageDialog(null,"Unblocked!");
            btnBlockAcc.setEnabled(true);
            btnEdit.setEnabled(true);
            btnMakeTran.setEnabled(true);
            btnSeeTran.setEnabled(true);
            btnUnblock.setEnabled(false);
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnUnblockActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
        try {
            if(t.isAlive())
                t.interrupt();
            t.join();
        }catch(NullPointerException e)  {}
        catch(InterruptedException e) {}
        try {
            if(cb.t.isAlive())
                cb.t.interrupt();
            cb.t.join();
        }catch(NullPointerException e) {}catch(InterruptedException e) {}
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnCloseAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseAccActionPerformed
        try {
            ResultSet rs=MiscTools.sta.executeQuery("SELECT PASSWORD FROM ADMININFO WHERE USERNAME ='"+PasswordField.adminName+"';");
            rs.next();
            try {
            if(!MiscTools.isAdminAcc(rs.getString("Password"),JOptionPane.showInputDialog("Enter Your Admin Password"))) {
                JOptionPane.showMessageDialog(null,"Oops,Wrong Password!");
                return;
            }
            }catch(NullPointerException e)  {
                return;
            }
            rs=MiscTools.sta.executeQuery("SELECT TRANSACTIONID FROM TRANSACTION WHERE USERID="+txtUserID.getText()+" ORDER BY TRANSACTIONID;");
            while(rs.next())  {
                MiscTools.sta.executeUpdate("INSERT INTO TRANRECYCLE(TRANID) VALUES ("+rs.getInt("TRANSACTIONID")+");");
            }
            MiscTools.sta.executeUpdate("DELETE * FROM ACCOUNT WHERE USERID="+txtUserID.getText()+";");
            JOptionPane.showMessageDialog(null,"Successfully Deleted.");
            MiscTools.sta.executeUpdate("INSERT INTO ACCRECYCLE(ID,RDATE,ADMIN) VALUES("+txtAccno.getText()+",'"+MiscTools.currDate+"','"+PasswordField.adminName+"');");
            try {
                if(cb.t.isAlive())
                    cb.t.interrupt();
                cb.t.join();
            }catch(NullPointerException e)  {}catch(InterruptedException e) {}
            try {
                if(t.isAlive())
                    t.interrupt();
                t.join();
            }catch(NullPointerException e){}catch(InterruptedException e)  {}
            txtUserID.setText("");
            txtCustname.setText("");
            btnMale.setSelected(false);
            btnFemale.setSelected(false);
            txtDOB.setText("");
            txtAddr.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtAcctype.setText("");
            txtAmt.setText("");
            txtCdate.setText("");
            txtAccno.setText("");
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnShowDetail.setEnabled(false);
            btnSeeTran.setEnabled(false); 
            btnMakeTran.setEnabled(false);
            btnEdit.setEnabled(false);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
            btnBlockAcc.setEnabled(false);
            btnUnblock.setEnabled(false);
            btnCloseAcc.setEnabled(false); 
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnCloseAccActionPerformed

    private void btnBlockAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlockAccActionPerformed
        try {
            ResultSet rs=MiscTools.sta.executeQuery("SELECT PASSWORD FROM ADMININFO WHERE USERNAME='"+PasswordField.adminName+"';");
            rs.next();
            try {
            if(!MiscTools.isAdminAcc(rs.getString("Password"),JOptionPane.showInputDialog("Enter Your Admin Password"))) {
                JOptionPane.showMessageDialog(null,"Oops, Wrong Password!");
                return;
            }
            }catch(NullPointerException e)  {
                return;
            }
            MiscTools.sta.executeUpdate("INSERT INTO BLOCK(BLOCKID,ACCID) VALUES ("+blockID+","+txtAccno.getText()+");");
            messClean();
            JOptionPane.showMessageDialog(null,"Blocked");
            btnMakeTran.setEnabled(false);
            btnEdit.setEnabled(false);
            btnBlockAcc.setEnabled(false);
            btnUnblock.setEnabled(true);
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnBlockAccActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(txtCustname.getText().isEmpty()||txtAddr.getText().isEmpty()||txtEmail.getText().isEmpty()||txtPhone.getText().isEmpty()||txtDOB.getText().isEmpty())  {
            JOptionPane.showMessageDialog(null,"Fill The Form Completely or cancel,otherwise!");
            return;
        }
        try {
            if(btnFemale.isSelected())
            MiscTools.sta.executeUpdate("UPDATE ACCOUNT SET CUSTNAME='"+txtCustname.getText()+"',ADDRESS='"+txtAddr.getText()+"',EMAIL='"+txtEmail.getText()+"',PHONENO='"+txtPhone.getText()+"',GENDER='female',DOB='"+txtDOB.getText()+"' WHERE USERID='"+txtUserID.getText()+"';");
            else
            MiscTools.sta.executeUpdate("UPDATE ACCOUNT SET CUSTNAME='"+txtCustname.getText()+"',ADDRESS='"+txtAddr.getText()+"',EMAIL='"+txtEmail.getText()+"',PHONENO='"+txtPhone.getText()+"',GENDER='male',DOB='"+txtDOB.getText()+"' WHERE USERID='"+txtUserID.getText()+"';");
            JOptionPane.showMessageDialog(null,"Successfully saved!");
        } catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
        txtCustname.setEnabled(false);
        txtAddr.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtDOB.setEnabled(false);
        btnShowDetail.setEnabled(true);
        btnSeeTran.setEnabled(true);
        btnCloseAcc.setEnabled(true);
        btnSave.setEnabled(false); 
        btnCancel.setEnabled(false);
        btnMale.setEnabled(false);
        btnFemale.setEnabled(false);
        try {
            boolean temp=this.rs.next();
            this.rs.previous();
            btnNext.setEnabled(temp);
            temp=this.rs.previous();
            this.rs.next();
            btnPrevious.setEnabled(temp); 
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        txtCustname.setEnabled(true);
        txtAddr.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        txtDOB.setEnabled(true);
        btnShowDetail.setEnabled(false);
        btnSeeTran.setEnabled(false);
        btnMakeTran.setEnabled(false);
        btnBlockAcc.setEnabled(false);
        btnCloseAcc.setEnabled(false);
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
        btnMale.setEnabled(true);
        btnFemale.setEnabled(true);
        btnNext.setEnabled(false);
        btnPrevious.setEnabled(false); 
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        cancelAction();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSeeTranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeTranActionPerformed
        SeeTransaction st=new SeeTransaction(txtUserID.getText(),txtCustname.getText());
        st.setVisible(true); 
    }//GEN-LAST:event_btnSeeTranActionPerformed

    private void btnMakeTranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeTranActionPerformed
        TransactionForm tf=new TransactionForm(txtUserID.getText(),txtAccno.getText());
        tf.setVisible(true);
    }//GEN-LAST:event_btnMakeTranActionPerformed

    private void btnShowDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDetailActionPerformed
        DetailForm df=new DetailForm(txtUserID.getText());
        df.setVisible(true);
    }//GEN-LAST:event_btnShowDetailActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        try {
            rs.next();
            try {
                if(cb.t.isAlive())
                    cb.t.interrupt();
                cb.t.join();
            }catch(NullPointerException e)  {}catch(InterruptedException e) {}
            try {
                if(t.isAlive())
                    t.interrupt();
                t.join();
            }catch(NullPointerException e){}catch(InterruptedException e)  {}
            String[] str=MiscTools.showDetail(rs.getString("UserID"));
            txtDOB.setText(str[3]);//
            txtAddr.setText(str[5]);
            txtPhone.setText(str[6]);
            txtEmail.setText(str[7]);
            txtAcctype.setText(str[1]);
            txtAmt.setText(str[8]);
            txtCdate.setText(str[9]);
            txtAccno.setText(str[0]);
            txtUserID.setText(rs.getString("UserID"));
            btnShowDetail.setEnabled(true);
            btnSeeTran.setEnabled(true); 
            CheckBlock cb=new CheckBlock(txtUserID.getText()) {

                @Override
                public void blockProcess() {
                    btnMakeTran.setEnabled(flag);
                    btnBlockAcc.setEnabled(flag);
                    btnEdit.setEnabled(flag);
                    btnUnblock.setEnabled(!flag);
                    if(btnSave.isEnabled())  {
                        cancelAction();
                    }
                }
            };
            t=new Thread(this);
            t.start();
            boolean temp=rs.next();
            rs.previous();
            btnNext.setEnabled(temp); 
            btnPrevious.setEnabled(true);
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousActionPerformed
        try {
            rs.previous();
            try {
                if(cb.t.isAlive())
                    cb.t.interrupt();
                cb.t.join();
            }catch(NullPointerException e)  {}catch(InterruptedException e) {}
            try {
                if(t.isAlive())
                    t.interrupt();
                t.join();
            }catch(NullPointerException e){}catch(InterruptedException e)  {}
            String[] str=MiscTools.showDetail(rs.getString("UserID"));
            txtDOB.setText(str[3]);//
            txtAddr.setText(str[5]);
            txtPhone.setText(str[6]);
            txtEmail.setText(str[7]);
            txtAcctype.setText(str[1]);
            txtAmt.setText(str[8]);
            txtCdate.setText(str[9]);
            txtAccno.setText(str[0]);
            txtUserID.setText(rs.getString("UserID")); 
            CheckBlock cb=new CheckBlock(txtUserID.getText()) {

                @Override
                public void blockProcess() {
                    btnMakeTran.setEnabled(flag);
                    btnBlockAcc.setEnabled(flag);
                    btnEdit.setEnabled(flag);
                    btnUnblock.setEnabled(!flag);
                    if(btnSave.isEnabled())  {
                        cancelAction();
                    }
                }
            };
            t=new Thread(this);
            t.start();
            boolean temp=rs.previous();
            rs.next();
            btnPrevious.setEnabled(temp); 
            btnNext.setEnabled(true);
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnPreviousActionPerformed

    /**
     *
     */
    public static synchronized void getBlockID()  {
        try {
            Statement s=MiscTools.c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet r=s.executeQuery("SELECT * FROM BLOCKRECYCLE ORDER BY ID;");
            if(r.next())  {
                blockID=r.getInt("ID");
                return;
            }
            r=s.executeQuery("SELECT COUNT(BLOCKID) FROM BLOCK;");
            r.next();
            blockID=r.getInt(1)+1;
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null, e); 
            blockID=-1;
        }
    }
    
    /**
     *
     */
    public static synchronized void messClean() {
        try {
            Statement s=MiscTools.c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet r=s.executeQuery("SELECT ID FROM BLOCKRECYCLE WHERE ID="+blockID+";");
            if(!r.next())
                return;
            s.executeUpdate("DELETE * FROM BLOCKRECYCLE WHERE ID="+blockID+";");
            getBlockID();
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null, e);
            blockID=-1;
        }
    }
    
    /**
     *
     */
    
    /**
     *
     */
    public synchronized void cancelAction()  {
        try {
            ResultSet rs=MiscTools.sta.executeQuery("SELECT CUSTNAME,ADDRESS,EMAIL,PHONENO,GENDER,DOB FROM ACCOUNT WHERE USERID='"+txtUserID.getText()+"';");
            rs.next();
            txtCustname.setText(rs.getString("Custname"));
            txtAddr.setText(rs.getString("Address"));
            txtEmail.setText(rs.getString("Email"));
            txtPhone.setText(rs.getString("PhoneNo"));
            txtDOB.setText(rs.getString("DOB"));
            txtCustname.setEnabled(false);
            txtAddr.setEnabled(false);
            txtEmail.setEnabled(false);
            txtPhone.setEnabled(false);
            txtDOB.setEnabled(false);
            btnShowDetail.setEnabled(true);
            btnSeeTran.setEnabled(true);
            btnCloseAcc.setEnabled(true);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
            if(rs.getString("Gender").equals("male"))
            btnMale.setSelected(true);
            else
            btnFemale.setSelected(true);
            boolean temp=this.rs.next();
            this.rs.previous();
            btnNext.setEnabled(temp);
            temp=this.rs.previous();
            this.rs.next();
            btnPrevious.setEnabled(temp); 
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args)  {
        MiscTools.isConnectable();
        frmSearch fs=new frmSearch();
        fs.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBlockAcc;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCloseAcc;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JButton btnMakeTran;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrevious;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSeeTran;
    private javax.swing.JButton btnShowDetail;
    private javax.swing.JButton btnUnblock;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField txtAccno;
    private javax.swing.JTextField txtAcctype;
    private javax.swing.JTextField txtAddr;
    private javax.swing.JTextField txtAmt;
    private javax.swing.JTextField txtCdate;
    private javax.swing.JTextField txtCustname;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSAccno;
    private javax.swing.JTextField txtSCustname;
    private javax.swing.JTextField txtUserID;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                Thread.sleep(2500);
                txtAmt.setText(String.valueOf(MiscTools.checkAmt(txtUserID.getText())));  
            }
        }catch(InterruptedException e)  {
            if(this.isDisplayable())  {
                txtAccno.setText(""); 
                txtAcctype.setText(""); 
                txtAddr.setText("");
                txtAmt.setText("");
                txtCdate.setText("");
                txtCustname.setText("");
                txtDOB.setText(""); 
                txtEmail.setText("");
                txtPhone.setText("");
                txtUserID.setText(""); 
                btnEdit.setEnabled(false);
                btnMakeTran.setEnabled(false);
                btnBlockAcc.setEnabled(false);
                btnUnblock.setEnabled(false);
                btnShowDetail.setEnabled(false);
                btnSeeTran.setEnabled(false);
                btnCloseAcc.setEnabled(false);
                btnSave.setEnabled(false);
                btnCancel.setEnabled(false); 
            }
        }
    }
}   
