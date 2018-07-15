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
    
    protected final boolean connect()  {
        try {
            Class.forName(this.dbDriver);                    // check if driver is in library(available)
            this.con = DriverManager.getConnection(this.dbUrl);   // connect to DB
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            return false;
        }
    }
    
    protected final boolean reconnect()  {
        try  {
            this.con.close();
            Class.forName(this.dbDriver);
            this.con = DriverManager.getConnection(this.dbUrl);
            return true;
        }catch(SQLException | ClassNotFoundException ex)  {
            return false;
        }
    }
    
    protected final boolean disconnect()  {
        try {
            this.con.close();
            return true;
        }catch(SQLException e)  {
            return false;
        }
    }
    
    public final boolean isConnected()  {
        return (this.con != null);
    }
    
    protected final PreparedStatement prepareSQL(String sql)  {
        try {
            return this.con.prepareStatement(sql);
        } catch (SQLException ex) {
            return null;
        }
    }
    
    protected final void setUser(String dbUser)  {
        this.dbUser = dbUser;
    }
    
    protected final void setPassword(String dbPswd)  {
        this.dbPswd = dbPswd;
    }
    
    protected final void setUrl(String dbUrl)  {
        this.dbUrl = dbUrl;
    }
    
    protected final void setDriver(String dbDriver)  {
        this.dbDriver = dbDriver;
    }
    
    protected final String getUser()  {
        return this.dbUser;
    }
    
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
