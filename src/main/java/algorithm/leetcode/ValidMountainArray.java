package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 有效的山脉数组
 *
 * 给定一个整数数组A，如果它是有效的山脉数组就返回true，否则返回 false。
 *
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
 *
 * A.length >= 3
 * 在0 < i< A.length - 1条件下，存在i使得：
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 
 *
 * 示例 1：
 *
 * 输入：[2,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：[3,5,5]
 * 输出：false
 * 示例 3：
 *
 * 输入：[0,3,2,1]
 * 输出：true
 * 
 *
 * 提示：
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-mountain-array
 */
@Slf4j
public class ValidMountainArray {

    public boolean validMountainArray(int[] A) {
        int len = A.length;
        if (len < 3) {
            return false;
        }
        int index = 0;
        while (index < len - 1 && A[index + 1] > A[index]) {
            index ++;
        }
        if (index == 0 || index == len - 1) {
            return false;
        }
        while (index < len - 1 && A[index + 1] < A[index]) {
            index ++;
        }
        return index == len - 1;
    }

    /**
     * 双指针
     */
    public boolean validMountainArray2(int[] A) {
        int len = A.length;
        if (len < 3) {
            return false;
        }
        int index1 = 0;
        while (index1 < len - 1 && A[index1 + 1] > A[index1]) {
            index1 ++;
        }
        if (index1 == len - 1) {
            return false;
        }
        int index2 = len - 1;
        while (index2 > 0 && A[index2 - 1] > A[index2]) {
            index2 --;
        }
        return index1 == index2 && index2 != 0;
    }

    @Test
    public void test() {
        int[] A = {0,1,2,3,4,5,6,7,8,9};
        log.info(validMountainArray2(A) + "");
    }

}
