package algorithm.aliyun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

@Slf4j
public class NewWays {

    public int newways(int steps, int arrlen) {
        int[][] dp = new int[steps + 1][arrlen + 1];
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 1);
        for (int i = 1; i <= steps; i ++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i <= steps; i++) {
            for (int j = 1; j <= arrlen; j ++) {
                if (j >= i) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j] * (j);
                }
            }
        }
        return dp[steps][arrlen];
    }

    @Test
    public void test() {
        int newways = newways(2, 2);
        log.info(newways + "");
    }

}
