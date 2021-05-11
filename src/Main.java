import pkg.*;
import weather.SkyClear;
import weather.WeatherStation;
import weather.Wind;

import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        IPosition roma = new Position(new Latitude(41, 53, 35), new Longitude(12, 28, 58), 0);
        IPosition milano = new Position(new Latitude(45, 27, 51), new Longitude(9, 11, 25), 0);

        ReportPoint rp1 = new ReportPoint("Report 1", new Position(new Latitude(1, 1, 1), new Longitude(1, 1, 1), 0));
        ReportPoint rp2 = new ReportPoint("Report 2", new Position(new Latitude(1, 1, 1), new Longitude(1, 1, 1), 0));
        ReportPoint rp3 = new ReportPoint("Report 3", new Position(new Latitude(1, 1, 1), new Longitude(1, 1, 1), 0));

        FlightPlan fp = new FlightPlan();
        fp.addReportPoint_estimatedTime_alt(rp2, LocalDateTime.now(),2000);
        fp.addReportPoint_estimatedTime_alt(rp2, LocalDateTime.now().plusMinutes(50),1000);
        fp.addReportPoint_estimatedTime_alt(rp3, LocalDateTime.now().plusHours(1).plusMinutes(4),1000);

        FlightPlan fp2 = new FlightPlan();
        fp2.addReportPoint_estimatedTime_alt(rp2, LocalDateTime.now(),1500);
        fp2.addReportPoint_estimatedTime_alt(rp1, LocalDateTime.now(),2511);
        fp2.addReportPoint_estimatedTime_alt(rp2, LocalDateTime.now().plusHours(1),3000);

        FlightPlan fp3 = new FlightPlan();
        fp3.addReportPoint_estimatedTime_alt(rp1, LocalDateTime.now(),1000);
        fp3.addReportPoint_estimatedTime_alt(rp2, LocalDateTime.now().plusHours(1),1400);
        fp3.addReportPoint_estimatedTime_alt(rp3, LocalDateTime.now().plusHours(1).plusMinutes(10),1900);

        AFlight flight = new Flight("Uno",new Airliner("aereo", "bo"), fp, roma);
        AFlight flight2 = new Flight("Due",new Airliner("aereo2", "bo2"), fp2, roma);
        AFlight flight3 = new Flight("Tre",new Airliner("aereo3", "bo3"), fp3, roma);


        flight = new FlightDelayDecorator(flight, Duration.ofMinutes(6));
        flight.getDelay();


        CheckForCollisionVisitor v1 = new CheckForCollisionVisitor();

        AirSpace airSpace = FIR.getFirInstance();
        airSpace.addReportPoint(rp1);
        airSpace.addReportPoint(rp2);
        airSpace.addReportPoint(rp3);

        Airport airport= new Airport("Roma airport","Roma",roma,new WeatherStation(0,0,new Wind(0,10),10, new SkyClear()));
        airSpace.addAirportInside(airport);

        airSpace.accept(v1);

        System.out.println();

        flight.addObserver(new EmergencyLandingObserver(airSpace));
        flight.addObserver(new EmergencyCallObserver("054236984"));
        flight.addObserver(new MonitorObserver(f->!f.getState().equals(Transponder.TX_7000)?"Flight "+f.getID()+" "+f.getState(): ""));

        flight.setState(Transponder.TX_7700);

        System.out.println();

    }
}
