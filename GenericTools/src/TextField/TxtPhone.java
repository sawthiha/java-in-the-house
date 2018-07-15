/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextField;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author sawthiha
 */
public class TxtPhone extends TextField{
    /**
     * Constructor
     */
    public TxtPhone()  {
        super();
        setPromptText("Phone");
        textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String str = getText();
            if(str.length() > maxChar)  {
                str = str.substring(0, maxChar);
                setText(str);
            }
        });
    }
    
    @Override
    public void replaceText(int start, int end, String text)  {
        if(isPhoneNo(text))  {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String text)  {
        if(isPhoneNo(text))  {
            super.replaceSelection(text);
        }
    }
    
    /**
     * Validate whether the text is a phone no
     * @param txt Text to be validated
     * @return true if txt is a phone no, false otherwise
     */
    private boolean isPhoneNo(String txt)  {
        return txt.matches("[0-9]*");
    }
    
    private final short maxChar = 15;
}
