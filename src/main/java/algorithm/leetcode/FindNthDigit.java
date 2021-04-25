package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 第N个数字
 *
 * 在无限的整数序列1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第n个数字。
 *
 * 注意:
 * n是正数且在32位整数范围内(n < 231)。
 *
 * 示例 1:
 *
 * 输入:
 * 3
 *
 * 输出:
 * 3
 * 示例 2:
 *
 * 输入:
 * 11
 *
 * 输出:
 * 0
 *
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 */
@Slf4j
public class FindNthDigit {


    public int findNthDigit(int n) {
        int digit = 1;
        int preSum = 0;
        int sum = 9;
        while (n > sum) {
            preSum = sum;
            sum += 9 * Math.pow(10, digit) * (digit + 1);
            digit ++;
        }
        //digit --;
        int nums = (n - preSum) / digit;
        int remainder = (n - preSum) % digit;
        int resNum = (int)Math.pow(10, digit - 1) - 1 + nums;
        if (remainder == 0) {
            return resNum % 10;
        }
        char[] chars = String.valueOf(resNum + 1).toCharArray();
        return Integer.parseInt(String.valueOf(chars[remainder - 1]));
    }

    @Test
    public void test() {
        int n = 9;
        log.info(findNthDigit(n) + "");
    }

}
