package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

/**
 * 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 */
@Slf4j
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int zeroCount = 0;
        int len = nums.length;
        int index = 0;
        for (; index < len ; index ++) {
            int num = nums[index];
            if (num == 0) {
                zeroCount ++;
                continue;
            }
            nums[index - zeroCount] = num;
        }
        while (zeroCount > 0) {
            nums[--index] = 0;
            zeroCount --;
        }

    }

    public void moveZeroes2(int[] nums) {
        int slow = 0, fast = 0;
        for (; fast < nums.length; fast ++) {
            if (nums[fast] != 0) {
                nums[slow ++] = nums[fast];
            }
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }

    @Test
    public void test() {
        int[] nums = {0,1,0,3,12};
        moveZeroes(nums);
        log.info(JsonHelper.toJson(nums));
    }
}
