package triangle.test;

import java.util.Scanner;

public class Messenger {

    private DataTransition dataTransition;

    Messenger(DataTransition dataTransition) {
        this.dataTransition = dataTransition;
    }

    public static void main(String[] args) {
        KeyBoard keyBoard = new KeyBoard();
        VoiceRecorder voiceRecorder = new VoiceRecorder();
        Messenger messenger1 = new Messenger(keyBoard);
        Messenger messenger2 = new Messenger(voiceRecorder);
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        messenger1.sendData(msg);
        msg = sc.nextLine();
        messenger2.sendData(msg);
        Calculationable<Integer> calculationable1;
        calculationable1 = (a, b) -> a + b;
        Calculationable<String> calculationable2;
        calculationable2 = (a, b) -> a + b;
        int result1 = calculationable1.calculate(10, 40);
        String result2 = calculationable2.calculate("10", "40");
        System.out.println(result1);
        System.out.println(result2);

    }

    void sendData(String data) {
        dataTransition.sendData(data);
    }

}
