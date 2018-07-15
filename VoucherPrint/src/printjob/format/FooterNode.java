/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob.format;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Footer of the format
 * @author sawthiha
 */
public class FooterNode extends VBox {
    /**
     * Constructor
     */
    public FooterNode()  {
        super();
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        txtCashier = new Text();
        txtPhone = new Text();
        txtViber = new Text();
        txtFacebook = new Text();
        font = Font.font("Times New Roman", FontWeight.SEMI_BOLD, 12);
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setSpacing(2.5);
        setAlignment(Pos.TOP_LEFT);
        txtCashier.setFont(font);
        txtPhone.setFont(font);
        txtViber.setFont(font);
        txtFacebook.setFont(font);
        getChildren().addAll(txtPhone, txtViber, txtCashier, txtFacebook);
    }
    
    /**
     * Set Cashier
     * @param phone
     */
    public void setTxtCashier(String phone)  {
        txtCashier.setText("Cashier(viber): " + phone);
    }
    
    /**
     * Set Facebook Contact
     * @param facebook page name
     */
    public void setTxtFacebook(String facebook)  {
        txtFacebook.setText("Search us on facebook: " + facebook);
    }
    
    /**
     * Set Phone
     * @param phone 
     */
    public void setTxtPhone(String phone)  {
        txtPhone.setText("Phone: " + phone);
    }
    
    /**
     * Set Viber
     * @param viber 
     */
    public void setTxtViber(String viber)  {
        txtViber.setText("Viber: " + viber);
    }

    /**
     * Cashier
     */
    private Text txtCashier;
    /**
     * Phone
     */
    private Text txtPhone;
    /**
     * Viber
     */
    private Text txtViber;
    /**
     * Facebook
     */
    private Text txtFacebook;
    /**
     * Font
     */
    private Font font;
}
