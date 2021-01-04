package abstractfactory.apple.products;

import abstractfactory.Device;

public class Ipad implements Device {
    @Override
    public String createDevice() {
        return "Ipad created";
    }
}
