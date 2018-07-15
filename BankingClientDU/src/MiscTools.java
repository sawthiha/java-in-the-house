

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author MIchael Pauk Sa
 */
public class MiscTools {
    public static Socket s;
    public static ObjectOutputStream oout;
    public static ObjectInputStream oin;
    public static boolean isConnectable()  {
        try {
            s=new Socket(InetAddress.getByName("192.168.1.1"),7896);
            oout=new ObjectOutputStream(s.getOutputStream());
            oin=new ObjectInputStream(s.getInputStream());
            oout.writeObject(new InfoObject("test",null,null));
            oout.flush();
            InfoObject io=(InfoObject) oin.readObject();
            if(!io.content.equals("success"))
                throw new Exception("");
            return true;
            }catch(Exception e)  {
                return false;
            }
    }
    public static synchronized InfoObject send(InfoObject io) {
        try {
            oout.writeObject(io);
            oout.flush();
            io=(InfoObject)oin.readObject();
            return io;
        }catch(IOException e)  {
            return null;
        }catch(ClassNotFoundException e) {
            return null;
        }
    }
    public static synchronized double checkAmt(String user)  {
        InfoObject io=new InfoObject("checkAmt",user,PasswordField.userID);
        io=send(io);
        if(io.content.equals("blocked"))
            return -2;
        return Double.parseDouble(io.content);
    }
    public static synchronized double deposit(String user,String amt)  {
        InfoObject io=new InfoObject("deposit",user+" "+amt,PasswordField.userID);
        io=send(io);
        if(io.content.equals("fail sql"))
            return -1;
        return Double.parseDouble(io.content);
    }
    public static synchronized double withdraw(String user,String amt)   {
        InfoObject io=new InfoObject("withdraw",user+" "+amt,PasswordField.userID);
        io=send(io);
        if(io.content.equals("fail sql"))
            return -1;
        if(io.content.equals("fail amt"))
            return -2;
        return Double.parseDouble(io.content);
    }
    public static synchronized double transfer(String user,String amt,String destination)  {
        InfoObject io=new InfoObject("transfer",user+" "+amt+" "+destination,PasswordField.userID);
        io=send(io);
        if(io.content.equals("fail sql"))
            return -1;
        if(io.content.equals("fail amt"))
            return -2;
        if(io.content.equals("fail des"))
            return -3;
        if(io.content.equals("blocked"))
            return -4;
        return Double.parseDouble(io.content);
    }
    
    public static synchronized void signOut()  {
        send(new InfoObject("signOut",null,null));
    }
    
}
