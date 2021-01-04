package abstractfactory;

import abstractfactory.apple.products.Ipad;
import abstractfactory.samsung.products.SamsungPhone;

public class DeviceFactory implements AbstractFactory<Device> {
    @Override
    public Device create(String deviceType) {
        if ("apple".equalsIgnoreCase(deviceType)) {
            return new Ipad();
        } else if ("samsung".equalsIgnoreCase(deviceType)) {
            return new SamsungPhone();
        }

        return null;
    }
}
