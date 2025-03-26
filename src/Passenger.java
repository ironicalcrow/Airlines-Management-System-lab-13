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
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> FlightReservation.bookFlight(userId);
                case 2 -> FlightReservation.viewBookings(userId);
                case 3 -> Customer.editProfile(userId);
            }
        } while (choice != 0);
    }

    public static void handleRegistration() {
        Customer.addNewCustomer();
    }
}