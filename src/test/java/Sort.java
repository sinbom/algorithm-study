import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("알고리즘 테스트")
public class Sort {

    private int[] expected = {1,2,3,4,5,6,7,8,9,10};

    /**
     * 선택 정렬, 시간 복잡도 N^2
     * 가장 크거나 작은 값을 찾아 왼쪽부터 완성해 나가는 방식
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("선택 정렬")
    @ArgumentsSource(ArgsProvider.class)
    public void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
        assertArrayEquals(expected, array);
    }
// =========================================================================================================

    /**
     * 버블 정렬, 시간 복잡도 n^2
     * 가장 크거나 작은 값을 오른쪽부터 완성해 나가는 방식
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("버블 정렬")
    @ArgumentsSource(ArgsProvider.class)
    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true; // 이미 정렬이 되어 있는지 플래그
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    isSorted = false; // 한 번이라도 순서가 교환된 경우 정렬된 상태라고 볼 수 없음
                }
            }
            if (isSorted) { // 순서 변경이 없었다면 이미 정렬되어 있는 상태이다.
                break;
            }
        }
        assertArrayEquals(expected, array);
    }
// =========================================================================================================

    /**
     * 삽입 정렬, 시간 복잡도 N^2 이지만 교환 횟수도 선택, 버블 정렬에 비해 적고
     * 정렬이 되어 있는 상태의 경우 1번 비교 후 중단하기 때문에 최선의 경우 시간 복잡도는 N (N - 1번)
     * 초기 정렬이 어느 정도 되어 있는 경우 선택, 버블 정렬을 쓰는 것 보다 좋은 효율을 볼 수 있는 것 같다.
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("삽입 정렬")
    @ArgumentsSource(ArgsProvider.class)
    public void insertionSort(int[] array) {
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
// =========================================================================================================

    /**
     * 쉘 정렬, 시간 복잡도 N^2 최선의 경우 N이 될 수 있다. 삽입 정렬이 어느 정도 정렬된 배열의 경우 빠른 것을 이용
     * 정렬 거리가 먼 경우(오름차 기준 초기 데이터 극단적으로 5,4,3,2,1) 간격을 나눠 정렬하여 먼 거리씩 이동하여 부분씩 정렬한다.
     * 정렬하는 간격을 총 갯수 / 2 (홀수인 경우 + 1이 효율적)로 하여 부분 리스트(간격 3인경우 0,1,2 시작 부분 리스트 3개)를 정렬하고
     * 간격을 줄여가며 정렬한다. 부분 리스트 갯수 == 간격이 1이 될 때까지 반복한다. 삽입 정렬과 동일하나 부분 리스트와 간격이 존재한다.
     * 쉘 정렬의 경우 삽입 정렬이 정렬 되어야하는 위치와 초기 위치가 먼 경우에 사용하면 좋은 성능을 보일 것 같다.
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("쉘 정렬")
    @ArgumentsSource(ArgsProvider.class)
    public void shellSort(int[] array) {
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
    // =========================================================================================================

    /**
     * 퀵 정렬, 기준점인 피봇을 사용하여 정렬 대상을 분할하며 정복(정렬)한다, 정렬이 되어 있는 경우는 비효율적이지만
     * 정렬이 안되어 있는 경우 시간 복잡도가 nlog2n으로 효율적이고 빠르다. 분할 정복의 경우 대부분 재귀이다.
     * 또한 왼쪽, 중간, 오른쪽 값을 우선으로 정렬하여 최대한 중간 값을 기준으로 이분 분할을 하기 위함이다.
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("퀵 정렬")
    @ArgumentsSource(ArgsProvider.class)
    public void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }

    /**
     * 왼쪽, 중간, 오른쪽부터 정렬 후 퀵 정렬 수행
     *
     * @param array 재귀로 분할되는 리스트
     * @param left  왼쪽 인덱스
     * @param right 오른쪽 인덱스
     * @return 피벗
     */
    public void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return; // 이중 분할이 계속되면서 오른쪽 인덱스가 왼쪽이랑 같거나 더 작아지는 경우 더 이상 분할되지 않는다.
        }
        int mid = (right + left) / 2;
        if (array[left] > array[mid]) {
            swap(array, left, mid);
        }
        if (array[left] > array[right]) {
            swap(array, left, right);
        }
        if (array[mid] > array[right]) {
            swap(array, mid, right);
        }

        if (right - left + 1 > 3) { // 총 갯수가 3개이하라면 이미 왼쪽, 중간, 오른쪽 정렬에서 정렬을 마친 상태,
            swap(array, left + 1, mid); // 왼쪽 + 1 값과 중간 값을 교체, 즉 중간 값을 왼쪽으로 보내서 피봇(기준)으로 사용
            int low, high, pivot; // , 왼쪽과 오른쪽 값을 제외시키고 정렬한다. left + 1, right - 1
            low = left + 2; // 피봇보다 큰 값을 찾는다, 왼쪽, 중간, 오른쪽에서 정렬한 왼쪽 값은 제외, 왼쪽은 자신을 제외한 다음부터 비교 시작
            high = right - 1; // 피봇보다 작은 값을 찾는다, 왼쪽, 중간, 오른쪽에서 정렬한 오른쪽 값은 제외
            pivot = array[left + 1];

            do {
                for (; right > low && pivot > array[low]; low++) ; // 최대 범위까지 피봇보다 큰 값 찾기
                for (; left < high && pivot < array[high]; high--) ; // 최소 범위까지 피봇보다 작은 값 찾기
                if (high > low) { // 교차하지 않은 경우 서로 찾은 피봇과 큰 값 작은 값 변경
                    swap(array, low, high); // 찾아서 서로 교체
                }
            } while (low < high); // 서로 교차하거나 만났다면 이미 피봇과 비교하여 low와 high 값을 교체한 경우이므로 진행 중단
            swap(array, left + 1, high);
            quickSort(array, left, high - 1);
            quickSort(array, high + 1, right);
        }

    }

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("힙 정렬")
    @ArgumentsSource(ArgsProvider.class)
    public void heapSort() {

    }


    // =========================================================================================================
    public void swap(int[] array, int swap, int swap2) {
        int temp = array[swap];
        array[swap] = array[swap2];
        array[swap2] = temp;
    }


    public static class ArgsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of((Object) new int[]{10, 6, 4, 5, 8, 2, 1, 7, 9, 3}),
                    Arguments.of((Object) new int[]{1, 8, 4, 3, 6, 2, 10, 9, 7, 5}),
                    Arguments.of((Object) new int[]{5, 8, 10, 4, 3, 7, 1, 2, 9, 6}),
                    Arguments.of((Object) new int[]{1, 6, 2, 7, 10, 8, 4, 9, 5, 3}),
                    Arguments.of((Object) new int[]{3, 6, 8, 5, 7, 4, 1, 2, 9, 10})
            );
        }
    }

}
