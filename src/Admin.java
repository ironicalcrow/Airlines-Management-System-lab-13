import java.util.Scanner;

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

    private static void showAdminDashboard(String username, boolean hasFullPrivileges) {
        int choice;
        do {
            System.out.printf("\n%sAdmin Dashboard (%s privileges)%n",
                    hasFullPrivileges ? "★ " : "",
                    hasFullPrivileges ? "FULL" : "LIMITED");

            System.out.println("1. View Flight Schedule");
            System.out.println("2. View Passenger List");

            if (hasFullPrivileges) {
                System.out.println("3. Manage Passengers");
                System.out.println("4. Manage Flights");
                System.out.println("5. Register New Admin");
            }

            System.out.println("0. Logout");
            System.out.print("Choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();
            Flight flight = new Flight();
            switch (choice) {
                case 1 -> flight.displayFlightSchedule();
                case 2 -> PassengerManager.viewPassengers(hasFullPrivileges);
                case 3 -> {
                    if (hasFullPrivileges) {
                        PassengerManager.managePassengers();
                    } else {
                        System.out.println("Insufficient privileges!");
                    }
                }
                case 4 -> {
                    if (hasFullPrivileges) {
                        FlightManager.manageFlights();
                    } else {
                        System.out.println("Insufficient privileges!");
                    }
                }
                case 5 -> {
                    if (hasFullPrivileges) {
                        registerNewAdmin();
                    } else {
                        System.out.println("Insufficient privileges!");
                    }
                }
                case 0 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void registerNewAdmin() {
        System.out.print("\nEnter new admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new admin password: ");
        String password = scanner.nextLine();

        adminCredentials[adminCount][0] = username;
        adminCredentials[adminCount][1] = password;
        adminCount++;
        System.out.println("Admin registered successfully!");
    }

    public static void handleRegistration() {
        System.out.print("\nEnter new admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new admin password: ");
        String password = scanner.nextLine();

        if (authenticateAdmin(username, password) != -1) {
            System.out.println("Username already exists!");
            return;
        }

        adminCredentials[adminCount][0] = username;
        adminCredentials[adminCount][1] = password;
        adminCount++;
        System.out.println("Admin registered successfully!");
    }
}