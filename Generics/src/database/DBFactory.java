/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import database.jdbc.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB Factory
 * @author sawthiha
 */
public class DBFactory {
    
    /**
     * Get JDBC connection.
     * @param name Username
     * @param password Password
     * @param driver DB Driver
     * @param bridge DB Bridge
     * @param path DB Path
     * @return DB Connection
     * @throws java.lang.ClassNotFoundException if DB Driver is not found
     * @throws java.sql.SQLException if DB connection error occurred
     */
    public static Connection getJDBC(String name, String password, String driver, String bridge, String path)
            throws ClassNotFoundException, SQLException  {
        DBUtils.isDriverFound(driver);
        String url =  DBUtils.parseURL(name, password, bridge, path);
        Connection con = DriverManager.getConnection(url);
                
        return new JDBC(con);
    }

}
