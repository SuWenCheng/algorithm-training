package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回[-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
@Slf4j
public class SearchRange {

    /**
     * 二分查找
     */
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int start = 0, end = len - 1;
        int[] res = new int[] {-1, -1};
        int half = (end - start + 1) / 2;
        while (start <= end) {
            if (start == end && nums[start] != target) {
                break;
            }
            if (nums[half] == target) {
                res[0] = res[1] = half;
                break;
            }
            if (nums[half] < target) {
                start = half + 1;
            } else {
                end = half - 1;
            }
            half = (end - start + 1) / 2 + start;
        }
        if (res[0] != -1) {
            start = end = half;
            while (start >= 0 && nums[start] == target) {
                start --;
            }
            while (end <= len - 1 && nums[end] == target) {
                end ++;
            }
            res[0] = start + 1;
            res[1] = end - 1;
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 4};
        int target = 4;
        int[] ints = searchRange(nums, target);
        log.info(JsonHelper.toJson(ints));
    }

}
