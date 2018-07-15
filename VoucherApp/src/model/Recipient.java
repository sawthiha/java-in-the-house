/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.LongBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sawthiha
 */
public class Recipient {
    /**
     * Idle Constructor
     */
    public Recipient()  {
        this(-1, -1, "", "", "", 0l, 0, 0, "");
    }
    
    /**
     * Clone Constructor
     * @param recipient
     */
    public Recipient(Recipient recipient)  {
        this(recipient.getId(), recipient.getVoucher(), recipient.getName(),
                recipient.getPhone(), recipient.getAddress(), 
                recipient.getAmount(), recipient.getPlus(), 
                recipient.getMinus(), recipient.getRemark());
    }
    
    /**
     * Generic Constructor
     * @param id
     * @param voucher
     * @param name
     * @param phone
     * @param address
     * @param amount
     * @param plus
     * @param minus
     * @param remark 
     */
    public Recipient(int id, int voucher, String name, String phone, 
            String address, long amount, int plus, int minus, String remark)  {
        this.id = new SimpleIntegerProperty(id);
        this.voucher = new SimpleIntegerProperty(voucher);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.amount = new SimpleLongProperty(amount);
        this.plus = new SimpleIntegerProperty(plus);
        this.remark = new SimpleStringProperty(remark);
        this.bindings();
    }
    
    /**
     * Initiate Bindings
     */
    private void bindings()  {
        this.delivery = new IntegerBinding() {
            {
                super.bind(plus, minus);
            }
            
            @Override
            protected int computeValue() {
                return plus.get() + minus.get();
            }
        };
        this.total = new LongBinding() {
            {
                super.bind(amount, plus, minus);
            }
            
            @Override
            protected long computeValue() {
                return amount.get() + plus.longValue() - minus.longValue();
            }
        };
    }
    
    /**
     * Get ID
     * @return id 
     */
    public int getId()  {
        return this.id.get();
    }
    
    /**
     * Get Voucher ID
     * @return voucher
     */
    public int getVoucher()  {
        return this.voucher.get();
    }
    
    /**
     * Get Name
     * @return name
     */
    public String getName()  {
        return this.name.get();
    }
    
    /**
     * Get Phone
     * @return phone
     */
    public String getPhone()  {
        return this.phone.get();
    }
    
    /**
     * Get address
     * @return address
     */
    public String getAddress()  {
        return this.address.get();
    }
    
    /**
     * Get amount
     * @return amount
     */
    public long getAmount()  {
        return this.amount.get();
    }
    
    /**
     * Get plus delivery fee
     * @return plus
     */
    public int getPlus()  {
        return this.plus.get();
    }
    
    /**
     * Get minus delivery fee
     * @return minus
     */
    public int getMinus()  {
        return this.minus.get();
    }
    
    /**
     * Get Remark
     * @return remark
     */
    public String getRemark()  {
        return this.remark.get();
    }
    
    /**
     * Get Delivery Fee
     * @return delivery
     */
    public int getDelivery()  {
        return this.delivery.get();
    }
    
    /**
     * Get Total Amount
     * @return total
     */
    public long getTotal()  {
        return this.total.get();
    }
    
    /**
     * Set ID
     * @param id 
     */
    public void setId(int id)  {
        this.id.set(id);
    }
    
    /**
     * Set Voucher ID
     * @param voucher 
     */
    public void setVoucher(int voucher)  {
        this.voucher.set(voucher);
    }
    
    /**
     * Set Name
     * @param name 
     */
    public void setName(String name)  {
        this.name.set(name);
    }
    
    /**
     * Set Phone
     * @param phone 
     */
    public void setPhone(String phone)  {
        this.phone.set(phone);
    }
    
    /**
     * Set Address
     * @param address 
     */
    public void setAddress(String address)  {
        this.address.set(address);
    }
    
    /**
     * Set Amount
     * @param amount 
     */
    public void setAmount(long amount)  {
        this.amount.set(amount);
    }
    
    /**
     * Set Plus Delivery Fee
     * @param plus 
     */
    public void setPlus(int plus)  {
        this.plus.set(plus);
    }
    
    /**
     * Set Minus Delivery Fee
     * @param minus 
     */
    public void setMinus(int minus)  {
        this.minus.set(minus);
    }
    
    /**
     * Set Remark
     * @param remark 
     */
    public void setRemark(String remark)  {
        this.remark.set(remark);
    }
    
    /**
     * ID Property Object
     * @return id
     */
    public ReadOnlyIntegerProperty idProperty()  {
        return this.id;
    }
    
    /**
     * Voucher Property Object
     * @return voucher
     */
    public ReadOnlyIntegerProperty voucherProperty()  {
        return this.voucher;
    }
    
    /**
     * Name Property Object
     * @return name
     */
    public ReadOnlyStringProperty nameProperty()  {
        return this.name;
    }
    
    /**
     * Phone Property Object
     * @return phone
     */
    public ReadOnlyStringProperty phoneProperty()  {
        return this.phone;
    }
    
    /**
     * Address Property Object
     * @return address
     */
    public ReadOnlyStringProperty addressProperty()  {
        return this.address;
    }
    
    /**
     * Amount Property Object
     * @return amount
     */
    public ReadOnlyLongProperty amountProperty()  {
        return this.amount;
    }
    
    /**
     * Plus Property Object
     * @return plus
     */
    public ReadOnlyIntegerProperty plusProperty()  {
        return this.plus;
    }
    
    /**
     * Minus Property Object
     * @return minus
     */
    public ReadOnlyIntegerProperty minusProperty()  {
        return this.minus;
    }
    
    /**
     * Remark Property Object
     * @return remark
     */
    public ReadOnlyStringProperty remarkProperty()  {
        return this.remark;
    }
    
    private IntegerProperty id;
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
