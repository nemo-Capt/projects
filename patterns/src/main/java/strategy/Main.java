package strategy;

import strategy.cars.Peugeot206;
import strategy.cars.VWPolo;
import strategy.transmission.Automatic;
import strategy.transmission.Manual;

public class Main {

    public static void main(String[] args) {
        Peugeot206 peugeot206 = new Peugeot206();
        peugeot206.performDrive();
        peugeot206.setDrivable(new Automatic());
        peugeot206.performDrive();

        VWPolo vwPolo = new VWPolo();

        vwPolo.performDrive();
        peugeot206.setDrivable(new Manual());
        peugeot206.performDrive();
    }
}
