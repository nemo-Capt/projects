import org.junit.jupiter.api.Test;
import proxy.Count;
import proxy.CountInterface;
import proxy.CountProxy;

import static org.junit.jupiter.api.Assertions.*;


class CountProxyTest {

    @Test
    void countProxyTest() {
        CountInterface test = new CountProxy(new Count());
        assertEquals("5 + 7 = 12", test.count(5, 7));
    }


}
