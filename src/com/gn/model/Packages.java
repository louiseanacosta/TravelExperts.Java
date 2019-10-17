/**
 * @author Liming Hong
 * Create on 2019 Jun 3
 */
package com.gn.model;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Packages {
    private SimpleIntegerProperty PackageId;
    private SimpleStringProperty PkgName;
    private ObjectProperty<Date> PkgStartDate;
    private ObjectProperty<Date> PkgEndDate;
    private SimpleStringProperty PkgDesc;
    private SimpleDoubleProperty PkgBasePrice;
    private SimpleDoubleProperty PkgAgencyCommission;
    String pattern = "yyyy-MM-dd";

    public int getPackageId() {
        return PackageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        this.PackageId.set(packageId);
    }

    public String getPkgName() {
        return PkgName.get();
    }

    public SimpleStringProperty pkgNameProperty() {
        return PkgName;
    }

    public void setPkgName(String pkgName) {
        this.PkgName.set(pkgName);
    }

    public Date getPkgStartDate() {
        return PkgStartDate.get();
    }

    public ObjectProperty<Date> pkgStartDateProperty() {
        return PkgStartDate;
    }

    public void setPkgStartDate(Date pkgStartDate) {
        this.PkgStartDate.set(pkgStartDate);
    }

    public Date getPkgEndDate() {
        return PkgEndDate.get();
    }

    public ObjectProperty<Date> pkgEndDateProperty() {
        return PkgEndDate;
    }

    public void setPkgEndDate(Date pkgEndDate) {
        this.PkgEndDate.set(pkgEndDate);
    }

    public String getPkgDesc() {
        return PkgDesc.get();
    }

    public SimpleStringProperty pkgDescProperty() {
        return PkgDesc;
    }

    public void setPkgDesc(String pkgDesc) {
        this.PkgDesc.set(pkgDesc);
    }

    public double getPkgBasePrice() {
        return PkgBasePrice.get();
    }

    public SimpleDoubleProperty pkgBasePriceProperty() {
        return PkgBasePrice;
    }

    public void setPkgBasePrice(double pkgBasePrice) {
        this.PkgBasePrice.set(pkgBasePrice);
    }

    public double getPkgAgencyCommission() {
        return PkgAgencyCommission.get();
    }

    public SimpleDoubleProperty pkgAgencyCommissionProperty() {
        return PkgAgencyCommission;
    }

    public void setPkgAgencyCommission(double pkgAgencyCommission) {
        this.PkgAgencyCommission.set(pkgAgencyCommission);
    }

    public Packages(int packageId, String pkgName, Date pkgStartDate, Date pkgEndDate, String pkgDesc, double pkgBasePrice, double pkgAgencyCommission) {
        this.PackageId = new SimpleIntegerProperty(packageId);
        this.PkgName = new SimpleStringProperty(pkgName);
        this.PkgStartDate = new SimpleObjectProperty<>(this, "PkgStartDate");
        this.PkgStartDate.set(pkgStartDate);
        this.PkgEndDate = new SimpleObjectProperty<>(this, "PkgEndDate");
        this.PkgEndDate.set(pkgEndDate);
        this.PkgDesc = new SimpleStringProperty(pkgDesc);
        this.PkgBasePrice = new SimpleDoubleProperty(pkgBasePrice);
        this.PkgAgencyCommission = new SimpleDoubleProperty(pkgAgencyCommission);
    }
}
