package triangle.test;

public class VoiceRecorder implements DataTransition {

    @Override
    public void sendData(String data) {
        System.out.println("Text " + data + " is sent via voice recorder");
    }
}
