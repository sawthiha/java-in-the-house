/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.input;

import FXThreadHandler.RuntimeHandler;
import datamodel.Recipient;
import datamodel.Voucher;
import datamodel.database.DBControl;
import gui.GUIFactory;
import gui.history.TabHistory;
import java.sql.SQLException;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import printjob.FXPrintJob;
import printjob.JobFactory;

/**
 * Voucher Entry Tab
 * @author sawthiha
 */
public class TabInput extends Tab{
    /**
     * Private Constructor
     */
    public TabInput()  {
        this(new Voucher());
    }
    
    public TabInput(Voucher voucher)  {
        super("Voucher Creation");
        this.voucher = voucher;
        initComponents();
        configComponents();
    }
    
    /**
     * Initiate Components
     */
    private void initComponents()  {
        layout = new VBox();
        form = GUIFactory.getFormInput(voucher);
        recipients = GUIFactory.getRecipientsNode(voucher);
        ops = GUIFactory.getOpsInput();
        db = DBControl.getInstance();
        isTotal = new SimpleBooleanProperty(true);
        conClear = new Alert(Alert.AlertType.CONFIRMATION);
        isSaved = new Alert(Alert.AlertType.INFORMATION);
        isNSaved = new Alert(Alert.AlertType.ERROR);
        isPrinted = new Alert(Alert.AlertType.INFORMATION);
        isNPrinted = new Alert(Alert.AlertType.ERROR);
        isCleared = new Alert(Alert.AlertType.INFORMATION);
        isNCleared = new Alert(Alert.AlertType.ERROR);
    }
    
    /**
     * Configure Components
     */
    private void configComponents()  {
        configBehaviours();
        setClosable(false);
        setContent(layout);
        layout.setSpacing(5);
        layout.getChildren().addAll(form,recipients, ops);
        conClear.setTitle("Confirm");
        conClear.setHeaderText("Clear the inputs?");
        conClear.setContentText("Please confirm to clear the input!");
        isSaved.setTitle("Success");
        isSaved.setHeaderText("Voucher is Saved.");
        isNSaved.setTitle("Something went wrong!");
        isNSaved.setHeaderText("Voucher can't be saved!");
        isPrinted.setTitle("Success");
        isPrinted.setHeaderText("Voucher is added to the printer queue.");
        isPrinted.setContentText("Voucher is being printed.");
        isNPrinted.setTitle("Something went wrong!");
        isNPrinted.setHeaderText("Voucher can't be printed!");
        isCleared.setTitle("Success");
        isCleared.setHeaderText("All the fields are cleared!");
        isNCleared.setTitle("Someting went wrong!");
        isNCleared.setHeaderText("Couldn't clear!");
    }
    
    /**
     * Configure Behaviors of the components
     */
    private void configBehaviours()  {
        ops.getBtnClear().setOnAction(event -> {
            if(conClear.showAndWait().get() == ButtonType.OK)  {
                clear();
            }
        });
        ops.getBtnSave().setOnAction(event -> {
            save();
        });
        ops.getBtnPrint().setOnAction(event -> {
            print();
        });
        this.isTotal.bind(ops.getCbTotal().selectedProperty());
    }
    
    /**
     * Save the contents 
     */
    protected void save()  {
        try {
            save(this.voucher);
            isSaved.showAndWait();
        } catch (UnsupportedOperationException | SQLException except) {
            this.showDialog(this.isNSaved, except.getMessage());
        }
    }
    
    /**
     * Save a Voucher
     * @param voucher To Save
     * @throws java.sql.SQLException In Case of DB Error
     */
    protected void save(Voucher voucher) throws SQLException, UnsupportedOperationException {
        this.validateVoucher();
        db.addVoucher(voucher);
        TabHistory.getInstance().onNewVoucher(new Voucher(voucher));
        clear();
    }
    
    /**
     * Print the contents
     */
    protected void print()  {
        try {
            print(this.voucher);
            isPrinted.showAndWait();
        } catch(UnsupportedOperationException except)  {
            this.showDialog(this.isNPrinted, except.getMessage());
        } catch(SQLException | RuntimeException except)  {
            this.showDialog(this.isNPrinted, except.getMessage());
        }
    }
    
    /**
     * Print A Voucher
     * @param voucher 
     * @throws java.sql.SQLException 
     */
    protected void print(Voucher voucher) throws UnsupportedOperationException, SQLException, RuntimeException {
        FXPrintJob print = JobFactory.getJob(this.voucher, isTotal.get());
        RuntimeHandler.runAndWait(print);
        if(!print.getStatus())
            throw new java.lang.RuntimeException("Printer is not operational!");
        this.save(this.voucher);
    }
    
    /**
     * Clear the contents
     */
    protected void clear()  {
        try  {
            clear(this.voucher);
            this.showDialog(this.isCleared);
        } catch(SQLException except)  {
            this.showDialog(this.isNCleared, except.getMessage());
        }
    }
    
    /**
     * Clear.
     * @param voucher
     * @throws SQLException
     */
    protected void clear(Voucher voucher) throws SQLException {
        voucher.setCustomer("");
        voucher.setPhone("");
        voucher.getRecipients().clear();
    }
    
    /**
     * Toggle Total Amount
     * @param value Total or not
     */
    protected void setTotalVisible(boolean value)  {
        isTotal.set(value);
    }
    
    /**
     * Show Dialog
     * @param alert Dialog 
     */
    protected void showDialog(Alert alert)  {
        alert.showAndWait();
    }
    
    /**
     * Show Error Dialog with specific error message
     * @param alert Dialog
     * @param message Error Message
     */
    protected void showDialog(Alert alert, String message)  {
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Parse a Voucher from input
     * @return voucher from input
     */
    public Voucher getVoucher()  {
        return this.voucher;
    }
    
    /**
     * Check Validation of the Input Voucher
     * 
     */
    public void validateVoucher() throws UnsupportedOperationException  {
        String customer, phone;
        
        customer = this.voucher.getCustomer();
        phone = this.voucher.getPhone();
        
        if(customer.isEmpty())  {
            this.form.requestFocusOnCustomer();
            throw new UnsupportedOperationException("Customer Name must be entered!");
        } else if(phone.isEmpty())  {
            this.form.requestFocusOnPhone();
            throw new UnsupportedOperationException("Phone No must be entered!");
        }
        
    }
    
    /**
     * 
     * @param old
     * @return 
     */
    public List<Recipient> getRemovedRecipients(List<Recipient> old)  {
        return this.voucher.getRecipients().filtered((Recipient t) -> {
            return old.indexOf(t) == -1;
        });
    }
    
    /**
     * Get whether to be summarized or not
     * @return isTotal
     */
    public boolean getIsTotal()  {
        return isTotal.get();
    }
    
    /**
     * Get Recipient List
     * @return Recipient List
     */
    public List<? extends Recipient> getRecipients()  {
        return (List<? extends Recipient>) recipients.getRecipients();
    }
    
    public Button getBtnClear()  {
        return ops.getBtnClear();
    }
    
    /**
     * Voucher
     */
    private Voucher voucher;
    /**
     * Layout
     */
    private VBox layout;
    /**
     * Input Form Customer
     * Name, Date, Phone
     */
    private FormInput form;
    /**
     * Recipients List
     */
    private RecipientsNode recipients;
    /**
     * Input Related Operations
     * Clear,  Save, Print
     */
    private OpsInput ops;
    /**
     * DB Control
     */
    private DBControl db;
    /**
     * 'Clear'
     */
    private Alert conClear;
    /**
     * Alert Valid Save
     */
    private Alert isSaved;
    /**
     * Alert Invalid Save
     */
    private Alert isNSaved;
    /**
     * Alert if printed successfully
     */
    private Alert isPrinted;
    /**
     * Alert if not printed
     */
    private Alert isNPrinted;
    /**
     * Alert if cleared
     */
    private Alert isCleared;
    /**
     * Alert if not cleared
     */
    private Alert isNCleared;
    /**
     * Decide whether Total must be included
     */
    private BooleanProperty isTotal;
}
