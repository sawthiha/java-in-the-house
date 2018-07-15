/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob;

import FXThreadHandler.RuntimeHandler;
import datamodel.Recipient;
import datamodel.Voucher;
import gui.GUIFactory;
import java.util.Date;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Test
 * @author sawthiha
 */
public class Test extends Application{
    public static void main(String[] args)  {
        launch(args);
        System.out.println(Long.decode("1000"));
    }
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        LinkedList<Recipient> list = new LinkedList<>();
        for(int i = 1; i <= 20; i++)  {
            list.add(new Recipient(0, i, 1, "Rep Rep", "0000000", "Middle of Nowhere", 1000, 1000, 1000, ""));
        }
        Voucher voucher = new Voucher(1, "Cust Cust", new Date(), "09798534555", list, false);

        FXPrintJob job = new FXPrintJob(voucher, true);
        RuntimeHandler.runAndWait(job);
        System.out.println(job.getStatus());
        Node node = GUIFactory.getPrintNode(voucher, 2, true);
        Scene scene = new Scene((Parent) node);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Test Application");
        primaryStage.show();
        //System.out.println(Math.ceil((0.0 + 18) / 18));
    }
}
