/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.event;

import javafx.event.EventType;

/**
 *
 * @author sawthiha
 */
public class PlusChangeEvent extends ChangeEvent {
    /**
     * Constructor
     */
    public PlusChangeEvent(int oldValue, int newValue)  {
        super(EVENT_PLUS);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    @Override
    public void invokeHandler(ChangeEventHandler handler) {
        handler.onPlus(this.oldValue, this.newValue);
    }
    
    /**
     * Old Value
     */
    private int oldValue;
    /**
     * New Value
     */
    private int newValue;
    /**
     * Specific Event Type
     */
    public static final EventType<ChangeEvent> EVENT_PLUS = ChangeEvent.EVENT_CHANGE; 
}
