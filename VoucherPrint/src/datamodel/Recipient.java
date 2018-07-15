/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * TODO: Remark & Phone added to Recipient must have intergrity
 */

/**
 *
 * @author sawthiha
 */
public class Recipient {
    /**
     * Initiate A New Recipient Entry
     * @param relativeID Relative ID according to other recipients of a Voucher
     */
    public Recipient(int relativeID)  {
        this(-1, relativeID, -1, "", "", "", 0l, 0, 0, "");
    }
    
    /**
     * Initiate A Recipients with Complete Info
     * @param id DB ID of the recipient
     * @param relativeID Relative ID according to other recipients of a Voucher
     * @param voucher DB ID of parent Voucher
     * @param name Name of the recipient
     * @param phone Ph No
     * @param address Address
     * @param amount Amount
     * @param plus +Delivery
     * @param minus -Delivery
     * @param remark Remark on the Recipient
     */
    public Recipient(int id, int relativeID, int voucher,String name, String phone,String address, long amount, int plus, int minus, String remark) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.relativeID = new SimpleIntegerProperty(this, "relativeID", relativeID);
        this.voucher = new SimpleIntegerProperty(this, "voucher", voucher);
        this.name = new SimpleStringProperty(this, "name", name);
        this.phone = new SimpleStringProperty(this, "phone", phone);
        this.address = new SimpleStringProperty(this, "address", address);
        this.amount = new SimpleLongProperty(this, "amount", amount);
        this.plus = new SimpleIntegerProperty(this, "plus", plus);
        this.minus = new SimpleIntegerProperty(this, "minus", minus);
        this.remark = new SimpleStringProperty(this, "remark", remark);
        
        this.bindings();
    }
    
    /**
     * Initiate Bindings between properties
     */
    private void bindings()  {
        /**
         * Total Delivery = +Delivery - -Delivery
         */
        delivery = new IntegerBinding() {
            
            {
                super.bind(plus, minus);
            }
            
            @Override
            protected int computeValue() {
                return plus.get() - minus.get();
            }
        };
        /**
         * Total Amount = Delivery Amount + Total Delivery
         */
        total = new LongBinding() {
            
            {
                super.bind(amount, delivery);
            }
            
            @Override
            protected long computeValue() {
                return amount.get() + delivery.get();
            }
        };
    }
    
    /**
     * Get DB ID of the Recipient
     * @return DB ID of the Recipient
     */
    public int getID() {
        return this.id.get();
    }
    
    /**
     * Get ID of the recipient accordingly to other Recipients in the Voucher
     * @return ID of the recipient accordingly to other Recipients in the Voucher
     */
    public int getRelativeID()  {
        return this.relativeID.get();
    }
    
    /**
     * Get DB ID of the voucher
     * @return DB ID of the voucher
     */
    public int getVoucher()  {
        return this.voucher.get();
    }
    
    /**
     * Get Name of the Recipient
     * @return Name of the Recipient
     */
    public String getName()  {
        return this.name.get();
    }
    
    /**
     * Get Phone No of the Recipient
     * @return Phone no of the Recipient
     */
    public String getPhone()  {
        return this.phone.get();
    }
    
    /**
     * Get Address of the Recipient
     * @return Address of the Recipient
     */
    public String getAddress()  {
        return this.address.get();
    }
    
    /**
     * Get Amount of the Delivery
     * @return Amount of the Delivery
     */
    public long getAmount()  {
        return this.amount.get();
    }
    
    /**
     * Get +Delivery Fee
     * @return -Delivery Fee
     */
    public int getPlus()  {
        return this.plus.get();
    }
    
    /**
     * Get -Delivery Fee
     * @return -Delivery Fee
     */
    public int getMinus()  {
        return this.minus.get();
    }
    
    /**
     * Get Remark
     * @return Remark of the recipient
     */
    public String getRemark()  {
        return this.remark.get();
    }
    
    /**
     * Get Total Delivery
     * @return Total Delivery Fee(Minus -Delivery)
     */
    public int getDelivery()  {
        return this.delivery.get();
    }
    
    /**
     * Get Total Amount
     * @return Total Amount
     */
    public long getTotal()  {
        return this.total.get();
    }
    /**
     * Amount Property
     * @return Amount
     */
    public LongProperty amountProperty()  {
        return this.amount;
    }
    
    /**
     * +Delivery Property
     * @return +Delivery
     */
    public IntegerProperty plusProperty()  {
        return this.plus;
    }
    
    /**
     * -Delivery Property
     * @return -Delivery
     */
    public IntegerProperty minusProperty()  {
        return this.minus;
    }
    
    /**
     * Set DB ID of the Recipient
     * @param id DB ID of the Recipient
     */
    public void setID(int id) {
        this.id.set(id);
    }
    
    /**
     * Set ID correspondingly to other recipients in the Voucher
     * @param relativeID ID correspondingly to other recipients in the Voucher
     */
    public void setRelativeID(int relativeID)  {
        this.relativeID.set(relativeID);
    }
    
    /**
     * Set DB ID of parent voucher
     * @param voucher DB ID of parent voucher
     */
    public void setVoucher(int voucher)  {
        this.voucher.set(voucher);
    }
    
    /**
     * Set Recipient Name
     * @param name Recipient Name
     */
    public void setName(String name)  {
        this.name.set(name);
    }
    
    /**
     * Set the Phone No of the Recipient
     * @param phone Phone No of the Recipient
     */
    public void setPhone(String phone)  {
        this.phone.set(phone);
    }
    
    /**
     * Set Address of the Delivery
     * @param address Amount of the Delivery
     */
    public void setAddress(String address)  {
        this.address.set(address);
    }
    
    /**
     * Set Amount of the Delivery
     * @param amount Amount of the Delivery
     */
    public void setAmount(long amount)  {
        this.amount.set(amount);
    }
    
    /**
     * Set +Delivery Fee
     * @param plus +Delivery Fee
     */
    public void setPlus(int plus)  {
        this.plus.set(plus);
    }
    
    /**
     * Set -Delivery Fee
     * @param minus -Delivery Fee
     */
    public void setMinus(int minus)  {
        this.minus.set(minus);
    }
    
    /**
     * Set Remark
     * @param remark remark
     */
    public void setRemark(String remark)  {
        this.remark.set(remark);
    }
    
    @Override
    public String toString()  {
        return "\nVoucher : " + voucher.get() + "\nName : " + name.get() + "\nPhone : " + phone.get() + "\nAddress : " + address.get() 
                + "\nAmount" + amount.get() + "\nPlus : " + plus.get() + "\nMinus : "+ minus.get() + "\nRemark : " + remark.get();
    }
    
    private IntegerProperty id;
    private IntegerProperty relativeID;
    private IntegerProperty voucher;
    private StringProperty name;
    private StringProperty phone;
    private StringProperty address;
    private LongProperty amount;
    private IntegerProperty plus;
    private IntegerProperty minus;
    private StringProperty remark;
    private IntegerBinding delivery;
    private LongBinding total;
}
