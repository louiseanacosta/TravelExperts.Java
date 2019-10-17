/**
 * @author: Louise Acosta
 */

package com.gn.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Booking {
    private SimpleIntegerProperty BookingId;
    private SimpleObjectProperty<LocalDate> BookingDate;
    private SimpleStringProperty BookingNo;
    private SimpleIntegerProperty TravelerCount;
    private SimpleIntegerProperty CustomerId;
    private SimpleStringProperty TripTypeId;
    private SimpleIntegerProperty PackageId;

    // constructor
    public Booking() {
    }

    public Booking(int bookingId, LocalDate bookingDate,
                   String bookingNo, int travelerCount,
                   int customerId, String tripTypeId, int packageId) {
        this.BookingId = new SimpleIntegerProperty(bookingId);
        this.BookingDate = new SimpleObjectProperty<>(bookingDate);
        this.BookingNo = new SimpleStringProperty(bookingNo);
        this.TravelerCount = new SimpleIntegerProperty((int) travelerCount);
        this.CustomerId = new SimpleIntegerProperty(customerId);
        this.TripTypeId = new SimpleStringProperty(tripTypeId);
        this.PackageId = new SimpleIntegerProperty(packageId);
    }

    // getters and setters
    public int getBookingId() {
        return BookingId.get();
    }

    public SimpleIntegerProperty bookingIdProperty() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        this.BookingId.set(bookingId);
    }

    public LocalDate getBookingDate() {
        return BookingDate.get();
    }

    public SimpleObjectProperty<LocalDate> bookingDateProperty() {
        return BookingDate;
    }

    public void setBookingDate(SimpleObjectProperty<LocalDate> bookingDate) {
        this.BookingDate = bookingDate;
    }

    public String getBookingNo() {
        return BookingNo.get();
    }

    public SimpleStringProperty bookingNoProperty() {
        return BookingNo;
    }

    public void setBookingNo(String bookingNo) {
        this.BookingNo.set(bookingNo);
    }

    public int getTravelerCount() {
        return TravelerCount.get();
    }

    public SimpleIntegerProperty travelerCountProperty() {
        return TravelerCount;
    }

    public void setTravelerCount(int travelerCount) {
        this.TravelerCount.set(travelerCount);
    }

    public int getCustomerId() {
        return CustomerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId.set(customerId);
    }

    public String getTripTypeId() {
        return TripTypeId.get();
    }

    public SimpleStringProperty tripTypeIdProperty() {
        return TripTypeId;
    }

    public void setTripTypeId(String tripTypeId) {
        this.TripTypeId.set(tripTypeId);
    }

    public int getPackageId() {
        return PackageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }

    public void setPackageId(int packageId) {
        this.PackageId.set(packageId);
    }

    @Override
    public String toString() {
        return bookingIdProperty().get() + "";
    }
}
