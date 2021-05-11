package pkg;

public class Longitude extends Coordinate {

    private static final int MIN_VALUE = -180;
    private static final int MAX_VALUE = 180;

    public Longitude(int grades, int minutes, int seconds) {
        super(grades, minutes, seconds);
    }

    @Override
    protected int gradeLowerDown() {
        return MIN_VALUE;
    }

    @Override
    protected int gradeUpperBound() {
        return MAX_VALUE;
    }
}
