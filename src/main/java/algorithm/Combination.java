package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 */
@Slf4j
public class Combination {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < k || k <= 0) {
            return result;
        }
        result = combine(n - 1, k - 1);
        if (result.isEmpty()) {
            result.add(new ArrayList<>());
        }
        for (List<Integer> temp : result) {
            temp.add(n);
        }
        result.addAll(combine(n - 1, k));
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> combine = combine(4, 2);
        log.info(JsonHelper.toJson(combine));
    }

}
