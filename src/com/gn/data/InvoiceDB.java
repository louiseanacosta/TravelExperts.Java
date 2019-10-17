    /*
     * AUTHOR: Colin Lee-Chee
     * PURPOSE: Get booking data from booking
     * */

    package com.gn.data;

    import com.gn.model.Invoice;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;

    public class InvoiceDB {

        /*
         * SUMMARY: Get booking
         * PARAMETER: bookingID
         * RETURNS: Booking
         * */


        public static Invoice getInvoice(int bookingId) {
            Invoice invoice = null;
            try {
                // get connection
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DBHelper.getConnection();
                // create statement
                Statement statement = connection.createStatement();
                // select query needst to be as follows:

                String selectQuery = "SELECT c.CustomerId, c.CustFirstName, c.CustLastName, c.CustEmail, b.bookingNo, " +
                        "bd.ItineraryNo, bd.Description, bd.Destination, bd.TripStart, bd.TripEnd, bd.BasePrice " +
                        "FROM ((bookings b INNER JOIN customers c ON c.CustomerID = b.CustomerID) " +
                        "INNER JOIN bookingDetails bd ON b.BookingID = bd.BookingId) " +
                        "WHERE b.BookingID = " + bookingId;

                // execute
                ResultSet resultSet = statement.executeQuery(selectQuery);
                // create new object
                if (resultSet.next()) {
                    invoice = new Invoice(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getDate(9).toLocalDate(),
                            resultSet.getDate(10).toLocalDate(),
                            resultSet.getDouble(11)
                    );
                }
                // close connection
                connection.close();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return invoice;
        }
    }
