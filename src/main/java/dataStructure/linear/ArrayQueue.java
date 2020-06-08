package dataStructure.linear;

import dataStructure.custom.MyQueue;

import java.util.NoSuchElementException;

/**
 * 연접 리스트 원형 큐
 *
 * @param <T> 데이터 제네릭 타입
 */
public class ArrayQueue<T> implements MyQueue<T> {

    /**
     * 데이터 저장 배열
     */
    private Object[] array;
    /**
     * 가장 최근 삭제 된 위치, -1로 하면 삭제가 일어나기전에 계속해서 삽입이 일어나면 꽉찼을때 감지를 못함
     */
    private int first = 0;
    /**
     * 가장 최근 삽입 된 위치, 초기 상태는 비어있음을 나타내야 하므로 front와 같은 값으로 설정
     */
    private int last = 0;

    public ArrayQueue(int size) {
        this.array = new Object[size];
    }

    @Override
    public void enqueue(T data) {
        int temp = last + 1;
        int index = temp % array.length; // 모듈러 연산으로 배열을 원형으로 순환할 수 있다.
        // front, rear가 같은 경우 빈 것으로 인식하기 때문에 삽입 시도시 rear의 다음 자리 인덱스가 front(비어있음)와 같은 경우 데이터가 비어있지만
        // 들어있는 것으로 인식하여 원형 큐의 경우 어쩔 수 없이 공간 1이 낭비된다는 단점이 있다. 하지만 선형 큐에 비하면 단점도 아닌듯...;;
        if (index != first) {
            last = index;
            array[last] = data;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            first = ++first % array.length;
            T data = (T) array[first];
            array[first] = null;
            return data;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public T peek() {
        if (!isEmpty()) {
            int index = (first + 1) % array.length;
            return (T) array[index];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isEmpty() {
        return first == last;
    }
}
