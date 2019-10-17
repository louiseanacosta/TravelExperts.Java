/*
 * @author Hayley Mead
 * Validates new customer entries and old customer updates
 */
package com.gn.data;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class CustomerValidation {


    public static boolean value(TextField input) {
        return !input.getText().equals("");
    }

    //for province validation
    public static boolean province(TextField input) {
        if (input.getText().matches("(N[BLSTU]|[AMN]B|[BQ]C|ON|PE|SK)")) {
            return false;
        }
        return true;
    }

    //postal codes
    public static boolean postal(TextField input) {
        if (!input.getText().matches("(?!.*[DFIOQU])[A-VXY][0-9][A-Z]●?[0-9][A-Z][0-9]")) {
            return true;
        }
        return false;
    }

    public static boolean emailFormat(TextField input, String name) {
        if (!input.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            Alert error = new Alert(Alert.AlertType.ERROR, name + " format must be: name@email.com",
                    ButtonType.OK);
            error.showAndWait();
            input.requestFocus();
            return false;
        }
        return true;
    }

    //first name and last name validation
    public static boolean allNames(TextField input) {
        if (!input.getText().matches("[a-zA-Z]+")) {
            return false;
        }
        return true;
    }

    //phone number
    public static boolean phoneNumber(TextField inputTextField) {
        if (!inputTextField.getText().matches("([0-9]{3})\\)?[-.●]?([0-9]{3})[-.●]?([0-9]{4})")) {
            return false;
        }
        return true;
    }
}
