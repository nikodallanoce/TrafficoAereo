package pkg;

@FunctionalInterface
public interface IgnoreStateStrategy {
    String ignore(AFlight flight);
}
