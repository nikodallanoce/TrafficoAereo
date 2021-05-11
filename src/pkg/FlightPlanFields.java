package pkg;

import java.time.LocalDateTime;

public class FlightPlanFields {

    private ReportPoint reportPoint;
    private LocalDateTime localDateTime;
    private Integer altitude;

    public FlightPlanFields(ReportPoint reportPoint, LocalDateTime localDateTime, Integer altitude) {
        this.reportPoint = reportPoint;
        this.localDateTime = localDateTime;
        this.altitude = altitude;
    }

    public ReportPoint getReportPoint() {
        return reportPoint;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Integer getAltitude() {
        return altitude;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof FlightPlanFields) {
            FlightPlanFields other = (FlightPlanFields) obj;
            eq = reportPoint.equals(other.reportPoint) && localDateTime.equals(other.localDateTime) && altitude.equals(other.altitude);
        }
        return eq;
    }
}
