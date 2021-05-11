package pkg;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlightDelayDecorator extends AFlight {

    private AFlight component;
    private Duration delay;

    public FlightDelayDecorator(AFlight component, Duration delay) {
        this.component = component;
        this.delay = delay;
        updateFlightPlan();
    }

    @Override
    public Transponder getState() {
        return component.getState();
    }

    @Override
    public void setState(Transponder newState) {
        component.setState(newState);
    }

    @Override
    public String getID() {
        return component.getID();
    }

    @Override
    public Duration getDelay() {
        return component.getDelay().plus(delay);
    }

    private void updateFlightPlan() {

        List<FlightPlanFields> newListFlightPlan = listNewPlan();
        FlightPlan newfp = new FlightPlan(newListFlightPlan);
        insertFlightPlan(newfp);
    }

    private List<FlightPlanFields> listNewPlan() {
        List<FlightPlanFields> newListFlightPlan = new LinkedList<>();
        Iterator<FlightPlanFields> itFlightPlan = component.getFlightPlan().iteratorReportPoint();
        while (itFlightPlan.hasNext()) {
            FlightPlanFields current = itFlightPlan.next();
            delayTimeFlightPlan(newListFlightPlan,new FlightPlanFields(current.getReportPoint(), current.getLocalDateTime(), current.getAltitude()));
        }
        return newListFlightPlan;
    }

    private void delayTimeFlightPlan(List<FlightPlanFields> newListFlightPlan, FlightPlanFields fpf) {
        ReportPoint currentRepPoint=fpf.getReportPoint();
        LocalDateTime currentTime= fpf.getLocalDateTime();
        currentRepPoint.removeIncomingFlight(this, currentTime);
        if (currentTime.isAfter(LocalDateTime.now().plusSeconds(1))) {
            newListFlightPlan.add(new FlightPlanFields(currentRepPoint, currentTime.plus(delay),fpf.getAltitude()));
        } else {
            newListFlightPlan.add(fpf);
        }
    }

    @Override
    public void addObserver(Observer<AFlight> observer) {
        component.addObserver(observer);
    }

    @Override
    public void removeObserver(Observer<AFlight> observer) {
        component.removeObserver(observer);
    }

    @Override
    public IPosition getPosition() {
        return component.getPosition();
    }

    @Override
    public IFlightPlan getFlightPlan() {
        return component.getFlightPlan();
    }

    @Override
    protected void insertFlightPlan(IFlightPlan flightPlan) {
        component.insertFlightPlan(flightPlan);
    }

}
