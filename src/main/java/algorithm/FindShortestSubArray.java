package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组的度
 *
 * 给定一个非空且只包含非负数的整数数组nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与nums拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：[1, 2, 2, 3, 1]
 * 输出：2
 * 解释：
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2：
 *
 * 输入：[1,2,2,3,1,4,2]
 * 输出：6
 * 
 *
 * 提示：
 *
 * nums.length在1到 50,000 区间范围内。
 * nums[i]是一个在 0 到 49,999 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/degree-of-an-array
 */
@Slf4j
public class FindShortestSubArray {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                int[] ints = map.get(num);
                ints[0]++;
                ints[2] = i;
            } else {
                map.put(num, new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (arr[0] > maxNum) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (arr[0] == maxNum) {
                int len = arr[2] - arr[1] + 1;
                if (len < minLen) {
                    minLen = len;
                }
            }
        }
        return minLen;
    }

    @Test
    public void test() {
        int[] arrs = {1,2,2,3,1,4,2};
        int subArray = findShortestSubArray(arrs);
        log.info(subArray + "");
    }

}
