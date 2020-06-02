import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("알고리즘 테스트")
public class Sort {

    /**
     * 선택 정렬, 시간 복잡도 N^2
     * 가장 크거나 작은 값을 찾아 왼쪽부터 완성해 나가는 방식
     */
    @Test
    public void selectionSort() {
        int[] array = {9, 6, 7, 3, 5};
        int[] expected = {3, 5, 6, 7, 9};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        assertArrayEquals(expected, array);
    }

    /**
     * 버블 정렬, 시간 복잡도 n^2
     * 가장 크거나 작은 값을 오른쪽부터 완성해 나가는 방식
     */
    @Test
    public void bubbleSort() {
        int[] array = {9, 6, 7, 3, 5, 2, 1};
        int[] expected = {1, 2, 3, 5, 6, 7, 9};
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        assertArrayEquals(expected, array);
    }

    /**
     * 삽입 정렬, 시간 복잡도 N^2 이지만 교환 횟수도 선택, 버블 정렬에 비해 적고
     * 정렬이 되어 있는 상태의 경우 1번 비교 후 중단하기 때문에 최선의 경우 시간 복잡도는 N (N - 1번)
     * 선택과 버블보다 효율적이고 정렬이 어느 정도 되어 있는 경우 구현 난이도에 비해 좋은 효율을 볼 수 있는 것 같다.
     */
    @Test
    public void insertionSort() {
        int[] array = {8, 5, 6, 2, 4};
        int[] expected = {2, 4, 5, 6, 8};
        int i, j;
        for (i = 1; i < array.length; i++) {
            int key = array[i];
            for (j = i - 1; j >= 0 && array[j] > key; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = key;
        }
        assertArrayEquals(expected, array);
    }

}
