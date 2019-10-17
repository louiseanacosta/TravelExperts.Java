/**
 * @author: Louise Acosta
 */

package com.gn.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class BookingDetail {
    private SimpleIntegerProperty BookingDetailId;
    private SimpleIntegerProperty ItineraryNo;
    private SimpleObjectProperty<LocalDate> TripStart;
    private SimpleObjectProperty<LocalDate> TripEnd;
    private SimpleStringProperty Description;
    private SimpleStringProperty Destination;
    private SimpleDoubleProperty BasePrice;
    private SimpleDoubleProperty AgencyCommission;
    private SimpleIntegerProperty BookingId;
    private SimpleStringProperty RegionId;
    private SimpleStringProperty ClassId; // change to Region object if there's time
    private SimpleStringProperty FeeId; // change to Class object
    private SimpleIntegerProperty ProductSupplierId; // change to product supplier object

    // constructor
    public BookingDetail(int bookingDetailId, int itineraryNo, LocalDate tripStart, LocalDate tripEnd,
                         String description, String destination, double basePrice, double agencyCommission,
                         int bookingId, String regionId, String classId, String feeId, Integer productSupplierId) {
        BookingDetailId = new SimpleIntegerProperty(bookingDetailId);
        ItineraryNo = new SimpleIntegerProperty(itineraryNo);
        TripStart = new SimpleObjectProperty<LocalDate>(tripStart);
        TripEnd = new SimpleObjectProperty<LocalDate>(tripEnd);
        Description = new SimpleStringProperty(description);
        Destination = new SimpleStringProperty(destination);
        BasePrice = new SimpleDoubleProperty(basePrice);
        AgencyCommission = new SimpleDoubleProperty(agencyCommission);
        BookingId = new SimpleIntegerProperty(bookingId);
        RegionId = new SimpleStringProperty(regionId);
        ClassId = new SimpleStringProperty(classId);
        FeeId = new SimpleStringProperty(feeId);
        ProductSupplierId = new SimpleIntegerProperty(productSupplierId);
    }

    // getters and setters

    public int getBookingDetailId() {
        return BookingDetailId.get();
    }

    public SimpleIntegerProperty bookingDetailIdProperty() {
        return BookingDetailId;
    }

    public void setBookingDetailId(int bookingDetailId) {
        this.BookingDetailId.set(bookingDetailId);
    }

    public int getItineraryNo() {
        return ItineraryNo.get();
    }

    public SimpleIntegerProperty itineraryNoProperty() {
        return ItineraryNo;
    }

    public void setItineraryNo(int itineraryNo) {
        this.ItineraryNo.set(itineraryNo);
    }

    public LocalDate getTripStart() {
        return TripStart.get();
    }

    public SimpleObjectProperty<LocalDate> tripStartProperty() {
        return TripStart;
    }

    public void setTripStart(LocalDate tripStart) {
        this.TripStart.set(tripStart);
    }

    public LocalDate getTripEnd() {
        return TripEnd.get();
    }

    public SimpleObjectProperty<LocalDate> tripEndProperty() {
        return TripEnd;
    }

    public void setTripEnd(LocalDate tripEnd) {
        this.TripEnd.set(tripEnd);
    }

    public String getDescription() {
        return Description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description.set(description);
    }

    public String getDestination() {
        return Destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return Destination;
    }

    public void setDestination(String destination) {
        this.Destination.set(destination);
    }

    public double getBasePrice() {
        return BasePrice.get();
    }

    public SimpleDoubleProperty basePriceProperty() {
        return BasePrice;
    }

    public void setBasePrice(double basePrice) {
        this.BasePrice.set(basePrice);
    }

    public double getAgencyCommission() {
        return AgencyCommission.get();
    }

    public SimpleDoubleProperty agencyCommissionProperty() {
        return AgencyCommission;
    }

    public void setAgencyCommission(double agencyCommission) {
        this.AgencyCommission.set(agencyCommission);
    }

    public int getBookingId() {
        return BookingId.get();
    }

    public SimpleIntegerProperty bookingIdProperty() {
        return BookingId;
    }

    public void setBookingId(int bookingId) {
        this.BookingId.set(bookingId);
    }

    public String getRegionId() {
        return RegionId.get();
    }

    public SimpleStringProperty regionIdProperty() {
        return RegionId;
    }

    public void setRegionId(String regionId) {
        this.RegionId.set(regionId);
    }

    public String getClassId() {
        return ClassId.get();
    }

    public SimpleStringProperty classIdProperty() {
        return ClassId;
    }

    public void setClassId(String classId) {
        this.ClassId.set(classId);
    }

    public String getFeeId() {
        return FeeId.get();
    }

    public SimpleStringProperty feeIdProperty() {
        return FeeId;
    }

    public void setFeeId(String feeId) {
        this.FeeId.set(feeId);
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
