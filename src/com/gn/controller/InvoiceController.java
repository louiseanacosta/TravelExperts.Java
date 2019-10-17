/**
 *
 * @author: Louise Acosta
 * purpose: create and display an invoice once a booking has been added
 *
 */

package com.gn.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import com.gn.model.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InvoiceController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Label txtTravelInvoice;
    @FXML private TextField txtInvoiceDate;
    @FXML private TextField txtCustomerNumber;
    @FXML private TextField txtBookingNumber;
    @FXML private TextField txtCustomerName;
    @FXML private TextField txtItineraryNo;
    @FXML private TextField txtTripStart;
    @FXML private TextField txtTripEnd;
    @FXML private TextField txtDescription;
    @FXML private TextField txtDestination;
    @FXML private TextField txtBasePrice;
    @FXML private TextField txtSubtotal;
    @FXML private TextField txtTax;
    @FXML private TextField txtTotal;
    @FXML private Button btnSendInvoice;
    @FXML private TextField txtCustEmail;

    @FXML
    void initialize() {
        // get current date and display on invoice
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        txtInvoiceDate.setText(dateFormat.format(cal.getTime()));
    }

    double tax = 0;
    double total = 0;

    // get invoice from Booking Controller and set text fields
    public void getInvoiceDetails(Invoice invoice) {
        txtCustomerNumber.setText(String.valueOf(invoice.getCustomerId()));
        txtCustomerName.setText(invoice.getCustFirstName() + " " + invoice.getCustLastName());
        txtCustEmail.setText(invoice.getCustEmail());
        txtBookingNumber.setText(String.valueOf(invoice.getBookingNo()));
        txtItineraryNo.setText(String.valueOf(invoice.getItineraryNo()));
        txtTripStart.setText(String.valueOf(invoice.getTripStart()));
        txtTripEnd.setText(String.valueOf(invoice.getTripEnd()));
        txtDescription.setText(invoice.getDescrption());
        txtDestination.setText(invoice.getDestination());
        txtBasePrice.setText(String.valueOf(invoice.getBasePrice()));
        txtSubtotal.setText(String.valueOf(invoice.getBasePrice()));

        tax = (invoice.getBasePrice()*0.05);
        total = (invoice.getBasePrice() + tax);

        txtTax.setText(String.valueOf(tax));
        txtTotal.setText(String.valueOf(total));
    }
}


