public class PremiumCustomer extends RegularCustomer {
    private int loyaltyPoints;
    private boolean hasPriorityBoarding;

    public PremiumCustomer(String name, String email, String password, String phone, String address, int age) {
        super(name, email, password, phone, address, age);
        this.loyaltyPoints = 1000;
        this.hasPriorityBoarding = true;
    }

    @Override
    public String toString(int i) {
        return super.toString(i) + String.format(" %-8d | %-5s |", loyaltyPoints, hasPriorityBoarding ? "Yes" : "No");
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
}