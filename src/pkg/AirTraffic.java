package pkg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class AirTraffic implements IAirTraffic {

    private static final AirTraffic AIR_TRAFFIC = new AirTraffic();
    private List<Flight> flightList;

    private AirTraffic() {
        this.flightList = new ArrayList<>();
    }

    public static AirTraffic getInstance() {
        return AIR_TRAFFIC;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public boolean add(Flight flight) {
        return flightList.add(flight);
    }

    public boolean remove(Flight flight) {
        return flightList.remove(flight);
    }

    public Iterator<Flight> flightIterator() {
        return flightList.iterator();
    }

    public Stream<Flight> flightStream() {
        return flightList.parallelStream();
    }

}
