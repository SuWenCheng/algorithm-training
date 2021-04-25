package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.Arrays;

/**
 * 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 */
@Slf4j
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int j = len - 1, i = j - 1;
        while (i >= 0) {
            if (nums[i] < nums[j]) {
                break;
            }
            i --;
            j --;
        }
        if (i < 0) {
            Arrays.sort(nums);
            return;
        }
        int numi = nums[i];
        // 找到[j, end]中最小的比num[i]大的数，与nums[i]换位置
        for (int k = len - 1; k >= j; k --) {
            if (nums[k] > numi) {
                int tmp = nums[k];
                nums[k] = numi;
                nums[i] = tmp;
                break;
            }
        }
        Arrays.sort(nums, j, len);
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 2};
        nextPermutation(nums);
        log.info(JsonHelper.toJson(nums));
    }

}
