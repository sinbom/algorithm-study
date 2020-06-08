package dataStructure;

import dataStructure.linear.ArrayQueue;
import dataStructure.linear.LinkedListQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("큐 테스트")
public class QueueTest {

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 큐 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void arrayQueue(int[] expected, int[] array) {
        ArrayQueue<Integer> stackQueue = new ArrayQueue<>(array.length + 1); // 원형 큐 특성상 공간 1이 낭비 되는 것을 고려
        for (int data : array) {
            stackQueue.enqueue(data);
        }
        int index = 0;
        while (!stackQueue.isEmpty()) {
            array[index++] = stackQueue.dequeue();
        }
        assertArrayEquals(expected, array);
        assertTrue(stackQueue.isEmpty());
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 큐 실패 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void arrayQueueFail(int[] array) {
        ArrayQueue<Integer> stackQueue = new ArrayQueue<>(array.length + 1); // 원형 큐 특성상 공간 1이 낭비 되는 것을 고려
        assertThrows(NoSuchElementException.class, stackQueue::dequeue);
        for (int data : array) {
            stackQueue.enqueue(data);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> stackQueue.enqueue(99));
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연결 리스트 큐 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void linkedListQueue(int[] expected, int[] array) {
        LinkedListQueue<Integer> linkedQueue = new LinkedListQueue<>();
        for (int data : array) {
            linkedQueue.enqueue(data);
        }
        int index = 0;
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

    public static class ArgsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(new int[]{10, 6, 4, 5, 8, 2, 1, 7, 9, 3}, new int[]{10, 6, 4, 5, 8, 2, 1, 7, 9, 3}),
                    Arguments.of(new int[]{1, 8, 4, 3, 6, 2, 10, 9, 7, 5}, new int[]{1, 8, 4, 3, 6, 2, 10, 9, 7, 5}),
                    Arguments.of(new int[]{5, 8, 10, 4, 3, 7, 1, 2, 9, 6}, new int[]{5, 8, 10, 4, 3, 7, 1, 2, 9, 6}),
                    Arguments.of(new int[]{1, 6, 2, 7, 10, 8, 4, 9, 5, 3}, new int[]{1, 6, 2, 7, 10, 8, 4, 9, 5, 3}),
                    Arguments.of(new int[]{3, 6, 8, 5, 7, 4, 1, 2, 9, 10}, new int[]{3, 6, 8, 5, 7, 4, 1, 2, 9, 10})
            );
        }
    }

}
