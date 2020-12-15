package multithreadapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedPortTest {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedPortTest.class);
    private String dockAmount;
    private String portCapacity;
    private String containersInPortAmount;
    private String containersToUpload1;
    private String containersToDownload1;
    private String containersToUpload2;
    private String containersToDownload2;
    private String expected;

    /**
     * @param dockAmount             количество доков
     * @param portCapacity           вместимость порта
     * @param containersInPortAmount количество контейнеров в порту
     * @param containersToUpload1    количество контейнеров, которые нужно загрузить кораблям в первой группе
     * @param containersToDownload1  количество контейнеров, которые нужно разгрузить кораблям в первой группе
     * @param containersToUpload2    количество контейнеров, которые нужно загрузить кораблям во второй группе
     * @param containersToDownload2  количество контейнеров, которые нужно разгрузить кораблям во второй группе
     * @param expected               ожидаемый результат оставшихся контейнеров в порту после окончания работы всех кораблей
     */
    public ParameterizedPortTest(String dockAmount, String portCapacity, String containersInPortAmount, String containersToUpload1, String containersToDownload1, String containersToUpload2, String containersToDownload2, String expected) {
        this.dockAmount = dockAmount;
        this.portCapacity = portCapacity;
        this.containersInPortAmount = containersInPortAmount;
        this.containersToUpload1 = containersToUpload1;
        this.containersToDownload1 = containersToDownload1;
        this.containersToUpload2 = containersToUpload2;
        this.containersToDownload2 = containersToDownload2;
        this.expected = expected;
    }

    /**
     * @return возвращает список параметров из файла tests.txt для тестов
     */
    @Parameterized.Parameters()
    public static Iterable<Object[]> dataForTest() throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader("tests.txt")));
        BufferedReader reader = new BufferedReader(new FileReader("tests.txt"));
        int size = 0;
        while (reader.readLine() != null) {
            size++;
        }
        reader.close();
        Object[][] objectTestValues = new Object[size][];
        int i = 0;
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            logger.info(str);
            objectTestValues[i] = new Object[8];
            objectTestValues[i] = str.split(" ");
            i++;
        }
        return Arrays.asList(objectTestValues);
    }

    @Test
    public void paramTest() {
        Port port = new Port(Integer.parseInt(dockAmount), Integer.parseInt(portCapacity), Integer.parseInt(containersInPortAmount));

        List<Ship> ships = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ships.add(new Ship("Ship " + i, Integer.parseInt(containersToUpload1), Integer.parseInt(containersToDownload1), port));
        }

        for (int i = 5; i < 10; i++) {
            ships.add(new Ship("Ship " + i, Integer.parseInt(containersToUpload2), Integer.parseInt(containersToDownload2), port));
        }

        for (Ship ship : ships) {
            try {
                ship.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(Integer.parseInt(expected), port.getContainersInPortAmount());
    }
}
