import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FlightReservation implements DisplayClass {
    private static Flight flight = new Flight();
    private static int flightIndexInFlightList;

    public static void bookFlight(String flightNo, int numOfTickets, String userID) {
        boolean isFound = false;
        for (Flight f1 : flight.getFlightList()) {
            if (flightNo.equalsIgnoreCase(f1.getFlightNumber())) {
                for (Customer customer : Customer.getCustomerCollection()) {
                    if (userID.equals(customer.getUserID())) {
                        isFound = true;
                        f1.setNoOfSeatsInTheFlight(f1.getNoOfSeats() - numOfTickets);

                        if (customer instanceof RegularCustomer) {
                            RegularCustomer regCustomer = (RegularCustomer) customer;

                            if (!f1.isCustomerAlreadyAdded(f1.getListOfRegisteredCustomersInAFlight(), customer)) {
                                f1.addNewCustomerToFlight(customer);
                            }

                            if (isFlightAlreadyAddedToCustomerList(regCustomer.getFlightsRegisteredByUser(), f1)) {
                                addNumberOfTicketsToAlreadyBookedFlight(regCustomer, numOfTickets);
                                int index = getFlightIndex(flight.getFlightList(), flight);
                                if (index != -1) {
                                    regCustomer.addTicketsToExistingFlight(index, numOfTickets);
                                }
                            } else {
                                regCustomer.addNewFlight(f1, numOfTickets);
                            }
                        }
                        break;
                    }
                }
            }
        }
        if (!isFound) {
            System.out.println("Invalid Flight Number...! No flight with the ID \"" + flightNo + "\" was found...");
        } else {
            System.out.printf("\n %50s You've booked %d tickets for Flight \"%5s\"...", "", numOfTickets, flightNo.toUpperCase());
        }
    }

    public static void cancelFlight(String userID) {
        String flightNum = "";
        Scanner read = new Scanner(System.in);
        int index = 0, ticketsToBeReturned;
        boolean isFound = false;
        FlightReservation fl= new FlightReservation();

        for (Customer customer : Customer.getCustomerCollection()) {
            if (userID.equals(customer.getUserID()) && customer instanceof RegularCustomer) {
                RegularCustomer regCustomer = (RegularCustomer) customer;
                List<Flight> flights = regCustomer.getFlightsRegisteredByUser();

                if (flights.size() != 0) {
                    System.out.printf("%50s %s Here is the list of all the Flights registered by you %s", " ", "++++++++++++++", "++++++++++++++");
                    fl.displayFlightsRegisteredByOneUser(userID);
                    System.out.print("Enter the Flight Number of the Flight you want to cancel : ");
                    flightNum = read.nextLine();
                    System.out.print("Enter the number of tickets to cancel : ");
                    int numOfTickets = read.nextInt();

                    Iterator<Flight> flightIterator = flights.iterator();
                    while (flightIterator.hasNext()) {
                        Flight flight = flightIterator.next();
                        if (flightNum.equalsIgnoreCase(flight.getFlightNumber())) {
                            isFound = true;
                            int numOfTicketsForFlight = regCustomer.getNumOfTicketsBookedByUser().get(index);

                            while (numOfTickets > numOfTicketsForFlight) {
                                System.out.print("ERROR!!! Number of tickets cannot be greater than " + numOfTicketsForFlight + " for this flight. Please enter the number of tickets again : ");
                                numOfTickets = read.nextInt();
                            }

                            if (numOfTicketsForFlight == numOfTickets) {
                                ticketsToBeReturned = flight.getNoOfSeats() + numOfTicketsForFlight;
                                regCustomer.getNumOfTicketsBookedByUser().remove(index);
                                flightIterator.remove();
                            } else {
                                ticketsToBeReturned = numOfTickets + flight.getNoOfSeats();
                                regCustomer.getNumOfTicketsBookedByUser().set(index, (numOfTicketsForFlight - numOfTickets));
                            }
                            flight.setNoOfSeatsInTheFlight(ticketsToBeReturned);
                            break;
                        }
                        index++;
                    }
                } else {
                    System.out.println("No Flight Has been Registered by you with ID \"" + flightNum.toUpperCase() + "\".....");
                }

                if (!isFound) {
                    System.out.println("ERROR!!! Couldn't find Flight with ID \"" + flightNum.toUpperCase() + "\".....");
                }
            }
        }
    }

    private static void addNumberOfTicketsToAlreadyBookedFlight(RegularCustomer customer, int numOfTickets) {
        int newNumOfTickets = customer.getNumOfTicketsBookedByUser().get(flightIndexInFlightList) + numOfTickets;
        customer.getNumOfTicketsBookedByUser().set(flightIndexInFlightList, newNumOfTickets);
    }

    private static boolean isFlightAlreadyAddedToCustomerList(List<Flight> flightList, Flight flight) {
        for (int i = 0; i < flightList.size(); i++) {
            if (flightList.get(i).getFlightNumber().equalsIgnoreCase(flight.getFlightNumber())) {
                flightIndexInFlightList = i;
                return true;
            }
        }
        return false;
    }

    private String flightStatus(Flight flight) {
        return flight.getFlightList().stream()
                .anyMatch(f -> f.getFlightNumber().equalsIgnoreCase(flight.getFlightNumber()))
                ? "As Per Schedule" : "Cancelled";
    }

    public String toString(int serialNum, Flight flights, Customer customer) {
        if (customer instanceof RegularCustomer) {
            RegularCustomer regCustomer = (RegularCustomer) customer;
            return String.format("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  | %-10s |",
                    serialNum, flights.getFlightSchedule(), flights.getFlightNumber(),
                    regCustomer.getNumOfTicketsBookedByUser().get(serialNum - 1),
                    flights.getFromWhichCity(), flights.getToWhichCity(),
                    flights.fetchArrivalTime(), flights.getFlightTime(),
                    flights.getGate(), flightStatus(flights));
        }
        return "";
    }

    @Override
    public void displayFlightsRegisteredByOneUser(String userID) {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO |  Booked Tickets  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |  FLIGHT STATUS  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");

        for (Customer customer : Customer.getCustomerCollection()) {
            if (userID.equals(customer.getUserID()) && customer instanceof RegularCustomer) {
                RegularCustomer regCustomer = (RegularCustomer) customer;
                List<Flight> flights = regCustomer.getFlightsRegisteredByUser();

                for (int i = 0; i < flights.size(); i++) {
                    System.out.println(toString((i + 1), flights.get(i), customer));
                    System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+-----------------+\n");
                }
            }
        }
    }

    public String toString(int serialNum, Customer customer, int index) {
        if (customer instanceof RegularCustomer) {
            RegularCustomer regCustomer = (RegularCustomer) customer;
            return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |       %-7s  |",
                    "", (serialNum + 1), customer.randomIDDisplay(customer.getUserID()),
                    customer.getName(), customer.getAge(), customer.getEmail(),
                    customer.getAddress(), customer.getPhone(),
                    regCustomer.getNumOfTicketsBookedByUser().get(index));
        }
        return "";
    }

    @Override
    public void displayHeaderForUsers(Flight flight, List<Customer> customers) {
        System.out.printf("\n%65s Displaying Registered Customers for Flight No. \"%-6s\" %s \n\n",
                "+++++++++++++", flight.getFlightNumber(), "+++++++++++++");
        System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
        System.out.printf("%10s| SerialNum  |   UserID   | Passenger Names                  | Age     | EmailID\t\t       | Home Address\t\t\t     | Phone Number\t       | Booked Tickets |%n", "");
        System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");

        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer instanceof RegularCustomer) {
                RegularCustomer regCustomer = (RegularCustomer) customer;
                int flightIndex = getFlightIndex(regCustomer.getFlightsRegisteredByUser(), flight);
                if (flightIndex != -1) {
                    System.out.println(toString(i, customer, flightIndex));
                    System.out.printf("%10s+------------+------------+----------------------------------+---------+-----------------------------+-------------------------------------+-------------------------+----------------+\n", "");
                }
            }
        }
    }


    @Override
    public void displayRegisteredUsersForAllFlight() {
        System.out.println();
        for (Flight flight : flight.getFlightList()) {
            List<Customer> customers = flight.getListOfRegisteredCustomersInAFlight();
            if (!customers.isEmpty()) {
                displayHeaderForUsers(flight, customers);
            }
        }
    }

    private static int getFlightIndex(List<Flight> flights, Flight flight) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightNumber().equals(flight.getFlightNumber())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void displayRegisteredUsersForASpecificFlight(String flightNum) {
        System.out.println();
        for (Flight flight : flight.getFlightList()) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                List<Customer> customers = flight.getListOfRegisteredCustomersInAFlight();
                if (!customers.isEmpty()) {
                    displayHeaderForUsers(flight, customers);
                }
                return;
            }
        }
        System.out.println("No passengers found for flight " + flightNum);
    }

    public static void viewBookings(String userId) {
        new FlightReservation().displayFlightsRegisteredByOneUser(userId);
    }
}