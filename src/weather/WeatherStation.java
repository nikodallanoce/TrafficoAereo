package weather;

public class WeatherStation implements IWeatherStation {
    private Double pression;
    private Double temperature;
    private Wind wind;
    private Integer visibility;
    private SkyCondition skyCondition;

    public WeatherStation(double pression, double temperature, Wind wind, Integer visibility, SkyCondition skyCondition) {
        this.pression = pression;
        this.temperature = temperature;
        this.wind = wind;
        this.visibility = visibility;
        this.skyCondition = skyCondition;
    }

    public Double getPression() {
        return pression;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public SkyCondition getSkyCondition() {
        return skyCondition;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof WeatherStation) {
            WeatherStation other = (WeatherStation) obj;
            eq = other.pression.equals(pression) && other.temperature.equals(temperature) && other.wind.equals(wind) && other.visibility.equals(visibility) && other.skyCondition.equals(skyCondition);
        }
        return eq;
    }
}
