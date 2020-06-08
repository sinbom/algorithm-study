package dataStructure.linear;

import dataStructure.custom.MyStack;

public class LinkedListStack<T> implements MyStack<T> {

    private CircularLinkedList<T> circularLinkedList = new CircularLinkedList<>();

    @Override
    public void push(T data) {
        circularLinkedList.addFirst(data);
    }

    @Override
    public T pop() {
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
