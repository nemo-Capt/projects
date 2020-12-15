package paramTests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;
import triangle.TriangleUtility;


@RunWith(Parameterized.class)
public class TriangleParamTestSquare {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;
    private double expected;

    /**
     * @param x1 координата первой вершины по оси х
     * @param x2 координата второй вершины по оси х
     * @param x3 координата третей вершины по оси х
     * @param y1 координата первой вершины по оси у
     * @param y2 координата второй вершины по оси у
     * @param y3 координата третей вершины по оси у
     * @param expected ожидаемая площадь
     */
    public TriangleParamTestSquare(double x1, double x2, double x3, double y1, double y2, double y3, double expected) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.expected = expected;
    }

    /**
     *
     * @return возвращает параметры для теста
     */
    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {0, 0, 3, 0, -4, -4, 6},
                {0, 0, 0, 0, 0, 0, 0},
                {2, 3, 4, 5, -6, -7, 4.999999999999998},
                {-5, -5, 4, 0, -10, -10, 45},
                {1, 1, 2, 2, -3, -3, 2.5}
        });
    }


    @Test
    public void squareCheck() {
        Assertions.assertEquals(expected, TriangleUtility.findSquare(x1, x2, x3, y1, y2, y3));
    }

}
