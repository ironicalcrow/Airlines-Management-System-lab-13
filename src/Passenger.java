import java.util.Scanner;

public class Passenger extends User {
    private static Scanner scanner = new Scanner(System.in);

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
                case 2 -> viewBookings(userId);
                case 3 -> editUserProfile(userId);  // Changed from editProfile to editUserProfile
                case 4 -> cancelFlight(userId);
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void editUserProfile(String userId) {
        for (Customer customer : Customer.getCustomerCollection()) {
            if (customer.getUserID().equals(userId)) {
                System.out.print("\nEnter new name: ");
                customer.setName(scanner.nextLine());
                System.out.print("Enter new email: ");
                customer.setEmail(scanner.nextLine());
                System.out.print("Enter new phone: ");
                customer.setPhone(scanner.nextLine());
                System.out.print("Enter new address: ");
                customer.setAddress(scanner.nextLine());
                System.out.print("Enter new age: ");
                customer.setAge(scanner.nextInt());
                scanner.nextLine(); // consume newline
                System.out.println("Profile updated successfully!");
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    private static void bookFlight(String userId) {
        Flight flight = new Flight();
        flight.displayFlightSchedule();
        System.out.print("\nEnter flight number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter number of tickets: ");
        int tickets = scanner.nextInt();
        scanner.nextLine();

        Customer.getCustomerCollection().stream()
                .filter(c -> c.getUserID().equals(userId) && c instanceof RegularCustomer)
                .findFirst()
                .ifPresent(customer -> {
                    FlightReservation.bookFlight(flightNumber, tickets, String.valueOf((RegularCustomer) customer));
                });
    }

    private static void viewBookings(String userId) {
        FlightReservation flightReservation = new FlightReservation();
        Customer.getCustomerCollection().stream()
                .filter(c -> c.getUserID().equals(userId) && c instanceof RegularCustomer)
                .findFirst()
                .ifPresent(customer -> {
                    flightReservation.displayFlightsRegisteredByOneUser(String.valueOf((RegularCustomer) customer));
                });
    }

    private static void cancelFlight(String userId) {
        Customer.getCustomerCollection().stream()
                .filter(c -> c.getUserID().equals(userId) && c instanceof RegularCustomer)
                .findFirst()
                .ifPresent(customer -> {
                    FlightReservation.cancelFlight(String.valueOf((RegularCustomer) customer));
                });
    }

    public static void handleRegistration() {
        Customer.addNewCustomer();
    }
}