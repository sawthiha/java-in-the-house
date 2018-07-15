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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * Header Node
 * @author sawthiha
 */
public class HeaderNode extends GridPane {
    /**
     * Constructor
     */
    public HeaderNode()  {
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
            FileInputStream file = new FileInputStream("data/img/Title.jpg");
            img = new Image(file);
            title = new ImageView(img);
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /*
    [0,0] [1,0] [2,0]
    [0,1] [1,1] [2,1]
    [0,2] [1,2] [2,2]
    */
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        title.setPreserveRatio(true);
        this.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHgrow(title, Priority.ALWAYS);
        //GridPane.setVgrow(title, Priority.ALWAYS);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(title, VPos.CENTER);
        GridPane.setRowSpan(this.title, 3);
        GridPane.setMargin(this.title, new Insets(10, 10, 0, 10));
        GridPane.setMargin(this.lblCustomer, new Insets(10, 10, 0, 10));
        GridPane.setMargin(this.lblDate, new Insets(10, 10, 0, 10));
        GridPane.setMargin(this.lblPhone, new Insets(10, 10, 10, 10));
        GridPane.setMargin(this.txtCustomer, new Insets(10, 10, 0, 10));
        GridPane.setMargin(this.txtDate, new Insets(10, 10, 0, 10));
        GridPane.setMargin(this.txtPhone, new Insets(10, 10, 10, 10));
        
        
        this.add(title, 0, 0);
        this.add(this.lblCustomer, 1, 0);
        this.add(this.txtCustomer, 2, 0);
        this.add(this.lblDate, 1, 1);
        this.add(this.txtDate, 2, 1);
        this.add(this.lblPhone, 1, 2);
        this.add(this.txtPhone, 2, 2);
        //this.add(form, 1, 0);
    }
    
    /**
     * Set Voucher
     * @param voucher voucher model
     */
    public void setVoucher(Voucher voucher)  {
        this.txtCustomer.setText(voucher.getCustomer());
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mma");
        this.txtDate.setText(format.format(java.sql.Date.valueOf(voucher.getDate())));
        this.txtPhone.setText(voucher.getPhone());
    }

    /**
     * Label for Customer Name
     */
    private Label lblCustomer;
    /**
     * Text for Customer Name
     */
    private Text txtCustomer;
    /**
     * Label for Date
     */
    private Label lblDate;
    /**
     * Text for Date
     */
    private Text txtDate;
    /**
     * Label for Phone
     */
    private Label lblPhone;
    /**
     * Text for Phone
     */
    private Text txtPhone;
    
    /**
     * Image View
     */
    private ImageView title;
    /**
     * Image Object
     */
    private Image img;
}
