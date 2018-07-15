/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.input;

import datamodel.Recipient;
import datamodel.Voucher;
import gui.GUIFactory;
import java.util.Collection;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author sawthiha
 */
public class RecipientsNode extends  VBox {
    /**
     * Private Constructor
     * @param voucher Voucher
     */
    public RecipientsNode(Voucher voucher)  {
        super();
        this.voucher = voucher;
        initComponents();
        configComponents();
        configBehaviours();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        tblRecipients = GUIFactory.getTableInput(this.voucher.getRecipients());
        btnAdd = new Button("+Recipient");
        lblDeli = new Label("Delivery");
        txtDeli = new Text();
        lblTotal = new Label("Total");
        txtTotal = new Text();
        layout = new GridPane();
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        tblRecipients.setItems(voucher.getRecipients());
        GridPane.setHalignment(lblDeli, HPos.RIGHT);
        GridPane.setHalignment(txtDeli, HPos.RIGHT);
        GridPane.setHalignment(lblTotal, HPos.RIGHT);
        GridPane.setHalignment(txtTotal, HPos.RIGHT);
        GridPane.setMargin(lblDeli, new Insets(0, 10, 0, 30));
        GridPane.setMargin(lblTotal, new Insets(0, 10, 0, 30));
        txtDeli.textProperty().bind(voucher.deliveryProperty().asString());
        txtTotal.textProperty().bind(voucher.totalProperty().asString());
        txtTotal.setWrappingWidth(50);
        txtTotal.setTextAlignment(TextAlignment.CENTER);
        txtDeli.setWrappingWidth(50);
        txtDeli.setTextAlignment(TextAlignment.CENTER);
        layout.setPadding(new Insets(0, 10, 0, 0));
        layout.setAlignment(Pos.CENTER_LEFT);
        layout.setPadding(new Insets(10, 0, 10, 10));
        layout.add(btnAdd, 1, 1);
        layout.add(lblDeli, 2, 1);
        layout.add(txtDeli, 3, 1);
        layout.add(lblTotal, 4, 1);
        layout.add(txtTotal, 5, 1);
        VBox.setMargin(btnAdd, new Insets(0, 10, 0, 10));
        VBox.setMargin(tblRecipients, new Insets(0, 10, 0, 10));
        getChildren().addAll(layout, tblRecipients);
    }
    
    
    /**
     * Configure Actions
     */
    private void configBehaviours()  {
        
        btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            tblRecipients.newRow();
        });
        
        this.tblRecipients.getAddMenuItm().setOnAction(event -> {
            this.tblRecipients.addNewRow();
        });
        
        this.tblRecipients.getDeleteMenuItm().setOnAction(event -> {
            Collection<Recipient> selected = this.tblRecipients.getSelectionModel().getSelectedItems();
            this.tblRecipients.deleteRows(selected);
        });
    }
    
    /**
     * Clear the table
     */
    public void clear()  {
        tblRecipients.clear();
    }
    
    /**
     * Return Recipients list
     * @return LinkedList of Recipient Objects
     */
    public Object getRecipients()  {
        return tblRecipients.getRecipients();
    }
    
    public ObservableList<Recipient> getObservablRecipientList()  {
        return tblRecipients.getItems();
    }
    
    /**
     * Set Recipients.
     * @param list 
     */
    public void setRecipients(Object list)  {
        tblRecipients.setRecipients(list);
    }
    
    private Voucher voucher;
    private TableInput tblRecipients;
    private Button btnAdd;
    private GridPane layout;
    private Label lblDeli;
    private Text txtDeli;
    private Label lblTotal;
    private Text txtTotal;
}
