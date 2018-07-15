/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JOptionPane;

/**
 *
 * @author MIchael Pauk Sa
 */
public class BankingServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(!MiscTools.isConnectable())  {
            System.exit(0); 
        }
        PasswordField pf=new PasswordField();
        pf.setVisible(true); 
    }
}
