package dataStructure;

import common.CommonTest;
import dataStructure.linear.ArrayQueue;
import dataStructure.linear.LinkedListQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("큐 테스트")
public class QueueTest extends CommonTest {

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 큐 테스트")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void arrayQueue(int[] expected) {
        ArrayQueue<Integer> stackQueue = new ArrayQueue<>(expected.length + 1); // 원형 큐 특성상 공간 1이 낭비 되는 것을 고려
        for (int data : expected) {
            stackQueue.enqueue(data);
        }
        int index = 0;
        int[] array = new int[expected.length];
        while (!stackQueue.isEmpty()) {
            array[index++] = stackQueue.dequeue();
        }
        assertArrayEquals(expected, array);
        assertTrue(stackQueue.isEmpty());
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 큐 실패 테스트")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void arrayQueueFail(int[] expected) {
        ArrayQueue<Integer> stackQueue = new ArrayQueue<>(expected.length + 1); // 원형 큐 특성상 공간 1이 낭비 되는 것을 고려
        assertThrows(NoSuchElementException.class, stackQueue::dequeue);
        for (int data : expected) {
            stackQueue.enqueue(data);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> stackQueue.enqueue(99));
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연결 리스트 큐 테스트")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void linkedListQueue(int[] expected) {
        LinkedListQueue<Integer> linkedQueue = new LinkedListQueue<>();
        for (int data : expected) {
            linkedQueue.enqueue(data);
        }
        int index = 0;
        int[] array = new int[expected.length];
        while (!linkedQueue.isEmpty()) {
            array[index++] = linkedQueue.dequeue();
        }
        assertArrayEquals(expected, array);
        assertTrue(linkedQueue.isEmpty());
    }

    @Test
    @DisplayName("연결 리스트 큐 실패 테스트")
    public void linkedListQueueFail() {
        LinkedListQueue<Integer> linkedQueue = new LinkedListQueue<>();
        assertThrows(NoSuchElementException.class, linkedQueue::dequeue);
    }

}
