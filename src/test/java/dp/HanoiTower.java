package dp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HanoiTower {

    private int[] hanoi;

    @ParameterizedTest(name = "{index}. {displayName} {arguments}")
    @DisplayName("하노이 타워")
    @CsvSource(value = {"1,1", "2,3", "3,7", "4,15"})
    public void hanoiTower(int count, int expected) {
        hanoi = new int[count + 1];
        int result = hanoiTower(count);
        assertEquals(expected, result);
    }

    public int hanoiTower(int count) {
        if (count == 0) return 0;
        if (count == 1) return 1;
        if (count == 2) return 3;
        if (hanoi[count] != 0) return hanoi[count];
        return hanoi[count] = (hanoiTower(count - 1) << 1) + 1;
    }


}
