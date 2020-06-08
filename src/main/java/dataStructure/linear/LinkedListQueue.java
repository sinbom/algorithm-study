package dataStructure.linear;

import dataStructure.custom.MyQueue;

public class LinkedListQueue<T> implements MyQueue<T> {

    private LinkedList<T> linkedList = new LinkedList<>();

    @Override
    public void enqueue(T data) {
        linkedList.addLast(data);
    }

    @Override
    public T dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public T peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
