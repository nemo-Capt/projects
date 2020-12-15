package multithreadapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ship extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Ship.class);

    private int containersToUpload;
    private int containersToDownload;
    private final Port port;

    /**
     * @param name                 имя корабля
     * @param containersToUpload   количество контейнеров, которые корабль должен загрузить
     * @param containersToDownload количество контейнеров, которые корабль должен выгрузить
     * @param port                 информация о порте
     */
    Ship(String name, int containersToUpload, int containersToDownload, Port port) {
        super(name);
        this.containersToUpload = containersToUpload;
        this.containersToDownload = containersToDownload;
        this.port = port;
        start();
    }


    static class NotEnoughContainersException extends Exception {
        NotEnoughContainersException(String message) {
            super(message);
        }
    }


    @Override
    public void run() {
        boolean accessToDock = false;

        try {
            while (true) {
                if (!accessToDock) {
                    port.enterTheDock();
                }

                accessToDock = false;
                if (containersToDownload > 0 && containersToUpload > 0) {
                    containersToUpload--;
                    containersToDownload--;
                    accessToDock = true;
                } else {
                    if (containersToDownload != 0) {
                        synchronized (port) {
                            if (port.getPortCapacity() > port.getContainersInPortAmount()) {
                                port.addContainer();
                                containersToDownload--;
                                accessToDock = true;
                            }
                        }
                    } else {
                        if (containersToUpload != 0) {
                            synchronized (port) {
                                if (port.getContainersInPortAmount() > 0) {
                                    port.removeContainer();
                                    containersToUpload--;
                                    accessToDock = true;
                                }
                                if (port.getContainersInPortAmount() < 0) {
                                    currentThread().interrupt();
                                    throw new NotEnoughContainersException("not enough containers!");
                                }
                            }
                        } else {
                            logger.info(Thread.currentThread().getName() + " has delivered everything");
                            port.leaveTheDock();
                            break;
                        }
                    }
                }

                if (accessToDock) {
                    Thread.sleep(10);
                } else {
                    port.leaveTheDock();
                }
            }
        } catch (InterruptedException | NotEnoughContainersException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }

    }

}
