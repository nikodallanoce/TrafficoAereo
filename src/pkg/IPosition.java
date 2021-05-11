package pkg;

public interface IPosition {

    Latitude getLatitude();
    Longitude getLongitude();
    int getAltitude();
    Double distanceFrom(IPosition other);

}
