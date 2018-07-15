/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateTimeUtils;

import java.util.Date;

/**
 *
 * @author sawthiha
 */
public class Compartor {
    /**
     * Compare Date only
     * @param d1 Date 1
     * @param d2 Date 2
     * @return true if same date
     */
    public static boolean compareDateOnly(Date d1, Date d2)  {
        d1 = Converter.zeroTimeDate(d1);
        d2 = Converter.zeroTimeDate(d2);
        
        return d1.compareTo(d2) == 0;
    }
}
