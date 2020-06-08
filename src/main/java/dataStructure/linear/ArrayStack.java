package dataStructure.linear;

import dataStructure.custom.MyStack;

import java.util.EmptyStackException;

public class ArrayStack<T> implements MyStack<T> {

    private Object[] array;

    private int top = -1;

    private int size;

    public ArrayStack(int size) {
        this.array = new Object[size];
    }

    @Override
    public void push(T data) {
        if (top + 1 < array.length) {
            array[++top] = data;
            size++;
        }
    }

    @Override
    public T pop() {
        T data = peek();
        top--;
        size--;
        return data;
    }

    @Override
    public T peek() {
        if (top > -1) {
            return (T) array[top];
        } else {
            throw new EmptyStackException();
        }
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

}
