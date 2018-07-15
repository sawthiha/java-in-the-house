/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob.format;

import FXPrinterHandler.Factory;
import datamodel.Recipient;
import datamodel.Voucher;
import java.util.Date;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  Testing Voucher Format
 * @author sawthiha
 */
public class Test extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        LinkedList<Recipient> list = new LinkedList();
        Recipient recipient;
        for(int i = 0; i < 20; i++)  {
            recipient = new Recipient(0, i, 0, "Saw Thiha", "N/A", "N/A", 10000, 100, 100, "N/A");
            list.add(recipient);
        }
        voucher = new Voucher(0, "Saw Thiha", new Date(), "+959999999", list, false);
        node = new PrintNode(voucher, 2, true);
        scene = new Scene(node);
        primaryStage.setTitle("Voucher Format");
        primaryStage.setScene(scene);
        primaryStage.show();
        Factory.getFXPrinter().setScale(node);
    }
    
    public static void main(String[] args)  {
        launch(args);
    }
    
    private Voucher voucher;
    private PrintNode node;
    private Scene scene;
}
