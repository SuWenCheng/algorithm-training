package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
@Slf4j
public class PermuteUnique {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] bools = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, bools, res, new ArrayDeque<>());
        return res;
    }

    public void dfs(int[] nums, int depth, boolean[] bools, List<List<Integer>> res, Deque<Integer> stack) {

        if (depth == nums.length) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < nums.length; i ++) {
            if (bools[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && bools[i - 1]) {
                continue;
            }

            bools[i] = true;
            stack.addLast(nums[i]);
            dfs(nums, depth + 1, bools, res, stack);
            stack.removeLast();
            bools[i] = false;
        }
    }

    @Test
    public void test() {
        int[] nums = {1,1,2};
        List<List<Integer>> res = permuteUnique(nums);
        log.info(JsonHelper.toJson(res));
    }


}
