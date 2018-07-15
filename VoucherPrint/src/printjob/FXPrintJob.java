/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob;

// PrintNode -> Print -> Status

import FXPrinterHandler.FXPrinter;
import FXPrinterHandler.Factory;
import datamodel.Voucher;
import gui.StageHandler;
import javafx.scene.Node;



/**
 *
 * @author sawthiha
 */
public class FXPrintJob implements Runnable{
    /**
     * Constructor
     * @param voucher
     * @param isSummarized Total Included or not
     */
    public FXPrintJob(Voucher voucher, boolean isSummarized)  {
        this.voucher = voucher;
        this.isSummarized = isSummarized;
    }
    
    @Override
    public void run() {
        Node node;
        FXPrinter printer = Factory.getFXPrinter();
        StageHandler stg = gui.GUIFactory.getStageHandler();
        int pages = voucher.getPageCount();
        while(pages > 0)  {
            this.status = true;
            node = gui.GUIFactory.getPrintNode(this.voucher, pages, this.isSummarized);
            stg.show(node);
            printer.setScale(node);
            this.status = this.status && printer.print(node);
            stg.hide();
            pages--;
        }
        this.voucher.setIsPrinted(this.status);
    }
    
    /**
     * Get Status
     * @return true if run successfully
     */
    public boolean getStatus()  {
        return this.status;
    }
    
    private Voucher voucher;
    private boolean status;
    private boolean isSummarized;
}
