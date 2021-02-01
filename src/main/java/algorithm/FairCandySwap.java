package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 公平的糖果棒交换
 *
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，
 * B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 *
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。
 * （一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 *
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，
 * ans[1]是 Bob 必须交换的糖果棒的大小。
 *
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 *
 * 示例 1：
 *
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 * 示例 2：
 *
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：A = [2], B = [1,3]
 * 输出：[2,3]
 * 示例 4：
 *
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * 保证爱丽丝与鲍勃的糖果总量不同。
 * 答案肯定存在。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fair-candy-swap
 */
@Slf4j
public class FairCandySwap {

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int average = (sumA + sumB) / 2;
        int needA = average - sumA;
        int[] res = new int[2];
        for (int i = 0; i < A.length; i ++) {
            for (int j = 0; j < B.length; j++) {
                int a = A[i];
                int b = B[j];
                if (b - a == needA) {
                    res[0] = a;
                    res[1] = b;
                    break;
                }
            }
        }
        return res;
    }

    public int[] fairCandySwap2(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumB - sumA) / 2;
        Set<Integer> collectB = new HashSet<>();
        for (int b : B) {
            collectB.add(b);
        }
        int[] res = new int[2];
        for (int a : A) {
            int b = a + delta;
            if (collectB.contains(b)) {
                res[0] = a;
                res[1] = b;
                break;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] A = {2};
        int[] B = {1, 3};
        int[] res = fairCandySwap2(A, B);
        log.info(JsonHelper.toJson(res));
    }

}
