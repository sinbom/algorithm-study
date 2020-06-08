package dataStructure;

import dataStructure.linear.LinkedListDeque;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("덱 테스트")
public class DequeTest {

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연결 리스트 덱 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void arrayDeque(int[] expected, int[] array) {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 0) {
                linkedListDeque.pushFirst(array[i]);
            } else {
                linkedListDeque.pushBack(array[i]);
            }
        }
        int index = 0;
        for (int i = 0; !linkedListDeque.isEmpty(); i++, index++) {
            if (i % 2 == 0) {
                array[index] = linkedListDeque.popFront();
            } else {
                array[index] = linkedListDeque.popBack();
            }
        }
        assertArrayEquals(expected, array);
        assertTrue(linkedListDeque.isEmpty());
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연결 리스트 큐 실패 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void arrayDequeFail() {
        LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();
        assertAll(
                () -> assertThrows(NoSuchElementException.class, linkedListDeque::popFront),
                () -> assertThrows(NoSuchElementException.class, linkedListDeque::popBack)
        );
    }

    public static class ArgsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of(new int[]{9, 3, 1, 7, 8, 2, 4, 5, 10, 6}, new int[]{10, 6, 4, 5, 8, 2, 1, 7, 9, 3}),
                    Arguments.of(new int[]{7, 5, 10, 9, 6, 2, 4, 3, 1, 8}, new int[]{1, 8, 4, 3, 6, 2, 10, 9, 7, 5}),
                    Arguments.of(new int[]{9, 6, 1, 2, 3, 7, 10, 4, 5, 8}, new int[]{5, 8, 10, 4, 3, 7, 1, 2, 9, 6}),
                    Arguments.of(new int[]{5, 3, 4, 9, 10, 8, 2, 7, 1, 6}, new int[]{1, 6, 2, 7, 10, 8, 4, 9, 5, 3}),
                    Arguments.of(new int[]{9, 10, 1, 2, 7, 4, 8, 5, 3, 6}, new int[]{3, 6, 8, 5, 7, 4, 1, 2, 9, 10})
            );
        }
    }


}
