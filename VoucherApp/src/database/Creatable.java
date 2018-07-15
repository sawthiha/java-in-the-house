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
public interface Creatable {
    /**
     * Create a Voucher.
     * @param voucher Voucher Object
     * @return true if created successfully
     */
    public boolean create(Voucher voucher);
    
    /**
     * Create a Recipient.
     * @param recipient Recipient Object
     * @return true if created successfully
     */
    public boolean create(Recipient recipient);
    
    /**
     * Create a List of Vouchers.
     * @param list Voucher List
     * @return true if created successfully
     */
    public boolean create(Iterable<Voucher> list);
    
    /**
     * Create a List of Recipients.
     * @param voucher Parent Voucher
     * @param list Recipient List
     * @return true if created successfully
     */
    public boolean create(Voucher voucher, Iterable<Recipient> list);
}
