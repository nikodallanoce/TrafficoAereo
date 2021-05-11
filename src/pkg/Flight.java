package pkg;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Flight extends AFlight implements IObservable<AFlight> {

    private final String ID;
    private Transponder state;
    private IPosition IPosition;
    private Aircraft aircraft;
    private IFlightPlan flightPlan;
    private List<Observer<AFlight>> observerFlightList;

    public Flight(String ID,Aircraft aircraft, IFlightPlan flightPlan, IPosition IPosition) { // il piano di volo lo decido alla fine
        this.ID=ID;
        this.aircraft = aircraft;
        this.state = Transponder.TX_7000;
        this.IPosition = IPosition;
        this.observerFlightList = new LinkedList<>();
        insertFlightPlan(flightPlan);
    }

    public IPosition getPosition() {
        return IPosition;
    }

    protected void insertFlightPlan(IFlightPlan flightPlan) {
        this.flightPlan = flightPlan;
        updateReportPoint();
    }

    public IFlightPlan getFlightPlan() {
        return flightPlan;
    }

    public Transponder getState() {
        return state;
    }

    public void setState(Transponder newState) {
        this.state = newState;
        notifyObserver();
    }

    public String getID() {
        return ID;
    }

    @Override
    public Duration getDelay() {
        return Duration.ZERO;
    }

    private void updateReportPoint() {
        Iterator<FlightPlanFields> flight_Time = flightPlan.iteratorReportPoint();
        while (flight_Time.hasNext()) {
            FlightPlanFields currentRepPoint = flight_Time.next();
            ReportPoint currentReport = currentRepPoint.getReportPoint();
            currentReport.addIncomingFlight(this, currentRepPoint.getLocalDateTime(),currentRepPoint.getAltitude());
        }
    }

    @Override
    public void addObserver(Observer<AFlight> observer) {
        observerFlightList.add(observer);
    }

    @Override
    public void removeObserver(Observer<AFlight> observer) {
        observerFlightList.remove(observer);
    }

    public void notifyObserver() {
        for (Observer<AFlight> o : observerFlightList) {
            o.update(this);
        }
    }

}