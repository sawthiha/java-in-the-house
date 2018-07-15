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
public class ClearEvent extends ChangeEvent   {
    public ClearEvent()  {
        super(EVENT_CLEAR);
    }
    
    public static EventType<ChangeEvent> EVENT_CLEAR = ChangeEvent.EVENT_CHANGE;

    @Override
    public void invokeHandler(ChangeEventHandler handler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
