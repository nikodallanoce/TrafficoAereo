package pkg;

import java.util.Arrays;

public abstract class Coordinate {

    private int[] gradesMinutesSeconds;

    public Coordinate(int grades, int minutes, int seconds) {
        gradesMinutesSeconds = new int[]{grades, minutes, seconds};
        checkValidity();
    }

    private void checkValidity() {
        if (getGrades() > gradeUpperBound() || getGrades() < gradeLowerDown()) {
            throw new Error();
        }
    }

    protected abstract int gradeUpperBound();

    protected abstract int gradeLowerDown();

    public Integer getGrades() {
        return gradesMinutesSeconds[0];
    }

    public Integer getMinutes() {
        return gradesMinutesSeconds[1];
    }

    public Integer getSeconds() {
        return gradesMinutesSeconds[2];
    }

    @Override
    public boolean equals(Object obj) {
        boolean eq = false;
        if (obj instanceof Coordinate) {
            Coordinate other = (Coordinate) obj;
            eq = other.getGrades().equals(getGrades()) && other.getMinutes().equals(getMinutes()) && other.getSeconds().equals(getSeconds());
        }
        return eq;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + Arrays.toString(gradesMinutesSeconds);
    }
}
