package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * 分割等和子集
 * 
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 *
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 *
 * 输入: [1, 5, 11, 5]
 *
 * 输出: true
 *
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 
 *
 * 示例2:
 *
 * 输入: [1, 2, 3, 5]
 *
 * 输出: false
 *
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 */
@Slf4j
public class CanPartition {

    /**
     * 转换为 「0 - 1」 背包问题 (动态规划)
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 如果数组和为奇数，则肯定不能分成两个和相等的数组
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;


        // dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数，
        // 每个数只能用一次，使得这些数的和恰好等于 j （改进为一维数组）
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i < len; i ++) {
            for (int j = target; nums[i] <= j; j --) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
            if (dp[target]) {
                return true;
            }
        }
        return dp[target];
    }

    @Test
    public void test() {
        int[] nums = {2,2,1,1};
        log.info(canPartition(nums) + "");
    }

}
