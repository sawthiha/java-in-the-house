/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.util.*;
import printjob.format.PrintNode;
import Interfaces.Observable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Voucher Model
 * @author sawthiha
 */
public class Voucher implements Observable{
    /**
     * Null Constructor
     */
    public Voucher()  {
        this(-1, "", new Date(), "", new ArrayList<>(), false);
    }
    
    /**
     * Clone
     * @param voucher 
     */
    public Voucher(Voucher voucher)  {
        this(voucher.getId(), voucher.getCustomer(), java.sql.Date.valueOf(voucher.getDate()), voucher.getPhone(), voucher.getRecipients(), voucher.getIsPrinted());
    }
    
    /**
     * Constructor with Parameters
     * @param id DB id
     * @param customer customer name
     * @param date created date
     * @param phone phone number
     * @param recipients Recipients
     */
    public Voucher(int id, String customer, Date date, String phone, List<Recipient> recipients, boolean isPrinted)  {
        this.id = new SimpleIntegerProperty(id);
        this.customer = new SimpleStringProperty(customer);
        this.date = new SimpleObjectProperty<>(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
        this.phone = new SimpleStringProperty(phone);
        this.recipients = FXCollections.observableList(new ArrayList());
        this.total = new SimpleLongProperty(0l);
        this.toCash = new SimpleLongProperty(0l);
        this.delivery = new SimpleIntegerProperty(0);
        this.minusDelivery = new SimpleIntegerProperty(0);
        this.isPrinted = new SimpleBooleanProperty(isPrinted);
        
        this.bindings();
        this.recipients.addAll(recipients);
    }
    
    /**
     * Configure Bindings
     */
    private void bindings()  {
        this.pageCount = new IntegerBinding() {
            
            {
                super.bind(recipients);
            }
            
            @Override
            protected int computeValue() {
                int pages = (int) Math.ceil((float) recipients.size() / PrintNode.MAX_ITEMS);
                pages = (pages == 0) ? 1: pages;
                return pages;
            }
        };
        
        ChangeListener<Number> amountListener, plusListener, minusListener;
        
        amountListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            long dif = newValue.longValue()- oldValue.longValue();
            
            this.total.set(this.total.get() + dif);
            this.toCash.set(this.toCash.get() + dif);
        };
        
        plusListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            int dif = newValue.intValue() - oldValue.intValue();
            this.delivery.set(this.delivery.get() + dif);
            this.total.set(this.total.get() + dif);
        };
        
        minusListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            int dif =  oldValue.intValue() - newValue.intValue();
            int rdif = newValue.intValue() - oldValue.intValue();
            this.delivery.set(this.delivery.get() + rdif);
            this.total.set(this.total.get() + dif);
            this.toCash.set(this.toCash.get() + dif);
            this.minusDelivery.set(this.minusDelivery.get() - dif);
        };
        
        recipients.addListener(new ListChangeListener<Recipient>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Recipient> c) {
                while(c.next())  {
                    List<? extends Recipient> sub;
                    if(c.wasAdded())  {
                        sub = c.getAddedSubList();
                        sub.forEach((Recipient r) ->  {
                            r.amountProperty().addListener(amountListener);
                            r.plusProperty().addListener(plusListener);
                            r.minusProperty().addListener(minusListener);
                        });
                        this.onAdd(sub);
                    } else if(c.wasRemoved())  {
                        sub = c.getRemoved();
                        sub.forEach((Recipient r) -> {
                            r.amountProperty().removeListener(amountListener);
                            r.plusProperty().removeListener(plusListener);
                            r.minusProperty().removeListener(minusListener);
                        });
                        this.onDelete(sub);
                    }
                }
            }
            
            /**
             * On Recipient Add Event
             * @param list list of added recipients
             */
            private void onAdd(List<? extends Recipient> list)  {
                list.forEach((Recipient r) ->  {
                    this.onAdd(r.getAmount(), r.getPlus(), r.getMinus());
                });
            }
            
            /**
             * On Recipient Add Event
             * @param amount Amount of the added
             * @param plus +Delivery Fee of the added
             * @param minus  -Delivery Fee of the added
             */
            private void onAdd(long amount, int plus, int minus)  {
                long amt, tc;
                int deli = plus + minus;
                amt = total.get() + amount + deli;
                deli += delivery.get();
                tc = toCash.get() + amount - minus;
                
                delivery.set(deli);
                total.set(amt);
                toCash.set(tc);
                minusDelivery.set(minusDelivery.get() + minus);
            }
            
            /**
             * On Recipient Delete Event
             * @param list list of deleted recipients
             */
            public void onDelete(List<? extends Recipient> list) {
                list.forEach((Recipient r) -> {
                    this.onDelete(r.getAmount(), r.getPlus(), r.getMinus());
                });
            }
            
            /**
             * On Recipient Delete Event
             * @param amount Amount of the deleted
             * @param plus +Delivery Fee of the deleted
             * @param minus  -Delivery Fee of the deleted
             */
            public void onDelete(long amount, int plus, int minus) {
                long amt, tc;
                int deli = minus + plus;
                amt = total.get() - amount + deli;
                deli = delivery.get() - deli;
                tc = toCash.get() - amount + minus;
                
                delivery.set(deli);
                total.set(amt);
                toCash.set(tc);
                minusDelivery.set(minusDelivery.get() - minus);
            }
        });
    }
    
    /**
     * Get ID
     * @return ID of the Voucher in DB
     */
    public int getId()  {
        return this.id.get();
    }
    
    /**
     * Get Customer Name
     * @return customer
     */
    public String getCustomer()  {
        return this.customer.get();
    }
    
    /**
     * Get Date
     * @return date
     */
    public LocalDate getDate() {
        return date.get();
    }
    
    /**
     * Get Phone No
     * @return phone no
     */
    public String getPhone()  {
        return phone.get();
    }
    
    /**
     * Get Page Count
     * @return no of pages
     */
    public int getPageCount()  {
        return pageCount.get();
    }
    
    /**
     * Get Recipient List
     * @return recipient list
     */
    public ObservableList<Recipient> getRecipients()  {
        return this.recipients;
    }
    
    /**
     * Get Amount Summary
     * @return Total Amount
     */
    public long getTotal()  {
        return this.total.get();
    }
    
    /**
     * Get To Cash Summary
     * @return minus delivery summary
     */
    public long getToCash()  {
        return this.toCash.get();
    }
    
    /**
     * Get Total Delivery Fee
     * @return Delivery Fee
     */
    public long getDelivery()  {
        return this.delivery.get();
    }
    
    /**
     * Get Minus Delivery Fee
     * @return -Delivery
     */
    public int getMinusDelivery()  {
        return this.minusDelivery.get();
    }
    
    /**
     * Get if printed.
     * @return true if printed
     */
    public boolean getIsPrinted()  {
        return this.isPrinted.get();
    }
    
    /**
     * Total Amount Property
     * @return Total Amount Property
     */
    public LongProperty totalProperty()  {
        return this.total;
    }
    
    /**
     * Delivery Amount Property
     * @return Delivery Amount Property
     */
    public IntegerProperty deliveryProperty()  {
        return this.delivery;
    }
    
    /**
     * Customer Property
     * @return Customer Property
     */
    public StringProperty customerProperty()  {
        return this.customer;
    }
    
    /**
     * Phone Property
     * @return Phone Property
     */
    public StringProperty phoneProperty()  {
        return this.phone;
    }
    
    /**
     * Date Property
     * @return Date Property
     */
    public ObjectProperty<LocalDate> dateProperty()  {
        return this.date;
    }
    
    /**
     * isPrinted property
     * @return boolean property
     */
    public BooleanProperty isPrintedProperty()  {
        return this.isPrinted;
    }
    
    /**
     * Get whether a voucher object is created today
     * @return true if created today, false otherwise
     */
    public boolean isCreatedToday()  {
        return DateTimeUtils.Compartor.compareDateOnly(new Date(), java.sql.Date.valueOf(date.get()));
    }
    
    /**
     * Set ID
     * @param id ID of the Voucher in DB
     */
    public void setId(int id)  {
        this.id.set(id);
        if(id == -1)  {
            this.updateAll();
        }
    }
    
    /**
     * Set Customer Name
     * @param customer customer name
     */
    public void setCustomer(String customer)  {
        this.customer.set(customer);
    }
    
    /**
     * Set Date
     * @param date created date of the voucher
     */
    public void setDate(Object date) {
        this.date.set((LocalDate) date);
    }
    
    /**
     * Set Phone no
     * @param phone phone number of the Customer
     */
    public void setPhone(String phone)  {
        this.phone.set(phone);
    }
    
    /**
     * Set if printed
     * @param isPrinted 
     */
    public void setIsPrinted(boolean isPrinted)  {
        this.isPrinted.set(isPrinted);
    }
    
    /**
     * Clear the list and reload with new Recipients.
     * @param recipients list of Recipient to be reloaded
     */
    public void reloadRecipients(List<? extends Recipient> recipients)  {
        this.recipients.clear();
        this.recipients.addAll(recipients);
    }
    
    /**
     * Add a recipient
     * @param r Recipient
     */
    public void addRecipient(Recipient r) {
        this.recipients.add(r);
    }
    
    /**
     * Set the Recipient list
     * @param recipients list of recipients
     */
    public void setRecipients(List<Recipient> recipients)  {
        this.recipients.addAll(recipients);
    }
    
    /**
     * Remove a recipients
     * @param r 
     */
    public void removeRecipient(Recipient r)  {
        recipients.remove(r);
    }
    
    private IntegerProperty id;
    private StringProperty customer;
    private ObjectProperty<LocalDate> date;
    private StringProperty phone;
    private IntegerBinding pageCount;
    private IntegerProperty minusDelivery;
    private LongProperty total;
    private LongProperty toCash;
    private IntegerProperty delivery;
    private ObservableList<Recipient> recipients;
    private BooleanProperty isPrinted;

    @Override
    public void addObserver(Interfaces.Observer o) {
        this.OBSERVERS.add(o);
    }

    @Override
    public void addObserver(Collection<Interfaces.Observer> list) {
        this.OBSERVERS.addAll(list);
    }

    @Override
    public void updateAll() {
        this.OBSERVERS.forEach((observer) -> observer.update());
        this.OBSERVERS.clear();
    }
    
    /**
     * List of Observers
     */
    private final Collection<Interfaces.Observer> OBSERVERS = new LinkedList<>();

    @Override
    public void removeObserver(Interfaces.Observer o) {
        this.OBSERVERS.remove(o);
    }

    @Override
    public void removeObserver(Collection<Interfaces.Observer> list) {
        this.OBSERVERS.removeAll(list);
    }
}
