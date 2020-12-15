package multithreadapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class Port {

    private static final Logger logger = LoggerFactory.getLogger(Port.class);

    private int dockAmount;
    private int portCapacity;
    private int containersInPortAmount;

    private List<Thread> ships = new ArrayList<>();

    /**
     * @param dockAmount             количество доков в порту, в одном доке может быть пришвартован только один корабль
     * @param portCapacity           вместимость порта (сколько контейнеров может одновременно в нем находиться)
     * @param containersInPortAmount количесвто контейнеров в порту на данный момент
     */
    Port(int dockAmount, int portCapacity, int containersInPortAmount) {
        this.dockAmount = dockAmount;
        this.portCapacity = portCapacity;
        this.containersInPortAmount = containersInPortAmount;
    }

    /**
     * @return возвращает вместимость порта
     */
    int getPortCapacity() {
        return portCapacity;
    }

    /**
     * @return возвращает количесвто контейнеров в порту
     */
    int getContainersInPortAmount() {
        return containersInPortAmount;
    }


    void addContainer() {
        containersInPortAmount++;
    }

    void removeContainer() {
        containersInPortAmount--;
    }


    synchronized void enterTheDock() throws InterruptedException {
        while (dockAmount == 0) {//исправить
            wait(100);
        }
        ships.add(Thread.currentThread());
        logger.info(Thread.currentThread().getName() + " has entered the dock");
        dockAmount--;
    }

    synchronized void leaveTheDock() throws InterruptedException {
        wait(100);

        logger.info(Thread.currentThread().getName() + " has left the dock");

        logger.info("Amount of containers in Port: " + containersInPortAmount);

        if (ships.contains(Thread.currentThread())) {
            dockAmount++;
        }
        ships.remove(Thread.currentThread());

        notifyAll();
    }
}
