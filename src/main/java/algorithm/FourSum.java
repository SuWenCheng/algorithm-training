package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;

/**
 * 四数之和
 *
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 */
@Slf4j
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int first = 0; first < len - 3; first ++) {
            int curr = nums[first];
            if (first > 0 && curr == nums[first - 1]) {
                continue;
            }
            int min = curr + nums[first + 1] + nums[first + 2] + nums[first + 3];
            if (min > target) {
                break;
            }
            int max = curr + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (max < target) {
                continue;
            }
            for (int i = first + 1; i < len -2; i ++) {
                if (i > first + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int min1 = curr + nums[i] + nums[i + 1] + nums[i + 2];
                if (min1 > target) {
                    break;
                }
                int max1 = curr + nums[len - 1] + nums[len -2] + nums[len - 3];
                if (max1 < target) {
                    continue;
                }
                int j = i + 1, k = len - 1;
                while (k > j) {
                    int sum = curr + nums[i] + nums[j] + nums[k];
                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(curr);
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        res.add(temp);
                        j ++;
                        while (j < k && nums[j] == nums[j - 1]) {
                            j ++;
                        }
                        k --;
                        while (j < k && k < len - 1 && nums[k] == nums[k + 1]) {
                            k --;
                        }
                    } else if (sum > target) {
                        k --;
                    } else {
                        j ++;
                    }
                }
            }

        }
        return res;
    }

    public void dfs(int[] nums, int depth, int sum, List<List<Integer>> res, boolean[] bools, Deque<Integer> stack, int target) {
        if (depth == 4) {
            if (sum == target) {
                res.add(new ArrayList<>(stack));
            }
            return;
        }

        for (int i = 0; i < nums.length; i ++) {
            if (bools[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && bools[i - 1]) {
                continue;
            }
            stack.push(nums[i]);
            bools[i] = true;
            sum += nums[i];
            dfs(nums, depth + 1, sum, res, bools, stack, target);
            stack.pop();
            bools[i] = false;
            sum -= nums[i];
        }

    }

    @Test
    public void test() {
        int[] nums = {1,-2,-5,-4,-3,3,3,5};
        List<List<Integer>> lists = fourSum(nums, -11);
        log.info(JsonHelper.toJson(lists));
    }

}
