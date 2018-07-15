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
public interface Deletable {
    /**
     * Delete a Voucher.
     * @param voucher Voucher Object
     * @return true if deleted successfully
     */
    public boolean delete(Voucher voucher);
    
    /**
     * Delete a Recipient.
     * @param recipient Recipient Object
     * @return true if deleted successfully
     */
    public boolean delete(Recipient recipient);
    
    /**
     * Dummy Interface to avoid override erasure
     */
    public interface VoucherIterable extends Iterable<Voucher>  {};
    /**
     * Delete a list of Vouchers.
     * @param list Voucher List
     * @return true if deleted successfully
     */
    public boolean delete(VoucherIterable list);
    
    /**
     * Dummy Interface to avoid override erasure
     */
    public interface RecipientIterable extends Iterable<Recipient> {};
    /**
     * Delete a list of Recipients.
     * @param list Recipient List
     * @return true if deleted successfully
     */
    public boolean delete(RecipientIterable list);
    
    /**
     * Delete All The Records.
     * @return true if deleted successfully
     */
    public boolean deleteAll();
}
