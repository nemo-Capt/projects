package listcontent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ListTest {


    @Test
    void addFirstTest() {
        String expected = "1";
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("1");
        Assertions.assertEquals(expected, list.getFirst().getValue());
    }

    @Test
    void firstEqualsLastTest() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("1");
        Assertions.assertEquals(list.getFirst().getValue(), list.getLast().getValue());
    }

    @Test
    void addLastTest() {
        String expected = "2";
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");
        Assertions.assertEquals(expected, list.getLast().getPrev().getValue());
    }

    @Test
    void removeFirstTest() {
        String expected = "2";
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");
        list.removeFirst();
        Assertions.assertEquals(expected, list.getFirst().getValue());
    }

    @Test
    void removeLastTest() {
        String expected = "2";
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("1");
        list.addLast("2");
        list.addLast("3");
        list.removeLast();
        Assertions.assertEquals(expected, list.getLast().getValue());
    }

    @Test
    void doubleAddFirstTest() {
        String expected = "2";
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("1");
        list.addFirst("2");
        Assertions.assertEquals(expected, list.getFirst().getValue());
    }

    @Test
    void doubleAddLastTest() {
        String expected = "1";
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addLast("1");
        list.addLast("2");
        Assertions.assertEquals(expected, list.getFirst().getValue());
    }

    @Test
    void removeNullFirstTest() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.removeFirst();
        Assertions.assertNull(list.getFirst());
    }

    @Test
    void removeNullLastTest() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.removeLast();
        Assertions.assertNull(list.getFirst());
    }

    @Test
    void removeNullFirstNextTest() {
        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        list.addFirst("2");
        list.removeFirst();
        Assertions.assertNull(list.getFirst());
    }
}
