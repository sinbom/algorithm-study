package dataStructure.custom;

public interface MyQueue<T> {

    void enqueue(T data);

    T dequeue();

    T peek();

    boolean isEmpty();

}
