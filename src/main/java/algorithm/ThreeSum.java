package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 */
@Slf4j
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int k = 0; k < nums.length - 2; k ++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            if (nums[k] > 0) {
                break;
            }
            int i = k + 1, j = nums.length - 1;
            while (j > i) {
                int sum = nums[k] + nums[i] + nums[j];
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    i ++;
                    continue;
                }
                if (j < nums.length - 1 && nums[j] == nums[j + 1]) {
                    j --;
                    continue;
                }
                if (sum == 0) {
                    temp.add(nums[k]);
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    res.add(new ArrayList<>(temp));
                    temp.clear();
                    i ++;
                } else if (sum > 0) {
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
        int[] nums = {0,0,0,0};
        List<List<Integer>> lists = threeSum(nums);
        log.info(JsonHelper.toJson(lists));
    }

}
