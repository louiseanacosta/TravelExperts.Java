package com.gn.data;

import javafx.scene.control.*;

import javax.xml.soap.Text;
import java.time.ZoneId;


public class DataValidation {

    /**
     * @author: Louise Acosta
     * Purpose: Data Validation
     */

    //******************************************** EMPTY VALUES ********************************************/

    /**
     * SUMMARY: Checks whether the user entered data in the textfield
     * PARAMETER: textfield, name of textfield
     * RETURNS: True if user entered data
     */

    public static boolean IsPresent(TextField textField, String name) {
        if (textField.getText().isEmpty()) // user did not enter a value
        {
            // error message
            Alert error = new Alert(Alert.AlertType.ERROR, "Please enter a value for " + name,
                    ButtonType.OK);
            error.showAndWait();
            textField.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * SUMMARY: Checks whether the user selected data in the combobox
     * PARAMETER: combobox, name of combo box
     * RETURNS: True if user selected data
     */

    public static boolean IsPresent(ComboBox comboBox, String name) {
        if (comboBox.getSelectionModel().getSelectedItem().equals(null)) // user did not enter a value
        {
            // error message
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select a value for " + name,
                    ButtonType.OK);
            error.showAndWait();
            comboBox.requestFocus();
            return false;
        }
        return true;
    }

    /**
     * SUMMARY: Checks whether the user selected data in the combobox
     * PARAMETER: combobox, name of combo box
     * RETURNS: True if user selected data
     */

    public static boolean IsPresent(DatePicker datePicker, String name) {
        if (datePicker.getValue() == null) // user did not enter a value
        {
            // error message
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select a value for " + name,
                    ButtonType.OK);
            error.showAndWait();
            datePicker.requestFocus();
            return false;
        }
        return true;
    }

    //******************************************** INTEGER VALUES ********************************************/

    /**
     * SUMMARY: Checks whether the the user input is an integer
     * PARAMETER: textfield, name of textfield
     * RETURNS: True if input is integer
     */

    public static boolean IsInteger(TextField textField, String name) {
        try {
            Integer.parseInt(textField.getText());
            return true;
        } catch (NumberFormatException e) {
            Alert error = new Alert(Alert.AlertType.ERROR, name + " must be an integer",
                    ButtonType.OK);
            error.showAndWait();
            textField.requestFocus();
            return false;
        }
    }


    /**
     * SUMMARY: Checks whether the the user input has negative value
     * PARAMETER: textfield, name of textfield
     * RETURNS: True if input has negative value
     */

    public static boolean HasNegativeValue(TextField textField, String name) {
        try {
            int number = Integer.parseInt(textField.getText());
            if (number < 0) {
                Alert error = new Alert(Alert.AlertType.ERROR, name + " must be greater than 0",
                        ButtonType.OK);
                error.showAndWait();
                textField.requestFocus();
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            Alert error = new Alert(Alert.AlertType.ERROR, name + " must be an integer",
                    ButtonType.OK);
            error.showAndWait();
            textField.requestFocus();
            return false;
        }
    }


    /**
     * Author: Liming Hong
     * 2019 Jun 6
     * Data Validation
     */
    public static boolean validateHasValue(TextField ctrl) {
        return !ctrl.getText().equals("");
    }

    //check if datePick has a date
    public static boolean validateHasDate(DatePicker ctrl) {
        boolean hasDate = false;


        if (ctrl.getValue() != null) {
            hasDate = true;


        }

        return hasDate;

    }


    //check the textfield length
    public static boolean dataLength(TextField inputTextField, String requiredLength) {
        boolean isDataLength = true;


        if (!inputTextField.getText().matches("\\b\\w" + "{" + requiredLength + "}")) {
            isDataLength = false;


        }

        return isDataLength;

    }

    public static boolean textAlphabet(TextField inputTextField) {
        boolean isAlphabet = true;


        if (!inputTextField.getText().matches("[a-z A-Z]+")) {
            isAlphabet = false;


        }
        return isAlphabet;

    }

    public static boolean textNumeric(TextField inputTextField) {
        boolean isNumeric = true;


        if (!inputTextField.getText().matches("[0-9]+")) {
            isNumeric = false;

        }

        return isNumeric;

    }

    public static boolean emailFormat(TextField inputTextField) {
        boolean isEmail = true;

        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            isEmail = false;


        }
        return isEmail;

    }

    public static boolean zID(TextField inputTextField) {
        boolean isZID = true;

        if (!inputTextField.getText().matches("\\z[0-9]{7}")) {
            isZID = false;


        }

        return isZID;

    }

    //Regular Expressions: zMail: \z[0-9]{7}
    public static boolean textFieldIsNull(TextField inputTextField) {
        boolean isNull = false;

        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;

        }

        return isNull;

    }

}
