package dataStructure.linear;

import dataStructure.custom.MyDeque;

public class LinkedListDeque<T> implements MyDeque<T> {

    private CircularLinkedList<T> circularLinkedList = new CircularLinkedList<>();

    @Override
    public void pushFirst(T data) {
        circularLinkedList.addFirst(data);
    }

    @Override
    public T popFront() {
        return circularLinkedList.removeFirst();
    }

    @Override
    public T peekFront() {
        return circularLinkedList.getFirst();
    }

    @Override
    public void pushBack(T data) {
        circularLinkedList.addLast(data);
    }

    @Override
    public T popBack() {
        return circularLinkedList.removeLast();
    }

    @Override
    public T peekBack() {
        return circularLinkedList.getLast();
    }

    @Override
    public boolean isEmpty() {
        return circularLinkedList.isEmpty();
    }
}
