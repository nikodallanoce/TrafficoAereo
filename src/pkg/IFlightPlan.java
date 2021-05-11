package pkg;

import java.time.LocalDateTime;
import java.util.Iterator;

public interface IFlightPlan {

    Iterator<FlightPlanFields> iteratorReportPoint();

    Iterator<Airport> iteratorAirport();

    boolean addReportPoint_estimatedTime_alt(ReportPoint reportPoint, LocalDateTime timeElapsed, int alt);

    boolean addInRouterAirports(Airport airport);
}