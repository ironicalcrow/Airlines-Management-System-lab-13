
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    private static RandomGenerator instance;
    private DestinationChart chart= DestinationChart.getInstance();
    private List<Destination> destinations= chart.getDestinations();

    private RandomGenerator() {
    }
public int randomIDGen() {
    Random rand = new Random();
    int randomID = rand.nextInt(20000,1000000);
    return randomID;
}

public Destination[] randomDestinations() {
    Random rand = new Random();
    int starting= rand.nextInt();
    int destination= rand.nextInt();
    while(starting==destination) {
        destination= rand.nextInt();
    }
    Destination[] dest = new Destination[2];
    dest[0]= destinations.get(starting); ;
    dest[1]= destinations.get(destination);
    return dest;
}

public int randomNumOfSeats() {
    int numOfSt = 500;
    int lowerBound = 75;
    Random random = new Random();
    int numOfSeats = random.nextInt(lowerBound,numOfSt);
    return numOfSeats;
}

public String randomFlightNumbGen(int uptoHowManyLettersRequired, int divisible) {
    Random random = new Random();
    StringBuilder randomAlphabets = new StringBuilder();
    for (int i = 0; i < uptoHowManyLettersRequired; i++) {
        randomAlphabets.append((char) (random.nextInt(26) + 'a'));
    }
    randomAlphabets.append("-").append(randomNumOfSeats() / divisible);
    return randomAlphabets.toString();
}
public RandomGenerator getInstance() {
    if(instance == null) {
        instance = new RandomGenerator();
    }
    return instance;
}
//        ************************************************************ Setters & Getters ************************************************************
}

