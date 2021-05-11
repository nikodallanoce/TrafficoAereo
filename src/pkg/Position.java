package pkg;

import static java.lang.Math.*;

public class Position implements IPosition {

    private Latitude latitude;
    private Longitude longitude;
    private Integer altitude;

    public Position(Latitude latitude, Longitude longitude, int altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public Position(Latitude latitude, Longitude longitude) {
        this(latitude, longitude, 0);
    }

    public Double distanceFrom(IPosition other) {
        final int earthRadiusKm = 6371;

        double deltaLat = degreesToRadians(other.getLatitude()) - degreesToRadians(latitude);
        double deltaLon = degreesToRadians(other.getLongitude()) - degreesToRadians(longitude);

        double lat1 = degreesToRadians(latitude);
        double lat2 = degreesToRadians(other.getLatitude());

        double a = sin(deltaLat / 2) * sin(deltaLat / 2) +
                sin(deltaLon / 2) * sin(deltaLon / 2) * cos(lat1) * cos(lat2);
        double c = 2 * atan2(sqrt(a), sqrt(1 - a));
        double distance = earthRadiusKm * c;
        return distance;

    }

    private Double decimalDegree(Coordinate coordinate) {
        return coordinate.getGrades() + coordinate.getMinutes() / 60.0 + coordinate.getSeconds() / 3600.0;
    }

    private Double degreesToRadians(Coordinate coordinate) {
        Double decimal = decimalDegree(coordinate);
        return decimal * Math.PI / 180;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof Position) {
            Position other = (Position) obj;
            eq = other.altitude.equals(altitude) && other.longitude.equals(longitude) && other.latitude.equals(latitude);
        }
        return eq;
    }

    @Override
    public String toString() {
        return String.format("%s %s alt %d", latitude, longitude, altitude);
    }

    @Override
    public Latitude getLatitude() {
        return latitude;
    }

    @Override
    public Longitude getLongitude() {
        return longitude;
    }

    @Override
    public int getAltitude() {
        return altitude;
    }
}
