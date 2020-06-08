package dataStructure;

import common.CommonTest;
import dataStructure.linear.ArrayQueue;
import dataStructure.linear.LinkedListQueue;
import dataStructure.linear.LinkedListStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
