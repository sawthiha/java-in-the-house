/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob.format;

import datamodel.Voucher;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Summary of the recipient list
 * @author sawthiha
 */
public class SummaryNode extends GridPane  {
    /**
     * Constructor
     */
    public SummaryNode()  {
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        lblMinus = new Label("Total -Deli");
        lblTotal = new Label("Total Amount");
        lblToCash = new Label("To Cash");
        txtTotal = new Text();
        txtToCash = new Text();
        txtMinus = new Text();
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setAlignment(Pos.CENTER_RIGHT);
        setVgap(5);
        setHgap(10);
        txtTotal.setText(String.valueOf(0));
        txtToCash.setText(String.valueOf(0));
        txtMinus.setText(String.valueOf(0));
        txtToCash.setWrappingWidth(50);
        txtMinus.setWrappingWidth(50);
        txtTotal.setWrappingWidth(50);
        GridPane.setHalignment(lblTotal, HPos.RIGHT);
        GridPane.setHalignment(lblMinus, HPos.RIGHT);
        GridPane.setHalignment(lblToCash, HPos.RIGHT);
        GridPane.setHalignment(txtTotal, HPos.RIGHT);
        GridPane.setHalignment(txtMinus, HPos.RIGHT);
        GridPane.setHalignment(txtToCash, HPos.RIGHT);
        add(lblTotal, 1, 1);
        add(txtTotal, 2, 1);
        add(lblMinus, 1, 2);
        add(txtMinus, 2, 2);
        add(lblToCash, 1, 3);
        add(txtToCash, 2, 3);
    }
    
    /**
     * Set Voucher
     * @param voucher 
     */
    public void setVoucher(Voucher voucher)  {
        this.voucher = voucher;
        loadData();
    }
    
    /**
     * Load Data
     */
    private void loadData()  {
        txtTotal.setText(String.valueOf(voucher.getTotal()));
        txtToCash.setText(String.valueOf(voucher.getToCash()));
        txtMinus.setText(String.valueOf(voucher.getMinusDelivery()));
    }
    
    /**
     * Toggle the presence of Total Amount in the print format
     * @param value true if to be included, false otherwise
     */
    public void setTotalVisible(boolean value)  {
        lblTotal.setVisible(value);
        lblToCash.setVisible(value);
        lblMinus.setVisible(value);
        txtTotal.setVisible(value);
        txtToCash.setVisible(value);
        txtMinus.setVisible(value);
    }

    /**
     * Voucher
     */
    private Voucher voucher;
    
    /**
     * Label Total
     */
    private Label lblTotal;
    /**
     * Total Amount
     */
    private Text txtTotal;
    /**
     * Label To Cash Amount
     */
    private Label lblToCash;
    /**
     * To Cash Amount
     */
    private Text txtToCash;
    private Label lblMinus;
    private Text txtMinus;
}
