public class Admin extends User {
    public static void handleLogin() {
        System.out.print("\nEnter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        int authResult = authenticateAdmin(username, password);
        if (authResult == -1) {
            System.out.println("Invalid credentials!");
        } else if (authResult == 0) {
            System.out.println("Logged in with standard privileges");
            showAdminDashboard(username, false);
        } else {
            System.out.println("Logged in as " + username);
            showAdminDashboard(username, true);
        }
    }

    private static void showAdminDashboard(String username) {
        // Admin-specific operations
        int choice;
        do {
            System.out.println("\nAdmin Dashboard");
            System.out.println("1. Manage Passengers");
            System.out.println("2. Manage Flights");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> PassengerManager.managePassengers();
                case 2 -> FlightManager.manageFlights();
            }
        } while (choice != 0);
    }

    public static void handleRegistration() {
        RolesAndPermissions r1 = new RolesAndPermissions();
        System.out.print("\nEnter new admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new admin password: ");
        String password = scanner.nextLine();

        if (r1.isPrivilegedUserOrNot(username, password) != -1) {
            System.out.println("Username already exists!");
            return;
        }

        adminCredentials[adminCount][0] = username;
        adminCredentials[adminCount][1] = password;
        adminCount++;
        System.out.println("Admin registered successfully!");
    }
}