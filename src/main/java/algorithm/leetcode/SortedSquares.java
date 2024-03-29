package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;


/**
 * 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A已按非递减顺序排序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 */
@Slf4j
public class SortedSquares {

    /**
     * 两个指针分别指向位置 0 和 len - 1
     * 每次比较两个指针对应的数，选择较大的那个逆序放入答案并移动指针。
     */
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int len = A.length;
        int[] res = new int[len];
        int i = 0, j = len - 1, pos = len - 1;
        while (i <= j) {
            if (A[i] * A[i] > A[j] * A[j]) {
                res[pos --] = A[i] * A[i];
                i ++;
            } else {
                res[pos --] = A[j] * A[j];
                j --;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] arrs = {-7,-3,2,3,11};
        arrs = sortedSquares(arrs);
        log.info(JsonHelper.toJson(arrs));
    }

}
