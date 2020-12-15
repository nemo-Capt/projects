package multithreadapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


class PortTest {

    private static final Logger logger = LoggerFactory.getLogger(PortTest.class);

    @Test
    void test() throws InterruptedException {
        Port port = new Port(3, 1000, 0);

        List<Ship> ships = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ships.add(new Ship("Ship " + i, 30, 10, port));
        }

        for (int i = 10; i < 15; i++) {
            ships.add(new Ship("Ship " + i, 0, 50, port));
        }

        for (Ship ship : ships) {
            ship.join();
        }
        logger.info("All ships have finished their task");
        Assertions.assertEquals("multithreadapp.Ship$NotEnoughContainersException", Ship.NotEnoughContainersException.class.getName());
    }

    @Test
    void test2() throws InterruptedException {
        Port port = new Port(3, 1000, 500);

        List<Ship> ships = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            ships.add(new Ship("Ship " + i, 34, 0, port));
        }

        for (int i = 15; i < 20; i++) {
            ships.add(new Ship("Ship " + i, 0, 200, port));
        }

        for (Ship ship : ships) {
            ship.join();
        }
        logger.info("All ships have finished their task");
        Assertions.assertEquals(990, port.getContainersInPortAmount());
    }

    @Test
    void test3() {
        Port port = new Port(3, 1000, 500);

        List<Ship> ships = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ships.add(new Ship("Ship " + i, 110, 0, port));
        }

        for (int i = 5; i < 10; i++) {
            ships.add(new Ship("Ship " + i, 0, 50, port));
        }

        for (Ship ship : ships) {
            try {
                ship.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("All ships have finished their task");
        Assertions.assertEquals(200, port.getContainersInPortAmount());
    }

    @Test
    void test4() {
        Port port = new Port(3, 1000, 500);

        List<Ship> ships = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ships.add(new Ship("Ship " + i, 110, 20, port));
        }

        for (int i = 5; i < 10; i++) {
            ships.add(new Ship("Ship " + i, 0, 50, port));
        }

        for (Ship ship : ships) {
            try {
                ship.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("All ships have finished their task");
        Assertions.assertEquals(300, port.getContainersInPortAmount());
    }

}
