package pkg;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class ReportPoint {
    private final String ID;  //nome
    private final IPosition coordinate;
    private List<ReportPointFields> incomingFlight;

    public ReportPoint(String ID, IPosition coordinate) {
        this.ID = ID;
        this.coordinate = coordinate;
        this.incomingFlight = new LinkedList<>();
    }

    public List<ReportPointFields> getIncomingFlight() {
        return Collections.unmodifiableList(incomingFlight);
    }

    public String getID() {
        return ID;
    }

    public boolean addIncomingFlight(AFlight f, LocalDateTime timeExpected, int altitude) {
        ReportPointFields elem = new ReportPointFields(f, timeExpected, altitude);
        return incomingFlight.add(elem);
    }

    public boolean removeIncomingFlight(AFlight f, LocalDateTime time) {
        Pair<AFlight, LocalDateTime> elem = new Pair<>(f, time);
        return incomingFlight.remove(elem);
    }

    public void sortIncomingFlight(Comparator<ReportPointFields> comparator) {
        incomingFlight.sort(comparator);
    }

    public Iterator<ReportPointFields> incomingFlightIterator() {
        return incomingFlight.iterator();
    }

    public Stream<ReportPointFields> incomingFlightStream() {
        return incomingFlight.parallelStream();
    }

}
