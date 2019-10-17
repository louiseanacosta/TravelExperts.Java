/**************************************
 *
 * @author Hayley Mead
 * purpose: crud functionality for customers tab
 * date: june 4th 2019
 * Travel Experts Workshop 6
 * group 1
 *
 **************************************/
package com.gn.controller;

    import java.net.URL;
    import java.sql.ResultSet;
    import java.time.LocalDate;
    import java.util.Optional;
    import java.util.ResourceBundle;
    import com.gn.data.BookingDB;
    import com.gn.data.CustomerDB;
    import com.gn.data.CustomerValidation;
    import com.gn.data.DataValidation;
    import com.gn.model.Booking;
    import com.gn.model.Customers;
    import com.mysql.jdbc.PreparedStatement;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.scene.input.MouseEvent;

public class CustomerController {

 //   ObservableList custList = FXCollections.observableArrayList();
    //ObservableList <Customers> olCustomerList = FXCollections.observableArrayList(custList);

    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML private ResourceBundle resources;
    @FXML private URL location;
   @FXML private TabPane CustomerTabPane;
    @FXML private TableView<Customers> tvCustomers;
    @FXML private TableColumn<Customers, Integer> colCustomerId;
    @FXML private TableColumn<Customers, String> colCustFirstName;
    @FXML private TableColumn<Customers, String> colCustLastName;
    @FXML private TableColumn<Customers, String> colCustAddress;
    @FXML private TableColumn<Customers, String> colCustCity;
    @FXML private TableColumn<Customers, String> colCustProv;
    @FXML private TableColumn<Customers, String> colCustPost;
    @FXML private TableColumn<Customers, String> colCustCountry;
    @FXML private TableColumn<Customers, String> colCustHomePhone;
    @FXML private TableColumn<Customers, String> colCustBusPhone;
    @FXML private TableColumn<Customers, String> colCustEmail;
    @FXML private TableColumn<Customers, Integer> colAgentId;
    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private TextField tfCustomerId;
    @FXML private TextField tfCustFirstName;
    @FXML private TextField tfCustLastName;
    @FXML private TextField tfCustAddress;
    @FXML private TextField tfCustCity;
    @FXML private TextField tfCustProv;
    @FXML private TextField tfCustPostal;
    @FXML private TextField tfCustCountry;
    @FXML private TextField tfCustHomePhone;
    @FXML private TextField tfCustBusPhone;
    @FXML private TextField tfCustEmail;
    @FXML private TextField tfAgentId;
    @FXML private Button btnNewCustSave;
    @FXML private TextField tfAddCustomerId;
    @FXML private TextField tfAddCustFirstName;
    @FXML private TextField tfAddCustLastName;
    @FXML private TextField tfAddCustAddress;
    @FXML private TextField tfAddCustCity;
    @FXML private TextField tfAddCustProv;
    @FXML private TextField tfAddCustPostal;
    @FXML private TextField tfAddCustCountry;
    @FXML private TextField tfAddCustHomePhone;
    @FXML private TextField tfAddCustBusPhone;
    @FXML private TextField tfAddCustEmail;
    @FXML private TextField tfAddAgentId;
    @FXML private Tab custTable;
    @FXML private Tab editCustTab;
    @FXML private Tab newCustTab;
    @FXML private ComboBox<Customers> comboCustEditId;
    @FXML private TableView<Booking> tvCustomerBooking;
    @FXML private TableColumn<Booking, Integer> colCustomerBookingId;
    @FXML private TableColumn<Booking, LocalDate> colCustomerBookingDate;
    @FXML private TableColumn<Booking, String> colCustomerBookingNo;
    @FXML private TableColumn<Booking, Integer> colCustomerTravelerCount;
    @FXML private TableColumn<Booking, String> colCustomerTripTypeId;
    @FXML private TableColumn<Booking, Integer> colCustomerPackageId;

    @FXML
    void initialize() {

        // SET COLUMN VALUES FOR CUSTOMER TABLE VIEW
        colCustomerId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty().asObject());
        colCustFirstName.setCellValueFactory(cellData -> cellData.getValue().custFirstNameProperty());
        colCustLastName.setCellValueFactory(cellData -> cellData.getValue().custLastNameProperty());
        colCustAddress.setCellValueFactory(cellData -> cellData.getValue().custAddressProperty());
        colCustCity.setCellValueFactory(cellData -> cellData.getValue().custCityProperty());
        colCustProv.setCellValueFactory(cellData -> cellData.getValue().custProvProperty());
        colCustPost.setCellValueFactory(cellData -> cellData.getValue().custPostalProperty());
        colCustCountry.setCellValueFactory(cellData -> cellData.getValue().custCountryProperty());
        colCustHomePhone.setCellValueFactory(cellData -> cellData.getValue().custHomePhoneProperty());
        colCustBusPhone.setCellValueFactory(cellData -> cellData.getValue().custBusPhoneProperty());
        colCustEmail.setCellValueFactory(cellData -> cellData.getValue().custEmailProperty());
        colAgentId.setCellValueFactory(cellData -> cellData.getValue().agentIdProperty().asObject());

        // SET COLUMN VALUES FOR BOOKING TABLE VIEW
        colCustomerBookingId.setCellValueFactory(cellData -> cellData.getValue().bookingIdProperty().asObject());
        colCustomerBookingDate.setCellValueFactory(cellData -> cellData.getValue().bookingDateProperty());
        colCustomerBookingNo.setCellValueFactory(cellData -> cellData.getValue().bookingNoProperty());
        colCustomerTravelerCount.setCellValueFactory(cellData -> cellData.getValue().travelerCountProperty().asObject());
        colCustomerTripTypeId.setCellValueFactory(cellData -> cellData.getValue().tripTypeIdProperty());
        colCustomerPackageId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());


        loadCustomers();
        // insertCustomers();
        fillComboBox();
    }



    /*****************************
     * Table Page
      LOADS CUSTOMERS INTO TABLE
   *****************************/
    private void loadCustomers() {
        // get all customers and put in table view
        ObservableList<Customers> data = CustomerDB.getCustomers();
        tvCustomers.setItems(data);

        // get id for new customer and put in customer id text field
        int customerIdForAddingNewCustomer = CustomerDB.getIdForNewCustomer();
        tfAddCustomerId.setText(String.valueOf(customerIdForAddingNewCustomer));
    }



    /***********************
    EDIT CUSTOMER PAGE
    ********************** */

    /*
        Fills combobox with customer ids
     */
    public void fillComboBox(){
        ObservableList<Customers> customers = CustomerDB.getCustomers();
        comboCustEditId.setItems(customers);
    }

    @FXML
    void comboCustEditIdAction(ActionEvent event) {
        Customers customers = comboCustEditId.getSelectionModel().getSelectedItem();

        if(customers != null)
        {
            tfCustFirstName.setText(customers.getCustFirstName());
            tfCustLastName.setText(customers.getCustLastName());
            tfCustAddress.setText(customers.getCustAddress());
            tfCustCity.setText(customers.getCustCity());
            tfCustPostal.setText(customers.getCustPostal());
            tfCustProv.setText(customers.getCustProv());
            tfCustCountry.setText(customers.getCustCountry());
            tfCustHomePhone.setText(customers.getCustHomePhone());
            tfCustBusPhone.setText(customers.getCustBusPhone());
            tfCustEmail.setText(customers.getCustEmail());
            tfAgentId.setText(String.valueOf(customers.getAgentId()));
        }

        /**
         *
         * @author: Louise Acosta
         * purpose: display bookings of selected customer
         * */
        // fill table view of bookings of selected customer
        if(comboCustEditId.getSelectionModel().getSelectedItem() != null) {
            // get selected customer in combobox
            int selectedCustId = comboCustEditId.getSelectionModel().getSelectedItem().getCustomerId();
            // get bookings of customer
            ObservableList<Booking> bookings = BookingDB.getBookingsOfCustomer(selectedCustId);
            // fill table view with bookings of customer
            tvCustomerBooking.setItems(bookings);
        }

    }

    @FXML
    void btnDeleteAction(ActionEvent event){
        // get selected customerId
        int customerId = comboCustEditId.getSelectionModel().getSelectedItem().getCustomerId();
        // delete customer
        int customerDeleted = CustomerDB.deleteCustomer(customerId);
        //reload customer table and combo box
        if (customerDeleted > 0) {
            loadCustomers();
            fillComboBox();
            clearInputFieldsInEditCustomers();
            //redirect to customer table
            CustomerTabPane.getSelectionModel().select(custTable);

        }
    }

    // UPDATE customer when user clicks save button on edit customer tab
    @FXML
    void btnSaveAction(ActionEvent event) {
        if(IsValidDataForCustomerEdit()) {
            // confirm to update
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to proceed?",
                    ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = confirm.showAndWait();
            // add booking after confirmation
            if (result.get() == ButtonType.YES) {
                // get selected customerId to update
                int customerId = comboCustEditId.getSelectionModel().getSelectedItem().getCustomerId();

                // create new object to get values from input fields
                Customers updatedCustomer = new Customers(
                        0,
                        tfCustFirstName.getText(),
                        tfCustLastName.getText(),
                        tfCustAddress.getText(),
                        tfCustCity.getText(),
                        tfCustProv.getText(),
                        tfCustPostal.getText(),
                        tfCustCountry.getText(),
                        tfCustHomePhone.getText(),
                        tfCustBusPhone.getText(),
                        tfCustEmail.getText(),
                        Integer.parseInt(tfAgentId.getText())
                );

                // update customer
                int numberOfUpdatedCustomer = CustomerDB.updateCustomer(customerId, updatedCustomer);
                if (numberOfUpdatedCustomer > 0) {
                    loadCustomers();
                    fillComboBox();
                    clearInputFieldsInEditCustomers();
                    //redirect to customer table
                    CustomerTabPane.getSelectionModel().select(custTable);
                }
            }
        }
    }

    private void clearInputFieldsInEditCustomers() {
        tfCustFirstName.clear();
        tfCustLastName.clear();
        tfCustAddress.clear();
        tfCustCity.clear();
        tfCustProv.clear();
        tfCustPostal.clear();
        tfCustCountry.clear();
        tfCustHomePhone.clear();
        tfCustBusPhone.clear();
        tfCustEmail.clear();
        tfAgentId.clear();
    }

    /**************************************
        ADD NEW CUSTOMER PAGE
     **************************************/

    //Save Btn
    @FXML
    void btnNewCustSaveAction(ActionEvent event) {
        if(IsValidDataForCustomerAdd()) {
            // confirm to update
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to proceed?",
                    ButtonType.YES, ButtonType.CANCEL);

            Optional<ButtonType> result = confirm.showAndWait();
            // add booking after confirmation
            if (result.get() == ButtonType.YES) {
                // get user input and create new object to add customer
                Customers newCustomer = new Customers(
                        0,
                        tfAddCustFirstName.getText(),
                        tfAddCustLastName.getText(),
                        tfAddCustAddress.getText(),
                        tfAddCustCity.getText(),
                        tfAddCustProv.getText(),
                        tfCustPostal.getText(),
                        tfAddCustCountry.getText(),
                        tfAddCustHomePhone.getText(),
                        tfAddCustBusPhone.getText(),
                        tfAddCustEmail.getText(),
                        Integer.parseInt(tfAgentId.getText())
                );

                int numberOfAddedCustomer = CustomerDB.addNewCustomer(newCustomer);
                if (numberOfAddedCustomer > 0) {
                    loadCustomers();
                    fillComboBox();
                    clearInputFieldsInAddCustomers();
                    CustomerTabPane.getSelectionModel().select(custTable);
                }
            }
        }
    }

    private void clearInputFieldsInAddCustomers() {
        tfAddAgentId.clear();
        tfAddCustFirstName.clear();
        tfAddCustLastName.clear();
        tfAddCustAddress.clear();
        tfAddCustCity.clear();
        tfAddCustProv.clear();
        tfAddCustPostal.clear();
        tfAddCustCountry.clear();
        tfAddCustHomePhone.clear();
        tfAddCustBusPhone.clear();
        tfAddCustEmail.clear();
        tfAddAgentId.clear();
    }

    // on double click of selected customer
    @FXML
    void tvCustomerClick(MouseEvent event) {

        if (event.getClickCount() == 2) //Checking double click
        {
            // get selected row
            Customers selectedRow = tvCustomers.getSelectionModel().getSelectedItem();
            // go to edit customer tab
            CustomerTabPane.getSelectionModel().select(editCustTab);
            // set values in input fields to update selected booking
            comboCustEditId.setValue(selectedRow);
            tfCustFirstName.setText(selectedRow.getCustFirstName());
            tfCustLastName.setText(selectedRow.getCustLastName());
            tfCustAddress.setText(selectedRow.getCustAddress());
            tfCustCity.setText(selectedRow.getCustCity());
            tfCustProv.setText(selectedRow.getCustProv());
            tfCustCountry.setText(selectedRow.getCustCountry());
            tfCustHomePhone.setText(selectedRow.getCustHomePhone());
            tfCustBusPhone.setText(selectedRow.getCustBusPhone());
            tfCustEmail.setText(selectedRow.getCustEmail());

        }
    }

    private boolean IsValidDataForCustomerEdit(){
        return
                DataValidation.IsPresent(tfCustFirstName, "First Name") &&
                DataValidation.IsPresent(tfCustLastName, "Last Name") &&
                DataValidation.IsPresent(tfCustAddress, "Address") &&
                DataValidation.IsPresent(tfCustCity, "City") &&
                DataValidation.IsPresent(tfCustProv, "Province") &&
                DataValidation.IsPresent(tfCustPostal, "Postal Code") &&
                DataValidation.IsPresent(tfCustCountry, "Country") &&
                DataValidation.IsPresent(tfCustHomePhone, "Home Phone") &&
                DataValidation.IsPresent(tfCustBusPhone, "Business Phone") &&
                DataValidation.IsPresent(tfCustEmail, "Email") &&
                CustomerValidation.emailFormat(tfCustEmail, "Email");
    }

    private boolean IsValidDataForCustomerAdd(){
        return
                DataValidation.IsPresent(tfAddCustFirstName, "First Name") &&
                DataValidation.IsPresent(tfAddCustLastName, "Last Name") &&
                DataValidation.IsPresent(tfCustAddress, "Address") &&
                DataValidation.IsPresent(tfAddCustCity, "City") &&
                DataValidation.IsPresent(tfAddCustProv, "Province") &&
                DataValidation.IsPresent(tfAddCustPostal, "Postal Code") &&
                DataValidation.IsPresent(tfAddCustCountry, "Country") &&
                DataValidation.IsPresent(tfAddCustHomePhone, "Home Phone") &&
                DataValidation.IsPresent(tfAddCustBusPhone, "Business Phone") &&
                DataValidation.IsPresent(tfAddCustEmail, "Email");
    }


}
