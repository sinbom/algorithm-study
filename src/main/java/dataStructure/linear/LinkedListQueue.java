package dataStructure.linear;

import dataStructure.custom.MyQueue;

public class LinkedListQueue<T> implements MyQueue<T> {

    private CircularLinkedList<T> circularLinkedList = new CircularLinkedList<>();

    @Override
    public void enqueue(T data) {
        circularLinkedList.addLast(data);
    }

    @Override
    public T dequeue() {
        return circularLinkedList.removeFirst();
    }

    @Override
    public T peek() {
        return circularLinkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return circularLinkedList.isEmpty();
    }
}
