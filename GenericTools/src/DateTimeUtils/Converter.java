/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateTimeUtils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author sawthiha
 */
public class Converter {
    public static Date zeroTimeDate(Date date)  {
        Calendar c = Calendar.getInstance();
        
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        return c.getTime();
    }
}
