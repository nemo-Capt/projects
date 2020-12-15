package triangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import triangle.test.DataTransition;
import triangle.test.KeyBoard;

import javax.xml.crypto.Data;
import java.util.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add(i + 1);
//        }
//        Collections.shuffle(list);
//
//        for (int i : list) {
//            System.out.println(i);
//        }
//
//        System.out.println("=================\n" + new Solution().findMax(list));
//
//        int choice;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Your choice:");
//        try {
//            choice = sc.nextInt();
//            new Solution().switchExample(choice);
//        } catch (InputMismatchException e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println(new Solution().recursionExample(6));
//
        int[] array = new int[10];
        for (int i = 0; i < array.length-1; i++) {
            array[i] = 1 + (int) (Math.random() * 10);
        }
        for (int value : array) {
            System.out.println(value);
        }
        System.out.println();

        QuickSort qs = new QuickSort();
        qs.quickSort(array);
//        BubbleSort bs = new BubbleSort();
//        bs.bubbleSort(array);

        for (int value : array) {
            System.out.println(value);
        }

        FindDuplicates findDuplicates = new FindDuplicates();
        String str = "qwertyqer";
        System.out.println(findDuplicates.findDuplicates(str));

        logger.info("Recursion: " + new Solution().recursionExample(6));

        logger.info("Result: " + new Solution().perfectPower(243)[0] + new Solution().perfectPower(243)[1]);

        logger.info(String.valueOf(CountingDuplicates.duplicateCount("indivnisibnility")));

    }

}
