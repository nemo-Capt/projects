package exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ExceptionsHandlingExamples {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionsHandlingExamples.class);

    /**
     * @param b переменная типа String, которую мы будем пытаться перевести в double
     * @return возвращает результат преведения String в double
     */
    static double tryCatchExample(String b) {
        double a = 1;
        try {
            a = Double.parseDouble(b);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }
        return a;
    }

    /**
     * @param b переменная типа String, которую мы будем пытаться перевести в double
     * @return возвращает результат преведения String в double
     */
    static int multipleCatchesExample(String b) {
        int[] arrayOfNumbers = new int[10];
        try {
            arrayOfNumbers[9] = Integer.parseInt(b);
            arrayOfNumbers[10] = Integer.parseInt(b);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            logger.error(e.getMessage());
        }
        return arrayOfNumbers[9];
    }

    /**
     * @param b переменная типа String, которую мы будем пытаться перевести в double
     * @return в не зависимости от действий вернет a=5, демонстрация работы finally
     */
    static double tryCatchFinallyExample(String b) {
        double a = 1;
        try {
            a = 10 / Double.parseDouble(b);
        } finally {
            a = 5;
        }
        return a;
    }

    static class MyException extends Exception {
        /**
         * @param message сообщение исключения
         */
        MyException(String message) {
            super(message);
        }
    }

    /**
     * @param b в зависимости от значения переменной кинется исключение с разнм текстом
     * @throws MyException проверка работы кастомного исключения
     */
    static void customExceptionExample(int b) throws MyException {
        if (b < 10) {
            throw new MyException("less than 10");
        } else {
            throw new MyException("more than 10");
        }
    }
}
