/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.event;

import javafx.event.EventHandler;
import javafx.event.EventType;

/**
 *
 * @author sawthiha
 */
public class AmtChangeEvent extends ChangeEvent  {
    
    public AmtChangeEvent(long oldValue, long newValue)  {
        super(EVENT_AMOUNT);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    
    @Override
    public void invokeHandler(ChangeEventHandler handler) {
        handler.onAmount(this.oldValue, this.newValue);
    }
    
    /**
     * Old Value
     */
    private long oldValue;
    /**
     * New Value
     */
    private long newValue;
    
    /**
     * Specific Event Type
     */
    public static final EventType<ChangeEvent> EVENT_AMOUNT = ChangeEvent.EVENT_CHANGE;
}
