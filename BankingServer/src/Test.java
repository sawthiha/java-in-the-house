


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
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
    /**
     *
     */

    /**
     *
     * @param args
     */
    public static void main(String[] args)  {
        try {
        ServerSocket s=new ServerSocket(7896);
        Socket accept=s.accept();
        ObjectInputStream oin=new ObjectInputStream(accept.getInputStream());
        ObjectOutputStream oout=new ObjectOutputStream(accept.getOutputStream());
        InfoObject io=(InfoObject) oin.readObject();
        System.out.println(io.type);
        oout.writeObject(io);
        oout.flush();
        }catch(IOException e)  {
            System.out.println(e);
        }catch(ClassNotFoundException e)  {
            System.out.println(e);
        }
    }
    
}
