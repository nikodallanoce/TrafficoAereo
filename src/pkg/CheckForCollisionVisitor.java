package pkg;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckForCollisionVisitor implements Visitor {

    private Duration deltaTime = Duration.ofMinutes(5);
    private int deltaAltitude = 1000;

    private void fillReportPointTime(AirSpace airSpace) {
        Iterator<ReportPoint> reportPoints = airSpace.getReportPointIterator();
    }

    private void sortReportPoint(List<ReportPoint> reportPoints) {

        Comparator<ReportPointFields> comp = Comparator.comparing(ReportPointFields::getTime);
        for (ReportPoint rp : reportPoints) {
            rp.sortIncomingFlight(comp);
        }
    }

    private boolean deltaTimeLessThan(LocalDateTime t1, LocalDateTime t2, Duration delta) {
        return Duration.ofMinutes(Math.abs(Duration.between(t1, t2).toMinutes())).compareTo(delta) < 0;
    }

    private boolean deltaAltitudeLessThan(int a1, int a2, int delta) {
        return Math.abs(a1 - a2) < delta;
    }

    private List<ReportPointFields> warningCollision(ReportPoint reportPoint) {
        List<ReportPointFields> flightColl = new LinkedList<>();

        Supplier<Stream<ReportPointFields>> s = reportPoint::incomingFlightStream;
        Iterator<ReportPointFields> iteratorFlightTime = reportPoint.incomingFlightIterator();
        flightColl.addAll(getPairs(s, iteratorFlightTime));

        return flightColl;
    }

    private List<ReportPointFields> getPairs(Supplier<Stream<ReportPointFields>> flight_Time, Iterator<ReportPointFields> iteratorFlightTime) {
        List<ReportPointFields> flightColl = new LinkedList<>();
        while (iteratorFlightTime.hasNext()) {
            ReportPointFields ft = iteratorFlightTime.next();
            flightColl.addAll(collisionFligts(flight_Time, ft));
        }
        return flightColl;
    }

    private List<ReportPointFields> collisionFligts(Supplier<Stream<ReportPointFields>> streamFlight_Time, ReportPointFields ft) {
        List<ReportPointFields> appo = new LinkedList<>();
        Predicate<ReportPointFields> predicate = flight_Time -> deltaTimeLessThan(flight_Time.getTime(), ft.getTime(), deltaTime) && deltaAltitudeLessThan(flight_Time.getAltitude(), ft.getAltitude(), deltaAltitude) && !flight_Time.getFlight().equals(ft.getFlight());
        if (streamFlight_Time.get().count() > 1) {
            appo = streamFlight_Time.get().filter(predicate).collect(Collectors.toList());
        }
        return appo;
    }


    @Override
    public void visitAirSpace(AirSpace airSpace) {
        sortReportPoint(airSpace.getReportPointList());
        Iterator<ReportPoint> reportPointIterator = airSpace.getReportPointIterator();
        iterateCollisions(reportPointIterator);
    }

    private void iterateCollisions(Iterator<ReportPoint> reportPointIterator) {
        List<ReportPointFields> appo;
        while (reportPointIterator.hasNext()) {
            ReportPoint rp = reportPointIterator.next();
            appo = warningCollision(rp);
            printCollision(appo, rp);
        }
    }

    private void printCollision(List<ReportPointFields> appo, ReportPoint rp) {
        if (appo.size() > 0) {
            System.out.println("--------------------------------");
            System.out.println("Collisions@ " + rp.getID());
            List<ReportPointFields> distinct = distinct(appo);
            for (LocalDateTime time : distinctTimes(distinct)) {
                System.out.println("@"+time.format(DateTimeFormatter.ofPattern("HH:mm:ss/ dd MMM yyyy")) + " +- min " + deltaTime.toMinutes());
                groupByTime(distinct,time).forEach(rpf->System.out.println("   Flight " + rpf.getFlight().getID() + " Altitude: " + rpf.getAltitude()));
            }
        }
    }

    private List<LocalDateTime> distinctTimes(List<ReportPointFields> list) {
        List<LocalDateTime> sameTimes = new LinkedList<>();
        for (ReportPointFields rpf : list) {
            boolean contains = false;
            for (LocalDateTime time : sameTimes) {
                if (deltaTimeLessThan(time, rpf.getTime(), deltaTime)) {
                    contains = true;
                }
            }
            if (!contains) {
                sameTimes.add(rpf.getTime());
            }
        }
        return sameTimes;
    }

    private List<ReportPointFields> groupByTime(List<ReportPointFields> list, LocalDateTime time) {
        List<ReportPointFields> sameTimes = new LinkedList<>();
        for (ReportPointFields reportPointFields : list) {
            if (deltaTimeLessThan(reportPointFields.getTime(), time, deltaTime)) {
                sameTimes.add(reportPointFields);
            }
        }
        return sameTimes;
    }

    private List<ReportPointFields> distinct(List<ReportPointFields> list) {
        List<ReportPointFields> distincts = new LinkedList<>();
        for (ReportPointFields rpf : list) {
            if (!distincts.contains(rpf)) {
                distincts.add(rpf);
            }
        }
        return distincts;
    }
}
