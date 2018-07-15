

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.*;
/**
 *
 * @author MIchael Pauk Sa
 */
public class MiscTools {
    static Connection c;
    static Statement sta;
    static String currDate=new SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date());
    /**
     *
     * @return
     */
    public static boolean isConnectable() 
    {   
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            c=DriverManager.getConnection("jdbc:ucanaccess://"+new File("Database\\BankingSystem.accdb"));
            sta=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            AccCreation.getAccID();
            TransactionForm.getTran();
            frmSearch.getBlockID();
            return true;
        } catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"JdbcOdbcDrier Not Found!");
            return false;
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"Cannot Be Connected");
            return false;
        }
    }
    
    /**
     *
     * @param sqlPass
     * @param txtPassword
     * @return
     */
    public static synchronized boolean isAdminAcc(String sqlPass,String txtPassword)  //frame, serverThread
    {
            for(int i=0,j=1,n=0,target=0;n<sqlPass.length()||target<txtPassword.length();n=i+j,i=j,j=n,target++)
            {   try  {
                    if(sqlPass.charAt(n)!=txtPassword.charAt(target))
                        return false;
                }catch(StringIndexOutOfBoundsException e) {
                        return false;
                }
            }
            return true;
    }
    /**
     *
     * @param p
     * @return
     */
    public static synchronized String encryption(String p) //frame,serverThread
    {   
        String _encrypted="";
        Random rd=new Random(); //65 - 122
        for(int i=0,j=1,s=0,destination=0,source=0;source<p.length();destination++) {
            if(destination==s) {
                _encrypted+=p.charAt(source);
                s=i+j;
                i=j;
                j=s;
                source++;
            }
            else {
                _encrypted+=(char)(65+rd.nextInt(57));
            }
        }
        return _encrypted;
    }
    /**
     *
     * @param acc
     * @return
     */
    public static synchronized boolean isBlocked(String acc)  { //frame,serverThread
        try {
            PreparedStatement ps=c.prepareStatement("SELECT * FROM BLOCK WHERE ACCID=?;");
            ps.setInt(1,Integer.parseInt(acc)); 
            ResultSet r=ps.executeQuery();
            if(r.next()) {
                ps.close();
                return true;
            }
            ps.close();
            return false;
        } catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
            return true;
        }
    }
    /**
     *
     * @param acc
     * @return
     */
    public static synchronized String[] showDetail(String acc)  { //frame,serverThread
        try {
            PreparedStatement ps=c.prepareStatement("SELECT * FROM ACCOUNT WHERE USERID=?;");
            ps.setString(1,acc);
            ResultSet rs=ps.executeQuery();
            if(!rs.next())  {
                return null;
            }
            String str[]={String.valueOf(rs.getInt("AccID")),rs.getString("AccType"),rs.getString("CustName"),rs.getString("DOB"),rs.getString("Gender"),rs.getString("Address"),rs.getString("PhoneNo"),rs.getString("Email"),String.valueOf(rs.getDouble("Amount")),rs.getString("CreationDate"),rs.getString("UserID")};
            ps.close();
            return str;
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    /**
     *
     * @param acc
     * @return
     */
    public static synchronized double checkAmt(String acc)  {   //frame,serverThread
        try {
            PreparedStatement ps=c.prepareStatement("SELECT AMOUNT FROM ACCOUNT WHERE USERID=?;");
            ps.setString(1, acc); 
            ResultSet rs=ps.executeQuery();
            if(!rs.next())
                return -1;
            return rs.getDouble("Amount");
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
            return 0.0;
        }
    }
    /**
     *
     * @param userID
     * @param password
     * @return
     */
    public static synchronized boolean accountVerification(String userID,String password)  {    //frame,serverThread
        try {
            PreparedStatement ps=c.prepareStatement("SELECT USERPSWD FROM ACCOUNT WHERE USERID=?;");
            ps.setString(1,userID); 
            ResultSet rs=ps.executeQuery();
            if(!rs.next())  {
                ps.close();
                return false;
            }
            if(!isAdminAcc(rs.getString("Userpswd"),password))  {
                ps.close();
                return false;
            }
            ps.close();
        }catch(SQLException e)  {
            JOptionPane.showMessageDialog(null,e);
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param name
     * @param password
     * @return
     */
    public static synchronized boolean adminVerification (String name,String password)  {
        try {
            PreparedStatement ps=c.prepareStatement("SELECT PASSWORD FROM ADMININFO WHERE USERNAME='"+name+"';");
            ResultSet rs=ps.executeQuery();
            if(!rs.next()) 
                return false;
            if(!isAdminAcc(rs.getString("Password"),password))
                return false;
            ps.close();
        }catch(SQLException e)  {
            return false;
        }
        return true;
    }
    
    /**
     *
     * @param userID
     * @return
     */
    public static int checkTransaction(String userID)  {
        try {
            PreparedStatement ps=c.prepareStatement("SELECT COUNT(TRANSACTIONID) FROM TRANSACTION WHERE USERID=?;");
            ps.setString(1, userID); 
            ResultSet rs=ps.executeQuery();
            rs.next();
            return rs.getInt(1); 
        }catch(SQLException e) {
            return -1;
        }
    }
    
    /**
     *
     * @param userID
     * @return
     */
    public static synchronized boolean closeAccount(String userID)  {
        try {
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=s.executeQuery("SELECT ACCID FROM ACCOUNT WHERE USERID='"+userID+"';");
            rs.next();
            ResultSet r=s.executeQuery("SELECT TRANSACTIONID FROM TRANSACTION WHERE USERID='"+userID+"';");
            while(r.next())  {
                s.executeUpdate("INSERT INTO TRANREYCLE(TRANID) VALUES("+r.getInt("TransactionID")+");");
            }
            s.executeUpdate("DELETE * FROM ACCOUNT WHERE USERID='"+userID+"';");
            s.executeUpdate("INSERT INTO ACCRECYCLE(ID,RDATE,ADMIN) VALUES ("+rs.getInt("AccID")+",'"+MiscTools.currDate+"','"+PasswordField.adminName+"');");
            s.close();
            return true;
        }catch(SQLException e)  {
            return false;
        }
    }
    /**
     *
     * @param userID
     * @param amt
     * @return
     */
    public static synchronized double depositAcc(String userID,String amt)  {
        try {
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=s.executeQuery("SELECT AMOUNT,ACCID FROM ACCOUNT WHERE USERID='"+userID+"';");
            rs.next();
            s.executeUpdate("UPDATE ACCOUNT SET AMOUNT ="+(Double.parseDouble(amt)+rs.getDouble("Amount"))+" WHERE USERID='"+userID+"';");
            s.executeUpdate("INSERT INTO TRANSACTION(TRANSACTIONID,USERID,TYPE,DESTINATION,DOT,AMOUNT,ACCID) VALUES ("+TransactionForm.tranID+",'"+userID+"','Deposit','-','"+MiscTools.currDate+"',"+amt+","+rs.getInt("ACCID")+");");
            TransactionForm.messClean();
            return (Double.parseDouble(amt)+rs.getDouble("Amount"));
        }catch(SQLException e)  {
            return -1;
        }
    }
    /**
     *
     * @param userID
     * @param amt
     * @return
     */
    public static synchronized double withdrawAcc(String userID,String amt)  {
        try {
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=s.executeQuery("SELECT AMOUNT,ACCID FROM ACCOUNT WHERE USERID='"+userID+"';");
            rs.next();
            if(rs.getDouble("Amount")-Double.parseDouble(amt)<3000)  
                return -2;
            s.executeUpdate("UPDATE ACCOUNT SET AMOUNT ="+(rs.getDouble("Amount")-Double.parseDouble(amt))+" WHERE USERID='"+userID+"';"); 
            s.executeUpdate("INSERT INTO TRANSACTION(TRANSACTIONID,USERID,TYPE,DESTINATION,DOT,AMOUNT,ACCID) VALUES ("+TransactionForm.tranID+",'"+userID+"','Withdraw','-','"+MiscTools.currDate+"',"+amt+","+rs.getInt("ACCID")+");");
            TransactionForm.messClean();
            return(rs.getDouble("Amount")-Double.parseDouble(amt));
        }catch(SQLException e) {
            return -1;
        } 
    }
    /**
     *
     * @param userID
     * @param amt
     * @param destination
     * @return
     */
    public static synchronized double transferAcc(String userID,String amt,String destination)  {
        try {
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=s.executeQuery("SELECT X.AMOUNT AS S,Y.AMOUNT AS D,X.ACCID AS SACCID FROM ACCOUNT X,ACCOUNT Y WHERE X.USERID='"+userID+"' AND Y.USERID='"+destination+"';");
            if(!rs.next())
                return -3;
            double des=rs.getDouble("D"),sou=rs.getDouble("S");
            if(sou-Double.parseDouble(amt)<3000) 
                return -2;
            if(isBlocked(String.valueOf(getAccNo(destination))))
                return -4;
            s.executeUpdate("UPDATE ACCOUNT SET AMOUNT="+(sou-Double.parseDouble(amt))+" WHERE USERID='"+userID+"';");
            s.executeUpdate("UPDATE ACCOUNT SET AMOUNT="+(des+Double.parseDouble(amt))+" WHERE USERID='"+destination+"';");
            s.executeUpdate("INSERT INTO TRANSACTION(TRANSACTIONID,USERID,TYPE,DESTINATION,DOT,AMOUNT,ACCID) VALUES ("+TransactionForm.tranID+",'"+userID+"','Transfer','"+destination+"','"+MiscTools.currDate+"',"+amt+","+rs.getInt("SACCID")+");"); 
            TransactionForm.messClean();
            return sou-Double.parseDouble(amt);
        }catch(SQLException e)  {
            return -1;
        }
    }
    
    /**
     *
     * @param userID
     * @return
     */
    public static synchronized int getAccNo(String userID)  {
        try {
            PreparedStatement ps=c.prepareStatement("SELECT ACCID FROM ACCOUNT WHERE USERID=?;");
            ps.setString(1, userID);
            ResultSet rs=ps.executeQuery();
            rs.next();
            return rs.getInt("AccID");
        }catch(SQLException e)  {
            return -1;
        }
    }
    
    /**
     *
     * @param userID
     * @return
     */
    public static synchronized boolean blockAcc(String userID)  {
        try {
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            s.executeUpdate("INSERT INTO BLOCK(BLOCKID,ACCID) VALUES ("+frmSearch.blockID+","+getAccNo(userID)+");"); 
            frmSearch.messClean();
            s.close();
            return true;
        }catch(SQLException e)  {
            return false;
        }
    }
    
    /**
     *
     * @param userID
     * @return
     */
    public static synchronized boolean unblockAcc(String userID)  {
        try {
            int accID=getAccNo(userID);
            Statement s=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=s.executeQuery("SELECT BLOCKID FROM BLOCK WHERE ACCID="+accID+";");
            rs.next();
            s.executeUpdate("INSERT INTO BLOCKRECYCLE(ID) VALUES("+rs.getInt("BlockID")+");"); 
            s.executeUpdate("DELETE * FROM BLOCK WHERE ACCID="+accID+";"); 
            s.close();
            return true;
        }catch(SQLException e)  {
            return false;
        }
    }
}
