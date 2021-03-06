package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 下一个更大元素 II
 *
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
 * 输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 * 示例 1:
 *
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 */
@Slf4j
public class NextGreaterElements {

    // 单调栈
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new int[0];
        }
        if (len == 1) {
            return new int[]{-1};
        }
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len * 2; i++) {
            int index = i % len;
            int num = nums[index];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                res[stack.pop()] = num;
            }
            stack.push(index);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {1,2,1};
        int[] nextGreaterElements = nextGreaterElements(nums);
        log.info(JsonHelper.toJson(nextGreaterElements));
    }

}
