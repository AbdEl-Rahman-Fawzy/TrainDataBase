import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DatabaseGUI extends JFrame {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/traindb";
    private static final String username = "root";
    private static final String password = "Abdo@112233";
    private Connection connection;

    public void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/traindb";
        String username = "root";
        String password = "Abdo@112233";
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private JTextArea outputTextArea;

    public DatabaseGUI() {
        connectToDatabase();
        setTitle("Database GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the output text area
        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Create the "View Users" button
        JButton viewUsersButton = new JButton("View Users");
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsers();
            }
        });
        buttonPanel.add(viewUsersButton);

        // Create the "View Admins" button
        JButton viewAdminsButton = new JButton("View Admins");
        viewAdminsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAdmins();
            }
        });
        buttonPanel.add(viewAdminsButton);

        // Create the "View Trains" button
        JButton viewTrainsButton = new JButton("View Trains");
        viewTrainsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTrains();
            }
        });
        buttonPanel.add(viewTrainsButton);

        // Create the "View Trips" button
        JButton viewTripsButton = new JButton("View Trips");
        viewTripsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTrips();
            }
        });
        buttonPanel.add(viewTripsButton);

        JButton showReportButton = new JButton("Show Report");
        showReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReport();
            }
        });
        buttonPanel.add(showReportButton);

        // Create the "Add User" button
        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddUserWindow();
            }
        });
        buttonPanel.add(addUserButton);
        JButton addTripButton = new JButton("Add Trip");
        addTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddTripWindow();
            }
        });
        buttonPanel.add(addTripButton);

        JButton updateButton = new JButton("Update trip");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUpdateWindow();
            }
        });
        buttonPanel.add(updateButton);
        JButton deleteButton = new JButton("Delete trip");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDeleteWindow();
            }
        });
        buttonPanel.add(deleteButton);


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void viewUsers() {
        outputTextArea.setText("Existing Users:\n");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            String selectQuery = "SELECT * FROM USERS";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String userName = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                String phoneNumber = resultSet.getString("phoneNumber");
                outputTextArea.append("User ID: " + userID + "\n");
                outputTextArea.append("User Name: " + userName + "\n");
                outputTextArea.append("Email: " + email + "\n");
                outputTextArea.append("Country: " + country + "\n");
                outputTextArea.append("Phone Number: " + phoneNumber + "\n");
                outputTextArea.append("\n");
            }

            resultSet.close();
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }

    private void viewAdmins() {
        outputTextArea.setText("Existing Admins:\n");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            String selectQuery = "SELECT * FROM AdminInfo";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int adminID = resultSet.getInt("adminID");
                String adminName = resultSet.getString("adminName");
                String email = resultSet.getString("email");
                outputTextArea.append("Admin ID: " + adminID + "\n");
                outputTextArea.append("Admin Name: " + adminName + "\n");
                outputTextArea.append("Email: " + email + "\n");
                outputTextArea.append("\n");
            }

            resultSet.close();
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }

    private void viewTrains() {
        outputTextArea.setText("Existing Trains:\n");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            String selectQuery = "SELECT * FROM TRAIN";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int trainID = resultSet.getInt("trainID");
                String trainClass = resultSet.getString("class");
                int capacity = resultSet.getInt("capacity");
                int trainState = resultSet.getInt("trainState");
                int adminID = resultSet.getInt("adminID");
                outputTextArea.append("Train ID: " + trainID + "\n");
                outputTextArea.append("Train Class: " + trainClass + "\n");
                outputTextArea.append("Capacity: " + capacity + "\n");
                outputTextArea.append("Train State: " + trainState + "\n");
                outputTextArea.append("Admin ID: " + adminID + "\n");
                outputTextArea.append("\n");
            }

            resultSet.close();
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }

    private void viewTrips() {
        outputTextArea.setText("Existing Trips:\n");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            String selectQuery = "SELECT * FROM TRIP";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int tripID = resultSet.getInt("tripID");
                int price = resultSet.getInt("price");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Time departureTime = resultSet.getTime("departualTime");
                String tripDate = resultSet.getString("tripDate");
                String destination = resultSet.getString("destination");
                String source = resultSet.getString("source");
                int adminID = resultSet.getInt("adminID");
                int trainID = resultSet.getInt("trainID");
                outputTextArea.append("Trip ID: " + tripID + "\n");
                outputTextArea.append("Price: " + price + "\n");
                outputTextArea.append("Arrival Time: " + arrivalTime + "\n");
                outputTextArea.append("Departure Time: " + departureTime + "\n");
                outputTextArea.append("Trip Date: " + tripDate + "\n");
                outputTextArea.append("Destination: " + destination + "\n");
                outputTextArea.append("Source: " + source + "\n");
                outputTextArea.append("Admin ID: " + adminID + "\n");
                outputTextArea.append("Train ID: " + trainID + "\n");
                outputTextArea.append("\n");
            }

            resultSet.close();
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }

    private void openAddUserWindow() {
        JFrame addUserFrame = new JFrame("Add User");
        addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addUserFrame.setSize(400, 300);
        addUserFrame.setLocationRelativeTo(null);

        JPanel addUserPanel = new JPanel(new GridLayout(7, 2));

        JLabel userIDLabel = new JLabel("User ID:");
        JTextField userIDField = new JTextField();
        JLabel userNameLabel = new JLabel("User Name:");
        JTextField userNameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel countryLabel = new JLabel("Country:");
        JTextField countryField = new JTextField();
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        addUserPanel.add(userIDLabel);
        addUserPanel.add(userIDField);
        addUserPanel.add(userNameLabel);
        addUserPanel.add(userNameField);
        addUserPanel.add(emailLabel);
        addUserPanel.add(emailField);
        addUserPanel.add(countryLabel);
        addUserPanel.add(countryField);
        addUserPanel.add(phoneNumberLabel);
        addUserPanel.add(phoneNumberField);
        addUserPanel.add(passwordLabel);
        addUserPanel.add(passwordField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userID = Integer.parseInt(userIDField.getText());
                String userName = userNameField.getText();
                String email = emailField.getText();
                String password = passwordField.getText();
                String country = countryField.getText();
                String phoneNumber = phoneNumberField.getText();

                addUser(userID, userName, email, password, country, phoneNumber);
                addUserFrame.dispose();
            }
        });

        addUserPanel.add(new JLabel());
        addUserPanel.add(addButton);

        addUserFrame.add(addUserPanel);
        addUserFrame.setVisible(true);
    }

    private void addUser(int userID, String userName, String email, String password, String country, String phoneNumber) {
        String insertQuery = "INSERT INTO users (userID, userName, email, userPassword, country, phoneNumber) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, "Abdo@112233");
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, userID);
            statement.setString(2, userName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, country);
            statement.setString(6, phoneNumber);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                outputTextArea.setText("User added to the database!\n");
            } else {
                outputTextArea.setText("Failed to add the user to the database.\n");
            }
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }

    private void openAddTripWindow() {
        JFrame addTripFrame = new JFrame("Add Trip");
        addTripFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addTripFrame.setSize(400, 450);
        addTripFrame.setLocationRelativeTo(null);

        JPanel addTripPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tripIDLabel = new JLabel("Trip ID:");
        JTextField tripIDField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 0, tripIDLabel);
        addComponent(addTripPanel, gbc, 1, 0, tripIDField);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 1, priceLabel);
        addComponent(addTripPanel, gbc, 1, 1, priceField);

        JLabel arrivalTimeLabel = new JLabel("Arrival Time:");
        JTextField arrivalTimeField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 2, arrivalTimeLabel);
        addComponent(addTripPanel, gbc, 1, 2, arrivalTimeField);

        JLabel departureTimeLabel = new JLabel("Departure Time:");
        JTextField departureTimeField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 3, departureTimeLabel);
        addComponent(addTripPanel, gbc, 1, 3, departureTimeField);

        JLabel tripDateLabel = new JLabel("Trip Date:");
        JTextField tripDateField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 4, tripDateLabel);
        addComponent(addTripPanel, gbc, 1, 4, tripDateField);

        JLabel destinationLabel = new JLabel("Destination:");
        JTextField destinationField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 5, destinationLabel);
        addComponent(addTripPanel, gbc, 1, 5, destinationField);

        JLabel sourceLabel = new JLabel("Source:");
        JTextField sourceField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 6, sourceLabel);
        addComponent(addTripPanel, gbc, 1, 6, sourceField);

        JLabel adminIdLabel = new JLabel("Admin ID:");
        JTextField adminIdField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 7, adminIdLabel);
        addComponent(addTripPanel, gbc, 1, 7, adminIdField);

        JLabel trainIdLabel = new JLabel("Train ID:");
        JTextField trainIdField = new JTextField(15);
        addComponent(addTripPanel, gbc, 0, 8, trainIdLabel);
        addComponent(addTripPanel, gbc, 1, 8, trainIdField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tripID = Integer.parseInt(tripIDField.getText());
                int price = Integer.parseInt(priceField.getText());
                String arrivalTime = arrivalTimeField.getText();
                Time departureTime = Time.valueOf(departureTimeField.getText());
                Date tripDate = Date.valueOf(tripDateField.getText());
                String destination = destinationField.getText();
                String source = sourceField.getText();
                int adminID = Integer.parseInt(adminIdField.getText());
                int trainID = Integer.parseInt(trainIdField.getText());

                addTrip(tripID, price, arrivalTime, departureTime, tripDate, destination, source, adminID, trainID);
                addTripFrame.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addTripPanel.add(addButton, gbc);

        addTripFrame.add(addTripPanel);
        addTripFrame.setVisible(true);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, int gridx, int gridy, JComponent component) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private void addTrip(int tripID, int price, String arrivalTime, Time departureTime, Date tripDate, String destination, String source, int adminID, int trainID) {
        String insertQuery = "INSERT INTO trip (tripID, price, arrivalTime, departureTime, tripDate, destination, source, adminID, trainID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, tripID);
            statement.setInt(2, price);
            statement.setString(3, arrivalTime);
            statement.setTime(4, departureTime);
            statement.setDate(5, tripDate);
            statement.setString(6, destination);
            statement.setString(7, source);
            statement.setInt(8, adminID);
            statement.setInt(9, trainID);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                outputTextArea.setText("Trip added to the database!\n");
            } else {
                outputTextArea.setText("Failed to add the trip to the database.\n");
            }
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }

    private void openDeleteWindow() {
        JFrame deleteFrame = new JFrame("Delete Item");
        deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteFrame.setSize(400, 300);
        deleteFrame.setLocationRelativeTo(null);

        JPanel deletePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tripIDLabel = new JLabel("Trip ID:");
        JTextField tripIDField = new JTextField(15);
        addComponent(deletePanel, gbc, 0, 0, tripIDLabel);
        addComponent(deletePanel, gbc, 1, 0, tripIDField);


        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int itemID = Integer.parseInt(tripIDField.getText());
                deleteItem(itemID);
                deleteFrame.dispose();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        deletePanel.add(deleteButton, gbc);

        deleteFrame.add(deletePanel);
        deleteFrame.setVisible(true);
    }

    public void deleteItem(int tripID) {
        try {
            // First, delete the associated bookings
            PreparedStatement cancelBookingStatement = connection.prepareStatement(
                    "DELETE FROM BOOKING WHERE tripID = ?");
            cancelBookingStatement.setInt(1, tripID);

            int bookingsDeleted = cancelBookingStatement.executeUpdate();

            // If there are bookings associated with the item, proceed with deleting the item
            if (bookingsDeleted >= 0) {
                PreparedStatement cancelTripStatement = connection.prepareStatement(
                        "DELETE FROM TRIP WHERE tripID = ?");
                cancelTripStatement.setInt(1, tripID);

                int itemDeleted = cancelTripStatement.executeUpdate();

                if (itemDeleted > 0) {
                    System.out.println("Deletion successful! Item and associated bookings deleted.");
                } else {
                    System.out.println("Item not found or already deleted.");
                }
            } else {
                System.out.println("No bookings found for the item.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting item: " + e.getMessage());
        }
    }

    private void openUpdateWindow() {
        JFrame updateTripFrame = new JFrame("Update Trip");
        updateTripFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateTripFrame.setSize(400, 300);
        updateTripFrame.setLocationRelativeTo(null);

        JPanel updateTripPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel tripIDLabel = new JLabel("Trip ID:");
        JTextField tripIDField = new JTextField();
        JLabel newPriceLabel = new JLabel("New Price:");
        JTextField newPriceField = new JTextField();
        JLabel newArrivalTimeLabel = new JLabel("New Arrival Time:");
        JTextField newArrivalTimeField = new JTextField();

        inputPanel.add(tripIDLabel);
        inputPanel.add(tripIDField);
        inputPanel.add(newPriceLabel);
        inputPanel.add(newPriceField);
        inputPanel.add(newArrivalTimeLabel);
        inputPanel.add(newArrivalTimeField);

        updateTripPanel.add(inputPanel, BorderLayout.CENTER);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tripID = Integer.parseInt(tripIDField.getText());
                int newPrice = Integer.parseInt(newPriceField.getText());
                String newArrivalTime = newArrivalTimeField.getText();

                updateTrip(tripID, newPrice, newArrivalTime);
                updateTripFrame.dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(updateButton);

        updateTripPanel.add(buttonPanel, BorderLayout.SOUTH);

        updateTripFrame.add(updateTripPanel);
        updateTripFrame.setVisible(true);
    }

    private void updateTrip(int tripID, int newPrice, String newArrivalTime) {
        String updateQuery = "UPDATE TRIP SET price = ?, arrivalTime = ? WHERE tripID = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setInt(1, newPrice);
            statement.setString(2, newArrivalTime);
            statement.setInt(3, tripID);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                outputTextArea.setText("Trip updated successfully!\n");
            } else {
                outputTextArea.setText("Failed to update the trip.\n");
            }
        } catch (SQLException e) {
            outputTextArea.append("An error occurred while executing the SQL statement.\n");
            e.printStackTrace();
        }
    }
    public void showReport() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/traindb";
        String username = "root";
        String password = "Abdo@112233";

        StringBuilder reportBuilder = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Total revenue per train class
            String revenuePerClassQuery = "SELECT t.class, SUM(b.totalPrice) AS revenue " +
                    "FROM TRIP tr " +
                    "INNER JOIN TRAIN t ON tr.trainID = t.trainID " +
                    "INNER JOIN BOOKING b ON tr.tripID = b.tripID " +
                    "GROUP BY t.class";

            PreparedStatement revenuePerClassStatement = connection.prepareStatement(revenuePerClassQuery);
            ResultSet revenuePerClassResult = revenuePerClassStatement.executeQuery();

            reportBuilder.append("Total Revenue per Train Class:\n");
            while (revenuePerClassResult.next()) {
                String trainClass = revenuePerClassResult.getString("class");
                double revenue = revenuePerClassResult.getDouble("revenue");
                reportBuilder.append("Class: ").append(trainClass).append(", Revenue: ").append(revenue).append("\n");
            }
            reportBuilder.append("\n");

            // Number of bookings per user
            String bookingsPerUserQuery =
                    "SELECT USERS.userID, USERS.userName, COUNT(*) AS numBookings " +
                            "FROM USERS " +
                            "JOIN BOOKING ON USERS.userID = BOOKING.userID " +
                            "GROUP BY USERS.userID, USERS.userName";

            PreparedStatement bookingsPerUserStatement = connection.prepareStatement(bookingsPerUserQuery);
            ResultSet bookingsPerUserResult = bookingsPerUserStatement.executeQuery();

            reportBuilder.append("Number of Bookings per User:\n");
            while (bookingsPerUserResult.next()) {
                int userID = bookingsPerUserResult.getInt("userID");
                String userName = bookingsPerUserResult.getString("userName");
                int numBookings = bookingsPerUserResult.getInt("numBookings");
                reportBuilder.append("UserID: ").append(userID).append(", UserName: ").append(userName)
                        .append(", NumBookings: ").append(numBookings).append("\n");
            }
            reportBuilder.append("\n");

            // Total revenue per trip
            String revenuePerTripQuery = "SELECT TRIP.tripID, TRIP.destination, TRIP.source, SUM(BOOKING.totalPrice) AS revenue " +
                    "FROM TRIP " +
                    "JOIN BOOKING ON TRIP.tripID = BOOKING.tripID " +
                    "GROUP BY TRIP.tripID, TRIP.destination, TRIP.source";

            PreparedStatement revenuePerTripStatement = connection.prepareStatement(revenuePerTripQuery);
            ResultSet revenuePerTripResult = revenuePerTripStatement.executeQuery();

            reportBuilder.append("Total Revenue per Trip:\n");
            while (revenuePerTripResult.next()) {
                int tripID = revenuePerTripResult.getInt("tripID");
                String destination = revenuePerTripResult.getString("destination");
                String source = revenuePerTripResult.getString("source");
                double revenue = revenuePerTripResult.getDouble("revenue");
                reportBuilder.append("TripID: ").append(tripID).append(", Destination: ").append(destination)
                        .append(", Source: ").append(source).append(", Revenue: ").append(revenue).append("\n");
            }
            reportBuilder.append("\n");

            // Number of bookings per train class
            String bookingsPerClassQuery = "SELECT TRAIN.class, COUNT(*) AS numBookings " +
                    "FROM TRAIN " +
                    "JOIN SEATS ON TRAIN.trainID = SEATS.trainID " +
                    "JOIN BOOKING ON SEATS.seatID = BOOKING.seatID " +
                    "GROUP BY TRAIN.class";

            PreparedStatement bookingsPerClassStatement = connection.prepareStatement(bookingsPerClassQuery);
            ResultSet bookingsPerClassResult = bookingsPerClassStatement.executeQuery();

            reportBuilder.append("Number of Bookings per Train Class:\n");
            while (bookingsPerClassResult.next()) {
                String trainClass = bookingsPerClassResult.getString("class");
                int numBookings = bookingsPerClassResult.getInt("numBookings");
                reportBuilder.append("Class: ").append(trainClass).append(", NumBookings: ").append(numBookings).append("\n");
            }
            reportBuilder.append("\n");

            // Month with the highest total revenue in a year
            String highestRevenueMonthQuery = "SELECT MONTH(tripDate) AS month, SUM(totalPrice) AS revenue " +
                    "FROM TRIP " +
                    "JOIN BOOKING ON TRIP.tripID = BOOKING.tripID " +
                    "GROUP BY MONTH(tripDate) " +
                    "HAVING SUM(totalPrice) = ( " +
                    "  SELECT MAX(revenueSum) " +
                    "  FROM ( " +
                    "    SELECT SUM(totalPrice) AS revenueSum " +
                    "    FROM TRIP " +
                    "    JOIN BOOKING ON TRIP.tripID = BOOKING.tripID " +
                    "    GROUP BY MONTH(tripDate) " +
                    "  ) AS revenueByMonth " +
                    ")";

            PreparedStatement highestRevenueMonthStatement = connection.prepareStatement(highestRevenueMonthQuery);
            ResultSet highestRevenueMonthResult = highestRevenueMonthStatement.executeQuery();

            reportBuilder.append("Month with the Highest Total Revenue:\n");
            while (highestRevenueMonthResult.next()) {
                int month = highestRevenueMonthResult.getInt("month");
                double revenue = highestRevenueMonthResult.getDouble("revenue");
                reportBuilder.append("Month: ").append(month).append(", Revenue: ").append(revenue).append("\n");
            }

            // Set the report text in the reportTextArea
            outputTextArea.setText(reportBuilder.toString());

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





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseGUI gui = new DatabaseGUI();
            gui.setVisible(true);
        });
    }
}
