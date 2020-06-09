package dataStructure.linear;

import interfaces.MyStack;

import java.util.EmptyStackException;

/**
 * 연접 리스트 스택
 *
 * @param <T> 데이터 제네릭 타입
 */
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
        } else {
            throw new IndexOutOfBoundsException();
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
