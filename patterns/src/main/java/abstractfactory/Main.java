package abstractfactory;


public class Main {

    public static void main(String[] args) {
        DeviceFactory device = new DeviceFactory();
        System.out.println(device.create("apple").createDevice());
        System.out.println(device.create("samsung").createDevice());

    }
}
