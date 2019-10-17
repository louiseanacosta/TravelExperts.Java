/**
 * @author Liming Hong
 * Create on 2019 Jun 3
 */
package com.gn.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {

    private SimpleIntegerProperty ProductSupplierId;
    private SimpleStringProperty ProdName;
    private SimpleStringProperty SupName;

    public Products(int productSupplierId, String prodName, String supName) {
        ProductSupplierId = new SimpleIntegerProperty(productSupplierId);
        ProdName = new SimpleStringProperty(prodName);
        SupName = new SimpleStringProperty(supName);
    }

    public int getProductSupplierId() {
        return ProductSupplierId.get();
    }

    public SimpleIntegerProperty productSupplierIdProperty() {
        return ProductSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.ProductSupplierId.set(productSupplierId);
    }

    public String getProdName() {
        return ProdName.get();
    }

    public SimpleStringProperty prodNameProperty() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        this.ProdName.set(prodName);
    }

    public String getSupName() {
        return SupName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return SupName;
    }

    public void setSupName(String supName) {
        this.SupName.set(supName);
    }
}