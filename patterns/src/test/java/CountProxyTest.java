import org.junit.jupiter.api.Test;
import proxy.CountInterface;
import proxy.CountProxy;

import static org.junit.jupiter.api.Assertions.*;


class CountProxyTest {

    @Test
    void countProxyTest() {
        CountInterface test = new CountProxy();
        assertEquals("5 + 7 = 12", test.count(5, 7));
    }


}
