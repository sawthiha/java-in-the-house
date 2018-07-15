/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.event;

import FXEvent.GenericEvent;
import datamodel.Recipient;
import java.util.Collection;
import javafx.event.EventHandler;

/**
 *
 * @author sawthiha
 */
public abstract class ChangeEventHandler implements EventHandler<ChangeEvent> {
    /**
     * On Amount Change
     * @param oldValue
     * @param newValue
     */
    public abstract void onAmount(long oldValue, long newValue);
    
    /**
     * On Plus Delivery Change
     * @param oldValue
     * @param newValue
     */
    public abstract void onPlus(int oldValue, int newValue);
    
    /**
     * On Minus Delivery Change
     * @param oldValue
     * @param newValue
     */
    public abstract void onMinus(int oldValue, int newValue);
    
    /**
     * Add Event with single Recipient
     * @param r Recipient
     * @deprecated Has a better alternative
     */
    public abstract void onAdd(Recipient r);
    
    /**
     * Add Event with a list of Recipients
     * @param list
     */
    public abstract void onAdd(Collection<Recipient> list);
    
    /**
     * Generic Add Event Handling
     * @param amount Amount
     * @param plus Plus Delivery
     * @param minus Minus Delivery
     */
    public abstract void onAdd(long amount, int plus, int minus);
    
    /**
     * Delete Event with single Recipient
     * @param r Recipient
     * @deprecated Has a better alternative
     */
    public abstract void onDelete(Recipient r);
    
    /**
     * Delete Event with a list of Recipients
     * @param list
     */
    public abstract void onDelete(Collection<Recipient> list);
    
    /**
     * Generic Delete Event Handling
     * @param amount Amount
     * @param plus Plus Delivery
     * @param minus Minus Delivery
     */
    public abstract void onDelete(long amount, int plus, int minus);
    
    /**
     * Clear Event
     */
    public abstract void onClear();
    
    @Override
    public void handle(ChangeEvent event) {
        event.invokeHandler(this);
        event.consume();
    }
    
}
