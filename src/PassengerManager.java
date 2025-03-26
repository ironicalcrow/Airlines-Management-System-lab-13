import java.util.Scanner;

public class PassengerManager {
    public static void managePassengers() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nPassenger Management");
            System.out.println("1. Add Passenger");
            System.out.println("2. Search Passenger");
            System.out.println("3. Update Passenger");
            System.out.println("4. Delete Passenger");
            System.out.println("5. View All Passengers");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> Customer.addNewCustomer(); // Static method
                case 2 -> {
                    Customer.displayCustomersData(false);
                    System.out.print("Enter CustomerID to search: ");
                    String searchId = scanner.nextLine();
                    searchPassenger(searchId);
                }
                case 3 -> {
                    Customer.displayCustomersData(false);
                    System.out.print("Enter CustomerID to update: ");
                    Customer.editUserInfo(scanner.nextLine());
                }
                case 4 -> {
                    Customer.displayCustomersData(false);
                    System.out.print("Enter CustomerID to delete: ");
                    deletePassenger(scanner.nextLine());
                }
                case 5 -> Customer.displayCustomersData(false);
            }
        } while (choice != 0);
    }

    private static void searchPassenger(String id) {
        Customer.getCustomerCollection().stream()
                .filter(c -> c.getUserID().equals(id))
                .findFirst()
                .ifPresentOrElse(
                        c -> System.out.println(c.toString(1)),
                        () -> System.out.println("Passenger not found!")
                );
    }

    private static void deletePassenger(String id) {
        if (Customer.getCustomerCollection().removeIf(c -> c.getUserID().equals(id))) {
            System.out.println("Passenger deleted successfully!");
        } else {
            System.out.println("Passenger not found!");
        }
    }

    public static void viewPassengers(boolean showFullDetails) {
        Customer.displayCustomersData(!showFullDetails);
    }
}