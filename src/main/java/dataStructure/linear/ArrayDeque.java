package dataStructure.linear;

import dataStructure.custom.MyDeque;

import java.util.NoSuchElementException;

public class ArrayDeque<T> implements MyDeque<T> {

    private Object[] array;

    private int first;

    private int last;

    private int size;

    public ArrayDeque(int size) {
        this.array = new Object[size];
        this.first = size / 2;
        this.last = size / 2;
    }

    @Override
    public void pushFront(T data) {
        if (isEmpty()) {
            array[first] = data;
        } else {
            int temp = first - 1;
            int index = temp >= 0 ? temp : array.length - 1;
            if (index != last) {
                first = index;
                array[first] = data;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        size++;
    }

    @Override
    public T popFront() {
        if (!isEmpty()) {
            T data = (T) array[first];
            array[first] = null;
            size--;
            if (first != last) {
                first = ++first % array.length;
            }
            return data;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T peekFront() {
        if (!isEmpty()) {
            return (T) array[first];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void pushBack(T data) {
        if (isEmpty()) {
            array[last] = data;
        } else {
            int index = (last + 1) % array.length;
            if (index != first) {
                last = index;
                array[last] = data;
            } else {
                throw new IndexOutOfBoundsException();
            }
        }
        size++;
    }

    @Override
    public T popBack() {
        if (!isEmpty()) {
            T data = (T) array[last];
            array[last] = null;
            size--;
            if (first != last) {
                int temp = last - 1;
                last = temp >= 0 ? temp : array.length - 1;
            }
            return data;
        }
        throw new NoSuchElementException();
    }

    @Override
    public T peekBack() {
        if (!isEmpty()) {
            return (T) array[last];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public int getSize() {
        return size;
    }
}
