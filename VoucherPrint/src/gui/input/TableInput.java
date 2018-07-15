/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.input;

import FXTable.TableFactory;
import TextField.TextFieldFactory;
import javafx.scene.control.*;
import datamodel.Recipient;
import gui.event.AmtChangeEvent;
import gui.event.MinusChangeEvent;
import gui.event.PlusChangeEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

/**
 * List of recipients
 * @author sawthiha
 */
public class TableInput extends TableView{
    public TableInput(List<Recipient> recipients)  {
        super();
        this.data = (ObservableList<Recipient>) recipients;
        initComponents();
        configComponents();
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
        initTypeConverters();
        tableContext = new ContextMenu();
        mNew = new MenuItem("New Item");
        mDelete = new MenuItem("Delete Item");
        getColumns().addAll(colNo, colName, colPhone,colAdd, colAmount, colMinus, colPlus, colRemark);
    }
    
    /**
     * Initiate Type Converters
     */
    private void initTypeConverters()  {
        iConverter = new IntegerStringConverter()  {
            @Override
            public String toString(Integer value)  {
                return value.toString();
            }
            
            @Override
            public Integer fromString(String value)  {
                try  {
                    return Integer.parseInt(value);
                }catch(Exception e)  {
                    return 0;
                }
            }
        };
        lConverter = new LongStringConverter()  {
            @Override
            public String toString(Long value)  {
                return value.toString();
            }
            
            @Override
            public Long fromString(String value)  {
                try  {
                    return Long.parseLong(value);
                }catch(Exception e)  {
                    return 0l;
                }
            }
        };
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        getSelectionModel().setCellSelectionEnabled(true);
        configColumnWidths();
        configCellFacts();
        configCellSize();
        tableContext.getItems().addAll(mNew, mDelete);
        setContextMenu(tableContext);
        configEdit();
        configColumnStyles();
        setItems(data);
        //configRows();
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
     * Delete Rows from the Table
     * @param list 
     */
    public void deleteRows(Collection<Recipient> list)  {
        data.removeAll(list);
        resetNo();
    }
    
    /**
     * Add New Row
     */
    public void addNewRow()  {
        data.add(new Recipient(data.size() + 1));
    }
    
    /**
     * Configure Cell Sizes
     */
    private void configCellSize()  {
        setFixedCellSize(30);
        prefHeightProperty().bind(fixedCellSizeProperty().multiply(Bindings.size(getItems()).add(13 + 1.01)));
        minHeightProperty().bind(prefHeightProperty());
        maxHeightProperty().bind(prefHeightProperty());
    }
    
    /**
     * Reset the relativeIDs of the recipients
     * 
     */
    private void resetNo()  {
        IntStream.range(0, data.size()).forEach(i -> {
            data.get(i).setRelativeID(i + 1);
        });
        /* ******This is Atomic Integer Implementation*******
        AtomicInteger count = new AtomicInteger(1);
        data.forEach((Recipient recipient) -> {
            recipient.setRelativeID(count.getAndIncrement());
        });
        */
    }
    
    /**
     * Configure Column Widths
     */
    private void configColumnWidths()  {
        // Following method is for UNCONSTRAINED
        // colNo.prefWidthProperty().bind(widthProperty().multiply(0.05));
        colNo.setMaxWidth(50);
        colNo.setMinWidth(50);
        colName.setMaxWidth(200);
        colName.setMinWidth(200);
        colPhone.setMaxWidth(150);
        colPhone.setMinWidth(150);
        colAdd.setMaxWidth(250);
        colAdd.setMinWidth(250);
        colAmount.setMaxWidth(150);
        colAmount.setMinWidth(150);
        colMinus.setMaxWidth(100);
        colMinus.setMinWidth(100);
        colPlus.setMaxWidth(100);
        colPlus.setMinWidth(100);
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
     * Configure Editable Cells
     *
     */
    private void configEdit()  {
        setEditable(true);
        colNo.setEditable(false);
        colName.setCellFactory(col -> TableFactory.getStringEditableCell(null, TextFieldFactory.getTxtName(null)));
        colName.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, String>>() {
            @Override
            public void handle(CellEditEvent<Recipient, String> event) {
                String value = event.getNewValue();
                try  {
                    event.getRowValue().setName((value == null) ? "" : value);
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
        colPhone.setCellFactory(col -> TableFactory.getStringEditableCell(null, TextFieldFactory.getTxtPhone(null)));
        colPhone.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, String>>() {
            @Override
            public void handle(CellEditEvent<Recipient, String> event) {
                String value = event.getNewValue();
                try  {
                    event.getRowValue().setPhone((value == null) ? "" : value);
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
        colAdd.setCellFactory(col -> TableFactory.getStringEditableCell(null, TextFieldFactory.getGenericTxt("Address")));
        colAdd.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, String>>() {
            @Override
            public void handle(CellEditEvent<Recipient, String> event) {
                String value = event.getNewValue();
                try  {
                    event.getRowValue().setAddress((value == null) ? "" : value);
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
        colAmount.setCellFactory(col -> TableFactory.getLongEditableCell(null, TextFieldFactory.getDecimalTxt("Amount")));
        colAmount.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, Long>>() {
            @Override
            public void handle(CellEditEvent<Recipient, Long> event) {
                Long oldValue, value;
                oldValue = event.getOldValue();
                value = event.getNewValue();
                try  {
                    event.getRowValue().setAmount((value == null) ? 0l : value);
                    Event.fireEvent(colAmount.getTableView().getParent(), new AmtChangeEvent(oldValue, value));
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
        colPlus.setCellFactory(col -> TableFactory.getIntEditableCell(null, TextFieldFactory.getDecimalTxt("+Deli")));
        colPlus.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, Integer>>() {
            @Override
            public void handle(CellEditEvent<Recipient, Integer> event) {
                Integer oldValue, value;
                value = event.getNewValue();
                oldValue = event.getOldValue();
                try  {
                    event.getRowValue().setPlus((value == null) ? 0 : value);
                    Event.fireEvent(colPlus.getTableView().getParent(), new PlusChangeEvent(oldValue, value));
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
        colMinus.setCellFactory(col -> TableFactory.getIntEditableCell(null, TextFieldFactory.getDecimalTxt("-Deli")));
        colMinus.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, Integer>>() {
            @Override
            public void handle(CellEditEvent<Recipient, Integer> event) {
                Integer value, oldValue;
                value= event.getNewValue();
                oldValue = event.getOldValue();
                try  {
                    event.getRowValue().setMinus((value == null) ? 0 : value);
                    Event.fireEvent(colMinus.getTableView().getParent(), new MinusChangeEvent(oldValue, value));
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
        colRemark.setCellFactory(col -> TableFactory.getStringEditableCell(null, TextFieldFactory.getGenericTxt("Remark")));
        colRemark.setOnEditCommit(new EventHandler<CellEditEvent<Recipient, String>>() {
            @Override
            public void handle(CellEditEvent<Recipient, String> event) {
                String value = event.getNewValue();
                try  {
                    event.getRowValue().setRemark((value == null) ? "" : value);
                } catch (NullPointerException except)  {
                    // Table Refresh
                }
            }
        });
    }
    
    /**
     * Configure Row Factory
     * @deprecated Not used
     */
    private void configRows()  {
        setRowFactory(new Callback<TableView<Recipient>, TableRow<Recipient>>() {
            @Override
            public TableRow<Recipient> call(TableView<Recipient> param) {
               TableRow<Recipient> row = new TableRow<>();
               mDelete.setOnAction((ActionEvent event) -> {
                   data.remove(row.getItem());
               });
               //row.setContextMenu(cellContext);
               return row;
            }
        });
    }
    
    /**
     * Add new row to the table
     */
    public void newRow()  {
        this.addNewRow();
    }
    
    /**
     * Clear the table
     */
    public void clear()  {
        data.clear();
    }
    
    public LinkedList<Recipient> getRecipients()  {
        LinkedList<Recipient> list = new LinkedList<>();
        Iterator<Recipient> iterator = data.iterator();
        while(iterator.hasNext())  {
            list.add(iterator.next());
        }
        return list;
    }
    
    public MenuItem getDeleteMenuItm()  {
        return this.mDelete;
    }
    
    public MenuItem getAddMenuItm()  {
        return this.mNew;
    }
    
    /**
     * Set Recipients
     * @param list 
     */
    public void setRecipients(Object list)  {
        data.setAll((LinkedList<Recipient>) list);
    }
    
    /**
     * Records of Table
     */
    private ObservableList<Recipient> data; 
    /**
     * No
     */
    private TableColumn colNo;
    /**
     * Name
     */
    private TableColumn colName;
    /**
     * Phone
     */
    private TableColumn colPhone;
    /**
     * Address
     */
    private TableColumn colAdd;
    /**
     * Amount
     */
    private TableColumn colAmount;
    /**
     * Minus
     */
    private TableColumn colMinus;
    /**
     * Plus
     */
    private TableColumn colPlus;
    /**
     * Remark
     */
    private TableColumn colRemark;
    
    /**
     * Integer to String Converter
     * For Cell Factory
     */
    private IntegerStringConverter iConverter;
    /**
     * Long to String Converter
     * For Cell Factory
     */
    private LongStringConverter lConverter;
    
    /**
     * Right-Click Menu
     */
    private ContextMenu tableContext;
    /**
     * New Record
     */
    private MenuItem mNew;
    /**
     * Delete Record
     */
    private MenuItem mDelete;
}
