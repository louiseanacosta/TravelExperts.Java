/**
 * @author: Louise Acosta
 * PURPOSE: Get,Update,Delete,Create new booking data in travel experts database (Booking Table)
 */


package com.gn.data;

import com.gn.model.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;

public class BookingDB {

    /**
     * @SUMMARY: Get booking list
     * PARAMETER: bookingID
     * RETURNS: booking,
     * returns all bookings if parameter is 0
     */

    public static ObservableList<Booking> getBookings(int bookingId) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();

        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // select query
            String selectQuery = "SELECT * FROM BOOKINGS";

            // check booking id
            if (bookingId != 0) {
                selectQuery += " WHERE BOOKINGID = " + bookingId;
            }

            // execute
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // create new object
            while (resultSet.next()) {
                bookings.add(new Booking(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                ));
            }
            // close connection
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return bookings;
    }

    /**
     * @SUMMARY: Deletes booking detail in booking (in order to delete booking,
     * booking detail must be deleted first)
     * PARAMETER: bookingid
     * RETURNS: indicator of success
     */

    public static int deleteBookingDetail(int bookingId) {
        int rowsDeleted = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // prepare statement
            String deleteQuery = "DELETE FROM BOOKINGDETAILS WHERE BOOKINGID =?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, bookingId);
            // execute
            rowsDeleted = statement.executeUpdate();
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return rowsDeleted;
    }

    /**
     * @SUMMARY: Deletes booking in BOOKING TABLE(in order to delete booking,
     * booking detail must be deleted first)
     * PARAMETER: bookingid
     * RETURNS: indicator of success
     */
    public static int deleteBooking(int bookingId) {
        int rowsDeleted = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // prepare statement
            String deleteQuery = "DELETE FROM BOOKINGS WHERE BOOKINGID =?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, bookingId);
            // execute
            rowsDeleted = statement.executeUpdate();
            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return rowsDeleted;
    }

    /**
     * @SUMMARY: Adds New Booking booking
     * PARAMETER: booking - object that contains data of the new booking
     * RETURNS: auto-generated booking id
     */

    public static int addNewBooking(Booking booking) {
        int bookingId = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();

            // insert query
            String insertQuery = "INSERT INTO BOOKINGS (BOOKINGDATE, BOOKINGNO, " +
                    "TRAVELERCOUNT, CUSTOMERID, TRIPTYPEID, PACKAGEID) " +
                    "VALUES (?,?,?,?,?,?)";

            // prepare statement
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, Date.valueOf(booking.getBookingDate()));
            stmt.setString(2, booking.getBookingNo());
            stmt.setDouble(3, booking.getTravelerCount());
            stmt.setInt(4, booking.getCustomerId());
            stmt.setString(5, booking.getTripTypeId());
            stmt.setInt(6, booking.getPackageId());

            // execute
            stmt.executeUpdate();

            // get the booking id of last inserted
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }

            // close connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return bookingId;
    }

    /**
     * @SUMMARY: Update booking
     * PARAMETER: bookingId, new Booking object
     * RETURNS: number of rows updated
     */

    public static int updateBooking(int bookingId, Booking updatedBooking) {
        int rowsUpdated = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // update query
            String updateQuery = "UPDATE BOOKINGS SET " +
                    "BOOKINGDATE = ?, BOOKINGNO = ?, TRAVELERCOUNT = ?, CUSTOMERID = ?, "
                    + "TRIPTYPEID = ? WHERE BOOKINGID = ?";
            // create statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setDate(1, Date.valueOf(updatedBooking.getBookingDate()));
            statement.setString(2, updatedBooking.getBookingNo());
            statement.setInt(3, updatedBooking.getTravelerCount());
            statement.setInt(4, updatedBooking.getCustomerId());
            statement.setString(5, updatedBooking.getTripTypeId());
            statement.setInt(6, bookingId);

            // execute
            rowsUpdated = statement.executeUpdate();
            // check
            if (rowsUpdated == 0) {
                // show fail to update alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Failed to update Boooking", ButtonType.CLOSE);
                alert.showAndWait();
            } else {
                // show success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Booking ID " + bookingId +
                        " updated successfully", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return rowsUpdated;
    }

    /**
     * @SUMMARY: get last booking id
     * RETURNS: last booking id + 1
     */


    public static int getIdForNewBooking() {
        int newBookingId = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // query to get last id
            String query = "SELECT MAX(BOOKINGID) FROM BOOKINGS";
            // create statement
            Statement statement = connection.createStatement();
            // execute
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                newBookingId = resultSet.getInt(1);
            }

            // close connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newBookingId + 1;
    }


    /**
     * @SUMMARY: Get booking list for each customer
     * PARAMETER: customer id
     * RETURNS: booking,
     * returns all bookings for selected customer
     */

    public static ObservableList<Booking> getBookingsOfCustomer(int customerId) {
        ObservableList<Booking> bookings = FXCollections.observableArrayList();

        try {
            // load driver
            Class.forName("com.mysql.jdbc.Driver");
            // get connection
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // select query
            String selectQuery = "SELECT * FROM BOOKINGS WHERE CUSTOMERID = " + customerId;


            // execute
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // create new object
            while (resultSet.next()) {
                bookings.add(new Booking(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                ));
            }
            // close connection
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return bookings;
    }


}
