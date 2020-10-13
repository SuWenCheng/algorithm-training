package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 回文数
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 */
@Slf4j
public class IsPalindrome {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int tmp = x;
        int rev = 0;
        while (tmp != 0) {
            rev = rev * 10 + tmp % 10;
            tmp = tmp / 10;
        }
        return rev == x;
    }

    /**
     * 优化 : 只要反转一半数字
     */
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x > 0)) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }

    @Test
    public void test() {
        log.info(isPalindrome2(12222) + "");
    }

}
