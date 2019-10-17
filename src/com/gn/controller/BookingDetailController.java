/**
 *
 * @author: Louise Acosta
 * purpose: View all booking details
 *
 * */

package com.gn.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.gn.data.BookingDetailDB;
import com.gn.model.BookingDetail;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookingDetailController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Tab tabBookingDetail;
    @FXML private TextField etSearchBookingDetail;
    @FXML private TableView<BookingDetail> tvBookingDetails;
    @FXML private TableColumn<BookingDetail, Integer> colBookingDetailId;
    @FXML private TableColumn<BookingDetail, Integer> colIteneraryNo;
    @FXML private TableColumn<BookingDetail, LocalDate> colTripStart;
    @FXML private TableColumn<BookingDetail, LocalDate> colTripEnd;
    @FXML private TableColumn<BookingDetail, String> colDestination;
    @FXML private TableColumn<BookingDetail, Double> colBasePrice;
    @FXML private TableColumn<BookingDetail, Integer> colBookingIdDetail;
    @FXML private TableColumn<BookingDetail, String> colDescription;
    @FXML private TableColumn<BookingDetail, Double> colAgencyCommission;
    @FXML private TableColumn<BookingDetail, String> colRegionId;
    @FXML private TableColumn<BookingDetail, String> colClassId;
    @FXML private TableColumn<BookingDetail, String> colFeeId;
    @FXML private TableColumn<BookingDetail, Integer> colProductSupplierId;


    @FXML
    void initialize() {
        // set data columns of booking list table view
        colBookingDetailId.setCellValueFactory(cellData -> cellData.getValue().bookingDetailIdProperty().asObject());
        colIteneraryNo.setCellValueFactory(cellData -> cellData.getValue().itineraryNoProperty().asObject());
        colTripStart.setCellValueFactory(cellData -> cellData.getValue().tripStartProperty());
        colTripEnd.setCellValueFactory(cellData -> cellData.getValue().tripEndProperty());
        colDestination.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        colDescription.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        colBasePrice.setCellValueFactory(cellData -> cellData.getValue().basePriceProperty().asObject());
        colAgencyCommission.setCellValueFactory(cellData -> cellData.getValue().agencyCommissionProperty().asObject());
        colBookingIdDetail.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
        colRegionId.setCellValueFactory(cellData -> cellData.getValue().regionIdProperty());
        colClassId.setCellValueFactory(cellData -> cellData.getValue().classIdProperty());
        colFeeId.setCellValueFactory(cellData -> cellData.getValue().feeIdProperty());
        colProductSupplierId.setCellValueFactory(cellData -> cellData.getValue().productSupplierIdProperty().asObject());

        // display booking details list
        ObservableList<BookingDetail> bookingDetails = BookingDetailDB.getBookingDetail(0);
        tvBookingDetails.setItems(bookingDetails);

    }
}
