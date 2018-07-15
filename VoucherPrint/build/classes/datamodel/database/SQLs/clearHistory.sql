/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  sawthiha
 * Created: Aug 27, 2017
 */

    /*
     * Delete records
     */
    delete from tblVoucher;
    /*
     * Restart auto_increment primary key
     */
    alter table tblVoucher
        alter column id restart with 0;

    /*
     * Restart auto_increment primary key
     */
    alter table tblRecipient
        alter column id restart with 0;