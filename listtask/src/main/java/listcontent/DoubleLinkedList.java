package listcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @param <T> список может принимать переменные разных типов
 */
class DoubleLinkedList<T> {
    private static final Logger logger = LoggerFactory.getLogger(DoubleLinkedList.class);
    private Node<T> first;
    private Node<T> last;

    /**
     * @param aValue входное значение, которое примет узел
     */
    void addFirst(T aValue) {
        final Node<T> currFirst = first;
        final Node<T> newNode = new Node<>(null, currFirst, aValue);
        first = newNode;
        if (currFirst == null) {
            last = newNode;
        } else {
            currFirst.setPrev(newNode);
        }
    }

    /**
     * @param aValue входное значение, которое примет узел
     */
    void addLast(T aValue) {
        final Node<T> currLast = last;
        final Node<T> newNode = new Node<>(currLast, null, aValue);
        last = newNode;
        if (currLast == null) {
            first = newNode;
        } else {
            currLast.setNext(newNode);
        }
    }

    /**
     * @return возвращает последний элемент
     */
    Node<T> getLast() {
        return last;
    }

    /**
     * @return возвращает первый элемент
     */
    Node<T> getFirst() {
        return first;
    }

    void removeFirst() {
        if (first != null) {
            if (first.getNext() != null) {
                first = first.getNext();
                first.setPrev(null);
            } else {
                first = null;
            }
        } else {
            logger.error("First element doesn't exist!");
        }
    }

    void removeLast() {
        if (last != null) {
            last = last.getPrev();
        } else {
            logger.error("Last element doesn't exist!");
        }
    }
}
