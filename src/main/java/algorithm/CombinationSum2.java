package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, res, new ArrayList<>(), 0, target);
        return res;

    }

    public void dfs(int[] candidates, List<List<Integer>> res, List<Integer> temp, int index, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        if (index >= candidates.length) {
            return;
        }

        for(int i = index; i < candidates.length; i ++) {
            if (target < candidates[i]) {
                break;
            }

            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (target >= candidates[i]) {
                temp.add(candidates[i]);
                log.info("temp before: {}, 剩余 :{}", JsonHelper.toJson(temp), target - candidates[i]);
                dfs(candidates, res, temp, i + 1, target - candidates[i]);
                temp.remove(temp.size() - 1);
                log.info("temp after: {}", JsonHelper.toJson(temp));
            }
        }

    }

    @Test
    public void test() {
        int[] candidates = {10,1,2,7,6,1,5};
        log.info(JsonHelper.toJson(combinationSum2(candidates, 8)));
    }

}
