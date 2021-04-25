package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 三个数的最大乘积
 *
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 */
@Slf4j
public class MaximumProduct {

    public int maximumProduct(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        Arrays.sort(nums);

        int res1 = nums[len - 1] * nums[len - 2] * nums[len - 3];
        if (len == 3) {
            return res1;
        }
        int res2 = nums[len - 1] * nums[0] * nums[1];

        return Math.max(res1, res2);
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4};
        int maximumProduct = maximumProduct(nums);
        log.info(maximumProduct + "");
    }
}
