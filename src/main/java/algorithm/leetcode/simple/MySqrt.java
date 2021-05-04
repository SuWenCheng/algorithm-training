package algorithm.leetcode.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * x 的平方根
 *
 * 实现int sqrt(int x)函数。
 *
 * 计算并返回x的平方根，其中x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842..., 
 *     由于返回类型是整数，小数部分将被舍去。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
@Slf4j
public class MySqrt {

    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int left = 0, right = x / 2, ans = -1;
        while (left <= right) {
            int middle = (left + right) / 2;
            long m2 = (long) middle * middle;

            if (m2 > x) {
                right = middle - 1;
            } else {
                ans = middle;
                left = middle + 1;
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int x = 2147395599;
        int mySqrt = mySqrt(x);
        log.info(mySqrt + "");
    }

}
