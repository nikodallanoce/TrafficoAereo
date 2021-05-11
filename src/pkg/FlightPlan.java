package pkg;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlightPlan implements IFlightPlan {

    private final List<Airport> inRouterAirports;
    private final List<FlightPlanFields> reportPoint_estimatedTime;

    public FlightPlan() {
        this(new LinkedList<>());
    }

    public FlightPlan(List<FlightPlanFields> reportPoint_estimatedTime) {
        this.inRouterAirports = new ArrayList<>();
        this.reportPoint_estimatedTime = reportPoint_estimatedTime;
    }

    public boolean addReportPoint_estimatedTime_alt(ReportPoint reportPoint, LocalDateTime estimatedTime, int alt) {
        return reportPoint_estimatedTime.add(new FlightPlanFields(reportPoint, estimatedTime,alt));
    }

    public boolean addInRouterAirports(Airport airport) {
        return inRouterAirports.add(airport);
    }

    public Iterator<FlightPlanFields> iteratorReportPoint() {
        return reportPoint_estimatedTime.iterator();
    }

    public Iterator<Airport> iteratorAirport() {
        return inRouterAirports.iterator();
    }

}
