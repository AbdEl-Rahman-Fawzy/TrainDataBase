import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("If you want GUI interface, enter 1; otherwise, enter any other input for the console interface.");
        int inp = scanner.nextInt();
        scanner.nextLine();

        if (inp == 1) {
            SwingUtilities.invokeLater(() -> {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DatabaseGUI gui = new DatabaseGUI();
                gui.setVisible(true);
            });
        }

        else {
            System.out.println("Welcome to the System!");

            // Database connection details
            String url = "jdbc:mysql://localhost:3306/traindb";
            String username = "root";
            String password = "Abdo@112233";
            SystemCore systemCore = new SystemCore(url, username, password);

            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Add User");
                System.out.println("2. Add Admin");
                System.out.println("3. Add Train");
                System.out.println("4. Add Trip");
                System.out.println("5. Book Trip");
                System.out.println("6. Cancel Trip");
                System.out.println("7. Update Trip");
                System.out.println("8. Update Train");
                System.out.println("9. Select Seat");
                System.out.println("10. Display Users");
                System.out.println("11. report showing");
                System.out.println("0. Exit");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> systemCore.AddUser();
                    case 2 -> systemCore.AddAdmin();
                    case 3 -> systemCore.AddTrain();
                    case 4 -> systemCore.AddTrip();
                    case 5 -> {
                        System.out.println("Enter Booking ID:");
                        int bookingId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Trip ID:");
                        int tripId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter User ID:");
                        int userId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Seat ID:");
                        int seatId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Booking Date:");
                        String bookingDate = scanner.nextLine();
                        systemCore.bookTrip(bookingId, tripId, userId, seatId, bookingDate);
                    }
                    case 6 -> {
                        System.out.println("Enter Trip ID to cancel:");
                        int cancelTripId = scanner.nextInt();
                        scanner.nextLine();
                        systemCore.cancelTrip(cancelTripId);
                    }
                    case 7 -> {
                        System.out.println("Enter Admin ID:");
                        int adminId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Trip ID to update:");
                        int updateTripId = scanner.nextInt();
                        scanner.nextLine();
                        systemCore.Update_trip(adminId, updateTripId);
                    }
                    case 8 -> {
                        System.out.println("Enter Train ID to update:");
                        int updateTrainId = scanner.nextInt();
                        scanner.nextLine();
                        systemCore.UpdateTrain(updateTrainId);
                    }
                    case 9 -> {
                        System.out.println("Enter Train ID:");
                        int selectSeatTrainId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Date (YYYY-MM-DD):");
                        String selectSeatDate = scanner.nextLine();
                        System.out.println("Enter Source:");
                        String selectSeatSource = scanner.nextLine();
                        System.out.println("Enter Destination:");
                        String selectSeatDest = scanner.nextLine();
                        systemCore.selectseat(selectSeatTrainId, java.sql.Date.valueOf(selectSeatDate), selectSeatSource, selectSeatDest);
                    }
                    case 10 -> systemCore.displayUsers();
                    case 11 -> systemCore.showReport();
                    case 0 -> {
                        System.out.println("Goodbye!");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}