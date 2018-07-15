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
public class TextFieldFactory {
    /**
     * Get Name Text Field
     * @param prompt
     * @return TxtName Object
     */
    public static TextField getTxtName(String prompt)  {
        TxtName txtName = new TxtName();
        setPromptText(txtName, prompt);
        return txtName;
    }
    
    /**
     * Get Phone Text Field
     * @param prompt
     * @return TxtPhone Object
     */
    public static TextField getTxtPhone(String prompt)  {
        TxtPhone txtPhone = new TxtPhone();
        setPromptText(txtPhone, prompt);
        return txtPhone;
    }
    
    /**
     * Get Generic FX text field
     * @param prompt
     * @return Text Field
     */
    public static TextField getGenericTxt(String prompt)  {
        TextField txt = new TextField();
        setPromptText(txt, prompt);
        return txt;
    }
    
    /**
     * Get Generic FX text field
     * @param prompt
     * @return Text Field
     */
    public static TextField getDecimalTxt(String prompt)  {
        TxtDecimal txt = new TxtDecimal();
        setPromptText(txt, prompt);
        return txt;
    }
    
    /**
     * Set Prompt Text
     * @param txt Text Field to be set
     * @param prompt 
     */
    private static void setPromptText(TextField txt, String prompt)  {
        if(prompt != null)  {
            txt.setPromptText(prompt);
        }
    }
}
