/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXTable;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 *
 * @author sawthiha
 */
public class TableFactory {
    /**
     * Get String Editable Cell
     * @param converter
     * @param txtInput
     * @return EditableCell Object
     */
    public static TableCell getStringEditableCell(StringConverter converter, TextField txtInput)  {
        if(converter == null)  {
            converter = STR_STR_CONV;
        }
        
        return new EditableCell<>(converter, txtInput);
    }
    
    /**
     * Get Long Editable Cell
     * @param converter
     * @param txtInput 
     * @return EditableCell Object
     */
    public static TableCell getLongEditableCell(StringConverter converter, TextField txtInput)  {
        if(converter == null)  {
            converter = LNG_STR_CONV;
        }
        
        return new EditableCell<>(converter, txtInput);
    }
    
    /**
     * Get Integer Editable Cell
     * @param converter
     * @param txtInput 
     * @return EditableCell Object
     */
    public static TableCell getIntEditableCell(StringConverter converter, TextField txtInput)  {
        if(converter == null)  {
            converter = INT_STR_CONV;
        }
        
        return new EditableCell<>(converter, txtInput);
    }
    
    /**
     * Generic String-to-String converter
     */
    private static final StringConverter STR_STR_CONV = new StringConverter<String>()  {

        @Override
        public String toString(String object) {
            return (object == null)? "": object;
        }

        @Override
        public String fromString(String string) {
            return (string == null)? "": string;
        }
        
    };
    
    /**
     * Generic Long-to-String converter
     */
    private static final StringConverter LNG_STR_CONV = new StringConverter<Long>() {

        @Override
        public String toString(Long object) {
            return object.toString();
        }

        @Override
        public Long fromString(String string) {
            try  {
                return Long.decode(string);
            }catch(Exception ex)  {
                return 0l;
            }
        }
        
    };
    
    private static final StringConverter INT_STR_CONV = new StringConverter<Integer>()  {

        @Override
        public String toString(Integer object) {
            return object.toString();
        }

        @Override
        public Integer fromString(String string) {
            try  {
                return Integer.decode(string);
            }catch(Exception ex)  {
                return 0;
            }
        }
        
    };
}
