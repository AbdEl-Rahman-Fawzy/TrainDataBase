import java.sql.*;
import java.util.*;

class SystemCore {
    private Scanner scanner;
    private Connection connection;
    public SystemCore(){
        scanner = new Scanner(System.in);
        connectToDatabase();
    }
    private void connectToDatabase() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/train";
        String username = "root";
        String password = "Abdo@112233";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }
    public void AddUser(){
        System.out.println("Enter ID :");
        int Id=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Username :");
        String UserName=scanner.next();
        scanner.nextLine();
        System.out.println("Enter E-mail :");
        String Email=scanner.next();
        scanner.nextLine();
        System.out.println("Enter Password :");
        String Password=scanner.next();
        scanner.nextLine();
        System.out.println("Enter Country :");
        String Country=scanner.next();
        scanner.nextLine();
        System.out.println("Enter Phone number :");
        String PhoneNumber=scanner.next();
        scanner.nextLine();
        String InsertQuery = "INSERT INTO users (userID, userName, email, userPassword, country, phoneNumber) VALUES (?, ?, ?, ?, ?,?)";
        try{
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, Id);
            statement.setString(2, UserName);
            statement.setString(3, Email);
            statement.setString(4,Password );
            statement.setString(5, Country);
            statement.setString(6, PhoneNumber);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("User added to the database!");
            } else {
                System.out.println("Failed to add the trip to the database.");
            }
        }
        catch ( SQLException e){
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }

    }
    public void DisplayUser(){

        System.out.println("Existing Users:");
        String selectQuery = "SELECT * FROM users";

        try {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("userName");
                String email = resultSet.getString("email");
                String Country = resultSet.getString("country");
                String PhoneNumber = resultSet.getString("phoneNumber");
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Country: " + Country);
                System.out.println("Phone number: " + PhoneNumber);
                System.out.println();
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
    public void DisplayAdmin(){
        System.out.println("Existing Admins:");
        String selectQuery = "SELECT * FROM admininfo";

        try {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String adminname = resultSet.getString("adminName");
                String email = resultSet.getString("email");
                System.out.println("Adminname: " + adminname);
                System.out.println("Email: " + email);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }
    }
    public void AddTrain(){
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
        String InsertQuery = "INSERT INTO train (trainID, class, capacity, trainState) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, TrainId);
            statement.setString(2, Trainclass);
            statement.setInt(3, Capacity);
            statement.setInt(4, TrainState);
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
    public void DisplayTrain(){
        System.out.println("Existing Trains:");
        String selectQuery = "SELECT * FROM train";

        try {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int TrainId = resultSet.getInt("trainID");
                String Trainclass = resultSet.getString("class");
                int Capacity = resultSet.getInt("capacity");
                int TrainState = resultSet.getInt("trainState");
                int AdminId = resultSet.getInt("adminId");
                System.out.println("TrainId: " + TrainId);
                System.out.println("TrainClass: " + Trainclass);
                System.out.println("TrainCapacity: " + Capacity);
                System.out.println("TrainState: " + TrainState);
                System.out.println("AdminId: " + AdminId);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }
    }
    public void AddTrip(){
        System.out.println("Enter TripID :");
        int tripID = scanner.nextInt();
        scanner.nextLine();
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
        String tripDate = scanner.next();
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
        String InsertQuery = "INSERT INTO train (tripID, price, arrivalTime, departualTime, tripDate,destination,source,adminID,trainID) VALUES (?, ?, ?, ?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(InsertQuery);
            statement.setInt(1, tripID);
            statement.setInt(2,price);
            statement.setTime(3, arrivalTime) ;
            statement.setTime(4, departureTime);
            statement.setString(5, tripDate);
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
    public void DisplayTrip(){
        System.out.println("Existing Trips:");
        String selectQuery = "SELECT * FROM trip";

        try {
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int tripID = resultSet.getInt("tripID");
                int price = resultSet.getInt("price");
                Time arrivalTime = resultSet.getTime("arrivalTime");
                Time departureTime = resultSet.getTime("departualTime");
                String tripDate = resultSet.getString("tripDate");
                String destination = resultSet.getString("destination");
                String source = resultSet.getString("source");
                int AdminId = resultSet.getInt("adminID");
                int TrainId = resultSet.getInt("trainID");
                System.out.println("TripID: " + tripID);
                System.out.println("Price : " + price);
                System.out.println("Arrival Time : " + arrivalTime);
                System.out.println("Departure Time : " + departureTime);
                System.out.println("Trip Date : " + tripDate);
                System.out.println("Destination : " + destination);
                System.out.println("Source : " + source);
                System.out.println("AdminID : " + AdminId);
                System.out.println("TrainID : " + TrainId);
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while executing the SQL statement.");
            e.printStackTrace();
        }
    }

}
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemCore systemCore=new SystemCore();
        System.out.println("Press 1 to create a new user, 2 to add a new admin,3 to add and display trains, and 4 to add and display trips");
        int Choice = scanner.nextInt();
        if (Choice==1) {
            systemCore.AddUser();
            systemCore.DisplayUser();
        }
        if(Choice==2) {
            systemCore.AddAdmin();
            systemCore.DisplayAdmin();
       }
        if(Choice==3) {
            systemCore.AddTrain();
            systemCore.DisplayTrain();
        }
        if(Choice== 4){
            systemCore.AddTrip();
            systemCore.DisplayTrip();

        }
    }
}
