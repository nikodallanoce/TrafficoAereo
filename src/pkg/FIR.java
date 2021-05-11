package pkg;

import java.util.LinkedList;
import java.util.List;

public final class FIR extends AirSpace {

    private static final FIR FIR_INSTANCE = new FIR("FIR-Roma", 195_000, 100); //185 km
    private List<CTR> CTRAirSpaces;

    private FIR(String ID, Integer competenceUpperLevel, Integer ray) {
        this(ID, competenceUpperLevel, ray, new LinkedList<>());
    }

    private FIR(String ID, Integer competenceUpperLevel, Integer ray, List<CTR> CTRAirSpaces) {
        super(ID, new LinkedList<>(), competenceUpperLevel, ray, new LinkedList<>());
        this.CTRAirSpaces = CTRAirSpaces;
    }

    public static FIR getFirInstance() {
        return FIR_INSTANCE;
    }

    public boolean addReportPointInside(ReportPoint reportPoint) {
        return addReportPoint(reportPoint);
    }

    public boolean addCTRAirSpace(CTR CTR) {
        return CTRAirSpaces.add(CTR);
    }

    public boolean addAirportInside(Airport airport) {
        return super.getAirportsInside().add(airport);
    }

}
