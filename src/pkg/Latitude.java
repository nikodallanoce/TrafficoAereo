package pkg;

public class Latitude extends Coordinate {

    private static final int MIN_VALUE = -90;
    private static final int MAX_VALUE = 90;

    public Latitude(int grades, int minutes, int seconds) {
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
