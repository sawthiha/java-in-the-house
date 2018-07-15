/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextField;

import javafx.scene.control.TextField;

/**
 *
 * @author sawthiha
 */
public class TxtDecimal extends TextField  {
    public TxtDecimal()  {
        super();
    }
    
    public TxtDecimal(String prompt)  {
        this();
        setPromptText(prompt);
    }
    
    @Override
    public void replaceText(int start, int end, String text)  {
        if(isDecimal(text))  {
            super.replaceText(start, end, text);
        }
    }
    
    @Override
    public void replaceSelection(String text)  {
        if(isDecimal(text))  {
            super.replaceSelection(text);
        }
    }
    
    private boolean isDecimal(String txt)  {
        return txt.matches("[0-9]*");
    }
    
}
