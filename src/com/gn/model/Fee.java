package com.gn.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Fee {
    private SimpleStringProperty FeeId;
    private SimpleStringProperty FeeName;
    private SimpleDoubleProperty FeeAmt;
    private SimpleStringProperty FeeDesc;

    // constructor

    public Fee(String feeId, String feeName, Double feeAmt, String feeDesc) {
        this.FeeId = new SimpleStringProperty(feeId);
        this.FeeName = new SimpleStringProperty(feeName);
        this.FeeAmt = new SimpleDoubleProperty(feeAmt);
        this.FeeDesc = new SimpleStringProperty(feeDesc);
    }

    // getters and setters

    public String getFeeId() {
        return FeeId.get();
    }

    public SimpleStringProperty feeIdProperty() {
        return FeeId;
    }

    public void setFeeId(String feeId) {
        this.FeeId.set(feeId);
    }

    public String getFeeName() {
        return FeeName.get();
    }

    public SimpleStringProperty feeNameProperty() {
        return FeeName;
    }

    public void setFeeName(String feeName) {
        this.FeeName.set(feeName);
    }

    public double getFeeAmt() {
        return FeeAmt.get();
    }

    public SimpleDoubleProperty feeAmtProperty() {
        return FeeAmt;
    }

    public void setFeeAmt(double feeAmt) {
        this.FeeAmt.set(feeAmt);
    }

    public String getFeeDesc() {
        return FeeDesc.get();
    }

    public SimpleStringProperty feeDescProperty() {
        return FeeDesc;
    }

    public void setFeeDesc(String feeDesc) {
        this.FeeDesc.set(feeDesc);
    }
}
