package pkg;

import java.time.LocalDateTime;

public class ReportPointFields {

    private AFlight flight;
    private LocalDateTime time;
    private Integer altitude;

    public AFlight getFlight() {
        return flight;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public ReportPointFields(AFlight flight, LocalDateTime time, int altitude) {
        this.flight = flight;
        this.time = time;
        this.altitude = altitude;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof ReportPointFields) {
            ReportPointFields other = (ReportPointFields) obj;
            eq = flight.equals(other.flight) && time.equals(other.time) && altitude.equals(other.altitude);
        }
        return eq;
    }

    @Override
    public String toString() {
        return flight + "|" + time + "|" + altitude;
    }
}
