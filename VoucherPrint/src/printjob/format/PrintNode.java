/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob.format;

import datamodel.Voucher;
import datamodel.json.JsonHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

/**
 * Printable Format
 * @author sawthiha
 */
public class PrintNode extends VBox  {
    /**
     * Constructor
     * @param voucher
     * @param page
     * @param isSummarized
     */
    public PrintNode(Voucher voucher, int page, boolean isSummarized)  {
        super();
        initComponents();
        configComponents();
        setVoucher(voucher, page);
        setTotalVisible(isSummarized);
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        json = JsonHandler.getInstance();
        header = new HeaderNode();
        tblRecipients = new TableNode();
        summary = new SummaryNode();
        footer = new FooterNode();
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setSpacing(10);
        setBackground(Background.EMPTY);
        configMargins();
        configFooter();
        getChildren().addAll(header, tblRecipients, summary, footer);
    }
    
    /**
     * Configure Margins
     */
    private void configMargins()  {
        VBox.setMargin(header, new Insets(5, 5, 5, 5));
        VBox.setMargin(tblRecipients, new Insets(5, 5, 5, 5));
        VBox.setMargin(summary, new Insets(5, 5, 5, 5));
        VBox.setMargin(footer, new Insets(5, 5, 5, 5));
    }
    
    /**
     * Configure Footer
     */
    private void configFooter()  {
        footer.setTxtCashier(json.getCashier());
        footer.setTxtFacebook(json.getFacebook());
        footer.setTxtPhone(json.getPhone());
        footer.setTxtViber(json.getViber());
    }
    
    /**
     * Load data
     */
    private void loadData(int page)  {
        header.setVoucher(voucher);
        tblRecipients.setVoucher(voucher, page);
        summary.setVoucher(voucher);
    }
    
    /**
     * Toggle the presence of Total Amount
     * @param value true or false
     */
    public void setTotalVisible(boolean value)  {
        summary.setTotalVisible(value);
    }
    
    /**
     * Set Voucher to be formatted
     * @param voucher Voucher
     * @param page page no
     */
    public void setVoucher(Voucher voucher, int page)  {
        this.voucher = voucher;
        loadData(page);
    }
    
    public static final int MAX_ITEMS = 18;
    
    /**
     * JSON Handler
     */
    private JsonHandler json;
    
    /**
     * Voucher
     */
    private Voucher voucher;
    
    /**
     * Header
     */
    private HeaderNode header; 
    /**
     * Table
     */
    private TableNode tblRecipients;
    /**
     * Summary
     */
    private SummaryNode summary;
    /**
     * Footer
     */
    private FooterNode footer;
}
