package triangle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriangleTest {
    @Test
    void findPerimeterTest() {
        Assertions.assertEquals(12, TriangleUtility.findPerimeter(0, 0, 3, 0, -4, -4));
    }

    @Test
    void findSquareTest() {
        Assertions.assertEquals(6, TriangleUtility.findSquare(0, 0, 3, 0, -4, -4));
    }

    @Test
    void findTest() {
        Assertions.assertEquals(0, TriangleUtility.findSquare(0, 0, 0, 0, 0, 0));
    }
}
