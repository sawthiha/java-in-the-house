/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 *
 * @author sawthiha
 */
public class JSONHandler {
    
    
    /**
     * Read JSON file and assign in data String
     * @param url of JSON file
     */
    private void readJSON(String url)  {
        data = new String();
        try (Scanner scan = new Scanner(new FileInputStream(url))) {
            while(scan.hasNextLine())  {
                data += scan.nextLine();
            }
            scan.close();
        }catch(java.io.FileNotFoundException ex)  {
            data = null;
        }
    }
    
    /**
     * JSON String
     */
    private String data;
}
