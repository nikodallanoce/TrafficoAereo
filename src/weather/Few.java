package weather;

import java.util.Random;

public class Few extends SkyCondition {
    @Override
    public Integer getCeilingClouds() {
        return 1 + new Random().nextInt(2);
    }
}
