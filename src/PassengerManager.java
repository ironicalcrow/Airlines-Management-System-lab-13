import java.util.Scanner;

public class PassengerManager {
    public static void managePassengers() {
        Customer c1 = new Customer();
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
                case 1 -> c1.addNewCustomer();
                case 2 -> {
                    c1.displayCustomersData(false);
                    System.out.print("Enter CustomerID to search: ");
                    c1.searchUser(scanner.nextLine());
                }
                case 3 -> {
                    c1.displayCustomersData(false);
                    System.out.print("Enter CustomerID to update: ");
                    c1.editUserInfo(scanner.nextLine());
                }
                case 4 -> {
                    c1.displayCustomersData(false);
                    System.out.print("Enter CustomerID to delete: ");
                    c1.deleteUser(scanner.nextLine());
                }
                case 5 -> c1.displayCustomersData(false);
            }
        } while (choice != 0);
    }
}