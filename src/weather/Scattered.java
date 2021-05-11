package weather;

import java.util.Random;

public class Scattered extends SkyCondition {
    @Override
    public Integer getCeilingClouds() {
        return 3 + new Random().nextInt(2); // 0 o 1
    }
}
