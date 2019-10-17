/**
 *
 * @author: Louise Acosta
 * purpose: CRUD functionality for bookings
 *
 */

package com.gn.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import com.gn.data.*;
import com.gn.model.Booking;
import com.gn.model.BookingDetail;
import com.gn.model.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookingController {
    @FXML private TabPane tpBookings;
    @FXML private Tab tabBooking;
    @FXML private Tab tabEditBooking;
    @FXML private Tab tabNewBooking;

    /******************************** BOOKING LIST TAB CONTROLS *****************************/
    @FXML private TextField etSearchBooking;
    @FXML private TableView<Booking> tvBookings;
    @FXML private TableColumn<Booking, Integer> colBookingId;
    @FXML private TableColumn<Booking, LocalDate> colBookingDate;
    @FXML private TableColumn<Booking, String> colBookingNo;
    @FXML private TableColumn<Booking, Integer> colTravelerCount;
    @FXML private TableColumn<Booking, Integer> colCustomerId;
    @FXML private TableColumn<Booking, String> colTripTypeId;
    @FXML private TableColumn<Booking, Integer> colPackageId;

    /******************************** EDIT BOOKING TAB CONTROLS *****************************/

    @FXML private Button btnUpdateBooking;
    @FXML private Button btnDeleteBooking;
    @FXML private ComboBox<Booking> cbBookingIdEdit;
    @FXML private DatePicker dpBookingDateEdit;
    @FXML private TextField etBookingNoEdit;
    @FXML private TextField etTravelerCountEdit;
    @FXML private ComboBox<Integer> cbCustomerIdEdit;
    @FXML private ComboBox<String> cbTripTypeIdEdit;
    @FXML private ComboBox<Integer> cbPackageIdEdit;
    @FXML private TableView<BookingDetail> tvBookingDetailEdit;
    @FXML private TableColumn<BookingDetail, Integer> colBookingDetailIdEdit;
    @FXML private TableColumn<BookingDetail, Integer> colItineraryNoEdit;
    @FXML private TableColumn<BookingDetail, LocalDate> colTripStartEdit;
    @FXML private TableColumn<BookingDetail, LocalDate> colTripEndEdit;
    @FXML private TableColumn<BookingDetail, String> colDestinationEdit;
    @FXML private TableColumn<BookingDetail, String> colDescriptionEdit;
    @FXML private TableColumn<BookingDetail, Double> colBasePriceEdit;
    @FXML private TableColumn<BookingDetail, Double> colAgencyCommissionEdit;
    @FXML private TableColumn<BookingDetail, String> colRegionIdEdit;
    @FXML private TableColumn<BookingDetail, String> colClassIdEdit;
    @FXML private TableColumn<BookingDetail, String> colFeeIdEdit;
    @FXML private TableColumn<BookingDetail, Integer> colProductSupplierIdEdit;
    @FXML private Button btnAddBookingDetailld;

    /******************************** CREATE NEW TAB CONTROLS *****************************/
    @FXML private Button btnSaveNewBooking;
    @FXML private TextField etBookingIdAdd;
    @FXML private DatePicker dpBookingDateAdd;
    @FXML private TextField etBookingNoAdd;
    @FXML private TextField etTravelerCountAdd;
    @FXML private ComboBox<Integer> cbCustomerIdAdd;
    @FXML private ComboBox<String> cbTripTypeIdAdd;
    @FXML private ComboBox<Integer> cbPackageIdAdd;
    @FXML private Button btnAddBookingDetailNew;
    @FXML private TableView<BookingDetail> tvBookingDetailNew;
    @FXML private TableColumn<BookingDetail, Integer> colItineraryNoAdd;
    @FXML private TableColumn<BookingDetail, LocalDate> colTripStartAdd;
    @FXML private TableColumn<BookingDetail, LocalDate> colTripEndAdd;
    @FXML private TableColumn<BookingDetail, String> colDestinationAdd;
    @FXML private TableColumn<BookingDetail, String> colDescriptionAdd;
    @FXML private TableColumn<BookingDetail, Double> colBasePriceAdd;
    @FXML private TableColumn<BookingDetail, Double> colAgencyCommissionAdd;
    @FXML private TableColumn<BookingDetail, String> colRegionIdAdd;
    @FXML private TableColumn<BookingDetail, String> colClassIdAdd;
    @FXML private TableColumn<BookingDetail, String> colFeeIdAdd;
    @FXML private TableColumn<BookingDetail, Integer> colProductSupplierIdAdd;

    public ObservableList<BookingDetail> bookingDetailForNewBooking = null;


    @FXML
    void initialize() {
        setColumnValuesOfBookings();
        loadBookings();
    }


    /*********************************************************************************************************
     *
     *                                  @PAGE: BOOKING LIST TAB
     *                                  PURPOSE: VIEW ALL BOOKINGS
     *
     * *******************************************************************************************************/

    // when user double clicks on row, redirect to edit booking tab and set values on input fields
    @FXML
    void tvBookingClickItem(MouseEvent event) {
        //Checking double click
        if (event.getClickCount() == 2) {
            // get selected row
            Booking selectedRow = tvBookings.getSelectionModel().getSelectedItem();
            // go to edit booking tab
            tpBookings.getSelectionModel().select(tabEditBooking);
            // set values in input fields to update selected booking
            cbBookingIdEdit.setValue(selectedRow);
            dpBookingDateEdit.setValue(selectedRow.getBookingDate());
            etBookingNoEdit.setText(selectedRow.getBookingNo());
            etTravelerCountEdit.setText(String.valueOf(selectedRow.getTravelerCount()));
            cbCustomerIdEdit.setValue(selectedRow.getCustomerId());
            cbTripTypeIdEdit.setValue(selectedRow.getTripTypeId());
            cbPackageIdEdit.setValue(selectedRow.getPackageId());
        }
    }

    // as user types on search bar, table view will show filtered data
    @FXML
    void etSearchBookingKeyType(KeyEvent event) {
        ObservableList<Booking> bookingMaster = BookingDB.getBookings(0);
        FilteredList<Booking> filteredData = new FilteredList<>(bookingMaster, b -> true);
        etSearchBooking.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(booking -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (booking.getBookingNo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Booking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvBookings.comparatorProperty());
        tvBookings.setItems(sortedData);

    }


    /*********************************************************************************************************
     *
     *                                  @PAGE: EDIT BOOKING TAB
     *                                  PURPOSE: update or delete existing booking
     *
     * *******************************************************************************************************/

    // as user selects booking id on combo box, fill fields with currently selected booking ID
    @FXML
    void cbBookingIdEditAction(ActionEvent event) {
        if (cbBookingIdEdit.getSelectionModel().getSelectedItem() != null) {
            // get selected booking ID
            Booking selectedBooking = cbBookingIdEdit.getSelectionModel().getSelectedItem();

            // fill all text fields based on selected booking ID
            dpBookingDateEdit.setValue(selectedBooking.getBookingDate());
            etBookingNoEdit.setText(selectedBooking.getBookingNo());
            etTravelerCountEdit.setText(String.valueOf(selectedBooking.getTravelerCount()));
            cbCustomerIdEdit.setValue(selectedBooking.getCustomerId());
            cbTripTypeIdEdit.setValue(selectedBooking.getTripTypeId());
            cbPackageIdEdit.setValue(selectedBooking.getPackageId());
            setColumnValuesOfBookingDetails();

            // get selected booking ID
            int bookingSelected = selectedBooking.getBookingId();
            // fill booking detail table view base on selected booking ID
            ObservableList<BookingDetail> selectedBookingDetail = BookingDetailDB.getBookingDetailOnSelectedBooking(bookingSelected);
            tvBookingDetailEdit.setItems(selectedBookingDetail);
        }
        return;
    }




    // as user clicks save booking button, confirmation pop up will display to update booking
    @FXML
    void btnEditBookingAction(ActionEvent event) {
        if (IsValidDataForEdit()) {
            // get selected booking ID
            Booking selectedId = cbBookingIdEdit.getSelectionModel().getSelectedItem();
            int selectedBookingToUpdate = selectedId.getBookingId();

            // confirmation message
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Update Booking ID " + selectedBookingToUpdate + "?",
                    ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = confirm.showAndWait();

            // update booking after user confirmation
            if (result.get() == ButtonType.YES) {
                updateBooking(selectedBookingToUpdate);
            }
        }
    }



    // as user clicks delete booking button, confirmation pop up will display to delete booking
    @FXML
    void btnDeleteBookingAction(ActionEvent event) {
        // get selected booking id
        Booking selectedBooking = cbBookingIdEdit.getSelectionModel().getSelectedItem();
        int bookingSelected = selectedBooking.getBookingId();

        // delete booking detail included in booking
        int bookingDetailDeleted = BookingDB.deleteBookingDetail(bookingSelected);

        // confirmation message
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, " Booking details will also be deleted. " +
                "Are you sure you want to delete Booking ID " +
                bookingSelected + "?", ButtonType.YES, ButtonType.CANCEL);

        Optional<ButtonType> result = confirm.showAndWait();
        // delete booking after confirmation
        if (result.get() == ButtonType.YES) {
            deleteBooking(bookingSelected, bookingDetailDeleted);
        }
    }


    // as user double clicks on a booking detail
    @FXML
    void tvBookingDetailClickItem(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            try {
                // open add/modify booking detail window
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-modify-booking-detail.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                // create object of add/modify controller
                AddModifyBookingDetailController addModifyBookingDetailController = fxmlLoader.getController();
                addModifyBookingDetailController.isAddBookingDetail(false); // modify booking
                // pass selected booking detail to update to add/modify controller
                addModifyBookingDetailController.getSelectedBookingDetail(tvBookingDetailEdit.getSelectionModel().getSelectedItem());

                stage = new Stage();
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Modify Booking Detail");
                stage.setScene(new Scene(root1));
                stage.resizableProperty().setValue(false);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // as user selects on add, add/modify booking detail pop up will be displayed
    @FXML
    void btnAddBookingDetailldAction(ActionEvent event) {
        try {
            // open add/modify booking detail window
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../module/bookings/add-modify-booking-detail.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            // create object of add/modify controller
            AddModifyBookingDetailController addModifyBookingDetailController = fxmlLoader.getController();
            addModifyBookingDetailController.isAddBookingDetail(true); // add booking

            stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Add Booking Detail");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().setValue(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*********************************************************************************************************
     *
     *                                  @PAGE: NEW BOOKING TAB
     *                                  PURPOSE: create new booking
     *
     * *******************************************************************************************************/

    private Stage stage;

    // **************************************** CREATE NEW  *****************************************************

    // Booking must have booking detail in order to save as new booking & to create invoice

    // as user clicks on save button, confirmation will be displayed to add new booking
    @FXML
    void btnSaveNewBookingAction(ActionEvent event) {
        if (IsValidDataForNewBooking()) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to proceed?",
                    ButtonType.YES, ButtonType.CANCEL);


            Optional<ButtonType> result = confirm.showAndWait();
            // add booking after confirmation
            if (result.get() == ButtonType.YES) {
                int bookingIdOfNewBooking = addNewBooking();

                // check: save new booking detail only if booking was added
                if (bookingIdOfNewBooking > 0) {
                    addNewBookingDetail(bookingIdOfNewBooking);

                    // confirmation message
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Booking ID " + bookingIdOfNewBooking +
                            " added", ButtonType.OK);
                    Optional<ButtonType> successAdd = alert.showAndWait();
                    loadBookings();
                    clearAllFieldsInAddBooking();
                    // go to invoice page
                    if (successAdd.get() == ButtonType.OK) {
                        try {
                            // load invoice controller
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../module/bookings/invoice.fxml"));
                            Parent root1 = (Parent) fxmlLoader.load();

                            // create object invoice
                            Invoice newInvoice = InvoiceDB.getInvoice(bookingIdOfNewBooking);

                            // get invoice controller
                            InvoiceController invoiceController = fxmlLoader.getController();
                            // pass invoice object to controller
                            invoiceController.getInvoiceDetails(newInvoice);

                            stage = new Stage();
                            stage.initStyle(StageStyle.DECORATED);
                            stage.setTitle("Invoice");
                            stage.setScene(new Scene(root1));
                            stage.showAndWait();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Failed to add New Booking", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        }
    }




    // as user clicks on add new booking detail, add/modify detail pop up will be displayed
    @FXML
    void btnAddBookingDetailldNewAction(ActionEvent event) {
        // open add modify booking detail view
        try {
            // load addModifyController
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../module/bookings/add-modify-booking-detail.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            // get add modify controllercontroller
            AddModifyBookingDetailController addModifyBookingDetailController = fxmlLoader.getController();
            // pass data
            addModifyBookingDetailController.isAddBookingDetailForNewBooking(true); // determines add booking detail to new booking
            addModifyBookingDetailController.setList(this.bookingDetailForNewBooking); // new booking detail object

            stage = new Stage();
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Add Booking Detail");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().setValue(false);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
 *
 * BOOKING FUNCTIONS
 *
 * */

    public void loadBookings() {
        // display booking list in bookings tab
        ObservableList<Booking> bookingList = BookingDB.getBookings(0);
        tvBookings.setItems(bookingList);

        // fill combo boxes in edit bookings tab
        if (cbBookingIdEdit != null) {
            cbBookingIdEdit.setItems(bookingList);
        }

        // fill data on all combo boxes on edit page
        // trip types
        ObservableList<String> tripTypes = TripTypeDB.getTripType();
        cbTripTypeIdEdit.setItems(tripTypes);
        // packages
        ObservableList<Integer> packageIdList = PackagesDB.getPackageId();
        cbPackageIdEdit.setItems(packageIdList);

        // fill data on all combo boxes on add page
        ObservableList<Integer> customerId = CustomerDB.getAllCustomerId();
        cbCustomerIdAdd.setItems(customerId);
        cbTripTypeIdAdd.setItems(tripTypes);
        cbPackageIdAdd.setItems(packageIdList);

        int bookingIdForNewBooking = BookingDB.getIdForNewBooking();
        etBookingIdAdd.setText(String.valueOf(bookingIdForNewBooking));

        // fill booking details in new booking tab
        this.bookingDetailForNewBooking = FXCollections.observableArrayList();
        tvBookingDetailNew.setItems(this.bookingDetailForNewBooking);
    }

    private void updateBooking(int selectedBookingToUpdate) {
        // get user input and create new object to update selected booking
        Booking updatedBooking = new Booking(
                0,
                dpBookingDateEdit.getValue(),
                etBookingNoEdit.getText(),
                Integer.parseInt(etTravelerCountEdit.getText()),
                cbCustomerIdEdit.getSelectionModel().getSelectedItem(),
                cbTripTypeIdEdit.getSelectionModel().getSelectedItem(),
                cbPackageIdEdit.getSelectionModel().getSelectedItem()
        );

        // update booking
        int rowsUpdated = BookingDB.updateBooking(selectedBookingToUpdate, updatedBooking);
        if (rowsUpdated > 0) {
            loadBookings();
            clearAllFieldsInEditBooking();

            // go to Booking List tab
            tpBookings.getSelectionModel().select(tabBooking);

            // select updated booking in table
            int selectRecentUpdate = cbBookingIdEdit.getSelectionModel().getSelectedIndex();
            tvBookings.getSelectionModel().select(selectRecentUpdate);
            clearAllFieldsInEditBooking();
        }
    }

    private void deleteBooking(int bookingSelected, int bookingDetailDeleted) {
        int bookingDeleted = BookingDB.deleteBooking(bookingSelected);

        // alerts to show if delete was successful or not
        if (bookingDeleted > 0 || bookingDetailDeleted > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Booking ID " + bookingSelected +
                    " deleted", ButtonType.OK);
            alert.showAndWait();
            loadBookings();
            clearAllFieldsInEditBooking();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Failed to delete Boooking", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    private int addNewBooking() {
        // create new object to get values from input fields
        Booking newBooking = new Booking(
                0,
                dpBookingDateAdd.getValue(),
                etBookingNoAdd.getText(),
                Integer.parseInt(etTravelerCountAdd.getText()),
                Integer.parseInt(String.valueOf(cbCustomerIdAdd.getValue())),
                cbTripTypeIdAdd.getValue(),
                Integer.parseInt(String.valueOf(cbPackageIdAdd.getValue()))
        );
        // save new booking
        return BookingDB.addNewBooking(newBooking);
    }

    private void addNewBookingDetail(int bookingIdOfNewBooking) {
        // save new booking details to new booking
        for (BookingDetail newbookingDetail : bookingDetailForNewBooking) {
            // add to database
            int bookingDetailofNewBooking = BookingDetailDB.addNewBookingDetail(bookingIdOfNewBooking, newbookingDetail);
        }
    }

    // clear input fields in edit booking tab
    private void clearAllFieldsInEditBooking() {
        etBookingNoEdit.clear();
        dpBookingDateEdit.setValue(null);
        etTravelerCountEdit.clear();
        cbCustomerIdEdit.setValue(null);
        cbTripTypeIdEdit.setValue(null);
        cbPackageIdEdit.setValue(null);
        tvBookingDetailEdit.setItems(null);
    }

    // clear input fields in add new booking tab
    private void clearAllFieldsInAddBooking() {
        etBookingNoAdd.clear();
        dpBookingDateAdd.setValue(null);
        etTravelerCountAdd.clear();
    }

    // user input validation for Edit Booking
    private boolean IsValidDataForEdit() {
        return
                DataValidation.IsPresent(dpBookingDateEdit, "Booking Date") &&
                        DataValidation.IsPresent(etBookingNoEdit, "Booking No") &&
                        DataValidation.IsPresent(etTravelerCountEdit, "Traveler Count") &&
                        DataValidation.IsPresent(cbCustomerIdEdit, "Customer ID") &&
                        DataValidation.IsPresent(cbTripTypeIdEdit, "Trip Type") &&
                        DataValidation.IsPresent(cbPackageIdEdit, "Package ID") &&

                        DataValidation.IsInteger(etTravelerCountEdit, "Traveler Count") &&
                        DataValidation.HasNegativeValue(etTravelerCountEdit, "Traveler Count");

    }

    // user input validation for Add New Booking
    private boolean IsValidDataForNewBooking() {
        return
                DataValidation.IsPresent(dpBookingDateAdd, "Booking Date") &&
                        DataValidation.IsPresent(etBookingNoAdd, "Booking No") &&
                        DataValidation.IsPresent(etTravelerCountAdd, "Traveler Count") &&
                        DataValidation.IsPresent(cbCustomerIdAdd, "Customer ID") &&
                        DataValidation.IsPresent(cbTripTypeIdAdd, "Trip Type") &&
                        DataValidation.IsPresent(cbPackageIdAdd, "Package ID") &&

                        DataValidation.IsInteger(etTravelerCountAdd, "Traveler Count") &&
                        DataValidation.HasNegativeValue(etTravelerCountAdd, "Traveler Count");

    }

    private void setColumnValuesOfBookingDetails() {
        // set data columns of booking list table view
        colBookingDetailIdEdit.setCellValueFactory(cellData -> cellData.getValue().bookingDetailIdProperty().asObject());
        colItineraryNoEdit.setCellValueFactory(cellData -> cellData.getValue().itineraryNoProperty().asObject());
        colTripStartEdit.setCellValueFactory(cellData -> cellData.getValue().tripStartProperty());
        colTripEndEdit.setCellValueFactory(cellData -> cellData.getValue().tripEndProperty());
        colDestinationEdit.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        colDescriptionEdit.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        colBasePriceEdit.setCellValueFactory(cellData -> cellData.getValue().basePriceProperty().asObject());
        colAgencyCommissionEdit.setCellValueFactory(cellData -> cellData.getValue().agencyCommissionProperty().asObject());
        colRegionIdEdit.setCellValueFactory(cellData -> cellData.getValue().regionIdProperty());
        colClassIdEdit.setCellValueFactory(cellData -> cellData.getValue().classIdProperty());
        colFeeIdEdit.setCellValueFactory(cellData -> cellData.getValue().feeIdProperty());
        colProductSupplierIdEdit.setCellValueFactory(cellData -> cellData.getValue().productSupplierIdProperty().asObject());
    }

    private void setColumnValuesOfBookings() {
        // set data columns of (EDIT)booking list table view
        colBookingId.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
        colBookingDate.setCellValueFactory(cellData -> cellData.getValue().bookingDateProperty());
        colBookingNo.setCellValueFactory(cellData -> cellData.getValue().bookingNoProperty());
        colTravelerCount.setCellValueFactory(cellData -> cellData.getValue().travelerCountProperty().asObject());
        colCustomerId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        colTripTypeId.setCellValueFactory(cellData -> cellData.getValue().tripTypeIdProperty());
        colPackageId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());

        // set data columns of (NEW) booking list table view
        colItineraryNoAdd.setCellValueFactory(cellData -> cellData.getValue().itineraryNoProperty().asObject());
        colTripStartAdd.setCellValueFactory(cellData -> cellData.getValue().tripStartProperty());
        colTripEndAdd.setCellValueFactory(cellData -> cellData.getValue().tripEndProperty());
        colDestinationAdd.setCellValueFactory(cellData -> cellData.getValue().destinationProperty());
        colDescriptionAdd.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        colBasePriceAdd.setCellValueFactory(cellData -> cellData.getValue().basePriceProperty().asObject());
        colAgencyCommissionAdd.setCellValueFactory(cellData -> cellData.getValue().agencyCommissionProperty().asObject());
        colRegionIdAdd.setCellValueFactory(cellData -> cellData.getValue().regionIdProperty());
        colClassIdAdd.setCellValueFactory(cellData -> cellData.getValue().classIdProperty());
        colFeeIdAdd.setCellValueFactory(cellData -> cellData.getValue().feeIdProperty());
        colProductSupplierIdAdd.setCellValueFactory(cellData -> cellData.getValue().productSupplierIdProperty().asObject());
    }

}

