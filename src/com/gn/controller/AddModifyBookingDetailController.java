/**
 *
 * @author: Louise Acosta
 * purpose: add/modify booking detail - popup will be displayed when
 *          adding or updating a booking from the booking tab
 *
 */

package com.gn.controller;

import java.awt.print.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.gn.data.*;
import com.gn.model.Booking;
import com.gn.model.BookingDetail;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddModifyBookingDetailController {
    BookingDetail newBookingDetail;

    @FXML private ResourceBundle resources;
    @FXML private Label lblAddModifyBookingDetails;
    @FXML private Label lblBookingDetailId;
    @FXML private URL location;
    @FXML private TextField etBookingDetailIdModify;
    @FXML private TextField etItineraryNoModify;
    @FXML private DatePicker dpTripStartModify;
    @FXML private DatePicker dpTripEndModify;
    @FXML private TextField etDestinationModify;
    @FXML private TextField etDescriptionModify;
    @FXML private TextField etBasePriceModify;
    @FXML private TextField etAgencyCommissionModify;
    @FXML private ComboBox<String> cbRegionIdModify;
    @FXML private ComboBox<String> cbClassIdModify;
    @FXML private ComboBox<String> cbFeeIdModify;
    @FXML private ComboBox<Integer> cbProductSupplierIdModify;
    @FXML private Button btnAddModifyBookingDetail;
    @FXML private Button btnDeleteBookingDetail;

    public boolean isAddingBookingDetail; // indicates whether it is ADD/MODIFY
    public boolean isAddingBookingDetailForNewBooking;
    public ObservableList<BookingDetail> bookingDetailList;

    // on load
    @FXML
    void initialize() {
        loadComboBoxes();
    }

    // user clicks on add button
    @FXML
    void btnAddModifyBookingDetailAction(ActionEvent event) {
        if (isAddingBookingDetail && !isAddingBookingDetailForNewBooking) { // add to existing booking

        }
        else if (!isAddingBookingDetail && !isAddingBookingDetailForNewBooking) { // update to existing booking
            updateBookingDetailToExistingBooking();


        } else if (!isAddingBookingDetail && isAddingBookingDetailForNewBooking) { // add to new booking
            addBookingDetailToNewBooking();
        }

    }


    // user clicks on delete button to delete booking detail
    @FXML
    void btnDeleteBookingDetailAction(ActionEvent event) {
        deleteBookingDetail();
    }


    /**
     *
     * FUNCTIONS
     *
     * */


    private void updateBookingDetailToExistingBooking() {
        // get selected booking detail id
        int bookingDetailId = Integer.parseInt(etBookingDetailIdModify.getText());

        // create object with new values
        BookingDetail updatedBookingDetail = new BookingDetail(
                0,
                Integer.parseInt(etItineraryNoModify.getText()),
                dpTripStartModify.getValue(),
                dpTripEndModify.getValue(),
                etDescriptionModify.getText(),
                etDestinationModify.getText(),
                Double.parseDouble(etBasePriceModify.getText()),
                Double.parseDouble(etAgencyCommissionModify.getText()),
                0, // bookingid will not be updated
                cbRegionIdModify.getSelectionModel().getSelectedItem(),
                cbClassIdModify.getSelectionModel().getSelectedItem(),
                cbFeeIdModify.getSelectionModel().getSelectedItem(),
                cbProductSupplierIdModify.getSelectionModel().getSelectedItem()
        );

        // update
        int rowsUpdated = BookingDetailDB.updateBookingdDetail(bookingDetailId, updatedBookingDetail);
        confirmUpdate(bookingDetailId, rowsUpdated);
    }

    private void confirmUpdate(int bookingDetailId, int rowsUpdated) {
        // close window when update successful
        if (rowsUpdated > 0) {
            // confirmation alert pop to confirm changes were saved
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Booking detail ID " + bookingDetailId +
                    " updated successfully", ButtonType.OK);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                // close window
                Stage stage = (Stage) btnAddModifyBookingDetail.getScene().getWindow();
                stage.close();
            }
        } else {
            // error message, update unsuccessful
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error updating", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    private void addBookingDetailToNewBooking() {
        // create new booking detail object
        BookingDetail newBookingDetail = new BookingDetail(
                0,
                Integer.parseInt(etItineraryNoModify.getText()),
                dpTripStartModify.getValue(),
                dpTripEndModify.getValue(),
                etDescriptionModify.getText(),
                etDestinationModify.getText(),
                Double.parseDouble(etBasePriceModify.getText()),
                Double.parseDouble(etAgencyCommissionModify.getText()),
                0, // new bookingId will be determined when new booking saved
                cbRegionIdModify.getSelectionModel().getSelectedItem(),
                cbClassIdModify.getSelectionModel().getSelectedItem(),
                cbFeeIdModify.getSelectionModel().getSelectedItem(),
                cbProductSupplierIdModify.getSelectionModel().getSelectedItem()
        );

        // add object to list
        this.bookingDetailList.add(newBookingDetail);

        // close window
        Stage stage = (Stage) btnAddModifyBookingDetail.getScene().getWindow();
        stage.close();
    }

    private void deleteBookingDetail() {
        // get booking detail id
        int bookingDetailId = Integer.parseInt(etBookingDetailIdModify.getText());
        // delete booking detail
        int rowsUpdated = BookingDetailDB.deleteBookingDetail(bookingDetailId);
        // check
        if (rowsUpdated > 0) {
            // confirmation alert pop to confirm changes were saved
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Booking detail ID " + bookingDetailId +
                    " deleted successfully", ButtonType.OK);
            alert.showAndWait();
            // close window
            Stage stage = (Stage) btnAddModifyBookingDetail.getScene().getWindow();
            stage.close();

            // TODO: UPDATE TABLE VIEW IN BOOKING CONTROLLER
        } else {
            // error message - delete failed
            Alert alert = new Alert(Alert.AlertType.ERROR, "Delete unsuccessful", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    public void isAddBookingDetail(boolean isAdd) {
        if (isAdd == true) { // add bookingDetail
            btnDeleteBookingDetail.setVisible(false); // hide delete button
            lblAddModifyBookingDetails.setText("Add Booking Detail");
            isAddingBookingDetail = true;
            isAddingBookingDetailForNewBooking = false;

        } else if (isAdd == false) { // modify bookingDetail
            btnDeleteBookingDetail.setVisible(true); // show delete button
            isAddingBookingDetail = false;
            isAddingBookingDetailForNewBooking = false;

        }
    }

    public void isAddBookingDetailForNewBooking(boolean isAddingForNew) {
        if (isAddingForNew == true) {
            btnDeleteBookingDetail.setVisible(false); // hide delete button
            lblAddModifyBookingDetails.setText("Add Booking Detail To New Booking");
            isAddingBookingDetail = false;
            isAddingBookingDetailForNewBooking = true;
            lblBookingDetailId.setVisible(false);
            etBookingDetailIdModify.setVisible(false);
        }
    }

    // load all combo boxes
    private void loadComboBoxes() {
        // fee id
        ObservableList<String> feeIds = FeeDB.getFeeId();
        cbFeeIdModify.setItems(feeIds);

        // class id
        ObservableList<String> classIds = ClassesDB.getClassId();
        cbClassIdModify.setItems(classIds);

        // region id
        ObservableList<String> regionIds = RegionDB.getRegionId();
        cbRegionIdModify.setItems(regionIds);

        // product supplier id
        ObservableList<Integer> productSupplierIds = Packages_products_suppliersDB.getProductSupplierId();
        cbProductSupplierIdModify.setItems(productSupplierIds);

    }

    public void setList(ObservableList<BookingDetail> bookingDetailList) {
        this.bookingDetailList = bookingDetailList;
    }

    // function to get selected bookingdetail in booking controller & set values in input fields
    public void getSelectedBookingDetail(BookingDetail bookingDetail) {
        etBookingDetailIdModify.setText(String.valueOf(bookingDetail.getBookingDetailId()));
        etItineraryNoModify.setText(String.valueOf(bookingDetail.getItineraryNo()));
        dpTripStartModify.setValue(bookingDetail.getTripStart());
        dpTripEndModify.setValue(bookingDetail.getTripEnd());
        etDescriptionModify.setText(bookingDetail.getDescription());
        etDestinationModify.setText(bookingDetail.getDestination());
        etBasePriceModify.setText(String.valueOf(bookingDetail.getBasePrice()));
        etAgencyCommissionModify.setText(String.valueOf(bookingDetail.getAgencyCommission()));
        cbRegionIdModify.setValue(bookingDetail.getRegionId());
        cbClassIdModify.setValue(bookingDetail.getClassId());
        cbFeeIdModify.setValue(bookingDetail.getFeeId());
        cbProductSupplierIdModify.setValue(bookingDetail.getProductSupplierId());
    }


}
