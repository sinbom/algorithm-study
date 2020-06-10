package union;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnionFind {

    private int[] array = new int[11];

    @Test
    @DisplayName("합집합 찾기")
    public void unionFind() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i; // 자기 자신하고만 연결된 노드 (그래프)
        }
        unionFind(array, 1, 2);
        unionFind(array, 2, 3);
        unionFind(array, 3, 4);
        unionFind(array, 5, 6);
        unionFind(array, 6, 7);
        unionFind(array, 7, 8);
        System.out.println("1과 5는 연결되어 있나요? " + (isConnected(array, 1,5) ? "YES" : "No"));
        unionFind(array, 1, 5);
        System.out.println("1과 5는 연결되어 있나요? " + (isConnected(array, 1,5) ? "YES" : "No"));
        System.out.println("2과 8은 연결되어 있나요? " + (isConnected(array, 2,8) ? "YES" : "No"));
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public int getParent(int[] array, int index) {
        if (array[index] == index) return index;
        return getParent(array, array[array[index]]);
    }

    // 쉽게 비유하자면 두개의 지렁이가 우두머리인 머리를 비교하여 머리가 상대 머리를 가르키면서 연결되는 형식
    public void unionFind(int[] array, int n, int m) {
        int nParent = getParent(array, n);
        int mParent = getParent(array, m);
        if (nParent > mParent) {
            array[nParent] = mParent;
        } else {
            array[mParent] = nParent;
        }
    }

    public boolean isConnected(int[] array, int n, int m) {
        return getParent(array, n) == getParent(array, m);
    }


    // 문제 잘못 이해하고 풀었음, 실제로 1-2 연결된 후 1-2-3 이어 졌을때 3의 값이 2인 상황에도 연결된 것을 파악할 수 있게
    // 만드는 것이 문제인데 나는 1-2-3을 모두 값 1 1 1으로 넣어서 연결로 인식하게끔 만들었다.... 바보새꺄...
//    public void unionFind(int array[], int n, int m) {
//        int parentValue = Math.min(array[n], array[m]);
//        if (parentValue == array[n] && array[n] == n) { // 연결되지 않은 노드가 최솟 값이라면
//            int temp = array[m];
//            for (int i = 0; i < array.length; i++) { // 모든 배열을 순회하면서
//                if (array[i] == temp) { // 기존의 같은 부모를 가진 노드인 경우
//                    array[i] = parentValue; // 최소 값을 가진 연결되지 않은 노드의 값을
//                }
//            }
//        } else { // 연결되지 않은 노드의 값이 더 큰 경우
//            array[m] = parentValue; // 기존에 같은 부모 값을 가지던 노드들과 같은 부모값을 가진다.
//        }
//    }
//
//    public boolean isConnected(int n, int m) {
//        return array[n] == array[m];
//    }

}
