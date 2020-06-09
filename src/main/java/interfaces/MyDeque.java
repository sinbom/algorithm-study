package interfaces;

public interface MyDeque<T> {

    void pushFront(T data);

    T popFront();

    T peekFront();

    void pushBack(T data);

    T popBack();

    T peekBack();

    boolean isEmpty();

    int getSize();

}
