/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import datamodel.*;
import gui.input.*;
import gui.record.TabRecord;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import printjob.format.PrintNode;

/**
 *
 * @author sawthiha
 */
public class GUIFactory {
    public static TabPane getTabPane()  {
        return new TabPane()  {
            /**
             * Add a Record.
             * @param record 
             */
            public void addRecord(TabRecord record)  {
                this.activeRecords.add(record);
                this.getTabs().add(record);
            }
            
            /**
             * Add Records.
             * @param list 
             */
            public void addRecords(Collection<TabRecord> list)  {
                activeRecords.addAll(list);
                this.getTabs().addAll(list);
            }
            
            /**
             * Remove a record.
             * @param record 
             */
            public void removeRecord(TabRecord record)  {
                activeRecords.remove(record);
                this.getTabs().remove(record);
            }
            
            public void removeRecords(Collection<TabRecord> list)  {
                activeRecords.removeAll(list);
                this.getTabs().removeAll(list);
            }
            
            private LinkedList<TabRecord> activeRecords = new LinkedList<TabRecord>();
        };
    }
    
    /**
     * Get PrintNode instance
     * @param voucher
     * @param page page no
     * @param isSummarized Include total amount or not
     * @return PrintNode Object
     */
    public static Node getPrintNode(Voucher voucher, int page, boolean isSummarized)  {
        int pages = voucher.getPageCount();
        return new PrintNode(voucher, page, (page == pages) && isSummarized);
    }
    
    /**
     * Recipient Input Table
     * @param recipients
     * @return TableInput
     */
    public static TableInput getTableInput(List<Recipient> recipients)  {
        return new TableInput(recipients);
    }
    
    /**
     * Customer Information Input Form
     * i.e. Name, Date of Transaction, Phone No
     * @return FormInput
     */
    public static FormInput getFormInput(Voucher voucher)  {
        return new FormInput(voucher);
    }
    
    /**
     * Operations of Input Tab 
     * @return OpsInput
     */
    public static OpsInput getOpsInput()  {
        return new OpsInput();
    }
    
    /**
     * Recipient Input Table altogether with its own operations
     * @param voucher
     * @return RecipientsNode
     */
    public static RecipientsNode getRecipientsNode(Voucher voucher)  {
        return new RecipientsNode(voucher);
    }
    
    /**
     * Get an Input Tab
     * @return TabInput
     */
    public static TabInput getInputTab()  {
        return new TabInput();
    }
    
    /**
     * Get a Record Tab
     * @param voucher
     * @return 
     */
    public static TabRecord getRecordTab(Voucher voucher)  {
        TabRecord tab = new TabRecord(voucher);
        voucher.addObserver(tab);
        return tab;
    }
    
    /**
     * Get StageHandler instance
     * @return StageHandler
     */
    public static StageHandler getStageHandler()  {
        if(stgHandle != null)  {
            return stgHandle;
        }
        
        Scene scene = new Scene(new Region());
        Stage stage = new Stage();
        stage.setScene(scene);
        if(Font.font("Zawgyi-One", 13.0) == null)  {
            Font.loadFont(Home.class.getResourceAsStream("font/Zawgyi-One.ttf"), 13.0);
        }
        scene.getStylesheets().add(Home.class.getResource("Home.css").toExternalForm());
        stage.setTitle("Printing..");
        stgHandle = new StageHandler(stage, scene);
        return stgHandle;
    }
    
    private static StageHandler stgHandle;
}
