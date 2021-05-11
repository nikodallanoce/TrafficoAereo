package pkg;

import java.util.Iterator;
import java.util.List;

public abstract class AirSpace {

    private final String ID;
    private List<Airport> airportsInside;
    private Integer competenceUpperLevel;
    private Integer ray;
    private List<ReportPoint> reportPointList;
    public AirSpace(String ID, List<Airport> airportsInside, Integer competenceUpperLevel, Integer ray, List<ReportPoint> reportPointList) {
        this.ID = ID;
        this.airportsInside = airportsInside;
        this.competenceUpperLevel = competenceUpperLevel;
        this.ray = ray;
        this.reportPointList = reportPointList;
    }

    public List<Airport> getAirportsInside() {
        return airportsInside;
    }

    public List<ReportPoint> getReportPointList() {
        return reportPointList;
    }

    public Iterator<Airport> iteratorAirportsInside() {
        return airportsInside.iterator();
    }

    public Iterator<ReportPoint> getReportPointIterator() {
        return reportPointList.iterator();
    }

    public boolean addReportPoint(ReportPoint reportPoint) {
        return reportPointList.add(reportPoint);
    }

    public boolean addAirportInside(Airport airport){
       return airportsInside.add(airport);
    }

    public void accept(Visitor v) {
        v.visitAirSpace(this);
    }


}
