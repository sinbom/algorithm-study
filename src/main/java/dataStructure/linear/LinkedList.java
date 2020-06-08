package dataStructure.linear;

import java.util.NoSuchElementException;

/**
 * 연결 리스트, Deque 기능도 수행 가능.
 *
 * @param <T> 노드 데이터 제네릭 타입
 */
public class LinkedList<T> {

    /**
     * 시작 노드
     */
    private MyNode<T> first;
    /**
     * 마지막 노드
     */
    private MyNode<T> last;
    /**
     * 현재 노드 갯수
     */
    private int size;

    /**
     * 해당 데이터의 노드를 생성하고 시작 노드로 등록
     *
     * @param data 데이터
     */
    public void addFirst(T data) {
        MyNode<T> firstNode = first;
        MyNode<T> newNode = new MyNode<>(data, null, first);
        first = newNode;
        if (firstNode == null) {
            last = newNode;
        } else {
            firstNode.prev = newNode;
        }
        size++;
    }

    /**
     * 해당 데이터의 노드를 생성하고 마지막 노드로 등록
     *
     * @param data 데이터
     */
    public void addLast(T data) {
        MyNode<T> lastNode = last;
        MyNode<T> newNode = new MyNode<>(data, last, null);
        last = newNode;
        if (lastNode == null) {
            first = newNode;
        } else {
            lastNode.next = newNode;
        }
        size++;
    }

    /**
     * 해당 데이터의 노드를 생성하고 해당 노드의 이전 노드로 등록
     *
     * @param data 데이터
     * @param node 노드
     */
    public void addBefore(T data, MyNode<T> node) {
        MyNode<T> prevNode = node.prev;
        MyNode<T> newNode = new MyNode<>(data, prevNode, node);
        node.prev = newNode;
        if (prevNode != null) {
            prevNode.next = newNode;
        } else {
            first = newNode;
        }
        size++;
    }

    /**
     * 해당 데이터의 노드를 생성하고 해당 노드의 다음 노드로 등록
     *
     * @param data 데이터
     * @param node 노드
     */
    public void addAfter(T data, MyNode<T> node) {
        MyNode<T> nextNode = node.next;
        MyNode<T> newNode = new MyNode<>(data, node, nextNode);
        node.next = newNode;
        if (nextNode != null) {
            nextNode.prev = newNode;
        } else {
            last = newNode;
        }
        size++;
    }

    /**
     * 시작 노드의 데이터를 제거하지 않고 값만 반환
     *
     * @return 데이터
     */
    public T getFirst() {
        if (first != null) {
            return first.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * 마지막 노드의 데이터를 제거하지 않고 값만 반환
     *
     * @return 데이터
     */
    public T getLast() {
        if (last != null) {
            return last.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * 시작 노드의 데이터를 제거하고 값을 반환
     *
     * @return 데이터
     */
    public T removeFirst() {
        if (first != null) {
            T data = first.data;
            MyNode<T> next = first.next;
            first.data = null;
            first.next = null;
            first = next;
            if (next != null) {
                next.prev = null;
            } else {
                last = null;
            }
            size--;
            return data;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * 마지막 노드의 데이터를 제거하고 값을 반환
     *
     * @return 데이터
     */
    public T removeLast() {
        if (last != null) {
            T data = last.data;
            MyNode<T> prevNode = last.prev;
            last.data = null;
            last.prev = null;
            last = prevNode;
            if (prevNode != null) {
                prevNode.next = null;
            } else {
                first = null;
            }
            size--;
            return data;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * 해당 데이터의 노드를 생성하고 마지막 노드로 등록, 사용 편의를 위한 메소드
     *
     * @param data 데이터
     */
    public void add(T data) {
        addLast(data);
    }

    /**
     * 연결 리스트가 비었는지 노드의 갯수로 파악하여 논리값 반환
     *
     * @return 비었는지 여부
     */
    public boolean isEmpty() {
        return size < 1;
    }


    /**
     * 연결리스트를 구성하는 노드
     *
     * @param <T> 노드의 제네릭 타입 데이터
     */
    public static class MyNode<T> {
        /**
         * 데이터
         */
        private T data;
        /**
         * 이전 노드
         */
        private MyNode<T> prev;
        /**
         * 다음 노드
         */
        private MyNode<T> next;

        private MyNode(T data, MyNode<T> prev, MyNode<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }


}