/**
 * @author Liming Hong
 * Create on 2019 Jun 3
 */
package com.gn.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Suppliers {
    private SimpleIntegerProperty SupplierId;
    private SimpleStringProperty SupName;

    public int getSupplierId() {
        return SupplierId.get();
    }

    public SimpleIntegerProperty supplierIdProperty() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        this.SupplierId.set(supplierId);
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

    public Suppliers(int supplierId, String supName) {
        this.SupplierId = new SimpleIntegerProperty(supplierId);
        this.SupName = new SimpleStringProperty(supName);
    }

    @Override
    public String toString() {
        return "supplier{" +
                "SupplierId=" + SupplierId +
                ", SupName=" + SupName +
                '}';
    }
}
