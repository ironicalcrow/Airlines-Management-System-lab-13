import java.util.Scanner;

public class Passenger extends User {
    public static void handleLogin() {
        System.out.print("\nEnter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String authResult = authenticatePassenger(email, password);
        if (authResult.equals("0")) {
            System.out.println("Invalid credentials!");
        } else {
            String userId = authResult.split("-")[1];
            System.out.println("Logged in successfully!");
            showPassengerDashboard(userId);
        }
    }

    private static void showPassengerDashboard(String userId) {
        int choice;
        do {
            System.out.println("\nPassenger Dashboard");
            System.out.println("1. Book Flight");
            System.out.println("2. View Bookings");
            System.out.println("3. Update Profile");
            System.out.println("4. Cancel Booking");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> bookFlight(userId);
                case 2 -> FlightReservation.viewBookings(userId);
                case 3 -> Customer.editProfile(userId);
                case 4 -> FlightReservation.cancelFlight(userId);
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void bookFlight(String userId) {
        Flight flight = new Flight();
        flight.displayFlightSchedule();
        System.out.print("\nEnter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter number of tickets: ");
        int tickets = scanner.nextInt();
        scanner.nextLine();
        FlightReservation.bookFlight(flightNumber, tickets, userId);
    }

    public static void handleRegistration() {
        Customer.addNewCustomer();
    }
}