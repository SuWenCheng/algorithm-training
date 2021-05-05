package algorithm.leetcode.medium;

import lombok.extern.slf4j.Slf4j;

/**
 * 删除并获得点数
 *
 * 给你一个整数数组nums，你可以对它进行一些操作。
 *
 * 每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。
 * 之后，你必须删除每个等于nums[i] - 1或nums[i] + 1的元素。
 *
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * 示例2：
 *
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 */
@Slf4j
public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(num, maxNum);
        }
        int[] sums = new int[maxNum + 1];
        for (int num : nums) {
            sums[num] += num;
        }
        int first = sums[0], second = Math.max(first, sums[1]);
        for (int i = 2; i < maxNum + 1; i++) {
            int temp = second;
            second = Math.max(second, first + sums[i]);
            first = temp;
        }

        return second;
    }

}
