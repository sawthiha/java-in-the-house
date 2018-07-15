/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXTable;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

/**
 *
 * @author sawthiha
 * @param <S> String Type
 * @param <T> Object Type
 */
public class EditableCell<S, T> extends TableCell<S, T> {
    public EditableCell(StringConverter<T> converter, TextField txtInput)  {
        this.converter = converter;
        this.txtInput = txtInput;
        setGraphic(txtInput);
        txtInput.setAlignment(Pos.CENTER);
        txtInput.setPadding(new Insets(0,0,0,0));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        setActionEvent();
        setFocusEvents();
        setItemListener();
        setKeyEvents();
    }
    
    @Override
    public void startEdit()  {
        super.startEdit();
        txtInput.setText(converter.toString(this.getItem()));
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        txtInput.requestFocus();
    }
    
    @Override
    public void cancelEdit()  {
        super.cancelEdit();
        this.setContentDisplay(ContentDisplay.TEXT_ONLY);
    }
    
    @Override
    public void commitEdit(T item)  {
        TableView<S> tblParent = this.getTableView();
        if(!this.isEditing() && !item.equals(this.getItem()))  {
            if(tblParent != null)  {
                TableColumn<S, T> colParent = this.getTableColumn();
                CellEditEvent<S, T> event = new CellEditEvent<>(tblParent,
                        new TablePosition<S, T>(tblParent, this.getIndex(), colParent),
                        TableColumn.editCommitEvent(), item
                );
                Event.fireEvent(colParent, event);
                this.setItem(converter.fromString(txtInput.getText()));
            }
        }
        
        super.commitEdit(item);

        setContentDisplay(ContentDisplay.TEXT_ONLY);
        
        //tblParent.refresh();
    }
    
    /**
     * Key Bindings
     */
    private void setKeyEvents()  {
        txtInput.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ESCAPE)  {
                commitEdit(converter.fromString(txtInput.getText()));
                event.consume();
            } else if(event.getCode() == KeyCode.TAB)  {
                commitEdit(converter.fromString(txtInput.getText()));
                editNextCell(true);
                event.consume();
            } else if(event.getCode() == KeyCode.RIGHT)  {
                int caret = this.txtInput.getCaretPosition();
                if(caret == this.txtInput.getText().length())  {
                    commitEdit(converter.fromString(txtInput.getText()));
                    editNextCell(true);
                    event.consume();
                }
            } else if(event.getCode() == KeyCode.LEFT)  {
                int caret = this.txtInput.getCaretPosition();
                if(caret == 0)  {
                    commitEdit(converter.fromString(txtInput.getText()));
                    editNextCell(false);
                    event.consume();
                }
            } else if(event.getCode() == KeyCode.UP)  {
                commitEdit(converter.fromString(txtInput.getText()));
                editTopDownCell(true);
                event.consume();
            } else if(event.getCode() == KeyCode.DOWN)  {
                commitEdit(converter.fromString(txtInput.getText()));
                editTopDownCell(false);
                event.consume();
            }
        });
    }
    
    private void setFocusEvents()  {
        ChangeListener<Boolean> commitOnClose;
        commitOnClose = (obsv, was, is) -> {
            if(!is)  {
                this.commitEdit(converter.fromString(txtInput.getText()));
            }
        };
        this.txtInput.focusedProperty().addListener(commitOnClose);
    }
    
    private void editNextCell(boolean isFwd)  {
        int idxRow, idxCol, idxNext, nCols, nRows;
        TableColumn colNext;
        TableView<S> tbl;
        
        tbl = getTableView();
        List<TableColumn> lsCols = new ArrayList<>();
        tbl.getColumns().forEach(col ->  {
            lsCols.add((col.isEditable()) ? col: null);
        });
        idxCol = lsCols.indexOf(getTableColumn());
        idxNext = idxCol;
        idxRow = getIndex();
        colNext = null;
        nCols = lsCols.size();
        nRows = tbl.getItems().size();
        
        do  {
            if (isFwd)  {
                idxNext++;
                if (idxNext == nCols)  {
                    idxNext = 0;
                    idxRow++;
                    if (idxRow == nRows)  {
                        idxRow = 0;
                    }
                }
            } else  {
                idxNext--;
                if (idxNext < 0)  {
                    idxNext = nCols - 1;
                    idxRow--;
                    if (idxRow < 0)  {
                        idxRow = nRows - 1;
                    }
                }
            }
            colNext = lsCols.get(idxNext);
        }while (colNext == null && idxNext !=  idxCol);
        
        tbl.getSelectionModel().clearAndSelect(idxRow, colNext);
        tbl.edit(idxRow, colNext);
    }
    
    private void editTopDownCell(boolean up)  {
        TableView<S> tbl;
        TableColumn colNext;
        int idxRow, nRows;
        
        tbl = getTableView();
        colNext = getTableColumn();
        idxRow = getIndex();
        nRows = tbl.getItems().size();
        if(up)  {
            idxRow--;
            if(idxRow < 0)  {
                idxRow = nRows - 1;
            }
        } else  {
            idxRow++;
            if(idxRow == nRows)  {
                idxRow = 0;
            }
        }
        
        tbl.getSelectionModel().clearAndSelect(idxRow, colNext);
        tbl.edit(idxRow, colNext);
    }
    
    
    
    private void setItemListener()  {
        itemProperty().addListener((obs, oldItm, newItm) -> {
            if(newItm == null)  {
                setText("");
            }else  {
                setText(converter.toString(newItm));
            }
        });
    }
    
    private void setActionEvent()  {
        txtInput.setOnAction(event -> {
            commitEdit(converter.fromString(txtInput.getText()));
        });
    }
    
    /**
     * Type T to String Converter
     */
    private final StringConverter<T> converter;
    /**
     * Text Field
     */
    private final TextField txtInput;
}
