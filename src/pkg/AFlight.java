package pkg;

import java.time.Duration;

public abstract class AFlight {
    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof AFlight) {
            AFlight other = (AFlight) obj;
            eq = other.getID().equals(getID());
        }
        return eq;
    }

    @Override
    public String toString() {
        return getID();
    }

    public abstract Transponder getState();

    public abstract void setState(Transponder newState);

    public abstract IFlightPlan getFlightPlan();

    protected abstract void insertFlightPlan(IFlightPlan flightPlan);

    public abstract String getID();

    public abstract Duration getDelay();

    public abstract void addObserver(Observer<AFlight> observer) ;

    public abstract void removeObserver(Observer<AFlight> observer);

    public abstract IPosition getPosition();


}
