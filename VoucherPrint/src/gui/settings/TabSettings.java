/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 *
 * @author sawthiha
 */
public class TabSettings extends Tab  {
    /**
     * Private Constructor
     */
    private TabSettings()  {
        super("Settings");
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        layout = new VBox();
        form = FormNode.getInstance();
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setClosable(false);
        layout.setAlignment(Pos.CENTER);
        VBox.setMargin(form, new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(form);
        setContent(layout);
    }
    
    /**
     * Get an instance
     * @return instance
     */
    public static TabSettings getInstance()  {
        return instance;
    }
    
    /**
     * Instance
     */
    private static final TabSettings instance = new TabSettings();
    /**
     * Layout
     */
    private VBox layout;
    /**
     * Input Form
     */
    private FormNode form;
}
