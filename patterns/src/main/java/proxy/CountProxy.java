package proxy;

public class CountProxy implements CountInterface {

    private CountInterface counter;

    public String count(int x, int y) {
        lazyInitialization();
        return counter.count(x, y);
    }


    private void lazyInitialization() {
        if (counter == null) {
            counter = new Count();
        }
    }

}
