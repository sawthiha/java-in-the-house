


import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MIchael Pauk Sa
 */
public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException  {
        try {
            Socket s=new Socket(InetAddress.getByName("192.168.1.1"),7896);
            ObjectOutputStream oout=new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream oin=new ObjectInputStream(s.getInputStream());
            InfoObject io=new InfoObject("test",null,null);
            oout.writeObject(io);
            oout.flush();
            io= (InfoObject) oin.readObject();
            System.out.println(io.type);
        }catch(IOException e)  {
            System.out.println(e);
        }catch(ClassNotFoundException e)  {
            System.out.println(e);
        }
    }
}
