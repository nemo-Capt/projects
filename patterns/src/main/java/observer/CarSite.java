package observer;

import java.util.ArrayList;
import java.util.List;

public class CarSite implements Observed {

    List<String> cars = new ArrayList<>();

    List<Observer> subscribers = new ArrayList<>();

    public void addCar(String car) {
        this.cars.add(car);
        notifyObservers();
    }

    public void removeCar(String car) {
        this.cars.remove(car);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : subscribers) {
            observer.handleEvent(this.cars);
        }
    }
}
