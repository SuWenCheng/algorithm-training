package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 分割回文串 II
 *
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 *
 * 返回符合要求的 最少分割次数 。
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 *
 * 输入：s = "ab"
 * 输出：1
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 */
@Slf4j
public class MinCut {

    // 动态规划
    public int minCut(String s) {
        int len = s.length();
        if (len <= 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        // dp[i][j] 表示从i到j的子串是否为回文串
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    dp[i][j] = true;
                } else {
                    if (j - i == 1) {
                        dp[i][j] = chars[i] == chars[j];
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] && chars[i] == chars[j];
                    }
                }
            }
        }

        // res[i]表示以下标i为结尾的字符串的最小分割数
        int[] res = new int[len];
        res[0] = 0;
        for (int j = 1; j < len; j++) {
            if (dp[0][j]) {
                res[j] = 0;
            } else {
                res[j] = res[j - 1] + 1;
                for (int i = 1; i < j; i++) {
                    if (dp[i][j]) {
                        res[j] = Math.min(res[j], res[i - 1] + 1);
                    }
                }
            }
        }
        return res[len - 1];
    }

    @Test
    public void test() {
        String s = "abaaba";
        int minCut = minCut(s);
        log.info(minCut + "");
    }

}
