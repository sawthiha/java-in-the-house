/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printjob.format;

import datamodel.Recipient;
import datamodel.Voucher;
import java.util.Iterator;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * List of Recipients
 * @author sawthiha
 */
class TableNode extends TableView {
    /**
     * Constructor
     */
    public TableNode()  {
        super();
        initComponents();
        configComponents();
        configCSS();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        colNo = new TableColumn("No");
        colName = new TableColumn("Name");
        colPhone = new TableColumn("Phone No");
        colAdd = new TableColumn("Address");
        colAmount = new TableColumn("Amount");
        colMinus = new TableColumn("-Deli");
        colPlus = new TableColumn("+Deli");
        colRemark = new TableColumn("Remark");
        data = FXCollections.observableArrayList();
        getColumns().addAll(colNo, colName, colPhone,colAdd, colAmount, colMinus, colPlus, colRemark);
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        setFocusTraversable(false);
        configCellSize();
        configColumnWidths();
        configCellFacts();
        //configColumnStyles();
        setItems(data);
    }
    
    /**
     * Configure CSS
     */
    private void configCSS()  {
        getStylesheets().add(getClass().getResource("table.css").toExternalForm());
    }
    
    /**
     * Configure Cell Sizes
     */
    private void configCellSize()  {
        setFixedCellSize(25);
        prefHeightProperty().bind(fixedCellSizeProperty().multiply(Bindings.size(getItems()).add(PrintNode.MAX_ITEMS + 1.09)));
        minHeightProperty().bind(prefHeightProperty());
        maxHeightProperty().bind(prefHeightProperty());
    }
    
    /**
     * Configure Cell Factories
     */
    private void configCellFacts()  {
        colNo.setCellValueFactory(
            new PropertyValueFactory<>("relativeID")
        );
        colName.setCellValueFactory(
            new PropertyValueFactory<>("name")
        );
        colPhone.setCellValueFactory(
            new PropertyValueFactory<>("phone")
        );
        colAdd.setCellValueFactory(
            new PropertyValueFactory<>("address")
        );
        colAmount.setCellValueFactory(
            new PropertyValueFactory<>("amount")
        );
        colPlus.setCellValueFactory(
            new PropertyValueFactory<>("plus")
        );
        colMinus.setCellValueFactory(
            new PropertyValueFactory<>("minus")
        );
        colRemark.setCellValueFactory(
            new PropertyValueFactory<>("remark")
        );
    }
    
    /**
     * Configure Column Widths
     */
    private void configColumnWidths()  {
        // Following method is for UNCONSTRAINED
        // colNo.prefWidthProperty().bind(widthProperty().multiply(0.05));
        colNo.setMaxWidth(40);
        colNo.setMinWidth(40);
        colName.setMaxWidth(175);
        colName.setMinWidth(175);
        colPhone.setMaxWidth(150);
        colPhone.setMinWidth(150);
        colAdd.setMaxWidth(250);
        colAdd.setMinWidth(250);
        colAmount.setMaxWidth(100);
        colAmount.setMinWidth(100);
        /*
            colMinus.setMaxWidth(100);
            colMinus.setMinWidth(100);
            colPlus.setMaxWidth(100);
            colPlus.setMinWidth(100);
        */
        colRemark.setMinWidth(100);
        colRemark.setMaxWidth(100);
    }
    
    /**
     * Configure Column Styles
     */
    private void configColumnStyles()  {
        colNo.setStyle( "-fx-alignment: CENTER;");
        colName.setStyle( "-fx-alignment: CENTER;");
        colPhone.setStyle( "-fx-alignment: CENTER;");
        colAdd.setStyle( "-fx-alignment: CENTER;");
        colAmount.setStyle( "-fx-alignment: CENTER;");
        colMinus.setStyle( "-fx-alignment: CENTER;");
        colPlus.setStyle( "-fx-alignment: CENTER;");
        colRemark.setStyle( "-fx-alignment: CENTER;");
    }
    
    /**
     * Load data
     */
    private void loadData(int page)  {
        int idx, count;
        Iterator<Recipient> list;
        
        count = PrintNode.MAX_ITEMS;
        idx = (page - 1) * count;
        list = voucher.getRecipients().listIterator(idx);
        while(list.hasNext() && count-- > 0)
            data.add(list.next());
    }
    
    /**
     * Set Voucher
     * @param voucher voucher
     */
    public void setVoucher(Voucher voucher, int page)  {
        this.voucher = voucher;
        loadData(page);
    }
    
    /**
     * Voucher
     */
    private Voucher voucher;
    private ObservableList<Recipient> data; 
    private TableColumn colNo;
    private TableColumn colName;
    private TableColumn colPhone;
    private TableColumn colAdd;
    private TableColumn colAmount;
    private TableColumn colMinus;
    private TableColumn colPlus;
    private TableColumn colRemark;
}
