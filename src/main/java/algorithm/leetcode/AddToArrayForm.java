package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;

/**
 * 数组形式的整数加法
 *
 * 对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。
 * 例如，如果X = 1231，那么其数组形式为[1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 *
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 *
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 *
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果A.length > 1，那么A[0] != 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 */
@Slf4j
public class AddToArrayForm {

    public List<Integer> addToArrayForm(int[] A, int K) {
        Deque<Integer> stack = new ArrayDeque<>();
        int tmp = K;
        while (tmp > 0) {
            stack.push(tmp % 10);
            tmp /= 10;
        }
        List<Integer> Klist = new ArrayList<>(stack);
        int kIndex = Klist.size() - 1;
        int aIndex = A.length - 1;
        int carry = 0;
        Deque<Integer> resStack = new ArrayDeque<>();
        while (kIndex >= 0 || aIndex >= 0) {
            int a = 0, b = 0;
            if (kIndex >= 0) {
                a = Klist.get(kIndex);
                kIndex --;
            }
            if (aIndex >= 0) {
                b = A[aIndex];
                aIndex --;
            }
            int sum = a + b + carry;
            carry = sum / 10;
            resStack.push(sum % 10);
        }
        if (carry > 0) {
            resStack.push(1);
        }
        return new ArrayList<>(resStack);
    }

    @Test
    public void test() {
        int[] A = {9,9,9,9,9,9,9,9,9,9};
        int K = 1;
        List<Integer> list = addToArrayForm(A, K);
        log.info(JsonHelper.toJson(list));
    }

}
