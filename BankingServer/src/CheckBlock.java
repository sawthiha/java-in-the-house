

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;

/**
 *
 * @author MIchael Pauk Sa
 */
public abstract class CheckBlock implements Runnable {
    
    Thread t;
    private String accID;
    boolean flag;
    
    /**
     *
     * @param userID
     */
    public CheckBlock(String userID)  {
        accID=String.valueOf(MiscTools.getAccNo(userID));
        t=new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY); 
        t.start();
    }
    
    @Override
    public void run()  {
        try {
            flag=!MiscTools.isBlocked(accID);
            while(!Thread.currentThread().isInterrupted())  {
                blockProcess();
                while(!MiscTools.isBlocked(accID))
                    Thread.sleep(2000); 
                flag=false;
                JOptionPane.showMessageDialog(null,"It's blocked!");
                blockProcess();
                while(MiscTools.isBlocked(accID)) 
                    Thread.sleep(2000); 
                JOptionPane.showMessageDialog(null,"It's unblocked!");
                flag=true;
            }
        }catch(InterruptedException e) {
            
        }
    }
    
    public abstract void blockProcess();
    
}
