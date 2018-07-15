/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.history;

import datamodel.*;
import java.util.Iterator;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * List of Vouchers
 * @author sawthiha
 */
public class TableVoucher extends TableView  {
    /**
     * Constructor
     */
    private TableVoucher()  {
        super();
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        colNo = new TableColumn("No");
        colCustomer = new TableColumn("Customer");
        colDate = new TableColumn("Date");
        colPhone = new TableColumn("Phone");
        colDelivery = new TableColumn("Delivery");
        colPrinted = new TableColumn("Printed?");
        data = FXCollections.observableArrayList();
        tblContext = new ContextMenu();
        itmOpen = new MenuItem("Open");
        itmDelete = new MenuItem("Delete");
        itmPrint = new MenuItem("Print");
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        configColumnWidths();
        configColumnStyles();
        configCellFacts();
        configCellSize();
        getColumns().addAll(colNo, colCustomer, colDate, colPhone, colDelivery, colPrinted);
        tblContext.getItems().addAll(itmOpen, itmDelete, itmPrint);
        setContextMenu(tblContext);
        setItems(data);
    }
    
    /**
     * Configure Cell Factories
     */
    private void configCellFacts()  {
        colNo.setCellValueFactory(
            new PropertyValueFactory<>("id")
        );
        colCustomer.setCellValueFactory(
            new PropertyValueFactory<>("customer")
        );
        colDate.setCellValueFactory(
            new PropertyValueFactory<>("date")
        );
        colPhone.setCellValueFactory(
            new PropertyValueFactory<>("phone")
        );
        colDelivery.setCellValueFactory(
            new PropertyValueFactory<>("delivery")
        );
        colPrinted.setCellFactory(column -> new CheckBoxTableCell<>());
        colPrinted.setCellValueFactory(
                value ->  {
                    Voucher v = value.getValue();
                    
                    return v.isPrintedProperty();
                }
        );
    }
    
    /**
     * Configure Column Widths
     */
    private void configColumnWidths()  {
        colNo.setMaxWidth(70);
        colNo.setMinWidth(70);
        // colCustomer.setMaxWidth(USE_PREF_SIZE);
        // colCustomer.setMinWidth(USE_PREF_SIZE);
        // colDate.setMaxWidth(USE_PREF_SIZE);
        // colDate.setMinWidth(USE_PREF_SIZE);
        // colPhone.setMaxWidth(350);
        // colPhone.setMinWidth(350);
    }
    
    /**
     * Configure Column Styles
     */
    private void configColumnStyles()  {
        colNo.setStyle( "-fx-alignment: CENTER;");
        colCustomer.setStyle( "-fx-alignment: CENTER;");
        colDate.setStyle( "-fx-alignment: CENTER;");
        colPhone.setStyle( "-fx-alignment: CENTER;");
        colDelivery.setStyle("-fx-alignment: CENTER;");
        colPrinted.setStyle("-fx-alignment: CENTER;");
    }
    
    /**
     * Configure Cell Sizes
     */
    private void configCellSize()  {
        setFixedCellSize(25);
        prefHeightProperty().bind(fixedCellSizeProperty().multiply(Bindings.size(getItems()).add(20 + 1.01)));
        minHeightProperty().bind(prefHeightProperty());
        maxHeightProperty().bind(prefHeightProperty());
    }
    
    /**
     * Clear the table
     */
    public void clear()  {
        data.forEach((voucher) ->  {
            voucher.setId(-1);
        });
        data.clear();
    }
    
    /**
     * Get Open Menu Item
     * @return itmOpen - MenuItem
     */
    public MenuItem getItmOpen()  {
        return itmOpen;
    }
    
    /**
     * Get Delete item
     * @return itmDelete
     */
    public MenuItem getItmDelete()  {
        return itmDelete;
    }
    
    /**
     * Get Print Menu Item
     * @return itmPrint - MenuItem
     */
    public MenuItem getItmPrint()  {
        return itmPrint;
    }
    
    /**
     * Get Today's Record
     * @return List of today records
     */
    public ObservableList<Voucher> getTodayRecords()  {
        return data.filtered((Voucher voucher) -> {
            return voucher.isCreatedToday();
        });
    }
    
    /**
     * Set Voucher list
     * @param list list of vouchers
     */
    public void setData(List<Voucher> list)  {
        Iterator<Voucher> iterator = list.iterator();
        while(iterator.hasNext())  {
            data.add(iterator.next());
        }
    }
    
    /**
     * Get an instance
     * @return instance
     */
    public static TableVoucher getInstance()  {
        return instance;
    }
    
    /**
     * Instance
     */
    private static TableVoucher instance = new TableVoucher();
    
    /**
     * Voucher List
     */
    private ObservableList<Voucher> data;
    /**
     * ID
     */
    private TableColumn colNo;
    /**
     * Customer
     */
    private TableColumn colCustomer;
    /**
     * Date
     */
    private TableColumn colDate;
    /**
     * Phone
     */
    private TableColumn colPhone;
    /**
     * Delivery
     */
    private TableColumn colDelivery;
    /**
     * Is Printed?
     */
    private TableColumn<Voucher, Boolean> colPrinted;
    
    /**
     * Context
     */
    private ContextMenu tblContext;
    /**
     * Open Menu Item
     */
    private MenuItem itmOpen;
    /**
     * Delete Menu Item
     */
    private MenuItem itmDelete;
    /**
     * Print Menu Item
     */
    private MenuItem itmPrint;
}
