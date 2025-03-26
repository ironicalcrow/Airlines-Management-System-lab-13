import java.util.Scanner;

public class FlightManager {
    public static void manageFlights() {
        Flight f1 = new Flight();
        FlightReservation reservation = new FlightReservation();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nFlight Management");
            System.out.println("1. View Flight Schedule");
            System.out.println("2. View Passengers by Flight");
            System.out.println("3. View All Flight Passengers");
            System.out.println("4. Delete Flight");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> f1.displayFlightSchedule();
                case 2 -> {
                    f1.displayFlightSchedule();
                    System.out.print("Enter flight number: ");
                    reservation.displayRegisteredUsersForASpecificFlight(scanner.nextLine());
                }
                case 3 -> reservation.displayRegisteredUsersForAllFlight();
                case 4 -> {
                    f1.displayFlightSchedule();
                    System.out.print("Enter flight number to delete: ");
                    f1.deleteFlight(scanner.nextLine());
                }
            }
        } while (choice != 0);
    }
}