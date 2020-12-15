package triangle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class Solution {

    private static final Logger logger = LoggerFactory.getLogger(Solution.class);


    int findMax(List<Integer> list) {
        int max = 0;
        for (int i : list) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    void sortArray(List<Integer> list) {
        for (int i : list) {
            logger.info(String.valueOf(i));
        }
    }

    void switchExample(int choice) {
        switch (choice) {
            case 1:
                logger.info("You've chosen 1");
                break;
            case 2:
                logger.info("You've chosen 2");
                break;
            case 3:
                logger.info("You've chosen 3");
                break;
            default:
                logger.info("You've chosen default");
                break;
        }
    }

    void mathExamples(double a, double b) {
        double result = a / b;
        logger.info("Result: " + result);
        result = Math.round(result * 100.00) / 100.00;
        logger.info("Rounded result: " + result);
        logger.info("Max number is: " + (int) Math.max(a, b));
        result = Math.pow(a, 5);
        logger.info("10^5 = " + (int) result);
        logger.info("Square root of a: " + Math.round(Math.sqrt(a) * 100.00) / 100.00);
        result = 1 + Math.random() * 9;
        logger.info("Random value from 1 to 10: " + (int) result);
    }

    int recursionExample(int a) {
        if (a > 0) {
            return a * recursionExample(a - 1);
        } else return 1;
    }



    int[] perfectPower(int n) {
        if (n == 0 || n == 1) {
            return null;
        }
        if (Math.sqrt(n) % 1 == 0) {
            return new int[]{(int) Math.sqrt(n), 2};
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (Math.pow(i, (int) countLog(i, n)) == n) {
                return new int[]{i, (int) countLog(i, n)};
            }
        }
        return null;
    }

    private double countLog(double base, double number) {
        return Math.round(Math.log(number) / Math.log(base));
    }
}
