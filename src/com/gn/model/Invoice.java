package com.gn.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Invoice {

    private SimpleIntegerProperty customerId;
    private SimpleStringProperty custFirstName;
    private SimpleStringProperty custLastName;
    private SimpleStringProperty custEmail;
    private SimpleStringProperty bookingNo;
    private SimpleIntegerProperty ItineraryNo;
    private SimpleStringProperty Descrption;
    private SimpleStringProperty Destination;
    private SimpleObjectProperty<LocalDate> TripStart;
    private SimpleObjectProperty<LocalDate> TripEnd;
    private SimpleDoubleProperty BasePrice;


    //constructor
    public Invoice(int customerId, String custFirstName,
                   String custLastName, String custEmail, String bookingNo,
                   int itineraryNo, String descrption,
                   String destination, LocalDate tripStart,
                   LocalDate tripEnd, double basePrice) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.custFirstName = new SimpleStringProperty(custFirstName);
        this.custLastName = new SimpleStringProperty(custLastName);
        this.custEmail = new SimpleStringProperty(custEmail);
        this.bookingNo = new SimpleStringProperty(bookingNo);
        ItineraryNo = new SimpleIntegerProperty(itineraryNo);
        Descrption = new SimpleStringProperty(descrption);
        Destination = new SimpleStringProperty(destination);
        TripStart = new SimpleObjectProperty<>(tripStart);
        TripEnd = new SimpleObjectProperty<>(tripEnd);
        BasePrice = new SimpleDoubleProperty(basePrice);

    }

    public int getCustomerId() {
        return customerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return customerId;
    }

    public String getCustFirstName() {
        return custFirstName.get();
    }

    public SimpleStringProperty custFirstNameProperty() {
        return custFirstName;
    }

    public String getCustLastName() {
        return custLastName.get();
    }

    public SimpleStringProperty custLastNameProperty() {
        return custLastName;
    }

    public String getCustEmail() {
        return custEmail.get();
    }

    public SimpleStringProperty custEmailProperty() {
        return custEmail;
    }

    public String getBookingNo() {
        return bookingNo.get();
    }

    public SimpleStringProperty bookingNoProperty() {
        return bookingNo;
    }

    public int getItineraryNo() {
        return ItineraryNo.get();
    }

    public SimpleIntegerProperty itineraryNoProperty() {
        return ItineraryNo;
    }

    public String getDescrption() {
        return Descrption.get();
    }

    public SimpleStringProperty descrptionProperty() {
        return Descrption;
    }

    public String getDestination() {
        return Destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return Destination;
    }

    public LocalDate getTripStart() {
        return TripStart.get();
    }

    public SimpleObjectProperty<LocalDate> tripStartProperty() {
        return TripStart;
    }

    public LocalDate getTripEnd() {
        return TripEnd.get();
    }

    public SimpleObjectProperty<LocalDate> tripEndProperty() {
        return TripEnd;
    }

    public Double getBasePrice() {
        return BasePrice.get();
    }

    public SimpleDoubleProperty basePriceProperty() {
        return BasePrice;
    }

}
