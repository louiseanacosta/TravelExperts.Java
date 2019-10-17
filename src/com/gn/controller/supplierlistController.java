/**
 * @author Liming Hong
 * Create on 2019 Jun 3
 */
package com.gn.controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.gn.model.Suppliers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static com.gn.data.DataValidation.validateHasValue;
import static com.gn.data.SuppliersDB.addNewSupplier;

import static com.gn.data.SuppliersDB.*;

public class supplierlistController {
    @FXML private TabPane supplierlistTabPane;
    @FXML private Tab supplierlistTab;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button btnEdt;
    @FXML private TableView<Suppliers> tvSuppliers;
    @FXML private TableColumn<Suppliers, Integer> tcID;
    @FXML private TableColumn<Suppliers, String> tcName;
    @FXML private Tab tabEditSupplier;
    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private Button btnSaveNew;
    @FXML private TextField tfIDNew;
    @FXML private TextField tfNameNew;


    @FXML void btnSaveNewAction(ActionEvent event) {
        boolean valid = true;
        if(!validateHasValue(tfName))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Package Name is empty", ButtonType.OK).show();
        }
        if(valid) {
            Suppliers supplier = new Suppliers(0, tfName.getText());
            addNewSupplier(supplier);
        }
    }

    @FXML
    void btnEdtAction(ActionEvent event) {
        Suppliers s = tvSuppliers.getSelectionModel().getSelectedItem();
        tfID.setText(String.valueOf(s.getSupplierId()));
        tfName.setText(s.getSupName());
        supplierlistTabPane.getSelectionModel().select(tabEditSupplier);

    }

    @FXML
    void btnSaveAction(ActionEvent event) {
        Suppliers supplier = new Suppliers(tvSuppliers.getSelectionModel().getSelectedItem().getSupplierId(),tfName.getText());
        updateSupplier(supplier);
        loadSuppliers(tvSuppliers);
    }

    @FXML
    void btnDeleteAction(ActionEvent event) {
        deleteSupplier(tvSuppliers.getSelectionModel().getSelectedItem().getSupplierId());
        loadSuppliers(tvSuppliers);
        supplierlistTabPane.getSelectionModel().select(supplierlistTab);
    }

    @FXML
    void initialize() {
        tcID.setCellValueFactory(cellData->cellData.getValue().supplierIdProperty().asObject());
        tcName.setCellValueFactory(cellData->cellData.getValue().supNameProperty());
        loadSuppliers(tvSuppliers);
    }
}
