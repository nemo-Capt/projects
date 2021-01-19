package strategy.transmission;

import strategy.Drivable;

public class Automatic implements Drivable {

    @Override
    public void drive() {
        System.out.println("Automatic transmission drive");
    }

}
