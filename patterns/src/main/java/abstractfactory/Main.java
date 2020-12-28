package abstractfactory;

import abstractfactory.apple.AppleFactory;
import abstractfactory.samsung.SamsungFactory;

public class Main {

    public static void main(String[] args) {
        AbstractFactory appleFactory;
        appleFactory = new AppleFactory();

        appleFactory.createPhone();
        appleFactory.createTablet();

        AbstractFactory samsungFactory;
        samsungFactory = new SamsungFactory();

        samsungFactory.createPhone();
        samsungFactory.createTablet();
    }
}
