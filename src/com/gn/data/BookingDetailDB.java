/**
 * @author: Louise Acosta
 * PURPOSE: Get booking detail data in travel experts database (Booking Detail Table)
 */


package com.gn.data;

import com.gn.model.Booking;
import com.gn.model.BookingDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.math.BigDecimal;
import java.sql.*;

public class BookingDetailDB {

    /**
     * @Summary: Get booking detail list
     * PARAMETER: bookingdetailid
     * RETURNS: List of all Booking Details
     */


    public static ObservableList<BookingDetail> getBookingDetail(int bookingDetailId) {
        ObservableList<BookingDetail> bookings = FXCollections.observableArrayList();

        try {
            // get connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // select query
            String selectQuery = "SELECT * FROM BOOKINGDETAILS";

            // check package id
            if (bookingDetailId != 0) {
                selectQuery += " WHERE BOOKINGDETAILID = ?";
            }

            // execute
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // create new object
            while (resultSet.next()) {
                bookings.add(new BookingDetail(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        resultSet.getDouble(8),
                        resultSet.getInt(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getInt(13)
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
     * @Summary: Get booking detail list on selected bookingID
     * PARAMETER: bookingID
     * RETURNS: List of all Booking Details per booking
     */

    public static ObservableList<BookingDetail> getBookingDetailOnSelectedBooking(int bookingId) {
        ObservableList<BookingDetail> bookingDetails = FXCollections.observableArrayList();

        try {
            // get connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DBHelper.getConnection();
            // create statement
            Statement statement = connection.createStatement();
            // select query
            String selectQuery = "SELECT * FROM BOOKINGDETAILS";

            // check package id
            if (bookingId != 0) {
                selectQuery += " WHERE BOOKINGID = " + bookingId;
            }

            // execute
            ResultSet resultSet = statement.executeQuery(selectQuery);
            // create new object
            while (resultSet.next()) {
                bookingDetails.add(new BookingDetail(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getDate(3).toLocalDate(),
                        resultSet.getDate(4).toLocalDate(),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDouble(7),
                        resultSet.getDouble(8),
                        resultSet.getInt(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getInt(13)
                ));
            }
            // close connection
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return bookingDetails;
    }

    /**
     * @Summary: Update booking detail list on selected bookingID
     * PARAMETER: bookingID
     * RETURNS: List of all Booking Details per booking
     */

    public static int updateBookingdDetail(int bookingDetailId, BookingDetail updatedBookingDetail) {
        int rowsUpdated = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // update query
            String updateQuery = "UPDATE BOOKINGDETAILS SET " +
                    "ITINERARYNO = ?, TRIPSTART = ?, TRIPEND = ?, DESCRIPTION = ?, " +
                    "DESTINATION = ?, BASEPRICE = ?, AGENCYCOMMISSION =?," +
                    "REGIONID = ?, CLASSID = ?, FEEID = ?, PRODUCTSUPPLIERID = ? " +
                    "WHERE BOOKINGDETAILID = ?";
            // prepare statement
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, updatedBookingDetail.getItineraryNo());
            statement.setDate(2, Date.valueOf(updatedBookingDetail.getTripStart()));
            statement.setDate(3, Date.valueOf(updatedBookingDetail.getTripEnd()));
            statement.setString(4, updatedBookingDetail.getDescription());
            statement.setString(5, updatedBookingDetail.getDestination());
            statement.setString(6, String.valueOf(updatedBookingDetail.getBasePrice()));
            statement.setBigDecimal(7, BigDecimal.valueOf(updatedBookingDetail.getAgencyCommission()));
            statement.setString(8, updatedBookingDetail.getRegionId());
            statement.setString(9, updatedBookingDetail.getClassId());
            statement.setString(10, updatedBookingDetail.getFeeId());
            statement.setInt(11, updatedBookingDetail.getProductSupplierId());
            statement.setInt(12, bookingDetailId);

            rowsUpdated = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return rowsUpdated;
    }


    /**
     * @SUMMARY: Deletes booking detail
     * PARAMETER: bookingdetailid
     * RETURNS: indicator of success
     */

    public static int deleteBookingDetail(int bookingDetailId) {
        int rowsDeleted = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();
            // prepare statement
            String deleteQuery = "DELETE FROM BOOKINGDETAILS WHERE BOOKINGDETAILID =?";
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, bookingDetailId);
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
     * @SUMMARY: Adds New Booking Detail to New Booking Detail
     * PARAMETER: booking - object that contains data of the new booking
     * RETURNS: auto-generated booking id
     */

    public static int addNewBookingDetail(int bookingId, BookingDetail bookingDetail) {
        int bookingDetailId = 0;
        try {
            // get connection
            Connection connection = DBHelper.getConnection();


            // insert query
            String insertQuery = "INSERT INTO BOOKINGDETAILS (ITINERARYNO, TRIPSTART,TRIPEND, DESCRIPTION, DESTINATION, " +
                    "BASEPRICE, AGENCYCOMMISSION, BOOKINGID, REGIONID, CLASSID, FEEID, PRODUCTSUPPLIERID) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";


            // prepare statement
            PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, bookingDetail.getItineraryNo());
            statement.setDate(2, Date.valueOf(bookingDetail.getTripStart()));
            statement.setDate(3, Date.valueOf(bookingDetail.getTripEnd()));
            statement.setString(4, bookingDetail.getDescription());
            statement.setString(5, bookingDetail.getDestination());
            statement.setString(6, String.valueOf(bookingDetail.getBasePrice()));
            statement.setBigDecimal(7, BigDecimal.valueOf(bookingDetail.getAgencyCommission()));
            statement.setInt(8, bookingId);
            statement.setString(9, bookingDetail.getRegionId());
            statement.setString(10, bookingDetail.getClassId());
            statement.setString(11, bookingDetail.getFeeId());
            statement.setInt(12, bookingDetail.getProductSupplierId());
            // execute
            bookingDetailId = statement.executeUpdate();

            // check
            if (bookingDetailId < 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Failed to add new booking detail", ButtonType.CLOSE);
                alert.showAndWait();
            }

            // close connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please contact IT", ButtonType.CLOSE);
            alert.showAndWait();
        }
        return bookingDetailId;
    }
}
