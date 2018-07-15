/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.input;

import TextField.TxtName;
import TextField.TxtPhone;
import datamodel.Voucher;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * Voucher Information Input
 * @author sawthiha
 * TODO : Date Customization
 */
public class FormInput extends GridPane{
    /**
     * Private Constructor
     * @param voucher Voucher
     */
    public FormInput(Voucher voucher)  {
        super();
        this.voucher = voucher;
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        lblCustomer = new Label("Customer");
        txtCustomer = new TxtName();
        lblDate = new Label("Date");
        txtDate = new DatePicker();
        lblPhone = new Label("Phone");
        txtPhone = new TxtPhone();
        cbCustom = new CheckBox("Custom Date");
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        
        // Label
        lblCustomer.setLabelFor(txtCustomer);
        lblDate.setLabelFor(txtDate);
        lblPhone.setLabelFor(txtPhone);
        txtDate.setDisable(true);
        cbCustom.setSelected(false);
        txtDate.setValue(LocalDate.now());
        cbCustom.setDisable(false);
        // set Alignment
        setAlignment(Pos.TOP_LEFT);
        configBehaviours();
        // set Horizontal and Vertical gaps between cells
        setHgap(10);
        setVgap(10);
        // set the padding
        setPadding(new Insets(10, 10, 10,10));
        // add them to the layout
        add(lblCustomer, 0, 0);
        add(txtCustomer, 1, 0);
        add(lblDate, 0, 1);
        add(txtDate, 1, 1);
        add(cbCustom, 2, 1);
        add(lblPhone, 0, 2);
        add(txtPhone, 1, 2);
    }
    
    /**
     * Configure Behaviours
     */
    private void configBehaviours()  {
        txtCustomer.textProperty().bindBidirectional(voucher.customerProperty());
        txtDate.valueProperty().bindBidirectional(voucher.dateProperty());
        txtPhone.textProperty().bindBidirectional(voucher.phoneProperty());
        cbCustom.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            boolean tmp = !newValue;
            txtDate.setDisable(tmp);
            if(tmp)
                txtDate.setValue(LocalDate.now());
        });
        
    }
    
    /**
     * Get Customer Name
     * @return Customer name
     */
    public String getCustomer()  {
        return txtCustomer.getText();
    }
    
    /**
     * Get Date
     * @return Date Current or Customized Date
     */
    public Date getDate()  {
        return java.sql.Date.valueOf(txtDate.getValue());
    }
    
    /**
     * Request focus on ' Customer Name ' Text Field
     */
    public void requestFocusOnCustomer()  {
        this.txtCustomer.requestFocus();
    }
    
    /**
     * Request focus on ' Phone ' Text Field
     */
    public void requestFocusOnPhone()  {
        this.txtPhone.requestFocus();
    }
    
    /**
     * Get Phone Number
     * @return phone no
     */
    public String getPhone()  {
        return txtPhone.getText();
    }
    
    /**
     * Set Customer Name
     * @param customer customer name
     */
    public void setCustomer(String customer)  {
        txtCustomer.setText(customer);
    }
    
    /**
     * Set Date
     * @param date 
     */
    public void setDate(Date date)  {
        txtDate.setValue(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
    }
    
    /**
     * Set Phone No Field
     * @param phone phone no
     */
    public void setPhone(String phone)  {
        txtPhone.setText(phone);
    }
    
    /**
     * Set Focus on Customer Field
     */
    public void setFocusOnCustomer()  {
        txtCustomer.requestFocus();
    }
    
    /**
     * Set Focus on Phone Field
     */
    public void setFocusOnPhone()  {
        txtPhone.requestFocus();
    }
    
    /**
     * Label Customer
     */
    private Label lblCustomer;
    /**
     * TextField Customer
     */
    private TxtName txtCustomer;
    /**
     * Label Date
     */
    private Label lblDate;
    /**
     * DatePicker Date
     */
    private DatePicker txtDate;
    /**
     * DateTime Format for DatePicker
     * E.g, 22-01-1996
     */
    private final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    /**
     * Label Phone
     */
    private Label lblPhone;
    /**
     * TextField Phone
     */
    private TxtPhone txtPhone;
    /**
     * CheckBox for Date Customization
     */
    private CheckBox cbCustom;
    private Voucher voucher;
}
