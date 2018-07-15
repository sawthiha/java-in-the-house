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
public class TxtName extends TextField  {
    public TxtName()  {
        super();
        setPromptText("Name");
        textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String str = getText();
            if(str.length() > maxChar)  {
                str = str.substring(0, maxChar);
                setText(str);
            }
        });
    }
    
    private static final short maxChar = 70;
}
