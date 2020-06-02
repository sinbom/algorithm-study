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
     * 초기 정렬이 어느 정도 되어 있는 경우 선택, 버블 정렬을 쓰는 것 보다 좋은 효율을 볼 수 있는 것 같다.
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

    /**
     * 쉘 정렬, 시간 복잡도 N^2 최선의 경우 N이 될 수 있다. 삽입 정렬이 어느 정도 정렬된 배열의 경우 빠른 것을 이용
     * 정렬 거리가 먼 경우(오름차 기준 초기 데이터 극단적으로 5,4,3,2,1) 간격을 나눠 정렬하여 먼 거리씩 이동하여 부분씩 정렬한다.
     * 정렬하는 간격을 총 갯수 / 2 (홀수인 경우 + 1이 효율적)로 하여 부분 리스트(간격 3인경우 0,1,2 시작 부분 리스트 3개)를 정렬하고
     * 간격을 줄여가며 정렬한다. 부분 리스트 갯수 == 간격이 1이 될 때까지 반복한다. 삽입 정렬과 동일하나 부분 리스트와 간격이 존재한다.
     * 쉘 정렬의 경우 삽입 정렬이 정렬 되어야하는 위치와 초기 위치가 먼 경우에 사용하면 좋은 성능을 보일 것 같다.
     */
    @Test
    public void shellSort() {
        int[] array = {8, 5, 6, 2, 4};
        int[] expected = {2, 4, 5, 6, 8};
        int j, k;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            gap = gap % 2 == 0 ? gap + 1 : gap; // 간격은 홀수가 성능이 좋음
            for (int i = 0; i < gap; i++) { // 부분 리스트는 간격의 갯수와 동일 7 / 2 = 3인경우 0,1,2 시작점들이 각 부분 리스트
                for (j = i + gap; j < array.length; j += gap) { // 부분 리스트를 간격으로 삽입 정렬
                    int key = array[j];
                    for (k = j - gap; k >= i && array[k] > key; k -= gap) {
                        array[k + gap] = array[k];
                    }
                    array[k + gap] = key;
                }
            }
        }
        assertArrayEquals(expected, array);
    }

}
