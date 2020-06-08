package dataStructure.custom;

public interface MyStack<T> {

    void push(T data);

    T pop();

    T peek();

    boolean isEmpty();

}
