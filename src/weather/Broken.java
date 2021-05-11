package weather;

import java.util.Random;

public class Broken extends SkyCondition {
    @Override
    public Integer getCeilingClouds() {
        return 5 + new Random().nextInt(3);
    }
}
