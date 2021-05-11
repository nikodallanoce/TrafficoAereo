package pkg;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class EmergencyLandingObserver extends Observer<AFlight> {

    private AirSpace airSpace;

    public EmergencyLandingObserver(AirSpace airSpace) {
        this.airSpace = airSpace;
    }

    @Override
    void update(AFlight event) {
        if (isMayDay(event.getState())) {
            Airport landingAirport = nearestAirport(event, airSpace);
            System.out.println(String.format("Land in %s with coordinates %s", landingAirport.getID(), landingAirport.getArp()));
        }
    }

    private Airport nearestAirport(AFlight f, AirSpace as) {
        Queue<Pair<Airport, Double>> priorityQueue = fillQueue(f, as);
        return findAirportForLanding(priorityQueue);
    }

    private Airport findAirportForLanding(Queue<Pair<Airport, Double>> priorityQueue) {
        Airport airportForLanding = null;
        while (!priorityQueue.isEmpty()) {
            Airport current = priorityQueue.remove().getKey();
            if (checkCondition(current)) {
                airportForLanding = current;
                break;
            }
        }
        return airportForLanding;
    }

    private Queue<Pair<Airport, Double>> fillQueue(AFlight f, AirSpace as) {
        List<Airport> airportList = as.getAirportsInside();

        Comparator<Pair<Airport, Double>> c = Comparator.comparingDouble(Pair::getValue);
        Queue<Pair<Airport, Double>> priorityQueue = new PriorityQueue<>(c);

        airportList.forEach(airport -> priorityQueue.add(new Pair<>(airport, airport.getArp().distanceFrom(f.getPosition()))));
        return priorityQueue;
    }

    private boolean checkCondition(Airport airport) {
        return airport.getWeatherStation().getWind().getIntensity() < 20;
    }

    private boolean isMayDay(Transponder state) {
        return state.equals(Transponder.TX_7700);
    }
}
