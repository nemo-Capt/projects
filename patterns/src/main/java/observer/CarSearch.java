package observer;

public class CarSearch {

    public static void main(String[] args) {
        CarSite carSite = new CarSite();

        carSite.addCar("Mercedes");
        carSite.addCar("BMW");

        Observer firstSub = new Subscriber("firstSub");
        Observer secondSub = new Subscriber("secondSub");

        carSite.addObserver(firstSub);
        carSite.removeCar("BMW");
        carSite.addObserver(secondSub);

        carSite.addCar("Toyota");

        carSite.addCar("Ford");

    }
}


