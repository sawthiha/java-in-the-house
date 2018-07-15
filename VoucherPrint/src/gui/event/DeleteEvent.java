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
public class DeleteEvent extends ChangeEvent {
    /**
     * A single Recipient Delete Event
     * @param r Recipient
     */
    public DeleteEvent(Recipient r)  {
        super(EVENT_DELETE);
        this.r = r;
    }
    
    /**
     * A list of Recipients Delete Event
     * @param list 
     */
    public DeleteEvent(Collection<Recipient> list)  {
        super(EVENT_DELETE);
        this.list = list;
    }
    
    @Override
    public void invokeHandler(ChangeEventHandler handler) {
        if(this.r == null)  {
            handler.onDelete(this.list);
        } else  {
            handler.onDelete(this.r.getAmount(), this.r.getPlus(), this.r.getMinus());
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
    public static final EventType<ChangeEvent> EVENT_DELETE = ChangeEvent.EVENT_CHANGE;
}
