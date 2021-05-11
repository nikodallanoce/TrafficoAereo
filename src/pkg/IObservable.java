package pkg;

public interface IObservable<T> {

     void addObserver(Observer<T> observer);

     void removeObserver(Observer<T> observer);

     void notifyObserver();

}
