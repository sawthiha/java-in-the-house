


import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MIchael Pauk Sa
 */
public class WithdrawForm extends javax.swing.JFrame implements Runnable {
    Thread t;
    private String userID;
    /**
     * Creates new form WithdrawForm
     */
    public WithdrawForm(String userID) {
        initComponents();
        this.userID=userID;
        t=new Thread(this);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAmt = new javax.swing.JTextField();
        txtWamt = new javax.swing.JTextField();
        btnW = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Withdraw Form"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Total Amount");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Withdraw Amount");

        txtAmt.setEnabled(false);

        txtWamt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtWamtKeyReleased(evt);
            }
        });

        btnW.setText("Withdraw");
        btnW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAmt, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addComponent(txtWamt))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(btnW)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addGap(67, 67, 67))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtWamt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnW)
                    .addComponent(btnCancel))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
        t.interrupt();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWActionPerformed
       if(txtWamt.getText().isEmpty())  {
           JOptionPane.showMessageDialog(null,"Enter Amount to withdraw.");
           return;
       }
       double amt=MiscTools.withdraw(userID,txtWamt.getText());
       if(amt==-1)  {
           JOptionPane.showMessageDialog(null,"Transaction Fail!");
           txtWamt.setText("");
           return;
       }
       if(amt==-2)  {
           JOptionPane.showMessageDialog(null,"Not enough amount to withdraw!Should have al least 3000 in account!");
           txtWamt.setText("");
           return;
       }
       txtAmt.setText(String.valueOf(amt)); 
       JOptionPane.showMessageDialog(null,"Transaction Success!");
       txtWamt.setText(""); 
    }//GEN-LAST:event_btnWActionPerformed

    private void txtWamtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWamtKeyReleased
        try {
            Double.parseDouble(txtWamt.getText());
        }catch(NumberFormatException e) {
            txtWamt.setText("");
        }
    }//GEN-LAST:event_txtWamtKeyReleased

    @Override
    public void run()  {
        try {
            double amt=MiscTools.checkAmt(userID);
            txtAmt.setText(String.valueOf(amt)); 
            while(!Thread.currentThread().isInterrupted())  {
                Thread.sleep(5000);
                amt=MiscTools.checkAmt(userID);
                if(amt==-1)
                    Thread.currentThread().interrupt();
                txtAmt.setText(String.valueOf(amt)); 
            }
        }catch(InterruptedException e)  {
            if(this.isDisplayable())  {
                JOptionPane.showMessageDialog(null,"Something's wrong with the account!Please check again!");
                System.exit(0);
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnW;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAmt;
    private javax.swing.JTextField txtWamt;
    // End of variables declaration//GEN-END:variables
}
