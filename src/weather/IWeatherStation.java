package weather;

public interface IWeatherStation {

    Double getPression();

    Double getTemperature();

    Wind getWind();

    Integer getVisibility();

    SkyCondition getSkyCondition();

}
