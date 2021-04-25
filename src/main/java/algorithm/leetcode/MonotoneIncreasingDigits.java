package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 单调递增的数字
 *
 * 给定一个非负整数N，找出小于或等于N的最大的整数，
 * 同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字x和y满足x <= y时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N是在[0, 10^9]范围内的一个整数。
 * 126525252
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotone-increasing-digits
 */
@Slf4j
public class MonotoneIncreasingDigits {

    /**
     * 贪心
     */
    public int monotoneIncreasingDigits(int N) {
        char[] array = Integer.toString(N).toCharArray();
        int size = array.length;
        int max = -1, idx = -1;
        for (int i = 0; i < size - 1; i++) {
            if (max < array[i]) {
                max = array[i];
                idx = i;
            }
            if (array[i] > array[i + 1]) {
                array[idx] -= 1;
                for(int j = idx + 1; j < array.length; j++) {
                    array[j] = '9';
                }
                break;
            }
        }
        return Integer.parseInt(new String(array));
    }

    /**
     * 贪心
     */
    public int monotoneIncreasingDigits2(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i ++;
        }
        if (i < strN.length) {
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] --;
                i --;
            }
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    private boolean increasingDigit(int N) {
        int prev = 9;
        while (N > 0) {
            int digit = N % 10;
            if (digit > prev) {
                return false;
            }
            prev = digit;
            N /= 10;
        }
        return true;
    }

    @Test
    public void test() {
        char[] arr = {'0', '9'};
        log.info(Integer.parseInt(new String(arr)) + "");
    }

}
