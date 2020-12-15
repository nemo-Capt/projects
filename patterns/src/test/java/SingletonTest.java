import org.junit.jupiter.api.Test;
import singleton.Singleton;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {

    @Test
    void singletonTest() {
        Singleton singleton = Singleton.getInstance("test1");
        Singleton singleton2 = Singleton.getInstance("test2");
        assertEquals("test1", singleton2.str);
    }

}
