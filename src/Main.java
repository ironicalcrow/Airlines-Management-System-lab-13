import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DestinationChart chart = DestinationChart.getInstance();
        Destination[] destinations = {
                new Destination(24.871940, 66.988060, "Karachi"),
                new Destination(13.921430, 100.595337, "Bangkok"),
                new Destination(-6.174760, 106.827072, "Jakarta"),
                new Destination(33.607587, 73.100316, "Islamabad"),
                new Destination(40.642422, -73.781749, "New York City"),
                new Destination(31.521139, 74.406519, "Lahore"),
                new Destination(35.919108, 74.332838, "Gilgit Baltistan"),
                new Destination(21.683647, 39.152862, "Jeddah"),
                new Destination(24.977080, 46.688942, "Riyadh"),
                new Destination(28.555764, 77.096520, "New Delhi"),
                new Destination(22.285005, 114.158339, "Hong Kong"),
                new Destination(40.052121, 116.609609, "Beijing"),
                new Destination(35.550899, 139.780683, "Tokyo"),
                new Destination(2.749914, 101.707160, "Kuala Lumpur"),
                new Destination(-33.942028, 151.174304, "Sydney"),
                new Destination(-37.671812, 144.846079, "Melbourne"),
                new Destination(-33.968879, 18.596982, "Cape Town"),
                new Destination(40.476938, -3.569428, "Madrid"),
                new Destination(53.424077, -6.256792, "Dublin"),
                new Destination(25.936834, 27.925890, "Johannesburg"),
                new Destination(51.504473, 0.052271, "London"),
                new Destination(33.942912, -118.406829, "Los Angeles"),
                new Destination(-27.388925, 153.116751, "Brisbane"),
                new Destination(52.308100, 4.764170, "Amsterdam"),
                new Destination(59.651236, 17.924793, "Stockholm"),
                new Destination(50.050085, 8.571911, "Frankfurt"),
                new Destination(25.066471, 121.551638, "New Taipei City"),
                new Destination(-22.812160, -43.248636, "Rio de Janeiro"),
                new Destination(37.558773, 126.802822, "Seoul"),
                new Destination(35.462819, 139.637008, "Yokohama"),
                new Destination(39.951898, 32.688792, "Ankara"),
                new Destination(33.368202, -7.580998, "Casablanca"),
                new Destination(22.633977, 113.809360, "Shenzhen"),
                new Destination(33.264824, 44.232014, "Baghdad"),
                new Destination(40.232302, -85.637150, "Alexandria"),
                new Destination(18.579019, 73.908572, "Pune"),
                new Destination(31.145326, 121.804512, "Shanghai"),
                new Destination(23.847177, 90.404133, "Dhaka"),
                new Destination(48.354327, 11.788680, "Munich"),
                new Destination(56.435749, -3.371675, "Perth"),
                new Destination(21.038103, -86.875259, "Mexico"),
                new Destination(32.733089, -117.194514, "California"),
                new Destination(34.564296, 69.211574, "Kabul"),
                new Destination(47.604505, -122.330604, "Yangon"),
                new Destination(17.981829, 102.565684, "Lagos"),
                new Destination(-33.394795, -70.790183, "Santiago"),
                new Destination(29.239250, 47.971575, "Kuwait"),
                new Destination(35.696000, 51.401000, "Tehran"),
                new Destination(60.013492, 29.722189, "Saint Petersburg"),
                new Destination(21.219185, 105.803967, "Hanoi"),
                new Destination(32.328361, 74.215310, "Sialkot"),
                new Destination(52.554316, 13.291213, "Berlin"),
                new Destination(48.999560, 2.539274, "Paris"),
                new Destination(25.249869, 55.366483, "Dubai")
        };
        for (Destination d : destinations) {
            chart.add(d);
        }
        User.initializeSystem();

        int choice;
        do {
            User.displayMainMenu();
            choice = User.scanner.nextInt();
            User.scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> Admin.handleLogin();
                case 2 -> Admin.handleRegistration();
                case 3 -> Passenger.handleLogin();
                case 4 -> Passenger.handleRegistration();
                case 5 -> User.manualInstructions();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}