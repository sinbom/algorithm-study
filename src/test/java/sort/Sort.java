package sort;

import common.CommonTest;
import dataStructure.linear.CircularLinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("정렬 테스트")
public class Sort extends CommonTest {

    /**
     * 선택 정렬, 시간 복잡도 N^2
     * 가장 크거나 작은 값을 찾아 왼쪽부터 완성해 나가는 방식
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("선택 정렬")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
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
    @ArgumentsSource(CommonTest.ArgsProvider.class)
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
    @ArgumentsSource(CommonTest.ArgsProvider.class)
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
    @ArgumentsSource(CommonTest.ArgsProvider.class)
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
    @ArgumentsSource(CommonTest.ArgsProvider.class)
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
            // 사용하는 이유는 피벗을 가장 왼쪽이나 오른쪽으로 두어야 low, high가 증감하며 교차했을 시 피벗과 high를 교체하는데 hight보다 피벗보다 왼쪽에 잇는 경우
            // high와 교체했을시 피벗보다 오른쪽으로 이동하게 되면서 분할해서 정렬한 결과에서 피벗 기준 오른쪽에 피벗 보다 더 작은 값인 high가 존재하게 되므로 정렬이 깨진다.
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

    /**
     * 힙 정렬, 완전 이진 트리(자식노드의 갯수가 최대 2개이고 왼쪽부터 차례대로 채워지는 트리)를 이용한 정렬
     * 전체의 자료를 정렬할 때도 준수한 성능으로 사용하지만 특히 가장 크거나 작은 몇 개의 값을 추출할 때 유용하다.
     * 시간 복잡도는 균일하게 nlog2^n 이다. 퀵 정렬이 기본적으로 더 빠르지만 퀵 정렬이 이미 정렬이 되어 있는 최악의 경우에 힙 보다 조금 느리다.
     * 배열의 인덱스를 활용하여 시작 인덱스를 1로하고 자식 노드들을 쉽게 인덱싱 접근하여(1 * 2 = 왼쪽 자식 노드, 1 * 2 + 1 = 오른쪽 자식 노드)
     * 값을 힙에 삽입 시 마지막 노드에 추가하고 부모 노드와 크기를 비교하여 결과에 따라 부모 노드와 자리가 교체된다.
     * 모든 값을 힙에 삽입하고 값을 추출하면서 힙의 특성상 루트 노드가 가장 크거나 작은 것을 이용하여(최대힙, 최소힙)정렬된 값을 얻을 수 있다.
     * 값을 추출 시 루트 노드를 추출하고 마지막 노드를 루트 노드로 올리고 자식 노드들 중 가장 크거나 작은 값을 선택하여 마지막 노드의 값이었던
     * 루트 노드로 올라간 값과 비교 후 결과에 따라 자식 노드와 자리가 교체된다. 삽입과 추출마다 부모의 노드가 자식 노드보다 크거나 작은 특성을 이용한다.
     *
     * @param array
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("힙 정렬")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void heapSort(int[] array) {
        class Heap {
            int[] array;
            int index = 0;

            Heap(int length) {
                this.array = new int[length + 1]; // 첫 번째 인덱스 0을 사용하지 않으므로 자식 / 2 = 부모 인덱스구현을 위함
            }
        }
        Heap heap = new Heap(array.length);
        // 삽입 연산(정렬)
        for (int number : array) {
            int index = ++heap.index; /// 새로운 노드를 실제로 삽입하지 않고 위치가 정해진 후 한 번의 삽입으로 마치기 위해서 삽입을 가정하기위해 값을 삽입하지 않고 인덱스를 증가시킨다.
            while (index > 1 && number < heap.array[index / 2]) { // 현재 인덱스가 루트인 1보다 크고, 삽입 값이 부모 값보다 작다면
                heap.array[index] = heap.array[index / 2]; // 부모 노드의 값을 자식 노드의 값으로 끝어내린다.
                index /= 2; // 인덱스를 부모 인덱스로 이동.
            }
            heap.array[index] = number; // 루트에 도달하거나 부모보다 작지 않은경우 현재 인덱스에 값을 입력
        }
        // 삭제 연산(사용, 추출)
        array = new int[array.length]; // 사용, 추출하기 위해 배열을 초기화
        int i = 0; // 추출을 위한 배열의 인덱스
        while (heap.index > 0) { // 추출할 노트(루트)가 존재하는 경우
            array[i++] = heap.array[1]; // 힙의 최대 값인 루트 노드의 값을 추출
            int number = heap.array[heap.index]; // 추출해서 비어지는 루트 노드를 메꾸기 위해 가장 마지막 노드의 루트 노드로 올리기위해 추출
            heap.array[heap.index--] = 0; // 추출한 값을 제거 후 인덱스 감소
            if (heap.index == 0) {
                break; // 더 이상 추출할 노드가 없는 힙의 경우 반복을 종료한다, 없는 경우 인덱스 0, 인덱스는 마지막 노드를 가르킨다.
            }
            int index = 1; // 마지막 노드가 이동한 루트 노드의 인덱스
            while (heap.index >= index * 2) { // 현재 노드와 비교할 자식 노드가 존재해야한다
                int childIndex;
                if (heap.index > index * 2) { // 자식 노드가 2개라면
                    childIndex = heap.array[index * 2] < heap.array[index * 2 + 1] ? index * 2 : index * 2 + 1; // 자식 노드 2개를 비교하여 더 작은 값을 비교 자식 노드로 사용
                } else { // 자식 노드가 1개라면
                    childIndex = index * 2; // 존재하는 자식 노드를 비교 노드로 사용
                }
                if (heap.array[childIndex] < number) { // 자식 노드가 값이 더 작다면
                    heap.array[index] = heap.array[childIndex]; // 현재 인덱스에 자식 노드의 값을 올린다.
                    index = childIndex; // 현재 인덱스의 위치를 자식 노드 인덱스 위치로 이동한다.
                } else {
                    break; // 자식 노드들보다 작지 않은 경우 더 이상 비교하지 않고 마친다.
                }
            }
            heap.array[index] = number; // 더 이상 비교할 현재 노드의 자식 노드들이 없는 경우 현재 인덱스 값에 원래 마지막 노드였던 값을 위치
        }
        assertArrayEquals(expected, array);
    }

    /**
     * 합병 정렬, 분할 정복 알고리즘으로 재귀로 정렬을 진행한다. 특이한 점은 배열로 정렬을 진행하면 이분 분할시 쪼개지는 배열들을 병합할 때 임시 배열이 필요하다.
     * 레코드의 크기가 큰 경우 이동횟수가 많고 분할하기 전 원본 배열의 사이즈 만큼의 임시 배열이 필요하므로 낭비가 크다.
     * 장점은 데이터의 분포(이미 정렬이 되어 있든 아니든)에 영향을 덜 받으며 입력 데이터가 무엇이든 간에 시간 복잡도가 nlog2^n으로 동일하다.
     * 분할 단계에서 비교 연산이 일어나지 않는다. 크기가 큰 데이터들을 정렬할 때 연결리스트를 사용하면 다른 어떤 정렬보다 효율적이다.
     *
     * @param array
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("합병 정렬")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
        assertArrayEquals(expected, array);
    }

    /**
     * @param array 재귀로 분할되는 배열
     * @param left  왼쪽 인덱스
     * @param right 오른쪽 인덱스
     */
    public void mergeSort(int[] array, int left, int right) {
        if (left >= right) { // 이중 분할되고 분할된 배열이 1개 이하이면 정렬할게 없다.
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        int[] temp = new int[10];
        int index = left;
        int low = left;
        int high = mid + 1;
        while (low <= mid && high <= right) {
            if (array[low] < array[high]) {
                temp[index++] = array[low++];
            } else {
                temp[index++] = array[high++];
            }
        }
        if (low > mid) {
            while (high <= right) {
                temp[index++] = array[high++];
            }
        } else {
            while (low <= mid) {
                temp[index++] = array[low++];
            }
        }

        for (int i = left; i <= right; i++) {
            array[i] = temp[i];
        }
    }

    /**
     * 기수 정렬, 1의 자리수 부터 가장 큰 수의 자리 수 까지 반복하며 1의 자리수 부터 큰 것들을 비교하여 자리수를 인덱스를 사용하여 버킷(연결리스트)에 넣은 후
     * 차례대로 끄내면서 다시 배열에 저장하고 다음 자리 수를 비교하며 버킷에 넣는 과정을 반복하는 정렬이다. 시간 복잡도는 N으로 균일하게 빠르지만.
     * 비교 연산을 수행하지 않는 대신 데이터 전체 크기(정수인 경우 0~9, 알파벳의 경우 a~z)의 임시 저장 메모리가 필요하므로 공간 복잡도가 크다.
     *
     * @param array
     */
    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("기수 정렬")
    @ArgumentsSource(CommonTest.ArgsProvider.class)
    public void radixSort(int[] array) {
        List<CircularLinkedList<Integer>> bucket = new ArrayList<>();
        IntStream.range(0, 10) // 데이터의 최대 사이즈 (예 정수(0~10), 영문(a~z))만큼의 버킷 생성
                .forEach(n -> bucket.add(new CircularLinkedList<>()));

        int divide = 1; // 자릿수 만큼 증가하며 나누기 위해 * 10 씩 곱셈되는 나눗셈 수
        int max = Arrays.stream(array).max().getAsInt(); // 가장 큰수를 조회
        int maxSize = (int) Math.log10(max) + 1; // 가장 큰수의 자릿수

        for (int i = 0; i < maxSize; i++) { // 가장 큰수의 자릿수 갯수 만큼 자릿수 비교
            for (int number : array) { // 배열의 모든 데이터를 버킷에 삽입
                int index = number / divide % 10; // 비교 자릿수의 값을 인덱스로 사용하여 버킷이 연결리스트에 삽입
                CircularLinkedList<Integer> circularLinkedList = bucket.get(index);
                circularLinkedList.add(number);
            }

            int index = 0;
            for (CircularLinkedList<Integer> circularLinkedList : bucket) { // 버킷에 있는 모든 연결리스트를 순회한다
                while (!circularLinkedList.isEmpty()) { // 모든 연결리스트를 순서대로 꺼내서 배열에 담는다
                    array[index++] = circularLinkedList.removeFirst(); // 담긴 배열은 자릿수 기준으로 정렬이 되어 있는 상태이다.
                }
            }
            divide *= 10; // 다음 자릿수 비교를 위해 * 10 연산
        }

        assertArrayEquals(expected, array);
    }


    // =========================================================================================================
    public void swap(int[] array, int swap, int swap2) {
        int temp = array[swap];
        array[swap] = array[swap2];
        array[swap2] = temp;
    }

}
