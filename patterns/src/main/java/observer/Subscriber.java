package observer;

import java.util.List;

public class Subscriber implements Observer {

    String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(List<String> cars) {
        System.out.println("Observer: " + name + "\nWe have some changes in car list:\n" + cars + "\n==============================");
    }
}
