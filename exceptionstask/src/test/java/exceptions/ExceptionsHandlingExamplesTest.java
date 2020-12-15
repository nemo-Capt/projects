package exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ExceptionsHandlingExamplesTest {

    @Test
    void tryCatchExampleTest() {
        Assertions.assertDoesNotThrow(() -> ExceptionsHandlingExamples.tryCatchExample("qwerty2"));
    }

    @Test
    void tryCatchExampleTest2() {
        Assertions.assertEquals(10, ExceptionsHandlingExamples.tryCatchExample("10"));
    }

    @Test
    void assertThrowsTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Integer.parseInt("qwerty"));
    }

    @Test
    void multipleExceptionsInOneCatchExampleTest() {
        Assertions.assertDoesNotThrow(() -> ExceptionsHandlingExamples.multipleCatchesExample("qwerty2"));
    }

    @Test
    void multipleExceptionsInOneCatchExampleTest2() {
        Assertions.assertEquals(10, ExceptionsHandlingExamples.multipleCatchesExample("10"));
    }

//    @Test
//    void tryCatchFinallyExampleTest() {
//        Assertions.assertEquals(5, ExceptionsHandlingExamples.tryCatchFinallyExample("qwerty2"));
//    }

    @Test
    void tryCatchFinallyExampleTest2() {
        Assertions.assertEquals(5, ExceptionsHandlingExamples.tryCatchFinallyExample("2"));
    }

    @Test
    void customExceptionExampleTest() {
        Assertions.assertThrows(ExceptionsHandlingExamples.MyException.class, () -> ExceptionsHandlingExamples.customExceptionExample(5));
    }

    @Test
    void customExceptionExampleTest2() {
        try {
            ExceptionsHandlingExamples.customExceptionExample(15);
        } catch (ExceptionsHandlingExamples.MyException e) {
            Assertions.assertEquals("more than 10", e.getMessage());
        }
    }

}
