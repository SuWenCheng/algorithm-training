package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 分割数组为连续子序列
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * 示例 1：
 *
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 : 
 * 1, 2, 3
 * 3, 4, 5
 * 
 *
 * 示例 2：
 *
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 : 
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 
 *
 * 示例 3：
 *
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 * 
 * 提示：
 *
 * 输入的数组长度范围为 [1, 10000]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 */
@Slf4j
public class IsPossible {
    /**
     * 贪心
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences/solution/fen-ge-shu-zu-wei-lian-xu-zi-xu-lie-by-l-lbs5/
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                int preEndCount = endMap.getOrDefault(num - 1, 0);
                if (preEndCount > 0) {
                    countMap.put(num, count - 1);
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                    endMap.put(num - 1, preEndCount - 1);
                } else {
                    int numPlus1 = countMap.getOrDefault(num + 1, 0);
                    if (numPlus1 <= 0) {
                        return false;
                    }
                    int numPlus2 = countMap.getOrDefault(num + 2, 0);
                    if (numPlus2 <= 0) {
                        return false;
                    }
                    countMap.put(num, count - 1);
                    countMap.put(num + 1, numPlus1 - 1);
                    countMap.put(num + 2, numPlus2 - 1);
                    endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                }
            }
        }
        return true;
    }

}
