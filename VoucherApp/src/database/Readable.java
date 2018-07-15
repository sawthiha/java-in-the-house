/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.Collection;
import model.*;

/**
 *
 * @author sawthiha
 */
public interface Readable {
    /**
     * Read a Voucher.
     * @param voucher voucher object
     * @return true if read successfully
     */
    public boolean read(Voucher voucher);
    
    /**
     * Read a Recipient.
     * @param recipient recipient object
     * @return true if read successfully
     */
    public boolean read(Recipient recipient);
    
    /**
     * Read list of Vouchers.
     * @param list Voucher List
     * @return true if successfully read
     */
    public boolean read(Collection<Voucher> list);
    
    /**
     * Read a list of Recipients of a Voucher.
     * @param voucher parent Voucher
     * @param list Recipient List
     * @return 
     */
    public boolean read(Voucher voucher, Collection<Voucher> list);
}
