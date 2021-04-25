package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 单调数列
 *
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A是单调数组时返回 true，否则返回 false。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 *
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 *
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 *
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例5：
 *
 * 输入：[1,1,1]
 * 输出：true
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 */
@Slf4j
public class IsMonotonic {

    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len <= 2) {
            return true;
        }
        int flag = 0;
        for (int i = 1; i < len; i++) {
            int front = A[i - 1];
            int num = A[i];
            if (num > front) {
                if (flag >= 0) {
                    flag = 1;
                } else {
                    return false;
                }
            } else if (num < front) {
                if (flag <= 0) {
                    flag = -1;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] A = {1,3,2};
        boolean monotonic = isMonotonic(A);
        log.info(monotonic + "");
    }

}
