public class Passenger extends User {
    public static void handleLogin() {
        RolesAndPermissions r1 = new RolesAndPermissions();
        System.out.print("\nEnter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String[] result = r1.isPassengerRegistered(email, password).split("-");
        if (Integer.parseInt(result[0]) == 1) {
            System.out.println("Logged in successfully!");
            showPassengerDashboard(result[1]);
        } else {
            System.out.println("Invalid credentials!");
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