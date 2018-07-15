/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob.format;

import datamodel.Voucher;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author sawthiha
 */
public class FormNode extends GridPane  {
    /**
     * Constructor
     */
    public FormNode()  {
        super();
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        lblCustomer = new Label("Customer");
        lblDate = new Label("Date");
        lblPhone = new Label("Phone");
        txtCustomer = new Text();
        txtDate = new Text();
        txtPhone = new Text();
        try {
            FileInputStream file = new FileInputStream("./src/printjob/format/img/PMDelivery.jpg");
            img = new Image(file);
            title = new ImageView(img);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        title.setPreserveRatio(true);
        // set Alignment
        // set Horizontal and Vertical gaps between cells
        setHgap(10);
        setVgap(10);
        // set the padding
        setPadding(new Insets(10, 10, 10,10));
        //add(title, 0, 0);
        add(lblCustomer, 1, 0);
        add(lblDate, 1, 1);
        add(lblPhone, 1, 2);
        add(txtCustomer, 2, 0);
        add(txtDate, 2, 1);
        add(txtPhone, 2, 2);
    }
    
    /**
     * Load data
     */
    private void loadData()  {
        txtCustomer.setText(voucher.getCustomer());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mma");
        txtDate.setText(format.format(voucher.getDate()));
        txtPhone.setText(voucher.getPhone());
    }
    
    /**
     * Set Voucher
     * @param voucher voucher
     */
    public void setVoucher(Voucher voucher)  {
        this.voucher = voucher;
        loadData();
    }
    
    private Voucher voucher;

    private Label lblCustomer;
    private Text txtCustomer;
    private Label lblDate;
    private Text txtDate;
    private Label lblPhone;
    private Text txtPhone;
    
    private ImageView title;
    private Image img;
}
