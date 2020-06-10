import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static int[] dp;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        dp = new int[count + 1];
        System.out.println(hannoi(count));
    }

    public static int hannoi(int count) {
        if (count == 0) return 0;
        if (count == 1) return 1;
        if (count == 2) return 3;
        if (dp[count] != 0) return dp[count];
        return dp[count] = (hannoi(count - 1) << 1) + 1;
    }


}
