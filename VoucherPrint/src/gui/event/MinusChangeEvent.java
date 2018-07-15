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
public class MinusChangeEvent extends ChangeEvent  {
    public MinusChangeEvent(int oldValue, int newValue)  {
        super(EVENT_MINUS);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void invokeHandler(ChangeEventHandler handler) {
        handler.onMinus(this.oldValue, this.newValue);
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
    public static EventType<ChangeEvent> EVENT_MINUS = ChangeEvent.EVENT_CHANGE;
}
