package dataStructure.custom;

public interface MyDeque<T> {

    void pushFirst(T data);

    T popFront();

    T peekFront();

    void pushBack(T data);

    T popBack();

    T peekBack();

    boolean isEmpty();

}
