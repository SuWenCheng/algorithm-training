package algorithm.aliyun;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StringCount {

    public int stringCount(String str) {
        int len = str.length();
        int[] dp = new int[len];
        int continuous = 0;
        if (str.charAt(0) == '0') {
            dp[0] = 1;
            continuous ++;
        } else {
            dp[0] = 0;
        }
        for (int i = 1; i < len; i ++) {
            char c = str.charAt(i);
            if (c == '0') {
                continuous++;
                dp[i] = dp[i - 1] + continuous;
            } else {
                continuous = 0;
                dp[i] = dp[i - 1];
            }
        }
        return dp[len - 1];
    }

    @Test
    public void test() {
        String str = "00010011";
        log.info(stringCount(str) + "");
    }

}
