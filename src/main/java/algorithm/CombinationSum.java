package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
@Slf4j
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, res, new ArrayList<>(), 0, target);
        return res;

    }

    public void dfs(int[] candidates, List<List<Integer>> res, List<Integer> temp, int index, int target) {
        if (candidates.length <= index) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }


        if (target >= candidates[index]) {
            temp.add(candidates[index]);
            dfs(candidates, res, temp, index, target - candidates[index]);
            log.info("before temp : " + JsonHelper.toJson(temp));
            temp.remove(temp.size() - 1);
        }
        dfs(candidates, res, temp, index + 1, target);

    }

    @Test
    public void test() {
        int[] candidates = {2,3,6,7};
        log.info(JsonHelper.toJson(combinationSum(candidates, 7)));
    }

}
