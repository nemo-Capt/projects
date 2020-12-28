package abstractfactory.apple;

import abstractfactory.AbstractFactory;
import abstractfactory.apple.products.Ipad;
import abstractfactory.apple.products.Iphone;
import abstractfactory.Phone;
import abstractfactory.Tablet;

public class AppleFactory implements AbstractFactory {

    @Override
    public Phone createPhone() {
        return new Iphone();
    }

    @Override
    public Tablet createTablet() {
        return new Ipad();
    }
}
