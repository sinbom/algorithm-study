package dataStructure;

import dataStructure.linear.ArrayStack;
import dataStructure.linear.LinkedListStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("스택 테스트")
public class StackTest {

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 스택 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void arrayStack(int[] expected, int[] array) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(array.length);
        for (int data : array) {
            arrayStack.push(data);
        }
        int index = array.length - 1;
        while (!arrayStack.isEmpty()) {
            array[index--] = arrayStack.pop();
        }
        assertArrayEquals(expected, array);
        assertTrue(arrayStack.isEmpty());
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 스택 실패 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void arrayStackFail(int[] array) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(array.length);
        assertThrows(EmptyStackException.class, arrayStack::pop);
        for (int data : array) {
            arrayStack.push(data);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> arrayStack.push(99));
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연결 리스트 스택 테스트")
    @ArgumentsSource(ArgsProvider.class)
    public void linkedListStack(int[] expected, int[] array) {
        LinkedListStack<Integer> linkedStack = new LinkedListStack<>();
        for (int data : array) {
            linkedStack.push(data);
        }
        int index = array.length - 1;
        while (!linkedStack.isEmpty()) {
            array[index--] = linkedStack.pop();
        }
        assertArrayEquals(expected, array);
        assertTrue(linkedStack.isEmpty());
    }

    @Test
    @DisplayName("연결 리스트 스택 실패 테스트")
    public void linkedListStackFail() {
        LinkedListStack<Integer> linkedStack = new LinkedListStack<>();
        assertThrows(NoSuchElementException.class, linkedStack::pop);
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
