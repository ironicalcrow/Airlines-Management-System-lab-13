import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User {
    protected static String[][] adminCredentials = new String[10][2];
    protected static List<Customer> customers = new ArrayList<>();
    protected static int adminCount = 1;
    protected static Scanner scanner = new Scanner(System.in);

    // Common initialization
    public static void initializeSystem() {
        adminCredentials[0][0] = "root";
        adminCredentials[0][1] = "root";
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++\n");
    }

    // Common display methods
    protected static void displayMainMenu() {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(d) Press 3 to Login as Passenger.");
        System.out.println("\t\t(e) Press 4 to Register as Passenger.");
        System.out.println("\t\t(f) Press 5 to Display the User Manual.");
        System.out.print("\t\tEnter the desired option:    ");
    }

    // Common getters
    public static List<Customer> getCustomers() {
        return customers;
    }
    public static void manualInstructions() {
        Scanner read = new Scanner(System.in);
        System.out.printf("%n%n%50s %s Welcome to BAV Airlines User Manual %s", "", "+++++++++++++++++",
                "+++++++++++++++++");
        System.out.println("\n\n\t\t(a) Press 1 to display Admin Manual.");
        System.out.println("\t\t(b) Press 2 to display User Manual.");
        System.out.print("\nEnter the desired option :    ");
        int choice = read.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("ERROR!!! Invalid entry...Please enter a value either 1 or 2....Enter again....");
            choice = read.nextInt();
        }
        if (choice == 1) {
            System.out.println(
                    "\n\n(1) Admin have the access to all users data...Admin can delete, update, add and can perform search for any customer...\n");
            // ... [rest of the original admin manual instructions]
        } else {
            System.out.println(
                    "\n\n(1) Local user has the access to its data only...He/She won't be able to change/update other users data...\n");
            // ... [rest of the original user manual instructions]
        }
    }
}