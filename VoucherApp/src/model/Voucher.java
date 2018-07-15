/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.collections.ObservableList;


/**
 * Voucher Data Model
 * @author sawthiha
 * 
 */
public class Voucher  {
    /**
     * Idle Constructor
     */
    public Voucher()  {
        this(-1, "", "", new Date(), new ArrayList<Recipient>());
    }
    
    /**
     * Clone Constructor
     * @param voucher Original Voucher
     */
    public Voucher(Voucher voucher)  {
        this(voucher.getId(), voucher.getName(), voucher.getPhone(), voucher.getDate(), voucher.getRecipients());
    }
    
    /**
     * Generic Constructor
     * @param id
     * @param name
     * @param phone
     * @param date 
     * @param recipients 
     */
    public Voucher(int id, String name, String phone, Date date, List<Recipient> recipients)  {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.date = new SimpleObjectProperty(date);
        this.recipients = new SimpleListProperty(javafx.collections.FXCollections.observableList(recipients));
    }
    
    /**
     * Set Customer Name
     * @param name 
     */
    public void setName(String name)  {
        this.name.set(name);
    }
    
    /**
     * Set Phone No
     * @param phone 
     */
    public void setPhone(String phone)  {
        this.phone.set(phone);
    }
    
    /**
     * Set Date
     * @param date 
     */
    public void setDate(Date date)  {
        this.date.set(LocalDate.ofEpochDay(date.getTime()));
    }
    
    /**
     * Set the recipients
     * Note: All the previous entries will be purged.
     * @param recipients 
     */
    public void setRecipients(List<Recipient> recipients)  {
        this.recipients.setAll(recipients);
    }
    
    /**
     * Add recipients
     * @param recipients 
     */
    public void addRecipients(List<Recipient> recipients)  {
        this.recipients.addAll(recipients);
    }
    
    /**
     * Remove the recipients
     * @param recipients 
     */
    public void removeRecipients(List<Recipient> recipients)  {
        this.recipients.removeAll(recipients);
    }
    
    /**
     * Remove all the recipients.
     */
    public void clearRecipients()  {
        this.recipients.clear();
    }
    
    /**
     * Get ID
     * @return id
     */
    public int getId()  {
        return this.id.get();
    }
    
    /**
     * Get Customer Name
     * @return name
     */
    public String getName()  {
        return this.name.get();
    }
    
    /**
     * Get Phone No
     * @return phone
     */
    public String getPhone()  {
        return this.phone.get();
    }
    
    /**
     * Get Date
     * @return date
     */
    public Date getDate()  {
        return java.sql.Date.valueOf(this.date.get());
    }
    
    /**
     * Get Recipients
     * @return recipients
     */
    public ObservableList<Recipient> getRecipients()  {
        return this.recipients.get();
    }
    
    /**
     * Get id Property of this bean
     * @return id
     */
    public ReadOnlyIntegerProperty idProperty()  {
        return this.id;
    }
    
    /**
     * Get name Property of this bean
     * @return name
     */
    public ReadOnlyStringProperty nameProperty()  {
        return this.name;
    }
    
    /**
     * Get phone Property of this bean
     * @return phone
     */
    public ReadOnlyStringProperty phoneProperty()  {
        return this.phone;
    }
    
    /**
     * Get date Property of the bean
     * @return date
     */
    public ReadOnlyObjectProperty<LocalDate> dateProperty()  {
        return this.date;
    }
    
    /**
     * Get recipients Property of the bean
     * @return recipients
     */
    public ReadOnlyListProperty<Recipient> recipientsProperty()  {
        return this.recipients;
    }
    
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty phone;
    private ObjectProperty<LocalDate> date;
    private ListProperty<Recipient> recipients;
}
