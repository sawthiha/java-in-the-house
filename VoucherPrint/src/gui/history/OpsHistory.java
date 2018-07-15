/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.history;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;

/**
 * History Operations
 * @author sawthiha
 */
public class OpsHistory extends HBox {
    /**
     * Constructor
     */
    private OpsHistory()  {
        super();
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        btnClear = new Button("Clear");
        btnDelete = new Button("Delete");
        btnPrint = new Button("Print");
        chkToday = new CheckBox("Recent Records");
        chkTotal = new CheckBox("Total");
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        chkToday.setSelected(false);
        chkTotal.setSelected(false);
        setSpacing(5);
        HBox.setMargin(btnClear, new Insets(0, 0, 10, 10));
        HBox.setMargin(btnDelete, new Insets(0, 0, 10, 10));
        HBox.setMargin(btnPrint, new Insets(0, 0, 10, 10));
        HBox.setMargin(chkToday, new Insets(0, 0, 10, 10));
        HBox.setMargin(chkTotal, new Insets(0, 0, 10, 10));
        getChildren().addAll(btnClear, btnDelete, btnPrint, chkToday, chkTotal);
    }
    
    /**
     * Get Button Object ' Clear History '
     * @return Button Clear History
     */
    public Button getBtnClear()  {
        return btnClear;
    }
    
    /**
     * Get Button Object 'Print'
     * @return Print Button Object
     */
    public Button getBtnPrint()  {
        return btnPrint;
    }
    
    /**
     * Get btnDelete
     * @return btnDelete
     */
    public Button getBtnDelete()  {
        return btnDelete;
    }
    
    /**
     * Check whether recent records are to be printed
     * @return true if they are, false otherwise
     */
    public boolean isToday()  {
        return chkToday.isSelected();
    }
    
    /**
     * Check if total is to be included
     * @return true if so
     */
    public boolean isTotal()  {
        return chkTotal.isSelected();
    }
    
    /**
     * Get an instance
     * @return instance
     */
    public static OpsHistory getInstance()  {
        return instance;
    }
    
    /**
     * Instance
     */
    private static final OpsHistory instance = new OpsHistory();
    
    /**
     * Clear History Button
     */
    private Button btnClear;
    /**
     * Delete
     */
    private Button btnDelete;
    /**
     * Print Records
     */
    private Button btnPrint;
    /**
     * Check whether today records are to be printed
     */
    private CheckBox chkToday;
    private CheckBox chkTotal;
}
