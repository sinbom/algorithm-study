package dataStructure;

import common.CommonTest;
import dataStructure.linear.ArrayStack;
import dataStructure.linear.LinkedListStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTest extends CommonTest {

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연접 리스트 스택 테스트")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void arrayStack(int[] expected) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(expected.length);
        for (int data : expected) {
            arrayStack.push(data);
        }
        int index = expected.length - 1;
        int[] array = new int[expected.length];
        while (!arrayStack.isEmpty()) {
            array[index--] = arrayStack.pop();
        }
        assertArrayEquals(expected, array);
        assertTrue(arrayStack.isEmpty());
    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("연결 리스트 스택 테스트")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void linkedListStack(int[] expected) {
        LinkedListStack<Integer> linkedStack = new LinkedListStack<>();
        for (int data : expected) {
            linkedStack.push(data);
        }
        int index = expected.length - 1;
        int[] array = new int[expected.length];
        while (!linkedStack.isEmpty()) {
            array[index--] = linkedStack.pop();
        }
        assertArrayEquals(expected, array);
        assertTrue(linkedStack.isEmpty());
    }

}
