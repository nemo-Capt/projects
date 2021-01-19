package strategy.cars;

import strategy.Car;
import strategy.transmission.Manual;

public class Peugeot206 extends Car {

    public Peugeot206() {
        setDrivable(new Manual());
    }
}
