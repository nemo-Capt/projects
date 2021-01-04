package abstractfactory.samsung.products;

import abstractfactory.Device;

public class SamsungPhone implements Device {

    @Override
    public String createDevice() {
        return "Samsung phone created";
    }
}
