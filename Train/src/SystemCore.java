import java.sql.*;
import java.util.Scanner;

class SystemCore {
    private Scanner scanner;
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public SystemCore(String url, String username, String password) {
        scanner = new Scanner(System.in);
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    public void AddUser() {
        System.out.println("Enter ID :");
        int Id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Username :");
        String UserName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter E-mail :");
        String Email = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Password :");
        String Password = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Country :");
        String Country = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Phone number :");
        String PhoneNumber = scanner.next();
        scanner.nextLine();
        String InsertQuery = "INSERT INTO users (userID, userName, email, userPassword, country, phoneNumber) VALUES (?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, Id);
            statement.setString(2, UserName);
            statement.setString(3, Email);
            statement.setString(4, Password);
            statement.setString(5, Country);
            statement.setString(6, PhoneNumber);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added to the database!");
            } else {
                System.out.println("Failed to add the trip to the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }

    }

    public void AddAdmin() {
        System.out.println("Enter ID :");
        int AdminId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Admin-name :");
        String adminName = scanner.next();
        scanner.nextLine();
        System.out.println("Enter E-mail :");
        String Email = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Password :");
        String adminPassword = scanner.next();
        scanner.nextLine();

        String InsertQuery = "INSERT INTO admininfo (adminID, adminName, email, adminPassword) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, AdminId);
            statement.setString(2, adminName);
            statement.setString(3, Email);
            statement.setString(4, adminPassword);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added to the database!");
            } else {
                System.out.println("Failed to add the trip to the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();

        }
    }

    public void AddTrain() {
        System.out.println("Enter TrainID :");
        int TrainId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Train-class :");
        String Trainclass = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Train-capacity :");
        int Capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Train-State :");
        int TrainState = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Admin ID:");
        int AdminID = scanner.nextInt();
        scanner.nextLine();
        String InsertQuery = "INSERT INTO train (trainID, class, capacity, trainState ,adminID ) VALUES (?, ?, ?, ? , ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, TrainId);
            statement.setString(2, Trainclass);
            statement.setInt(3, Capacity);
            statement.setInt(4, TrainState);
            statement.setInt(5, AdminID);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Train added to the database!");
            } else {
                System.out.println("Failed to add the trip to the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();

        }
    }

    public void AddTrip() {
        System.out.println("Enter TripID :");
        int tripID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Trip price :");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Arrival time :");
        String arrivalTime = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Departure time :");
        Time departureTime = Time.valueOf(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Trip date :");
        Date tripDate = Date.valueOf(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Destination :");
        String destination = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Source :");
        String source = scanner.next();
        scanner.nextLine();
        System.out.println("Enter AdminId :");
        int AdminId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter TrainId :");
        int TrainId = scanner.nextInt();
        scanner.nextLine();

        String InsertQuery = "INSERT INTO trip (tripID, price, arrivalTime, departualTime, tripDate,destination,source,adminID,trainID) VALUES (?, ?, ?, ?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, tripID);
            statement.setInt(2, price);
            statement.setString(3, arrivalTime);
            statement.setTime(4, departureTime);
            statement.setDate(5, tripDate);
            statement.setString(6, destination);
            statement.setString(7, source);
            statement.setInt(8, AdminId);
            statement.setInt(9, TrainId);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Train added to the database!");
            } else {
                System.out.println("Failed to add the trip to the database.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();

        }
    }
    public void bookTrip(int bookingId, int tripId, int userId, int seatId, String bookingDate) {
        try {
            PreparedStatement seatAvailabilityStatement = connection.prepareStatement(
                    "SELECT seatAvailability FROM SEATS WHERE seatID = ? "
            );
            seatAvailabilityStatement.setInt(1, seatId);
            ResultSet seatAvailabilityResult = seatAvailabilityStatement.executeQuery();

            if (seatAvailabilityResult.next() && seatAvailabilityResult.getInt(1) == 0) {
                PreparedStatement updateSeatAvailabilityStatement = connection.prepareStatement(
                        "UPDATE SEATS SET seatAvailability = 1 WHERE seatID = ?");
                updateSeatAvailabilityStatement.setInt(1, seatId);
                updateSeatAvailabilityStatement.executeUpdate();

                PreparedStatement tripPriceStatement = connection.prepareStatement(
                        "SELECT price FROM TRIP WHERE tripID = ?");
                tripPriceStatement.setInt(1, tripId);
                ResultSet tripPriceResult = tripPriceStatement.executeQuery();

                if (tripPriceResult.next()) {
                    int totalPrice = tripPriceResult.getInt(1);
                    PreparedStatement bookingStatement = connection.prepareStatement(
                            "INSERT INTO BOOKING (bookingID, tripID, userID, seatID, BDATE, CDATE, bookingStatus, totalPrice) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                    );
                    bookingStatement.setInt(1, bookingId);
                    bookingStatement.setInt(2, tripId);
                    bookingStatement.setInt(3, userId);
                    bookingStatement.setInt(4, seatId);
                    bookingStatement.setString(5, bookingDate);
                    bookingStatement.setNull(6, Types.DATE);
                    bookingStatement.setInt(7, 0);
                    bookingStatement.setInt(8, totalPrice);
                    bookingStatement.executeUpdate();

                    System.out.println("Booking successful!");
                } else {
                    System.out.println("Failed to get trip price.");
                }
            } else {
                System.out.println("Seat not available.");
            }
        } catch (SQLException e) {
            System.out.println("Error booking trip: " + e.getMessage());
        }
    }

    public void cancelTrip(int tripID) {
        try {
            // First, delete the associated bookings
            PreparedStatement cancelBookingStatement = connection.prepareStatement(
                    "DELETE FROM BOOKING WHERE tripID = ?");
            cancelBookingStatement.setInt(1, tripID);

            int bookingsDeleted = cancelBookingStatement.executeUpdate();

            // If there are bookings associated with the trip, proceed with deleting the trip
            if (bookingsDeleted >= 0) {
                PreparedStatement cancelTripStatement = connection.prepareStatement(
                        "DELETE FROM TRIP WHERE tripID = ?");
                cancelTripStatement.setInt(1, tripID);

                int tripDeleted = cancelTripStatement.executeUpdate();

                if (tripDeleted > 0) {
                    System.out.println("Cancellation successful! Trip and associated bookings deleted.");
                } else {
                    System.out.println("Trip not found or already canceled.");
                }
            } else {
                System.out.println("No bookings found for the trip.");
            }
        } catch (SQLException e) {
            System.out.println("Error canceling trip: " + e.getMessage());
        }
    }

    public void Update_trip(int adminID, int tripID) {

        System.out.println("Enter Trip price :");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Arrival time :");
        Time arrivalTime = Time.valueOf(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Departure time :");
        Time departureTime = Time.valueOf(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Trip date :");
        Date tripDate = Date.valueOf(scanner.next());
        scanner.nextLine();
        System.out.println("Enter Destination :");
        String destination = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Source :");
        String source = scanner.next();
        scanner.nextLine();
        System.out.println("Enter TrainId :");
        int TrainId = scanner.nextInt();
        scanner.nextLine();

        String InsertQuery = "update trip set tripID = ?, price = ?, arrivalTime = ? , departualTime = ?, tripDate = ?,destination = ?,source = ?,adminID = ?,trainID = ? where tripID = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, tripID);
            statement.setInt(2, price);
            statement.setTime(3, arrivalTime);
            statement.setTime(4, departureTime);
            statement.setDate(5, tripDate);
            statement.setString(6, destination);
            statement.setString(7, source);
            statement.setInt(8, adminID);
            statement.setInt(9, TrainId);
            statement.setInt(10, tripID);
            System.out.println("Data Updated Successfully\n");
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();

        }
    }
    public void UpdateTrain(int trainID) {

        System.out.println("Enter Train-class:");
        String Trainclass = scanner.next();
        scanner.nextLine();
        System.out.println("Enter Train-capacity:");
        int Capacity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Train-State:");
        int TrainState = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Admin ID:");
        int AdminID = scanner.nextInt();
        scanner.nextLine();
        String updateQuery = "UPDATE train SET class = ?, capacity = ?, trainState = ?, adminID = ? WHERE trainID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, Trainclass);
            statement.setInt(2, Capacity);
            statement.setInt(3, TrainState);
            statement.setInt(4, AdminID);
            statement.setInt(5, trainID);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Train data updated successfully!\n");
            } else {
                System.out.println("No train found with the given trainID.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }

    }
    public void selectseat(int trainID, Date D , String Source , String Dest) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/traindb", "root", "Abdo@112233")) {
            // Prepare the SQL query
            String query = "SELECT s.seatID, t.tripDate, t.departualTime, t.destination, t.source " +
                    "FROM SEATS s " +
                    "INNER JOIN TRIP t ON s.trainID = t.trainID " +
                    "LEFT JOIN BOOKING b ON s.seatID = b.seatID AND t.tripID = b.tripID " +
                    "WHERE t.tripDate = ? " +
                    "AND t.source = ? " +
                    "AND t.destination = ? " +
                    "AND (b.bookingID IS NULL)";

            // Create a prepared statement
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the parameter values
            statement.setDate(1, D);
            statement.setString(2, Source);
            statement.setString(3, Dest);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Retrieve the values from each column
                int seatID = resultSet.getInt("seatID");
                String tripDate = resultSet.getString("tripDate");
                String departureTime = resultSet.getString("departualTime");
                String destination = resultSet.getString("destination");
                String source = resultSet.getString("source");

                // Do something with the retrieved values
                System.out.println("Seat ID: " + seatID);
                System.out.println("Trip Date: " + tripDate);
                System.out.println("Departure Time: " + departureTime);
                System.out.println("Destination: " + destination);
                System.out.println("Source: " + source);
                System.out.println("\n");
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayUsers() {
        String selectQuery = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("UserID\tUserName\tEmail\t\t\tPassword\tCountry\t\tPhone Number");
            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String userName = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("userPassword");
                String country = resultSet.getString("country");
                String phoneNumber = resultSet.getString("phoneNumber");
                System.out.println(userID + "\t" + userName + "\t\t" + email + "\t" + password + "\t" +
                        country + "\t\t" + phoneNumber);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }
    }
    public void updateAndDeleteBooking(int bookingID) {
        // Assuming you have a JDBC connection to your database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/traindb", "root", "Abdo@112233")) {
            // Update the seats table
            String updateQuery = "UPDATE SEATS " +
                    "SET seatAvailability = 0 " +
                    "WHERE seatID = (SELECT seatID FROM BOOKING WHERE bookingID = ?)";

            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

            updateStatement.setInt(1, bookingID);

            int updatedRows = updateStatement.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Seats updated successfully.");
            } else {
                System.out.println("Failed to update seats.");
            }

            String deleteQuery = "DELETE FROM BOOKING WHERE bookingID = ?";

            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);

            deleteStatement.setInt(1, bookingID);

            int deletedRows = deleteStatement.executeUpdate();

            if (deletedRows > 0) {
                System.out.println("Booking deleted successfully.");
            } else {
                System.out.println("Failed to delete booking.");
            }

            // Close the statements
            updateStatement.close();
            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayAdmins() {
        String selectQuery = "SELECT * FROM admininfo";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);
            System.out.println("AdminID\tAdminName\tEmail\t\t\tAdminPassword");
            while (resultSet.next()) {
                int adminID = resultSet.getInt("adminID");
                String adminName = resultSet.getString("adminName");
                String email = resultSet.getString("email");
                String password = resultSet.getString("adminPassword");
                System.out.println(adminID + "\t" + adminName + "\t\t" + email + "\t" + password);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }
    }
    public void showReport() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/traindb";
        String username = "root";
        String password = "Abdo@112233";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Total revenue per train class
            String revenuePerClassQuery =
                    "SELECT t.class, SUM(b.totalPrice) AS revenue " +
                    "FROM TRIP tr " +
                    "INNER JOIN TRAIN t ON tr.trainID = t.trainID " +
                    "INNER JOIN BOOKING b ON tr.tripID = b.tripID " +
                    "WHERE MONTH(tr.tripDate) = 5 " +
                    "GROUP BY t.class";
            PreparedStatement revenuePerClassStatement = connection.prepareStatement(revenuePerClassQuery);
            ResultSet revenuePerClassResult = revenuePerClassStatement.executeQuery();
            System.out.println("Total Revenue per Train Class:");
            while (revenuePerClassResult.next()) {
                String trainClass = revenuePerClassResult.getString("class");
                double revenue = revenuePerClassResult.getDouble("revenue");
                System.out.println("Class: " + trainClass + ", Revenue: " + revenue);
            }
            System.out.println();

            // Number of bookings per user
            String bookingsPerUserQuery =
                    "SELECT USERS.userID, USERS.userName, COUNT(*) AS numBookings " +
                    "FROM USERS " +
                    "JOIN BOOKING ON USERS.userID = BOOKING.userID " +
                    "GROUP BY USERS.userID, USERS.userName";

            PreparedStatement bookingsPerUserStatement = connection.prepareStatement(bookingsPerUserQuery);
            ResultSet bookingsPerUserResult = bookingsPerUserStatement.executeQuery();

            System.out.println("Number of Bookings per User:");
            while (bookingsPerUserResult.next()) {
                int userID = bookingsPerUserResult.getInt("userID");
                String userName = bookingsPerUserResult.getString("userName");
                int numBookings = bookingsPerUserResult.getInt("numBookings");
                System.out.println("UserID: " + userID + ", UserName: " + userName + ", NumBookings: " + numBookings);
            }
            System.out.println();

            // Total revenue per trip
            String revenuePerTripQuery =
                    "SELECT TRIP.tripID, TRIP.destination, TRIP.source, SUM(BOOKING.totalPrice) AS revenue " +
                    "FROM TRIP " +
                    "JOIN BOOKING ON TRIP.tripID = BOOKING.tripID " +
                    "GROUP BY TRIP.tripID, TRIP.destination, TRIP.source";

            PreparedStatement revenuePerTripStatement = connection.prepareStatement(revenuePerTripQuery);
            ResultSet revenuePerTripResult = revenuePerTripStatement.executeQuery();

            System.out.println("Total Revenue per Trip:");
            while (revenuePerTripResult.next()) {
                int tripID = revenuePerTripResult.getInt("tripID");
                String destination = revenuePerTripResult.getString("destination");
                String source = revenuePerTripResult.getString("source");
                double revenue = revenuePerTripResult.getDouble("revenue");
                System.out.println("TripID: " + tripID + ", Destination: " + destination + ", Source: " + source + ", Revenue: " + revenue);
            }
            System.out.println();

            // Number of bookings per train class
            String bookingsPerClassQuery =
                    "SELECT TRAIN.class, COUNT(*) AS numBookings " +
                    "FROM TRAIN " +
                    "JOIN SEATS ON TRAIN.trainID = SEATS.trainID " +
                    "JOIN BOOKING ON SEATS.seatID = BOOKING.seatID " +
                    "GROUP BY TRAIN.class";

            PreparedStatement bookingsPerClassStatement = connection.prepareStatement(bookingsPerClassQuery);
            ResultSet bookingsPerClassResult = bookingsPerClassStatement.executeQuery();

            System.out.println("Number of Bookings per Train Class:");
            while (bookingsPerClassResult.next()) {
                String trainClass = bookingsPerClassResult.getString("class");
                int numBookings = bookingsPerClassResult.getInt("numBookings");
                System.out.println("Class: " + trainClass + ", NumBookings: " + numBookings);
            }
            System.out.println();

            // Month with the highest total revenue in a year
            String highestRevenueMonthQuery =
                    "SELECT MONTH(tripDate) AS month, SUM(totalPrice) AS revenue " +
                    "FROM TRIP " +
                    "JOIN BOOKING ON TRIP.tripID = BOOKING.tripID " +
                    "WHERE YEAR(tripDate) = 2023 " +
                    "GROUP BY MONTH(tripDate) " +
                    "HAVING SUM(totalPrice) = ( " +
                    "  SELECT MAX(revenueSum) " +
                    "  FROM ( " +
                    "    SELECT SUM(totalPrice) AS revenueSum " +
                    "    FROM TRIP " +
                    "    JOIN BOOKING ON TRIP.tripID = BOOKING.tripID " +
                    "    WHERE YEAR(tripDate) = 2023 " +
                    "    GROUP BY MONTH(tripDate) " +
                    "  ) AS revenueByMonth " +
                    ")";

            PreparedStatement highestRevenueMonthStatement = connection.prepareStatement(highestRevenueMonthQuery);
            ResultSet highestRevenueMonthResult = highestRevenueMonthStatement.executeQuery();

            System.out.println("Month with the Highest Total Revenue:");
            while (highestRevenueMonthResult.next()) {
                int month = highestRevenueMonthResult.getInt("month");
                double revenue = highestRevenueMonthResult.getDouble("revenue");
                System.out.println("Month: " + month + ", Revenue: " + revenue);
            }

            // Close result sets and statements
            revenuePerClassResult.close();
            revenuePerClassStatement.close();
            bookingsPerUserResult.close();
            bookingsPerUserStatement.close();
            revenuePerTripResult.close();
            revenuePerTripStatement.close();
            bookingsPerClassResult.close();
            bookingsPerClassStatement.close();
            highestRevenueMonthResult.close();
            highestRevenueMonthStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Updating a train details (by admin)


}