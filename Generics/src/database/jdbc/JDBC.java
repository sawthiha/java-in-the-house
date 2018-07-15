/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sawthiha
 */
public class JDBC extends database.DBConnection {
    /**
     * Construct a JDBC.
     * @param liaison Surrogate Connection
     */
    public JDBC(Connection liaison) {
        super(liaison);
    }
    
    @Override
    public void close() throws SQLException {
        super.close();
        
        String url = this.getMetaData().getURL();
        url += ";shutdown=true";
        DriverManager.getConnection(url);
    }
}
