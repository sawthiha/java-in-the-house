/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.input;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Voucher Input Operations
 * @author sawthiha
 */
public class OpsInput extends HBox  {
    /**
     * Private Constructor
     */
    public OpsInput()  {
        super();
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        btnSave = new Button("Save");
        btnPrint = new Button("Print");
        btnClear = new Button("Clear");
        cbTotal = new CheckBox("Total");
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        HBox.setMargin(btnSave, new Insets(10, 10, 10, 10));
        HBox.setMargin(btnPrint, new Insets(10, 10, 10, 10));
        HBox.setMargin(btnClear, new Insets(10, 10, 10, 10));
        HBox.setMargin(cbTotal, new Insets(10, 10, 10, 10));
        cbTotal.setSelected(true);
        getChildren().addAll(btnClear, btnSave, btnPrint, cbTotal);
    }
    
    /**
     * Get Total switch
     * @return Check Box 'Total'
     */
    public CheckBox getCbTotal()  {
        return cbTotal;
    }
    
    /**
     * Get Button instance of btnSave
     * @return btnSave
     */
    public Button getBtnSave()  {
        return btnSave;
    }
    
    /**
     * Get Button instance of btnPrint
     * @return btnPrint
     */
    public Button getBtnPrint()  {
        return btnPrint;
    }
    
    /**
     * Get Button instance of btnClear
     * @return btnClear
     */
    public Button getBtnClear()  {
        return btnClear;
    }
    
    /**
     * Button Save
     */
    private Button btnSave;
    /**
     * Button Print
     */
    private Button btnPrint;
    /**
     * Button Clear
     */
    private Button btnClear;
    /**
     * 'Total' Option
     */
    private CheckBox cbTotal;
}
