/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.json;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;
import JSON.JSONObject;



/**
 *
 * @author sawthiha
 */
public class JsonHandler {
    /**
     * Constructor
     */
    private JsonHandler()  {
        data = readJSON(url);
        obj = new JSONObject(data);
    }
    
    /**
     * Read JSON file and assign in data String
     * @param url of JSON file
     * @return JSON String or null if file not found
     */
    private String readJSON(String url)  {
        String str = new String();
        try (Scanner scan = new Scanner(new FileInputStream(url))) {
            while(scan.hasNextLine())  {
                str += scan.nextLine();
            }
            scan.close();
        }catch(java.io.FileNotFoundException ex)  {
            str = null;
        }finally  {
            return str;
        }
    }
    
    /**
     * Get Phone No
     * @return String - 
     */
    public String getPhone()  {
        return obj.getString("phone");
    }
    
    /**
     * Get Viber
     * @return String - 
     */
    public String getViber()  {
        return obj.getString("viber");
    }
    
    /**
     * Get Cashier
     * @return String - 
     */
    public String getCashier()  {
        return obj.getString("cashier");
    }
    
    /**
     * Get Facebook
     * @return String - 
     */
    public String getFacebook()  {
        return obj.getString("facebook");
    }
    
    /**
     * Set Phone
     * @param phone
     */
    public void setPhone(String phone)  {
        obj.put("phone", phone);
    }
    
    /**
     * Set Viber
     * @param viber
     */
    public void setViber(String viber)  {
        obj.put("viber", viber);
    }
    
    /**
     * Set Cashier
     * @param cashier
     */
    public void setCashier(String cashier)  {
        obj.put("cashier", cashier);
    }
    
    /**
     * Set Facebook
     * @param facebook
     */
    public void setFacebook(String facebook)  {
        obj.put("facebook", facebook);
    }
    
    /**
     * Set values to infos.json
     * @param phone - phone no
     * @param viber - viber contact
     * @param cashier - cashier contact
     * @param facebook  - facebook contact
     * @return true if set successfully, false otherwise
     */
    public boolean set(String phone, String viber, String cashier, String facebook)  {
        obj.put("phone", phone);
        obj.put("viber", viber);
        obj.put("cashier", cashier);
        obj.put("facebook", facebook);
        try(FileWriter writer = new FileWriter(url)) {
            obj.write(writer);
            writer.close();
            return true;
        }catch (java.io.IOException ex)  {
           return false;
        }
    }
    
    /**
     * Get an instance
     * @return instance
     */
    public static JsonHandler getInstance()  {
        return instance;
    }
    
    /**
     * instance
     */
    private static final JsonHandler instance = new JsonHandler();
    
    /**
     * URL of infos file
     */
    private final String url = "data/json/infos.json";
    /**
     * JSON string
     */
    private String data;
    private JSONObject obj;
}
