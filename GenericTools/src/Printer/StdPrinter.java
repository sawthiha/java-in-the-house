/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Printer;

import javafx.print.*;
import javafx.scene.Node;

/**
 * Printer Handler
 * @author sawthiha
 */
public class StdPrinter {
    /**
     * Get Printable Width
     * @return getPrintableWidth()
     */
    protected double getPrintableX()  {
        return page.getPrintableWidth();
    }
    
    /**
     * Get Printable Height
     * @return getPrintableHeight()
     */
    protected double getPrintableY()  {
        return page.getPrintableHeight();
    }
    
    /**
     * Print the node
     * @param node print node
     * @return true if printed, false otherwise
     */
    protected boolean print(Node node)  {
        if(job.printPage(page, node))
            return job.endJob();
        return false;
    }
    
    /**
     * Check if the printer is busy
     * @return True if Busy, false otherwise
     */
    public boolean isBusy()  {
        return (job.getJobStatus() == PrinterJob.JobStatus.PRINTING);
    }
    
    /**
     * If printer is connected
     * @return true if connected, false otherwise
     */
    public boolean isConnected()  {
        return (job != null);
    }
    
    public boolean isError()  {
        return (job.getJobStatus() == PrinterJob.JobStatus.ERROR);
    }
    
    /**
     * Connect to the Printer
     * @return true if connected, false otherwise
     */
    protected final boolean connect()  {
        try  {
            printer = Printer.getDefaultPrinter();
            att = printer.getPrinterAttributes();
            job = PrinterJob.createPrinterJob(printer);
            pageSetup(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
            return true;
        }catch(NullPointerException ex)  {
            nullifyComponents();
            return false;
        }
    }
    
    /**
     * PageLayout Setup
     * @param paper Paper Type
     * @param orient Orientation
     * @param margin Margin Type
     */
    protected final void pageSetup(Paper paper, PageOrientation orient, Printer.MarginType margin)  {
        page = printer.createPageLayout(paper, orient, margin);
    }
    
    /**
     * Printer Setup
     * @deprecated Not usable currently
     */
    protected final void printerSetup()  {
        
    }
    
    /**
     * Null Components
     */
    private void nullifyComponents()  {
        printer = null;
        att = null;
        job = null;
        page = null;
    }
    
    /**
     * Refresh Job
     * @return true if refreshed, or false otherwise
     */
    public boolean refreshJob()  {
        job = PrinterJob.createPrinterJob(printer);
        return (job != null);
    }
    
    /**
     * Printer
     */
    private Printer printer;
    /**
     * PrinterJob
     */
    private PrinterJob job;
    /**
     * Page Layout
     */
    private PageLayout page;
    /**
     * Page Attributes
     */
    private PrinterAttributes att;
}
