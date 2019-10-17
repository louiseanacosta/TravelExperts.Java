/**
 * @author: Louise Acosta
 */

package com.gn.model;

import javafx.beans.property.SimpleStringProperty;

public class TripType {
    private SimpleStringProperty TripTypeId;
    private SimpleStringProperty TTName;

    // constructor
    public TripType(String tripTypeId, String TTName) {
        this.TripTypeId = new SimpleStringProperty(tripTypeId);
        this.TTName = new SimpleStringProperty(TTName);
    }

    // getters and setters
    public String getTripTypeId() {
        return TripTypeId.get();
    }

    public SimpleStringProperty tripTypeIdProperty() {
        return TripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.TripTypeId.set(tripTypeId);
    }

    public String getTTName() {
        return TTName.get();
    }

    public SimpleStringProperty TTNameProperty() {
        return TTName;
    }

    public void setTTName(String TTName) {
        this.TTName.set(TTName);
    }
}
