package pkg;

public class MonitorObserver extends Observer<AFlight> {

    private IgnoreStateStrategy ignoreStateStrategy;

    public MonitorObserver(IgnoreStateStrategy ignoreStateStrategy) {
        this.ignoreStateStrategy = ignoreStateStrategy;
    }

    @Override
    void update(AFlight event) {
        String a = ignoreStateStrategy.ignore(event);
        System.err.println(a);
    }

}
