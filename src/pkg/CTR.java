package pkg;

import java.util.List;

public class CTR extends AirSpace {

    private CTR(String ID, List<Airport> airportsInside, int competenceUpperLevel, int ray, List<ReportPoint> reportPointList) {
        super(ID, airportsInside, competenceUpperLevel, ray, reportPointList);
    }

    private CTR() {
        this("nd", null, -1, -1, null);
    }

}
