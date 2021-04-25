package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 最长湍流子数组
 *
 * 当 A的子数组A[i], A[i+1], ..., A[j]满足下列条件时，我们称其为湍流子数组：
 *
 * 若i <= k < j，当 k为奇数时，A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若i <= k < j，当 k 为偶数时，A[k] > A[k+1]，且当 k为奇数时，A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 *
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 *
 * 输入：[100]
 * 输出：1
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 */
@Slf4j
public class MaxTurbulenceSize {

    // 动态规划
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return 1;
        } else if (len == 2){
            if (arr[0] == arr[1]) {
                return 1;
            }
            return 2;
        }
        // dp[i][0]表示0 ~ i的最长湍流子数组长度，且arr[i] > arr[i - 1];
        // dp[i][1]表示0 ~ i的最长湍流子数组长度，且arr[i] < arr[i - 1];
        int[][] dp = new int[len][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int res = 1;
        for (int i = 1; i < len; i++) {
            // 如果arr[i-1]=arr[i]，则arr[i-1]和arr[i] 不能同时出现在同一个湍流子数组中，因此dp[i][0]=dp[i][1]=1
            dp[i][0] = dp[i][1] = 1;
            int d = arr[i] - arr[i - 1];
            if (d > 0) {
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (d < 0) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            res = Math.max(res, dp[i][1]);
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }

    // 动态规划-空间优化
    public int maxTurbulenceSize2(int[] arr) {
        int res = 1;
        int len = arr.length;
        int dp0 = 1, dp1 = 1;
        for (int i = 1; i < len; i++) {
            int d = arr[i] - arr[i - 1];
            if (d > 0) {
                dp0 = dp1 + 1;
                dp1 = 1;
            } else if (d < 0) {
                dp1 = dp0 + 1;
                dp0 = 1;
            } else {
                dp0 = dp1 = 1;
            }
            res = Math.max(res, dp0);
            res = Math.max(res, dp1);
        }
        return res;
    }

    // 滑动窗口（双指针）
    public int maxTurbulenceSize3(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int left = 0, right = 0;

        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            ret = Math.max(ret, right - left + 1);
        }
        return ret;
    }

    @Test
    public void test() {
        int[] arr = {9,4,2,10,7,8,8,1,9};
        int maxTurbulenceSize = maxTurbulenceSize(arr);
        log.info(maxTurbulenceSize + "");
    }

}
