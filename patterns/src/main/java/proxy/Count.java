package proxy;

public class Count implements CountInterface {

    public String count(int x, int y) {
        String str = notifyUser(x, y);
        return str + (x + y);
    }

    private String notifyUser(int x, int y) {
        return x + " + " + y + " = ";
    }
}
