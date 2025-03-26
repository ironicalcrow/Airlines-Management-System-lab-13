import java.util.ArrayList;
import java.util.List;

public class Destination {
    private final double lat;
    private final double lng;
    private final String city;
    Destination(double lat, double lng, String city) {
        this.lat = lat;
        this.lng = lng;
        this.city = city;
    }
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }

    public String getCity() {
        return city;
    }
}
