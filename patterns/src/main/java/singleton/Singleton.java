package singleton;

public class Singleton {
    private static Singleton instance;
    public String str;

    private Singleton(String str) {
        this.str = str;
    }

    public static Singleton getInstance(String str) {
        if (instance == null) {
            instance = new Singleton(str);

        }
        return instance;
    }
}
