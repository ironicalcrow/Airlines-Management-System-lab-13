import java.util.ArrayList;
import java.util.List;

public class RegularCustomer extends Customer {
    private List<Flight> flightsRegisteredByUser;
    private List<Integer> numOfTicketsBookedByUser;

    public RegularCustomer(String name, String email, String password, String phone, String address, int age) {
        super(name, email, password, phone, address, age);
        this.flightsRegisteredByUser = new ArrayList<>();
        this.numOfTicketsBookedByUser = new ArrayList<>();
    }

    @Override
    public String toString(int i) {
        return String.format("| %-10d | %-10s | %-32s | %-7d | %-27s |",
                i, userID, name, age, email);
    }

    public void addNewFlight(Flight flight, int tickets) {
        flightsRegisteredByUser.add(flight);
        numOfTicketsBookedByUser.add(tickets);
    }

    public void addTicketsToExistingFlight(int index, int tickets) {
        numOfTicketsBookedByUser.set(index, numOfTicketsBookedByUser.get(index) + tickets);
    }

    public List<Flight> getFlightsRegisteredByUser() {
        return flightsRegisteredByUser;
    }

    public List<Integer> getNumOfTicketsBookedByUser() {
        return numOfTicketsBookedByUser;
    }
}