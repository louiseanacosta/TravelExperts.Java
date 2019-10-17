/**
 * @author: Louise Acosta
 */

package com.gn.model;

import javafx.beans.property.SimpleIntegerProperty;

public class Packages_products_suppliers {
    private SimpleIntegerProperty PackageId;
    private SimpleIntegerProperty ProductSupplierId;

    // constructor

    public Packages_products_suppliers(Integer packageId, Integer productSupplierId) {
        this.PackageId = new SimpleIntegerProperty(packageId);
        this.ProductSupplierId = new SimpleIntegerProperty(productSupplierId);
    }

    // getters and setters

    public int getPackageId() {
        return PackageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        this.PackageId.set(packageId);
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
}
