import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Flight extends FlightDistance {
    private final String flightSchedule;
    private final String flightNumber;
    private final String fromWhichCity;
    private final String gate;
    private final String toWhichCity;
    private double distanceInMiles;
    private double distanceInKm;
    private String flightTime;
    private int numOfSeatsInTheFlight;
    private List<Customer> listOfRegisteredCustomersInAFlight;
    private int customerIndex;
    private static int nextFlightDay = 0;
    private static final List<Flight> flightList = new ArrayList<>();

    Flight() {
        this.flightSchedule = null;
        this.flightNumber = null;
        this.numOfSeatsInTheFlight = 0;
        this.toWhichCity = null;
        this.fromWhichCity = null;
        this.gate = null;
        this.listOfRegisteredCustomersInAFlight = new ArrayList<>();
    }

    Flight(String flightSchedule, String flightNumber, int numOfSeatsInTheFlight,
           Destination[] chosenDestinations, double[] distanceBetweenTheCities, String gate) {
        this.flightSchedule = flightSchedule;
        this.flightNumber = flightNumber;
        this.numOfSeatsInTheFlight = numOfSeatsInTheFlight;
        this.fromWhichCity = chosenDestinations[0].getCity();
        this.toWhichCity = chosenDestinations[1].getCity();
        this.distanceInMiles = distanceBetweenTheCities[0];
        this.distanceInKm = distanceBetweenTheCities[1];
        this.flightTime = calculateFlightTime(distanceInMiles);
        this.listOfRegisteredCustomersInAFlight = new ArrayList<>();
        this.gate = gate;
    }

    public void flightScheduler() {
        int numOfFlights = 15;
        RandomGenerator r1 = RandomGenerator.getInstance();
        for (int i = 0; i < numOfFlights; i++) {
            Destination[] chosenDestinations = r1.randomDestinations();
            double[] distances = calculateDistance(
                    chosenDestinations[0].getLat(),
                    chosenDestinations[0].getLng(),
                    chosenDestinations[1].getLat(),
                    chosenDestinations[1].getLng()
            );
            String schedule = createNewFlightsAndTime();
            String number = r1.randomFlightNumbGen(2, 1).toUpperCase();
            int seats = r1.randomNumOfSeats();
            String gate = r1.randomFlightNumbGen(1, 30).toUpperCase();

            flightList.add(new Flight(schedule, number, seats, chosenDestinations, distances, gate));
        }
    }

    void addNewCustomerToFlight(Customer customer) {
        if (customer instanceof RegularCustomer) {
            this.listOfRegisteredCustomersInAFlight.add(customer);
        }
    }

    void addTicketsToExistingCustomer(Customer customer, int numOfTickets) {
        if (customer instanceof RegularCustomer) {
            RegularCustomer regCustomer = (RegularCustomer) customer;
            regCustomer.addTicketsToExistingFlight(customerIndex, numOfTickets);
        }
    }

    boolean isCustomerAlreadyAdded(List<Customer> customersList, Customer customer) {
        for (int i = 0; i < customersList.size(); i++) {
            if (customersList.get(i).getUserID().equals(customer.getUserID())) {
                customerIndex = i;
                return true;
            }
        }
        return false;
    }

    public String calculateFlightTime(double distanceBetweenTheCities) {
        double groundSpeed = 450;
        double time = (distanceBetweenTheCities / groundSpeed);
        String timeInString = String.format("%.4s", time);
        String[] timeArray = timeInString.replace('.', ':').split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        int modulus = minutes % 5;

        if (modulus < 3) {
            minutes -= modulus;
        } else {
            minutes += 5 - modulus;
        }
        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }
        if (hours <= 9 && Integer.toString(minutes).length() == 1) {
            return String.format("0%s:%s0", hours, minutes);
        } else if (hours <= 9 && Integer.toString(minutes).length() > 1) {
            return String.format("0%s:%s", hours, minutes);
        } else if (hours > 9 && Integer.toString(minutes).length() == 1) {
            return String.format("%s:%s0", hours, minutes);
        } else {
            return String.format("%s:%s", hours, minutes);
        }
    }

    public String fetchArrivalTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a ");
        LocalDateTime departureDateTime = LocalDateTime.parse(flightSchedule, formatter);
        String[] flightTime = getFlightTime().split(":");
        int hours = Integer.parseInt(flightTime[0]);
        int minutes = Integer.parseInt(flightTime[1]);
        LocalDateTime arrivalTime = departureDateTime.plusHours(hours).plusMinutes(minutes);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm a");
        return arrivalTime.format(formatter1);
    }

    void deleteFlight(String flightNumber) {
        Iterator<Flight> list = flightList.iterator();
        while (list.hasNext()) {
            if (list.next().getFlightNumber().equalsIgnoreCase(flightNumber)) {
                list.remove();
                displayFlightSchedule();
                return;
            }
        }
        System.out.println("Flight with given Number not found...");
    }

    @Override
    public double[] calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance = Math.sin(degreeToRadian(lat1)) * Math.sin(degreeToRadian(lat2))
                + Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) * Math.cos(degreeToRadian(theta));
        distance = Math.acos(distance);
        distance = radianToDegree(distance);
        distance = distance * 60 * 1.1515;

        return new double[] {
                distance * 0.8684,    // miles
                distance * 1.609344,  // km
                Math.round(distance * 100.0) / 100.0  // knots
        };
    }

    private double degreeToRadian(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double radianToDegree(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public void displayFlightSchedule() {
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |   DISTANCE(MILES/KMS)  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");

        int i = 0;
        for (Flight f1 : flightList) {
            System.out.println(f1.toString(++i));
            System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        }
    }

    @Override
    public String toString(int i) {
        return String.format("| %-5d| %-41s | %-9s | \t%-9s | %-21s | %-22s | %-10s  |   %-6sHrs |  %-4s  |  %-8s / %-11s|",
                i, flightSchedule, flightNumber, numOfSeatsInTheFlight,
                fromWhichCity, toWhichCity, fetchArrivalTime(),
                flightTime, gate, distanceInMiles, distanceInKm);
    }

    public String createNewFlightsAndTime() {
        Calendar c = Calendar.getInstance();
        nextFlightDay += Math.random() * 7;
        c.add(Calendar.DATE, nextFlightDay);
        c.add(Calendar.HOUR, nextFlightDay);
        c.set(Calendar.MINUTE, ((c.get(Calendar.MINUTE) * 3) - (int) (Math.random() * 45)));
        Date myDateObj = c.getTime();
        LocalDateTime date = Instant.ofEpochMilli(myDateObj.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        date = getNearestHourQuarter(date);
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a "));
    }

    public LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        LocalDateTime newDatetime;
        if (mod < 8) {
            newDatetime = datetime.minusMinutes(mod);
        } else {
            newDatetime = datetime.plusMinutes(15 - mod);
        }
        return newDatetime.truncatedTo(ChronoUnit.MINUTES);
    }

    // Getters
    public int getNoOfSeats() { return numOfSeatsInTheFlight; }
    public String getFlightNumber() { return flightNumber; }
    public String getFlightTime() { return flightTime; }
    public List<Flight> getFlightList() { return flightList; }
    public List<Customer> getListOfRegisteredCustomersInAFlight() { return listOfRegisteredCustomersInAFlight; }
    public String getFlightSchedule() { return flightSchedule; }
    public String getFromWhichCity() { return fromWhichCity; }
    public String getGate() { return gate; }
    public String getToWhichCity() { return toWhichCity; }

    // Setter
    public void setNoOfSeatsInTheFlight(int numOfSeatsInTheFlight) {
        this.numOfSeatsInTheFlight = numOfSeatsInTheFlight;
    }
}