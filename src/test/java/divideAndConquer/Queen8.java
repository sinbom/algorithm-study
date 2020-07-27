package divideAndConquer;

import org.junit.jupiter.api.Test;

public class Queen8 {

    /**
     * 같은 행에 이미 퀸이 위치 했는지 여부 플래그
     */
    public boolean[] rowStraight = new boolean[8];

    /**
     * 왼쪽 대각 선상에 이미 퀸이 위치 했는지 여부 플래그
     */
    public boolean[] leftDiagonal = new boolean[15];

    /**
     * 오른쪽 대각 선상에 이미 퀸이 위치 했는지 여부 플래그
     */
    public boolean[] rightDiagonal = new boolean[15];

    /**
     * 열마다 배치된 퀸의 위치
     */
    public int[] queens = new int[8];

    /**
     * 8퀸 배치의 경우의 수 집계
     */
    public int count = 0;

    @Test
    public void queen() {
        setQueens(0);
        System.out.println("8 Queen 문제 경우의 수 => " + count);
    }

    public void setQueens(int cols) {
        for (int rows = 0; rows < queens.length; rows++) {
            if (!rowStraight[rows] && !leftDiagonal[rows + cols] && !rightDiagonal[cols - rows + 7]) { // 현재 열의 행에서 행, 왼쪽 & 오른쪽 대각선이 차지 되지 않은 경우 배치할 수 있다
                queens[cols] = rows; // 현재 열에서 배치할 수 있는 행에 퀸을 배치
                if (cols == 7) { // 현재 배치하고 있는 열이 7(마지막)인 경우
                    print(); // 1부터 7열까지 배치한 현재 퀸들의 위치를 출력한다, 마지막 열의 경우 더 이상 퀸을 배치할 위치를 제한시킬 다음 열이 없기 때문에 행, 왼쪽 & 오른쪽 대각선을 차지 하여 플래그로 기록할 필요가 없음
                } else {
                    rowStraight[rows] = leftDiagonal[rows + cols] = rightDiagonal[cols - rows + 7] = true; // 퀸을 배치하는 현재 위치의 행, 왼쪽 & 오른쪽 대각선 차지
                    setQueens(cols + 1); // 다음 열을 0행 부터 7행까지 배치할 수 있는 행에 배치하며 경우의 수를 구한다.
                    rowStraight[rows] = leftDiagonal[rows + cols] = rightDiagonal[cols - rows + 7] = false; // 퀸을 배치하는 현재 위치의 행, 왼쪽 & 오른쪽 대각선 차지 해제
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < queens.length; i++) {
            StringBuilder builder = new StringBuilder("□□□□□□□□");
            int cols = 0;
            for (int j = 0; j < queens.length; j++) {
                if (queens[j] == i) {
                    cols = j;
                }
            }
            builder.setCharAt(cols, '■');
            System.out.println(builder.toString());
        }
        count++;
        System.out.println("=================" + count + "================");
    }

}
