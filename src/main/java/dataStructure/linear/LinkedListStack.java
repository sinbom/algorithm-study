package dataStructure.linear;

import dataStructure.custom.MyStack;

public class LinkedListStack<T> implements MyStack<T> {

    LinkedList<T> linkedList = new LinkedList<>();

    @Override
    public void push(T data) {
        linkedList.addFirst(data);
    }

    @Override
    public T pop() {
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
