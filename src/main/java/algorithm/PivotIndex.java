package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 寻找数组的中心索引
 *
 * 给定一个整数类型的数组nums，请编写一个能够返回数组 “中心索引” 的方法。
 *
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * 示例 2：
 *
 * 输入：
 * nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心索引。
 * 
 *
 * 说明：
 *
 * nums 的长度范围为[0, 10000]。
 * 任何一个nums[i] 将会是一个范围在[-1000, 1000]的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 */
@Slf4j
public class PivotIndex {

    public int pivotIndex(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return 0;
        }
        int[] leftSums = new int[len];
        int[] rightSums = new int[len];
        leftSums[0] = nums[0];
        rightSums[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            leftSums[i] = leftSums[i-1] + nums[i];
            rightSums[len - 1 - i] = rightSums[len - i] + nums[len - 1 - i];
        }
        for (int i = 0; i < len; i++) {
            if (leftSums[i] == rightSums[i]) {
                return i;
            }
        }
        return -1;
    }

    // 前缀和
    public int pivotIndex2(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < total; i++) {
            if (total - sum - nums[i] == sum) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {-1,-1,-1,-1,0,0};
        int res = pivotIndex(nums);
        log.info(res + "");
    }

}
