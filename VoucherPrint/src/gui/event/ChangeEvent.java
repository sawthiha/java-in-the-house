/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.event;

import FXEvent.GenericEvent;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Event of Changes
 * @author sawthiha
 */
public abstract class ChangeEvent extends GenericEvent{
    /**
     * Constructor
     * @param eventType
     */
    public ChangeEvent(EventType<? extends Event> eventType)  {
        super(eventType);
    }
    
    /**
     * Handle by Specific Event in a fashionable way.
     * @param handler 
     */
    public abstract void invokeHandler(ChangeEventHandler handler);
    
    /**
     * Event Type
     */
    public static final EventType<ChangeEvent> EVENT_CHANGE = new EventType<ChangeEvent>("Change");
}
