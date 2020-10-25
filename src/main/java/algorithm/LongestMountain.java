package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 数组中的最长山脉
 *
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 *
 * B.length >= 3
 * 存在 0 < i< B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 *
 * 给出一个整数数组 A，返回最长 “山脉”的长度。
 *
 * 如果不含有 “山脉”则返回 0。
 *
 * 示例 1：
 *
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 *
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class LongestMountain {

    /**
     * 动态规划
     */
    public int longestMountain(int[] A) {
        int len = A.length;
        if (A.length < 3) {
            return 0;
        }
        int[] left = new int[len]; // left[i] 表示A[i]左侧的“山脚”数量
        int[] right = new int[len]; // right[i] 表示A[i]右侧的山脚数量
        for (int i = 1; i < len; i ++) {
            if (A[i] > A[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
        }
        for (int i = len - 2; i > 0; i --) {
            if (A[i] > A[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0;
            }
        }
        int res = 0;
        for (int i = 0; i < len; i ++) {
            if (left[i] <= 0|| right[i] <= 0) {
                continue;
            }
            int hight = left[i] + right[i] + 1;
            if (hight > res) {
                res = hight;
            }
        }
        return res;
    }

    /**
     * 双指针 : 枚举山脚
     */
    public int longestMountain2(int[] A) {
        int len = A.length;
        if (A.length < 3) {
            return 0;
        }
        int left = 0;
        int res = 0;
        while (left < len - 2) {
            int right = left + 1;
            if (A[left] < A[left + 1]) {
                while (right < len - 1 && A[right + 1] > A[right]) {
                    right ++;
                }
                if (right < len - 1 && A[right] > A[right + 1]) {
                    while (right < len - 1 && A[right] > A[right + 1]) {
                        right ++;
                    }
                    res = Math.max(res, right - left + 1);
                } else {
                    right ++;
                }
            }
            left = right;
        }
        return res;
    }

    @Test
    public void test() {
        int[] A = {2,1,4,7,3,2,1};
        log.info(longestMountain2(A) + "");
    }

}
