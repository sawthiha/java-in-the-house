/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.*;

/**
 *
 * @author sawthiha
 */
public class StdDBControl {
    
    /**
     * Connect the DB
     * @return true if connected, false otherwise
     */
    protected final boolean connect()  {
        try {
            Class.forName(this.dbDriver);                    // check if driver is in library(available)
            this.con = DriverManager.getConnection(this.dbUrl);   // connect to DB
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            return false;
        }
    }
    
    /**
     * Reconnect the DB
     * @return true if reconnected, otherwise false
     */
    protected final boolean reconnect()  {
        try  {
            DriverManager.getConnection(dbUrl + ";shutdown=true");
            this.con.close();
            Class.forName(this.dbDriver);
            this.con = DriverManager.getConnection(this.dbUrl);
            return true;
        }catch(SQLException | ClassNotFoundException ex)  {
            return false;
        }
    }
    
    /**
     * Shutdown DB & close connection
     * @return true if disconnected, otherwise false
     */
    protected final boolean disconnect()  {
        try {
            this.con.close();
            DriverManager.getConnection(dbUrl + ";shutdown=true");
            return true;
        }catch(SQLException e)  {
            return true;
        }
    }
    
    /**
     * Get if DB is connected
     * @return true if connected, false otherwise
     */
    public final boolean isConnected()  {
        return (this.con != null);
    }
    
    /**
     * Prepare SQL
     * @param sql sql to be prepared
     * @return statement if success, null otherwise
     */
    protected final PreparedStatement prepareSQL(String sql)  {
        try {
            return this.con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /**
     * Prepare CLOB DB data object
     * @param data String to be written
     * @return Prepared CLOB
     */
    protected final Clob prepareCLOB(String data)  {
        try  {
            if(data == null)
                data = "N/A";
            Clob clob = con.createClob();
            clob.setString(1, data);
            return clob;
        }catch(SQLException e)  {
            return null;
        }
    }
    
    /**
     * Set username
     * @param dbUser username
     */
    protected final void setUser(String dbUser)  {
        this.dbUser = dbUser;
    }
    
    /**
     * Set Password
     * @param dbPswd password
     */
    protected final void setPassword(String dbPswd)  {
        this.dbPswd = dbPswd;
    }
    
    /**
     * Set Url
     * @param dbUrl url of the DB
     */
    protected final void setUrl(String dbUrl)  {
        this.dbUrl = dbUrl;
    }
    
    /**
     * Set Driver
     * @param dbDriver DBdriver
     */
    protected final void setDriver(String dbDriver)  {
        this.dbDriver = dbDriver;
    }
    
    /**
     * Get Username
     * @return username
     */
    protected final String getUser()  {
        return this.dbUser;
    }
    
    /**
     * Get Password
     * @return password
     */
    protected final String getPassword()  {
        return this.dbPswd;
    }
    
    /**
     * DB username
     */
    private String dbUser;
    
    /**
     * DB password
     */
    private String dbPswd;
    
    /**
     * DB URL
     */
    private String dbUrl;
    
    /**
     * DB Driver
     */
    private String dbDriver;
    
    /**
     * DB Connection Object
     */
    private Connection con;
}
