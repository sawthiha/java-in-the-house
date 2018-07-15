/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXEvent;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.event.EventHandler;

/**
 *
 * @author sawthiha
 * @param <Handler>
 */
public abstract class GenericEvent extends Event {
    /**
     * Generic Constructor
     * @param eventType 
     */
    public GenericEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
    
    public static EventType<GenericEvent> GENERIC_EVENT = new EventType(ANY);
}
