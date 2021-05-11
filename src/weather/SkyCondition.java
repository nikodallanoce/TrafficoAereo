package weather;

public abstract class SkyCondition {
    public abstract Integer getCeilingClouds(); // dammi la copertura nuvolosa (se 1-2-3-4- si vola senno no, max 7)

    @Override
    public boolean equals(Object obj) {   //Template
        boolean eq = false;
        if (obj instanceof SkyCondition) {
            SkyCondition other = (SkyCondition) obj;
            eq = other.getCeilingClouds().equals(getCeilingClouds());
        }
        return eq;
    }
}
