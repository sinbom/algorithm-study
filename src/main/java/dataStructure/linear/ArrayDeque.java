package dataStructure.linear;

import dataStructure.custom.MyDeque;

import java.util.NoSuchElementException;

/**
 * 배열 덱
 *
 * @param <T>
 */
public class ArrayDeque<T> implements MyDeque<T> {

    /**
     * 배열
     */
    private Object[] array;
    /**
     * 앞에서 삽입된 가장 최근 데이터의 위치
     */
    private int first;
    /**
     * 뒤에서 삽입된 가장 최근 데이터의 위치
     */
    private int last;
    /**
     * 데이터 갯수
     */
    private int size;

    /**
     * 배열 덱은 배열 원형 큐와 다르게 양방향에서 삽입 삭제가 가능하기 때문에 first == last 검사로 데이터가 빈 것을 확인할 수 없다.
     * first, last 어느 한 쪽이 삽입을 담당하고 삭제를 담당하는 배열 원형큐와 같은 형태가 아닌 삽입, 삭제를 둘다 담당하기 때문이다.
     * 원형 큐에서는 저장 공간 1을 낭비하는 대신 검사 로직을 최소화하여 이점을 볼 수 있었지만, 배열 덱에서는 검사 로직을 제외할 수 없는 것 같다.
     *
     * @param size 배열 사이즈
     */
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
