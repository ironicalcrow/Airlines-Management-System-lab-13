import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class User {
    protected static List<Customer> customers = new ArrayList<>();
    protected static String[][] adminCredentials = new String[10][2];
    protected static int adminCount = 0;
    protected static Scanner scanner = new Scanner(System.in);

    static {
        initializeSystem();
    }

    public static void initializeSystem() {
        adminCredentials[adminCount][0] = "root";
        adminCredentials[adminCount][1] = "root";
        adminCount++;
        System.out.println("\n\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++\n");
    }

    public static int authenticateAdmin(String username, String password) {
        for (int i = 0; i < adminCount; i++) {
            if (username.equals(adminCredentials[i][0])) {
                if (password.equals(adminCredentials[i][1])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static String authenticatePassenger(String email, String password) {
        for (Customer c : Customer.getCustomerCollection()) {
            if (email.equals(c.getEmail())) {
                if (password.equals(c.getPassword())) {
                    return "1-" + c.getUserID();
                }
            }
        }
        return "0";
    }

    public static boolean registerAdmin(String username, String password) {
        if (adminCount >= adminCredentials.length) {
            System.out.println("Admin capacity reached!");
            return false;
        }

        for (int i = 0; i < adminCount; i++) {
            if (username.equals(adminCredentials[i][0])) {
                System.out.println("Username already exists!");
                return false;
            }
        }

        adminCredentials[adminCount][0] = username;
        adminCredentials[adminCount][1] = password;
        adminCount++;
        return true;
    }

    protected static void displayMainMenu() {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(d) Press 3 to Login as Passenger.");
        System.out.println("\t\t(e) Press 4 to Register as Passenger.");
        System.out.println("\t\t(f) Press 5 to Display the User Manual.");
        System.out.print("\t\tEnter the desired option:    ");
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
            displayAdminManual();
        } else {
            displayPassengerManual();
        }
    }

    private static void displayAdminManual() {
        System.out.println(
                "\n\n(1) Admin have the access to all users data...Admin can delete, update, add and can perform search for any customer...\n");
        System.out.println(
                "(2) In order to access the admin module, you've to get yourself register by pressing 2, when the main menu gets displayed...\n");
        System.out.println(
                "(3) Provide the required details i.e., name, email, id...Once you've registered yourself, press 1 to login as an admin... \n");
        System.out.println(
                "(4) Once you've logged in, admin dashboard will be displayed on the screen...From here on, you can select from variety of options...\n");
    }

    private static void displayPassengerManual() {
        System.out.println(
                "\n\n(1) Local user has the access to its data only...He/She won't be able to change/update other users data...\n");
        System.out.println(
                "(2) In order to access local users benefits, you've to get yourself register by pressing 4, when the main menu gets displayed...\n");
        System.out.println(
                "(3) Provide the details asked by the program to add you to the users list...Once you've registered yourself, press \"3\" to login as a passenger...\n");
    }

    public static List<Customer> getCustomers() {
        return customers;
    }
}