import java.util.ArrayList;
import java.util.List;

public class DestinationChart {
    private static DestinationChart instance;
    private List<Destination> destinations;
    private DestinationChart() {
        destinations = new ArrayList<Destination>();
    }
    public static DestinationChart getInstance() {
        if(instance == null) {
            instance = new DestinationChart();
        }
        return instance;
    }
    public void add(Destination destination) {
        destinations.add(destination);
    }
    public void remove(Destination destination) {
        destinations.remove(destination);
    }
    public List<Destination> getDestinations() {
        return destinations;
    }
}
