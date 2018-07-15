/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import model.*;

/**
 *
 * @author sawthiha
 */
public interface Updatable {
    /**
     * Update a Voucher.
     * @param voucher voucher object
     * @return true if updated successfully
     */
    public boolean update(Voucher voucher);
    
    /**
     * Update a Recipient.
     * @param recipient recipient object
     * @return true if updated successfully
     */
    public boolean update(Recipient recipient);
    
    /**
     * Update a list of Voucher.
     * @param list Voucher List
     * @return true if updated successfully
     */
    public boolean update(Iterable<Voucher> list);
    
    /**
     * Update a list of Recipients of a Voucher.
     * @param voucher Parent Voucher
     * @param list Recipient List
     * @return true if successfully
     */
    public boolean update(Voucher voucher, Iterable<Recipient> list);
}
