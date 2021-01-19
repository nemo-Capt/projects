package strategy.cars;

import strategy.Car;
import strategy.transmission.Automatic;

public class VWPolo extends Car {

    public VWPolo() {
        setDrivable(new Automatic());
    }
}
