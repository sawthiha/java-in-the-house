/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.history.TabHistory;
import gui.input.TabInput;
import datamodel.database.DBControl;
import gui.settings.TabSettings;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Home - Main Entry
 * @author sawthiha
 */
public class Home extends Application  {
    
    /**
     * Initiate Components
     */
    private void initComponents() {
        root = new VBox();
        tabs = new TabPane();
        tabList = tabs.getTabs();
        tabInput = GUIFactory.getInputTab();
        tabHistory = TabHistory.getInstance();
        tabSettings = TabSettings.getInstance();
        try  {
            db = DBControl.getInstance();
        }catch(Exception ex)  {
            showDBErrorAndExit(ex);
        }
        scene = new Scene(root);
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        root.getChildren().add(tabs);
        tabList.addAll(tabInput, tabHistory, tabSettings);
    }
    
    /**
     * Configure Font
     */
    private void configFont()  {
        Font.loadFont(getClass().getResourceAsStream("font/Zawgyi-One.ttf"), 13.0);
    }
    
    /**
     * Configure CSS
     */
    private void configCSS()  {
        scene.getStylesheets().add(getClass().getResource("Home.css").toExternalForm());
    }
    
    /**
     * Entry Point of the application
     * @param primaryStage
     * @throws Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        initComponents();
        configComponents();
        configFont();
        configCSS();
        primaryStage.setTitle("PM Delivery");
        primaryStage.getIcons().add(new Image(Home.class.getResourceAsStream("Icon.jpg")));
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
           db.close();
        });
        primaryStage.show();
    }
    
    /**
     * Inform DB Error which is a critical error
     * And, Exit
     */
    private void showDBErrorAndExit(Exception ex)  {
        Alert isDBErr = new Alert(Alert.AlertType.ERROR);
        isDBErr.setTitle("Something went wrong!");
        isDBErr.setHeaderText("Database can't be connected!");
        isDBErr.setContentText(ex.getMessage());
        isDBErr.showAndWait();
        System.exit(1);
    }
    
    /**
     * Scene Object
     */
    private Scene scene;
    /**
     * Main Layout
     */
    private VBox root;
    
    /**
     * Main Tabs
     */
    private TabPane tabs;
    /**
     * Tab list
     */
    private ObservableList<Tab> tabList;
    /**
     * Input Tab
     */
    private TabInput tabInput;
    /**
     * History Tab
     */
    private TabHistory tabHistory;
    /**
     * Settings Tab
     */
    private TabSettings tabSettings;
    
    /**
     * DB Control
     */
    private DBControl db;
}
