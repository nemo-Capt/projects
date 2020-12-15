package triangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleUtility {
    private static final Logger logger = LoggerFactory.getLogger(TriangleUtility.class);

    private TriangleUtility() {

    }

    /**
     * @param x1 координата первой вершины по оси х
     * @param x2 координата второй вершины по оси х
     * @param x3 координата третей вершины по оси х
     * @param y1 координата первой вершины по оси у
     * @param y2 координата второй вершины по оси у
     * @param y3 координата третей вершины по оси у
     * @return возвращает периметр треугольника по вершинам
     */

    public static double findPerimeter(double x1, double x2, double x3, double y1, double y2, double y3) {
        double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        double perimeter = 0;


        if (a + b <= c || a + c <= b || b + c <= a)
            logger.error("Your triangle doesn't exist!");
        else {
            perimeter = (a + b + c);
        }
        return perimeter;
    }

    /**
     * @param x1 координата первой вершины по оси х
     * @param x2 координата второй вершины по оси х
     * @param x3 координата третей вершины по оси х
     * @param y1 координата первой вершины по оси у
     * @param y2 координата второй вершины по оси у
     * @param y3 координата третей вершины по оси у
     * @return возвращает площадь треугольника по вершинам
     */
    public static double findSquare(double x1, double x2, double x3, double y1, double y2, double y3) {
        double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        double c = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        double square;
        double p = findPerimeter(x1, x2, x3, y1, y2, y3) / 2;
        square = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return square;
    }

}
