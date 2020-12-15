import proxy.Count;
import proxy.CountInterface;
import proxy.CountProxy;
import singleton.Singleton;

public class Main {

    public static void main(String[] args) {

        CountInterface ex = new CountProxy();
        CountInterface ex2 = new Count();
        Singleton singleton = Singleton.getInstance("test1");
        Singleton singleton2 = Singleton.getInstance("test2");


        System.out.println(ex.count(4, 2));
        System.out.println(ex.count(5, 7));
        System.out.println(ex2.count(5, 10));
        System.out.println(singleton.str);
        System.out.println(singleton2.str);

    }
}
