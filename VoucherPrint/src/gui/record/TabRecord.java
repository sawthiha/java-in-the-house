/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.record;

import Interfaces.Observer;
import datamodel.Voucher;
import datamodel.database.DBControl;
import gui.input.TabInput;
import java.sql.SQLException;

/**
 * Voucher Tab
 * @author sawthiha
 */
public class TabRecord extends TabInput implements Observer {
    /**
     * Constructor
     * @param voucher data 
     */
    public TabRecord(Voucher voucher)  {
        super(voucher);
        this.DB = DBControl.getInstance();
        this.setText("Voucher " + voucher.getId());
        this.getBtnClear().setText("Reset");
        this.setClosable(true);
    }
    
    @Override
    protected void save(Voucher voucher) throws SQLException, UnsupportedOperationException  {
        this.validateVoucher();
        this.DB.deleteRecipients(this.getRemovedRecipients(this.DB.getAllRecipientsByVoucher(voucher.getId())));
        this.DB.updateVoucher(voucher);
    }
    
    @Override
    protected void clear(Voucher voucher) throws SQLException  {
        Voucher v = this.DB.getVoucherByID(voucher.getId());
        voucher.setCustomer(v.getCustomer());
        voucher.setDate(v.getDate());
        voucher.setPhone(v.getPhone());
        voucher.reloadRecipients(v.getRecipients());
    }
    
    /**
     * Database Connection
     */
    private final DBControl DB;

    @Override
    public void update() {
        this.getTabPane().getTabs().remove(this);
    }
}
