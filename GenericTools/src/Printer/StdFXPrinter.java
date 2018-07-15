/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printer;

import javafx.geometry.Bounds;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.transform.Scale;

/**
 *
 * @author sawthiha
 */
public class StdFXPrinter {
    /**
     * Private Constructor
     */
    private StdFXPrinter()  {
        try  {
            this.printer = Printer.getDefaultPrinter();
            this.page = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
            this.attrs = printer.getPrinterAttributes();
            this.printableX = page.getPrintableWidth();
            this.printableY = page.getPrintableHeight();
        }catch(NullPointerException ex)  {
            nullify();
        }
    }
    
    /**
     * Scale the node and print it.
     * @param node to be printed
     * @return true if added to the printer queue, false otherwise
     */
    public boolean print(Node node)  {
        PrinterJob job;
        
        if(isConnected() && setPrintableScale(node)){
            job = PrinterJob.createPrinterJob(printer);
            if(job.printPage(page, node))  {
                return job.endJob();
            }
        }
        
        return false;
    }
    
    /**
     * 
     * @param node element to be scaled
     * @return true if set, false otherwise
     */
    public boolean setPrintableScale(Node node)  {
        double x, y;
        Bounds bounds;
        
        try  {
            if(isConnected()){
                bounds = node.getBoundsInParent();
                x = printableX / bounds.getWidth();
                y = printableY / bounds.getHeight();
                node.getTransforms().add(new Scale(x, y));
                return true;
            }
        }catch(Exception ex)  {
            
        }
        
        return false;
    }
    
    /**
     * Check if printer is connected
     * @return true if connected, false otherwise
     */
    public boolean isConnected()  {
        return (printer != null);
    }
    
    /**
     * Get an instance of StdPrinter
     * @return StdPrinter object
     */
    public static StdFXPrinter getInstance()  {
        return INSTANCE;
    }
    
    /**
     * Set null to members
     */
    private void nullify()  {
        this.printer = null;
        this.page = null;
        this.attrs = null;
        this.printableX = 0.0d;
        this.printableY = 0.0d;
    }
    
    /**
     * Instance
     */
    private static final StdFXPrinter INSTANCE = new StdFXPrinter();
    /**
     * Printer Object - Standard Printer
     */
    private Printer printer;
    /**
     * Page Layout of the printer
     */
    private PageLayout page;
    /**
     * Printable X axis
     */
    private double printableX;
    /**
     * Printable Y axis
     */
    private double printableY;
    /**
     * Printer Attributes
     */
    private PrinterAttributes attrs;
}
