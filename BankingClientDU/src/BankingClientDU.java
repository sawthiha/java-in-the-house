/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author MIchael Pauk Sa
 */
public class BankingClientDU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        if(!MiscTools.isConnectable())
            System.exit(0);
        PasswordField pf=new PasswordField();
        pf.setVisible(true); 
    }
}
