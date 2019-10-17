/**
 * @author Liming Hong
 * Create on 2019 Jun 3
 */

package com.gn.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import com.gn.data.PackagesDB;
import com.gn.data.ProductsDB;
import com.gn.model.Packages;
import com.gn.model.Products;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static com.gn.data.DataValidation.*;
import static com.gn.data.DataValidation.validateHasDate;


public class packagelistController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Button btnEdt;
    @FXML private TableView<Packages> tvPackages;
    @FXML private TableColumn<Packages, Integer> tcID;
    @FXML private TableColumn<Packages, String> tcName;
    @FXML private TableColumn<Packages, Date> tcStartDate;
    @FXML private TableColumn<Packages, Date> tcEndDate;
    @FXML private TableColumn<Packages, String> tcDesciption;
    @FXML private TableColumn<Packages, Double> tcBasePrice;
    @FXML private TableColumn<Packages, Double> tcCommission;
    @FXML private Tab tabEditPackage;
    @FXML private Tab packagelistTab;
    @FXML private Tab tabPackageList;
    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private DatePicker tfStartDate;
    @FXML private DatePicker tfEndDate;
    @FXML private TextField tfDesc;
    @FXML private TextField tfBasePrice;
    @FXML private TextField tfAgencyCommission;
    @FXML private TabPane PackagelistTabPane;
    @FXML private TextField tfIDNew;
    @FXML private Button btnSaveNew;
    @FXML private TextField tfNameNew;
    @FXML private DatePicker tfStartDateNew;
    @FXML private DatePicker tfEndDateNew;
    @FXML private TextField tfDescNew;
    @FXML private TextField tfBasePriceNew;
    @FXML private TextField tfAgencyCommissionNew;
    @FXML private TableView<Products> tvProductsInPackage;
    @FXML private TableColumn<Products, Integer> colProductSupplierId;
    @FXML private TableColumn<Products, String> colProdName;
    @FXML private TableColumn<Products, String> colSupName;

    //Edit button
    @FXML
    void btnEdtAction(ActionEvent event) {

        Packages p = tvPackages.getSelectionModel().getSelectedItem();
        if(p==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a package", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            DecimalFormat df = new DecimalFormat("#.00");

            tfID.setText(String.valueOf(p.getPackageId()));
            tfName.setText(p.getPkgName());
            tfDesc.setText(p.getPkgDesc());
            tfStartDate.setValue(LocalDate.parse(p.getPkgStartDate().toString()));
            tfEndDate.setValue(LocalDate.parse(p.getPkgEndDate().toString()));
            tfBasePrice.setText(String.valueOf(df.format(p.getPkgBasePrice())));
            tfAgencyCommission.setText(String.valueOf(df.format(p.getPkgAgencyCommission())));

            PackagelistTabPane.getSelectionModel().select(tabEditPackage);

            loadPackages();
        }
    }
    //save button
    @FXML
    void btnSaveAction(ActionEvent event) {


        boolean valid = true;
        if(!validateHasValue(tfName))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Package Name is empty", ButtonType.OK).showAndWait();
        }

        if(!validateHasDate(tfStartDate))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Please pick a start date", ButtonType.OK).showAndWait();
        }
        if(!validateHasDate(tfEndDate))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Please pick a end date", ButtonType.OK).showAndWait();
        }

        if (valid) {
            Packages pack = new Packages(
                    Integer.parseInt(tfID.getText()),
                    tfName.getText(),
                    java.util.Date.from(tfStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    java.util.Date.from(tfEndDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    tfDesc.getText(),
                    Double.valueOf(tfBasePrice.getText()),
                    Double.valueOf(tfAgencyCommission.getText()
                    ));
            PackagesDB.updatePackage(pack);
            PackagesDB.loadPackages(tvPackages);
            PackagelistTabPane.getSelectionModel().select(tabPackageList);
        }

    }



    //Delete button
    @FXML
    void btnDeleteAction(ActionEvent event) {
        PackagesDB.deletePackage(tvPackages.getSelectionModel().getSelectedItem().getPackageId());
        PackagesDB.loadPackages(tvPackages);
        PackagelistTabPane.getSelectionModel().select(packagelistTab);
    }

    @FXML
    void btnSaveNewClicked(ActionEvent event) {
        // todo: create Pcakges obj from tfs

        boolean valid = true;
        if(!validateHasValue(tfNameNew))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Package Name is empty", ButtonType.OK).showAndWait();
        }
        if(!textNumeric(tfBasePriceNew))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Please enter a valid number for BasePrice", ButtonType.OK).showAndWait();
        }
        if(!textNumeric(tfBasePriceNew))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Please enter a valid number for Agent commission", ButtonType.OK).showAndWait();
        }
        if(!validateHasDate(tfStartDateNew))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Please pick a start date", ButtonType.OK).showAndWait();
        }
        if(!validateHasDate(tfEndDateNew))
        {
            valid = false;
            new Alert(Alert.AlertType.ERROR, "Please pick a end date", ButtonType.OK).showAndWait();
        }

        if (valid) {
            Packages pack = new Packages(
                    0,
                    tfNameNew.getText(),
                    java.util.Date.from(tfStartDateNew.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    java.util.Date.from(tfEndDateNew.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    tfDescNew.getText(),
                    Double.valueOf(tfBasePriceNew.getText()),
                    Double.valueOf(tfAgencyCommissionNew.getText())
            );
            PackagesDB.addNewPackage(pack);
            PackagelistTabPane.getSelectionModel().select(tabPackageList);
        }

    }


    @FXML
    void initialize() {

        // package list
        tcID.setCellValueFactory(cellData->cellData.getValue().packageIdProperty().asObject());
        tcName.setCellValueFactory(cellData->cellData.getValue().pkgNameProperty());
        tcStartDate.setCellValueFactory(cellData-> new SimpleObjectProperty(cellData.getValue().getPkgStartDate()));
        tcEndDate.setCellValueFactory(cellData-> new SimpleObjectProperty(cellData.getValue().getPkgEndDate()));
        tcDesciption.setCellValueFactory(cellData->cellData.getValue().pkgDescProperty());
        tcBasePrice.setCellValueFactory(cellData->cellData.getValue().pkgBasePriceProperty().asObject());
        tcCommission.setCellValueFactory(cellData->cellData.getValue().pkgAgencyCommissionProperty().asObject());


        // products in package
        colProductSupplierId.setCellValueFactory(cellData->cellData.getValue().productSupplierIdProperty().asObject());
        colProdName.setCellValueFactory(cellData->cellData.getValue().prodNameProperty());
        colSupName.setCellValueFactory(cellData->cellData.getValue().supNameProperty());

        loadPackages();


    }

    public void loadPackages(){
        PackagesDB.loadPackages(tvPackages);
        if(!tfID.getText().isEmpty()) {
            int selectedPackageId = Integer.parseInt(tfID.getText());
            ObservableList<Products> productsIncludedInPackage = ProductsDB.getProductsIncludedInPackage(selectedPackageId);
            tvProductsInPackage.setItems(productsIncludedInPackage);
        }
    }

}
