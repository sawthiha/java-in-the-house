/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.history;

import FXThreadHandler.RuntimeHandler;
import datamodel.Voucher;
import datamodel.database.DBControl;
import gui.GUIFactory;
import java.sql.SQLException;
import java.util.LinkedList;
import javafx.scene.Cursor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import printjob.JobChain;

/**
 * History Tab
 * @author sawthiha
 */
public class TabHistory extends Tab{
    /**
     * Constructor
     */
    private TabHistory()  {
        super("History");
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        tblVouchers = TableVoucher.getInstance();
        opsHistory = OpsHistory.getInstance();
        db = DBControl.getInstance();
        layout = new VBox();
        conCleared = new Alert(Alert.AlertType.CONFIRMATION);
        isCleared = new Alert(Alert.AlertType.INFORMATION);
        isNCleared = new Alert(Alert.AlertType.ERROR);
        conDeleted = new Alert(Alert.AlertType.CONFIRMATION);
        isDeleted = new Alert(Alert.AlertType.INFORMATION);
        isNDeleted = new Alert(Alert.AlertType.ERROR);
        isPrinted = new Alert(Alert.AlertType.INFORMATION);
        isNPrinted = new Alert(Alert.AlertType.ERROR);
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setClosable(false);
        setContent(layout);
        VBox.setMargin(tblVouchers, new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(tblVouchers, opsHistory);
        try  {
            tblVouchers.setData(db.getAllVouchers());
        } catch(SQLException except)  {
            tblVouchers.setData(new LinkedList<>());
        }
        configBehaviours();
        configAlerts();
    }
    
    /**
     * Configure Alerts
     */
    private void configAlerts()  {
        conCleared.setTitle("Confirm");
        conCleared.setHeaderText("Clear History?");
        conCleared.setContentText("Please confirm to clear the history.");
        isCleared.setTitle("Cleared");
        isCleared.setHeaderText("History Cleared.");
        isCleared.setContentText("History is successfully cleared.");
        isNCleared.setTitle("Failed!");
        isNCleared.setHeaderText("Failure!");
        isNCleared.setContentText("Failed to clear history!");
        conDeleted.setTitle("Confirm");
        conDeleted.setHeaderText("Delete the records?");
        conDeleted.setContentText("Please confirm to clear the records.");
        isDeleted.setTitle("Deleted");
        isDeleted.setHeaderText("Record Deleted!");
        isDeleted.setContentText("Records are successfully deleted.");
        isNDeleted.setTitle("Failed");
        isNDeleted.setHeaderText("Failure!");
        isNDeleted.setContentText("Failed to delete records.");
        isPrinted.setTitle("Printed");
        isPrinted.setHeaderText("Records printed.");
        isPrinted.setContentText("Records are successfuly queued.");
        isNPrinted.setTitle("Failed");
        isNPrinted.setHeaderText("Failure!");
        isNPrinted.setContentText("Printing hasn't added to the queue!");
    }
    
    /**
     * Configure Behaviours
     */
    private void configBehaviours()  {
        opsHistory.getBtnClear().setOnAction((ActionEvent event) -> {
            if(conCleared.showAndWait().get() == ButtonType.OK)  {
                clear();
            }
        });
        opsHistory.getBtnPrint().setOnAction((ActionEvent event) -> {
            boolean isTotal = opsHistory.isTotal();
            if(opsHistory.isToday())  {
                printToday(isTotal);
            }else  {
                print(isTotal);
            }
        });
        opsHistory.getBtnDelete().setOnAction((ActionEvent event) ->  {
            if(conDeleted.showAndWait().get() == ButtonType.OK)  {
                delete();
            }
        });
        tblVouchers.getItmOpen().setOnAction((ActionEvent event) -> {
            open();
        });
        tblVouchers.getItmDelete().setOnAction((event) ->  {
            if(conDeleted.showAndWait().get() == ButtonType.OK)  {
                delete();
            }
        });
        tblVouchers.getItmPrint().setOnAction((ActionEvent event) -> {
            print(opsHistory.isTotal());
        });
    }
    
    /**
     * Clear the contents
     */
    private void clear()  {
        try  {
            db.clearHistory();
            tblVouchers.clear();
            isCleared.showAndWait();
        }catch(SQLException except)  {
            isNCleared.setContentText(except.getMessage());
            isNCleared.showAndWait();
        }
    }
    
    /**
     * Open Selected Vouchers
     */
    private void open()  {
        ObservableList<Voucher> items = tblVouchers.getSelectionModel().getSelectedItems();
        ObservableList<Tab> tabs = getTabPane().getTabs();
        items.forEach((Voucher voucher) -> {
            tabs.add(GUIFactory.getRecordTab(voucher));
        });
        /*
        Voucher voucher = (Voucher)tblVouchers.getSelectionModel().getSelectedItem();
        if(voucher == null)
            return;
        voucher.setRecipients(db.getAllRecipientsByVoucher(voucher.getId()));
        getTabPane().getTabs().add(new TabRecord(voucher)); */
    }
    
    /**
     * Delete Vouchers
     */
    private void delete()  {
        try  {
            ObservableList<Voucher> items = tblVouchers.getSelectionModel().getSelectedItems();
            this.db.deleteVouchers(items);
            this.tblVouchers.getItems().removeAll(items);
            isDeleted.showAndWait();
        }catch(SQLException except)  {
            isNDeleted.setContentText(except.getMessage());
            isNDeleted.showAndWait();
        }
    }
    
    /**
     * Print All Selected Items
     */
    private void print(boolean isTotal)  {
        ObservableList<Voucher> items = tblVouchers.getSelectionModel().getSelectedItems();
        if(items.isEmpty())
            return;
        print(items, isTotal);
    }
    
    /**
     * Print All Recent Record(Today's Records)
     */
    private void printToday(boolean isTotal)  {
        ObservableList<Voucher> items = tblVouchers.getTodayRecords();
        if(items.isEmpty())  {
            return;
        }
        print(items, isTotal);
    }
    
    /**
     * Print a list of vouchers
     * @param items list of vouchers
     */
    private void print(ObservableList<Voucher> items, boolean isTotal)  {
        try  {
            JobChain chain = printjob.JobFactory.getChain(items, isTotal);
            getTabPane().setCursor(Cursor.WAIT);
            RuntimeHandler.runAndWait(chain);
            getTabPane().setCursor(Cursor.DEFAULT);
            if(chain.getStatus())  {
                this.db.updatePrinted(items);
                isPrinted.showAndWait();
            }else  {
                throw new RuntimeException("Printer is not operational!");
            }
        }catch(SQLException except)  {
            isNPrinted.setContentText(except.getMessage());
            isNPrinted.showAndWait();
        }catch(RuntimeException except)  {
            isNPrinted.setContentText(except.getMessage());
            isNPrinted.showAndWait();
        }
    }
    
    /**
     * New Voucher Handler
     * @param voucher New Voucher
     */
    public void onNewVoucher(Voucher voucher)  {
        this.tblVouchers.getItems().add(voucher);
    }
    
    /**
     * Get an instance
     * @return instance of TabHistory
     */
    public static TabHistory getInstance()  {
        return instance;
    }
    
    /**
     * Instance
     */
    private static final TabHistory instance = new TabHistory();
    /**
     * Layout
     */
    private VBox layout;
    
    /**
     * Voucher List
     */
    private TableVoucher tblVouchers;
    /**
     * Operations
     * Clear History
     */
    private OpsHistory opsHistory;
    /**
     * Confirm History Clear
     */
    private Alert conCleared;
    /**
     * Alert valid clear operation
     */
    private Alert isCleared;
    /**
     * Alert invalid clear operation
     */
    private Alert isNCleared;
    /**
     * Alert Successful Printing
     */
    private Alert isPrinted;
    /**
     * Alert invalid printing
     */
    private Alert isNPrinted;
    /**
     * Confirm Deletion
     */
    private Alert conDeleted;
    /**
     * Alert Valid Deletion
     */
    private Alert isDeleted;
    /**
     * Alert Invalid Deletion
     */
    private Alert isNDeleted;
    /**
     * DB control
     */
    private DBControl db;
}