package listcontent;

/**
 * @param <T> узел может принимать любые значения
 */
class Node<T> {
    private T value;
    private Node next;
    private Node prev;

    /**
     * @param aPrev указатель на предыдущий узел
     * @param aNext указатель на следующий узел
     * @param arg   значение узла
     */
    Node(Node<T> aPrev, Node<T> aNext, T arg) {
        prev = aPrev;
        next = aNext;
        value = arg;
    }

    /**
     * @return возврашает значение, хранимое в узле
     */
    T getValue() {
        return value;
    }

    /**
     * @return возвращает следующий узел
     */
    Node getNext() {
        return next;
    }

    /**
     * @param next устанавливает следующий узел
     */
    void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * @param prev устанавливает предыдущий узел
     */
    void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    /**
     * @return возвращает предыдущий узел
     */
    Node getPrev() {
        return prev;
    }


}
