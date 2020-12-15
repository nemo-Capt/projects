package triangle.test;

public class KeyBoard implements DataTransition {
    @Override
    public void sendData(String data) {
        System.out.println("Text " + data + " is sent via keyboard");
    }


}
