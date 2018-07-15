/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.event;

import datamodel.Recipient;
import java.util.Collection;
import javafx.event.EventType;

/**
 *
 * @author sawthiha
 */
public class AddEvent extends ChangeEvent {
    /**
     * A single recipient
     * @param r Recipient
     */
    public AddEvent(Recipient r)  {
        super(EVENT_ADD);
        this.r = r;
    }
    
    /**
     * List of recipients
     * @param list 
     */
    public AddEvent(Collection<Recipient> list)  {
        super(EVENT_ADD);
        this.list = list;
    }
    
    @Override
    public void invokeHandler(ChangeEventHandler handler) {
        if(this.r == null)  {
            handler.onAdd(this.list);
        } else  {
            handler.onAdd(this.r.getAmount(), this.r.getPlus(), this.r.getMinus());
        }
    }
    
    /**
     * Single Recipient Property
     */
    private Recipient r;
    
    /**
     * List of Recipients property
     */
    private Collection<Recipient> list;
    
    /**
     * Specific Event Type
     */
    public static final EventType<ChangeEvent> EVENT_ADD = ChangeEvent.EVENT_CHANGE;
}
