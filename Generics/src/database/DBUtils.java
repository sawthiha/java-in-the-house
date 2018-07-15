/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 * Database Utilities
 * @author sawthiha
 */
public class DBUtils {
    /**
     * Check the DB Driver.
     * @param driver DB Driver Class Name
     * @return true if found
     */
    public static boolean isDriverFound(String driver)  {
        try  {
            Class.forName(driver);
            
            return true;
        } catch(ClassNotFoundException except)  {
            return false;
        }
    }
    
    /**
     * Parse URL from DB informations.
     * @param name Username
     * @param password Password
     * @param bridge DB Bridge
     * @param path DB path
     * @return URL
     */
    public static String parseURL(String name, String password, String bridge, String path)  {
        String url = bridge + path;
        url += ";username=" + name;
        url += ";password=" + password;
        
        return url;
    }
}
