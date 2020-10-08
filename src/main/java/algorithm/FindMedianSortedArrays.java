package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 寻找两个正序数组的中位数
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的中位数。
 *
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * 示例 3：
 *
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * 示例 4：
 *
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * 示例 5：
 *
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * 
 *
 * 提示：
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
@Slf4j
public class FindMedianSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0) {
            return findMeidan(nums2);
        }
        if (len2 == 0) {
            return findMeidan(nums1);
        }
        int[] merge = new int[len1 + len2];
        int len = merge.length;
        int i = 0, j = 0, k = 0;
        while (k < len) {
            while (i < len1 && nums1[i] < nums2[j] && k < len) {
                merge[k ++] = nums1[i ++];
            }
            if (i >= len1) {
                while (k < len && j < len2) {
                    merge[k++] = nums2[j ++];
                }
                break;
            }
            while (j < len2 && nums1[i] >= nums2[j]  && k < len) {
                merge[k ++] = nums2[j ++];
            }
            if (j >= len2) {
                while (k < len && i < len1) {
                    merge[k++] = nums1[i ++];
                }
                break;
            }
        }
        return findMeidan(merge);
    }

    public double findMeidan(int[] arrys) {
        int len = arrys.length;
        if (arrys.length % 2 == 0) {
            return (arrys[len/2 - 1] + arrys[len / 2]) / 2.0;
        } else {
            return arrys[len / 2] * 1.0;
        }
    }

    @Test
    public void test() {
        int[] nums1 = {2};
        int[] nums2 = {};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        log.info(medianSortedArrays + "");
    }

    @Test
    public void test2() {
        int res = 5 + (10 + 2) / 2;
        log.info(res + "");
    }

}
