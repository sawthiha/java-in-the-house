/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.database;

import java.sql.*;
import datamodel.Voucher;
import datamodel.Recipient;
import java.util.*;
import Database.*;
import javafx.scene.control.Alert;

/**
 * Database Control - Singleton Pattern
 * @author sawthiha
 * @version 1.0
 * @see StdDBControl
 */
public class DBControl extends StdDBControl {
    /**
     * Constructor
     */
    private DBControl()  {
        this.setUser("pmdelivery");
        this.setPassword("N3xBaJE7");
        this.setDriver("org.apache.derby.jdbc.EmbeddedDriver");
        // TODO: jar directory access
        this.setUrl("jdbc:derby:data/database/dbVoucher;user=" + this.getUser() + ";password=" + this.getPassword());
        if(this.connect())  {
            this.prepareSQLs();
        }else  {
            showDBErrorAndExit();
        }
    }
    
    /**
     * Inform DB Error which is a critical error
     * And, Exit
     */
    private void showDBErrorAndExit()  {
        Alert isDBErr = new Alert(Alert.AlertType.ERROR);
        isDBErr.setTitle("Something went wrong!");
        isDBErr.setHeaderText("Database can't be connected!");
        isDBErr.setContentText("Application may already be running!");
        isDBErr.showAndWait();
        System.exit(1);
    }
    
    /**-
     * Close connection & shutdown DB
     * @return true if closed, false otherwise
     */
    public boolean close()  {
        try {
            this.stAddRecipient.close();
            this.stAddVoucher.close();
            this.stClearHistory[0].close();
            this.stClearHistory[1].close();
            this.stClearHistory[2].close();
            this.stDeleteVoucher.close();
            this.stGetAllVoucher.close();
            this.stGetLastVoucher.close();
            this.stGetRecipientsByVoucher.close();
        } catch (SQLException ex) {
        }
        return disconnect();
    }
    
    /**
     * Get an instance to the DBControl Object
     * @return an instance to the DBControl
     */
    public static DBControl getInstance()  {
        return instance;
    }
    
    
    
    /**
     * Clear All The Records in the DB
     * @return true if Success or false otherwise
     * @throws java.sql.SQLException
     */
    public boolean clearHistory() throws SQLException  {
        
        this.stClearHistory[0].executeUpdate();
        this.stClearHistory[1].executeUpdate();
        this.stClearHistory[2].executeUpdate();
        return true;
        
    }
    
    /**
     * Add a voucher to the DB
     * @param voucher Voucher Object to be stored
     * @return true if success, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean addVoucher(Voucher voucher) throws SQLException {
        this.stAddVoucher.setString(1, voucher.getCustomer());
        this.stAddVoucher.setTimestamp(2, new Timestamp(java.sql.Date.valueOf(voucher.getDate()).getTime()));
        this.stAddVoucher.setString(3, voucher.getPhone());
        this.stAddVoucher.setBoolean(4, voucher.getIsPrinted());
        this.stAddVoucher.executeUpdate();
        this.stAddVoucher.clearParameters();
            
        try (ResultSet result = this.stGetLastVoucher.executeQuery()) {
            int id = 0;
            if(result.next())
                id = result.getInt("voucher");
            voucher.setId(id);
            Iterator<Recipient> iterator = voucher.getRecipients().iterator();
            while(iterator.hasNext())  {
                iterator.next().setVoucher(id);
            }
        }
        return this.addRecipients(voucher.getRecipients());
    }
    
    /**
     * Delete Vouchers
     * @param list Voucher List
     * @return true if successfully Deleted
     * @throws java.sql.SQLException
     */
    public boolean deleteVouchers(Iterable<Voucher> list) throws SQLException  {
        Voucher v;
        Iterator<Voucher> it = list.iterator();
        while(it.hasNext())  {
            v = it.next();
            this.stDeleteVoucher.setInt(1, v.getId());
            this.stDeleteVoucher.addBatch();
            v.setId(-1);
        }
            
        this.stDeleteVoucher.executeBatch();
        this.stDeleteVoucher.clearBatch();
        return true;
    }
        
    /**
     * Delete Recipients
     * @param list
     * @return
     * @throws java.sql.SQLException
     */
    public boolean deleteRecipients(Iterable<Recipient> list)  throws SQLException  {
        Iterator<Recipient> it = list.iterator();
        while(it.hasNext())  {
            this.stDeleteRecipient.setInt(1, it.next().getID());
            this.stDeleteRecipient.addBatch();
        }
            
        this.stDeleteRecipient.executeBatch();
        this.stDeleteRecipient.clearBatch();
        return true;
    }
        
    /**
     * Update a voucher
     * @param voucher
     * @return true if success
     * @throws java.sql.SQLException 
     */
    public boolean updateVoucher(Voucher voucher) throws SQLException {
        boolean sts;
        
        this.stUpdateVoucher.setString(1, voucher.getCustomer());
        this.stUpdateVoucher.setTimestamp(2, new Timestamp(java.sql.Date.valueOf(voucher.getDate()).getTime()));
        this.stUpdateVoucher.setString(3, voucher.getPhone());
        this.stUpdateVoucher.setInt(5, voucher.getId());
        this.stUpdateVoucher.setBoolean(4, voucher.getIsPrinted());
        sts = this.stUpdateVoucher.executeUpdate() == 1;
        this.stUpdateVoucher.clearParameters();
        sts &= this.updateRecipients(voucher);
        return sts;
    }
    
    /**
     * Update Printed Status of the Vouchers
     * @param list Vouchers
     * @return true if success
     * @throws SQLException 
     */
    public boolean updatePrinted(List<Voucher> list) throws SQLException {
        Voucher v;
        Iterator<Voucher> it = list.iterator();
        while(it.hasNext())  {
            v = it.next();
            this.stUpdatePrinted.setBoolean(1, v.getIsPrinted());
            this.stUpdatePrinted.setInt(2, v.getId());
            this.stUpdatePrinted.addBatch();
        }
        
        this.stUpdatePrinted.executeBatch();
        this.stUpdatePrinted.clearBatch();
        return true;
    }
    
    /**
     * Get Voucher by ID.
     * @param id
     * @return voucher
     * @throws SQLException 
     */
    public Voucher getVoucherByID(int id) throws SQLException {
        this.stGetVoucherByID.setInt(1, id);
        try (ResultSet result = this.stGetVoucherByID.executeQuery()) {
            this.stGetVoucherByID.clearParameters();
            return this.parseVoucher(result);
        }
    }
    
    /**
     *
     * @param voucher
     * @return true if success
     * @throws SQLException
     */
    public boolean updateRecipients(Voucher voucher) throws SQLException {
        Recipient r;
        Iterator<Recipient> i = voucher.getRecipients().listIterator();

        while(i.hasNext())  {
            r = i.next();
            if(r.getID() >= 0)  {
                this.stUpdateRecipient.setString(1, r.getName());
                this.stUpdateRecipient.setString(2, r.getPhone());
                this.stUpdateRecipient.setString(3, r.getAddress());
                this.stUpdateRecipient.setLong(4, r.getAmount());
                this.stUpdateRecipient.setInt(5, r.getPlus());
                this.stUpdateRecipient.setInt(6, r.getMinus());
                this.stUpdateRecipient.setString(7, r.getRemark());
                this.stUpdateRecipient.setInt(8, r.getID());
                    
                this.stUpdateRecipient.addBatch();
            }else  {
                this.addRecipient(voucher.getId(), r);
            }
        }
         
        this.stUpdateRecipient.executeBatch();
        this.stUpdateRecipient.clearBatch();
        return true;
    }
    
    /**
     * Remove a voucher
     * @param voucher id of the voucher
     * @return true if success, false otherwise
     * @throws java.sql.SQLException
     */
    public boolean removeVoucher(int voucher) throws SQLException {
        boolean sts;
        this.stDeleteVoucher.setInt(1, voucher);
          
        sts = this.stDeleteVoucher.executeUpdate() == 1;
        this.stDeleteVoucher.clearParameters();
        return sts;
    }
    
    /**
     * Add a recipient
     * @param voucher Voucher ID
     * @param r Recipient
     * @return true if added
     * @throws java.sql.SQLException
     */
    public boolean addRecipient(int voucher, Recipient r) throws SQLException {
        r.setVoucher(voucher);
        this.stAddRecipient.setInt(1, voucher);
        this.stAddRecipient.setString(2, r.getName());
        this.stAddRecipient.setString(3, r.getPhone());
        this.stAddRecipient.setString(4, r.getAddress());
        this.stAddRecipient.setLong(5, r.getAmount());
        this.stAddRecipient.setInt(6, r.getPlus());
        this.stAddRecipient.setInt(7, r.getMinus());
        this.stAddRecipient.setClob(8, this.prepareCLOB(r.getRemark()));
        
        this.stAddRecipient.executeUpdate();
        this.stAddRecipient.clearParameters();
        try (ResultSet result = this.stGetLastRecipient.executeQuery()) {
            if(result.next())  {
                r.setID(result.getInt("recipient"));
            }
        }
            return true;
    }
    
    /**
     * Add all the recipients correspond to the voucher
     * 
     * @param list a list of recipient
     * @return true if success, false otherwise 
     */
    private boolean addRecipients(List<Recipient> list) throws SQLException {
        
            Iterator<Recipient> it = list.iterator();
            Recipient tmp;
            while(it.hasNext())  {
                tmp = it.next();
                this.stAddRecipient.setInt(1, tmp.getVoucher());     // set voucher
                this.stAddRecipient.setString(2, tmp.getName());    // set recipient
                this.stAddRecipient.setString(3, tmp.getPhone());   // set phone
                this.stAddRecipient.setString(4, tmp.getAddress()); // set address
                this.stAddRecipient.setLong(5, tmp.getAmount());    // set amount
                this.stAddRecipient.setInt(6, tmp.getPlus());       // set plus
                this.stAddRecipient.setInt(7, tmp.getMinus());      // set minus
                this.stAddRecipient.setClob(8, this.prepareCLOB(tmp.getRemark()));  // set remark
                this.stAddRecipient.addBatch();
            }
            
            this.stAddRecipient.executeBatch();
            this.stAddRecipient.clearBatch();
            return true;
    }
    
    /**
     * Get All Voucher in the History
     * @return a list of vouchers if success or null otherwise
     * @throws java.sql.SQLException
     */
    public LinkedList<Voucher> getAllVouchers() throws SQLException {
        
        LinkedList<Voucher> vouchers;
        try (ResultSet result = this.stGetAllVoucher.executeQuery()) {
            vouchers = new LinkedList<>();
            while(result.next())  {
                vouchers.add(parseVoucher(result));
            }
        }
        return vouchers;
    }
    
    /**
     * Get all recipients correspond to a voucher
     * @param voucher id of the voucher
     * @return a list of Recipients if success or null otherwise
     * @throws java.sql.SQLException
     */
    public LinkedList<Recipient> getAllRecipientsByVoucher(int voucher) throws SQLException {
        
        this.stGetRecipientsByVoucher.setInt(1, voucher);
        LinkedList<Recipient> recipients;
        try (ResultSet result = this.stGetRecipientsByVoucher.executeQuery()) {
            this.stGetRecipientsByVoucher.clearParameters();
            recipients = new LinkedList<>();
            int count = 1;
            while(result.next())  {
                recipients.add(parseRecipient(result, count++));
            }
        }
        return recipients;
    }
    
    /**
     * Prepare SQL Statements to be used
     * 
     */
    private void prepareSQLs(){
        this.stClearHistory[0] = this.prepareSQL("DELETE FROM tblVoucher");
        this.stClearHistory[1] = this.prepareSQL("ALTER TABLE tblVoucher ALTER COLUMN id RESTART WITH 0");
        this.stClearHistory[2] = this.prepareSQL("ALTER TABLE tblRecipient ALTER COLUMN id RESTART WITH 0");
        this.stGetVoucherByID = this.prepareSQL("SELECT * FROM tblVoucher WHERE id = ?");
        this.stGetAllVoucher = this.prepareSQL("SELECT * FROM tblVoucher ORDER BY created_date");
        this.stGetRecipientsByVoucher = this.prepareSQL("SELECT * FROM tblRecipient WHERE voucher=? ORDER BY id");
        this.stAddRecipient = this.prepareSQL("INSERT INTO tblRecipient(voucher, recipient, phone, address, amount, plus, minus, remark) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
        this.stAddVoucher = this.prepareSQL("INSERT INTO tblVoucher(customer, created_date, phone, printed) VALUES(?, ?, ?, ?)");
        this.stDeleteVoucher = this.prepareSQL("DELETE FROM tblVoucher WHERE id=?");
        this.stDeleteRecipient = this.prepareSQL("DELETE FROM tblRecipient WHERE id=?");
        this.stGetLastVoucher = this.prepareSQL("SELECT MAX(id) as voucher FROM tblVoucher");
        this.stGetLastRecipient = this.prepareSQL("SELECT MAX(id) as recipient FROM tblRecipient");
        this.stUpdateVoucher = this.prepareSQL("UPDATE tblVoucher SET customer = ?, created_date = ?, phone = ?, printed = ? WHERE id = ?");
        this.stUpdateRecipient = this.prepareSQL("UPDATE tblRecipient SET recipient = ?, phone = ?, address = ?, amount = ?, plus = ?, minus = ?, remark = ? WHERE id = ?");
        this.stUpdatePrinted = this.prepareSQL("UPDATE tblVoucher SET printed = ? WHERE id = ?");
    }
    
    /**
     * Parse a voucher from a result set
     * @param result result set to be parsed
     * @return Voucher Object if success or null otherwise
     * 
     */
    private Voucher parseVoucher(ResultSet result) throws SQLException  {
        
        int id = result.getInt("id");
        String customer = result.getString("customer").trim();
        java.util.Date date = result.getTimestamp("created_date");
        String phone = result.getString("phone").trim();
        boolean printed = result.getBoolean("printed");
        List<Recipient> recipients = this.getAllRecipientsByVoucher(id);
        
        return new Voucher(id, customer, date, phone, recipients, printed);
    }
    
    /**
     * Parse a recipient from a result set
     * @param result result set to be parsed
     * @param relativeID relativeID of the recipient in the list
     * @return Recipient Object if success, null otherwise
     */
    private Recipient parseRecipient(ResultSet result, int relativeID) throws SQLException {
        int id, voucher, plus, minus;
        long amount;
        String name, phone,address;
        Clob clob;
        
        id = result.getInt("id");
        voucher = result.getInt("voucher");
        name = result.getString("recipient").trim();
        phone = result.getString("phone").trim();
        address = result.getString("address").trim();
        amount = result.getLong("amount");
        plus = result.getInt("plus");
        minus = result.getInt("minus");
        clob = result.getClob("remark");
            
        return new Recipient(id, relativeID, voucher, name, phone, address, amount, plus, minus, clob.getSubString(1, 255).trim());
        
    }
    
    private static DBControl instance = new DBControl();
    
    /**
     * DELETE FROM tblVoucher;
     * ALTER TABLE tblVoucher
     *  ALTER COLUMN id RESTART WITH 0 INCREMENT BY 1;
     * ALTER TABLE tblRecipient
     *  ALTER COLUMN id RESTART WITH 0 INCREMENT BY 1;
     */
    private PreparedStatement stClearHistory[] = {null, null, null};
    /**
     * SELECT * FROM tblVoucher WHERE id = ?;
     */
    private PreparedStatement stGetVoucherByID;
    /**
     * SELECT * FROM tblVoucher ORDER BY created_date;
     */
    private PreparedStatement stGetAllVoucher;
    
    /**
     * SELECT * FROM tblRecipient WHERE voucher=? ORDER BY id;
     */
    private PreparedStatement stGetRecipientsByVoucher;
    
    /**
     * INSERT INTO tblRecipient(voucher, recipient, phone, address, amount, plus, minus, remark) VALUES(?, ?, ?, ?, ?, ?, ?, ?);
     */
    private PreparedStatement stAddRecipient;
    
    /**
     * INSERT INTO tblVoucher(customer, created_date, phone)VALUES(?, ?, ?);
     */
    private PreparedStatement stAddVoucher;
    /**
     * DELETE FROM tblVoucher WHERE id=?;
     */
    private PreparedStatement stDeleteVoucher;
    /**
     * DELETE FROM tblRecipient WHERE id=?;
     */
    private PreparedStatement stDeleteRecipient;
    /**
     * SELECT MAX(id) as voucher FROM tblVoucher;
     */
    private PreparedStatement stGetLastVoucher;
    /**
     * SELECR MAX(id) as recipient FROM tblRecipient;
     */
    private PreparedStatement stGetLastRecipient;
    /**
     * UPDATE tblVoucher SET customer = ?, created_date = ?, phone = ?, printed = ? WHERE id = ?;
     */
    private PreparedStatement stUpdateVoucher;
    /**
     * UPDATE tblRecipient SET recipient = ?, phone = ?, address = ?, amount = ?, plus = ?, minus = ?, remark = ? WHERE id = ?;
     */
    private PreparedStatement stUpdateRecipient;
    /**
     * UPDATE tblVoucher SET printed = ? WHERE id = ?
     */
    private PreparedStatement stUpdatePrinted;
}