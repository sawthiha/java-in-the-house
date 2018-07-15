/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sawthiha
 */
public class StageHandler {
    public StageHandler(Stage stage, Scene scene)  {
        this.stage = stage;
        this.scene = scene;
    }
    
    /**
     * Show the node
     * @param node 
     */
    public void show(Node node)  {
        scene.setRoot((Parent) node);
        stage.show();
    }
    
    /**
     * Change root
     * @param node 
     */
    public void change(Node node)  {
        scene.setRoot((Parent) node);
    }
    
    /**
     * Hide the stage
     */
    public void hide()  {
        stage.hide();
    }
    
    private Stage stage;
    private Scene scene;
}
