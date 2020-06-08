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
    public void pushFirst(T data) {
        if (size == 0) {
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
            if (first == last && array[first] != null) {
                T data = (T) array[first];
                array[first] = null;
                size--;
                return data;
            } else if (first != last) {
                T data = (T) array[first];
                array[first] = null;
                first = ++first % array.length;
                size--;
            }
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
        int index = (last + 1) % array.length;
        if (index != first) {
            array[index] = data;
            size++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T popBack() {
        return null;
    }

    @Override
    public T peekBack() {
        if (!isEmpty()) {
            int index = last > 0 ? last - 1 : array.length - 1;
            return (T) array[index];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }
}
