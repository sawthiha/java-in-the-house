/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.settings;

import datamodel.json.JsonHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 *
 * @author sawthiha
 */
public class FormNode extends GridPane  {
    /**
     * Private Constructor
     */
    private FormNode()  {
        super();
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        json = JsonHandler.getInstance();
        lblPhone = new Label("Phone No");
        txtPhone = new TextField();
        lblViber = new Label("Viber");
        txtViber = new TextField();
        lblCashier = new Label("Cashier(Viber)");
        txtCashier = new TextField();
        lblFacebook = new Label("Facebook");
        txtFacebook = new TextField();
        btnSave = new Button("Save");
        btnReset = new Button("Reset");
    }   
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setAlignment(Pos.CENTER);
        setVgap(20);
        setHgap(10);
        txtPhone.setPromptText("Phone No");
        txtViber.setPromptText("Viber");
        txtCashier.setPromptText("Cashier");
        txtFacebook.setPromptText("Face");
        addComponents();
        configBehaviours();
        loadData();
    }
    
    /**
     * Add Components to the layout
     */
    private void addComponents()  {
        add(lblPhone, 1, 1);
        add(lblViber, 1, 2);
        add(lblCashier, 1, 3);
        add(lblFacebook, 1, 4);
        add(txtPhone, 2, 1);
        add(txtViber, 2, 2);
        add(txtCashier, 2, 3);
        add(txtFacebook, 2, 4);
        add(btnSave, 1, 5);
        add(btnReset, 2, 5);
    }
    
    /**
     * Load Data
     */
    private void loadData()  {
        txtPhone.setText(json.getPhone());
        txtViber.setText(json.getViber());
        txtCashier.setText(json.getCashier());
        txtFacebook.setText(json.getFacebook());
    }
    
    /**
     * Configure Behaviours
     */
    private void configBehaviours()  {
        btnSave.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            save();
        });
        btnReset.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            reset();
        });
    }
    
    /**
     * Save New Contents
     */
    private void save()  {
        json.set(txtPhone.getText(), txtViber.getText(), txtCashier.getText(), txtFacebook.getText());
    }
    
    /**
     * Reset Fields to last saved values
     */
    private void reset()  {
        loadData();
    }
    
    /**
     * Get an instance
     * @return instance
     */
    public static FormNode getInstance()  {
        return instance;
    }
    
    /**
     * Instance
     */
    private static final FormNode instance = new FormNode();
    /**
     * JSON Handler
     */
    private JsonHandler json;
    /**
     * Label Phone
     */
    private Label lblPhone;
    /**
     * TextField Phone
     */
    private TextField txtPhone;
    /**
     * Label Viber
     */
    private Label lblViber;
    /**
     * TextField Viber
     */
    private TextField txtViber;
    /**
     * Label Cashier
     */
    private Label lblCashier;
    /**
     * TextField Cashier
     */
    private TextField txtCashier;
    /**
     * Label Facebook
     */
    private Label lblFacebook;
    /**
     * TextField Facebook
     */
    private TextField txtFacebook;
    /**
     * Button Save
     */
    private Button btnSave;
    /**
     * Button Reset
     */
    private Button btnReset;
}
