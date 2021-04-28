package algorithm.leetcode.medium;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 平方数之和
 *
 * 给定一个非负整数c，你要判断是否存在两个整数 a 和 b，使得a2 + b2 = c 。
 *
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 *
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 *
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 *
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 *
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 *
 * 提示：
 * 0 <= c <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 */
@Slf4j
public class JudgeSquareSum {

    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a*a);
            if (b == (int)b) {
                return true;
            }
        }
        return false;
    }

    // 双指针
    public boolean judgeSquareSum2(int c) {
        int a = 0;
        int b = (int)Math.sqrt(c);
        while (a <= b) {
            int sum = a * a + b * b;
            if (sum == c) {
                return true;
            }
            if (sum > c) {
                b --;
            } else {
                a ++;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int c = 5;
        boolean judgeSquareSum = judgeSquareSum(c);
        log.info(judgeSquareSum + "");
    }

}
