package weather;

public class Wind {
    private Integer windHeading;
    private Integer intensity;

    public Wind(Integer windHeading, Integer intensity) {
        this.windHeading = windHeading;
        this.intensity = intensity;
    }

    public Integer getIntensity() {
        return intensity;
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof Wind) {
            Wind other = (Wind) obj;
            eq = other.windHeading.equals(windHeading) && other.intensity.equals(intensity);
        }
        return eq;
    }
}
