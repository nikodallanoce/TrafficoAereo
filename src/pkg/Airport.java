package pkg;

import weather.IWeatherStation;

public class Airport {

    private final String ID;
    private final String name;
    private final IPosition arp;
    private IWeatherStation weatherStation;
    public Airport(String ID, String name, IPosition arp, IWeatherStation weatherStation) {
        this.ID = ID;
        this.name = name;
        this.arp = arp;
        this.weatherStation = weatherStation;
    }

    public String getID() {
        return ID;
    }

    public IWeatherStation getWeatherStation() {
        return weatherStation;
    }

    public IPosition getArp() {
        return arp;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof Airport) {
            Airport other = (Airport) obj;
            eq = other.ID.equals(ID) && other.name.equals(name) && other.arp.equals(arp) && weatherStation.equals(weatherStation);
        }
        return eq;
    }
}
