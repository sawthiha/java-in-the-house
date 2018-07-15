


import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MIchael Pauk Sa
 */
public abstract class CheckBlock implements Runnable {
    Thread t;
    String userID;
    boolean flag;
    public CheckBlock(String userID)  {
        this.userID=userID;
        t=new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY); 
        t.start();
    }
    public abstract void blockProcess();
    @Override
    public void run()  {
        try {
            InfoObject io=MiscTools.send(new InfoObject("checkBlock",userID,PasswordField.userID));
            flag=io.content.equals("unblocked");
            while(!Thread.currentThread().isInterrupted())  {
                blockProcess();
                while(io.content.equals("unblocked"))   {
                    Thread.sleep(2000);
                    io.content=userID;
                    io=MiscTools.send(io);
                }
                JOptionPane.showMessageDialog(null,"It's Blocked!");
                flag=false;
                blockProcess();
                while(io.content.equals("blocked"))  {
                    io.content=userID;
                    io=MiscTools.send(io);
                    Thread.sleep(2000); 
                }
                JOptionPane.showMessageDialog(null,"It's unblocked!");
                flag=true;
            }
        }catch(InterruptedException e)  {
            
        }
    }
}
