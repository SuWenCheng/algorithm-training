package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包括n 个整数的数组nums和 一个目标值target。
 * 找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3<= nums[i]<= 10^3
 * -10^4<= target<= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 */
@Slf4j
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int firstSum = nums[0] + nums[1] + nums[2];
        int minDiff = Math.abs(firstSum - target);
        int res = firstSum;
        if (nums.length == 3 || minDiff == 0) {
            return res;
        }
        for (int k = 0; k < nums.length - 2; k ++) {
            int i = k + 1, j = nums.length - 1;
            while (j > i) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = sum - target;
                if (diff == 0) {
                    return sum;
                }
                if (Math.abs(diff) < minDiff) {
                    res = sum;
                    minDiff = Math.abs(diff);
                }
                if (sum > target) {
                    j --;
                } else {
                    i ++;
                }
            }
        }

        return res;
    }

    @Test
    public void test() {
        int[] nums = {-3,-2,-5,3,-4};
        int i = threeSumClosest(nums, -1);
        log.info(i + "");
    }

}
