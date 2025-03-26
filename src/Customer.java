import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Customer {
    protected final String userID;
    protected String email;
    protected String name;
    protected String phone;
    protected final String password;
    protected String address;
    protected int age;
    public static final List<Customer> customerCollection = new ArrayList<>();

    protected Customer(String name, String email, String password, String phone, String address, int age) {
        this.userID = generateUserID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    protected Customer() {
        this.userID = null;
        this.name = null;
        this.email = null;
        this.password = null;
        this.phone = null;
        this.address = null;
        this.age = 0;
    }

    private String generateUserID() {
        return Integer.toString((int)(Math.random() * 900000) + 100000);
    }

    public static void addNewCustomer() {
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String name = read.nextLine();
        System.out.print("Enter your email: ");
        String email = read.nextLine();
        System.out.print("Enter your password: ");
        String password = read.nextLine();
        System.out.print("Enter your phone: ");
        String phone = read.nextLine();
        System.out.print("Enter your address: ");
        String address = read.nextLine();
        System.out.print("Enter your age: ");
        int age = read.nextInt();

        customerCollection.add(new RegularCustomer(name, email, password, phone, address, age));
    }

    public static boolean isUniqueData(String email) {
        return customerCollection.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    public static void displayCustomersData(boolean showHeader) {
        if (showHeader) displayHeader();
        int i = 0;
        for (Customer c : customerCollection) {
            System.out.println(c.toString(++i));
        }
    }

    protected static void displayHeader() {
        System.out.println("\n+------------+------------+----------------------------------+---------+-----------------------------+");
        System.out.println("| SerialNum  |   UserID   | Passenger Names                  | Age     | EmailID                     |");
        System.out.println("+------------+------------+----------------------------------+---------+-----------------------------+");
    }

    public abstract String toString(int i);

    public static void editUserInfo(String ID) {
        customerCollection.stream()
                .filter(c -> c.getUserID().equals(ID))
                .findFirst()
                .ifPresentOrElse(
                        c -> {
                            Scanner read = new Scanner(System.in);
                            System.out.print("\nEnter new name: ");
                            c.setName(read.nextLine());
                            System.out.print("Enter new email: ");
                            c.setEmail(read.nextLine());
                            System.out.print("Enter new phone: ");
                            c.setPhone(read.nextLine());
                            System.out.print("Enter new address: ");
                            c.setAddress(read.nextLine());
                            System.out.print("Enter new age: ");
                            c.setAge(read.nextInt());
                        },
                        () -> System.out.println("Customer not found!")
                );
    }

    public static List<Customer> getCustomerCollection() {
        return customerCollection;
    }
    public String randomIDDisplay(String randomID) {
        if (randomID == null || randomID.length() <= 3) {
            return randomID;
        }
        return randomID.substring(0, 3) + " " + randomID.substring(3);
    }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getUserID() { return userID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setAge(int age) { this.age = age; }
}