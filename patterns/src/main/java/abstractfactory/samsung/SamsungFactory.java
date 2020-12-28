package abstractfactory.samsung;

import abstractfactory.AbstractFactory;
import abstractfactory.Phone;
import abstractfactory.Tablet;
import abstractfactory.samsung.products.SamsungPhone;
import abstractfactory.samsung.products.SamsungTablet;

public class SamsungFactory implements AbstractFactory {

    @Override
    public Phone createPhone() {
        return new SamsungPhone();
    }

    @Override
    public Tablet createTablet() {
        return new SamsungTablet();
    }
}
