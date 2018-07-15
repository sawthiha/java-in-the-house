/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXPrinterHandler;

/**
 *
 * @author sawthiha
 */
public class Factory {
    /**
     * Get FXPrinter
     * @return FXPrinter instance
     */
    public static FXPrinter getFXPrinter()  {
        return printer;
    }
    
    private static final FXPrinter printer = new FXPrinter();
}
