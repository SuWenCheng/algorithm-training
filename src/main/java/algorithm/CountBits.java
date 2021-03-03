package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

/**
 * 比特位计数
 *
 * 给定一个非负整数num。对于0 ≤ i ≤ num 范围中的每个数字i，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的__builtin_popcount）来执行此操作。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 */
@Slf4j
public class CountBits {

    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 != 0) {
                res[i] = res[i - 1] + 1;
            } else {
                int temp = i / 2;
                while (temp % 2 == 0) {
                    temp /= 2;
                }
                res[i] = res[temp];
            }
        }
        return res;
    }

    @Test
    public void test() {
        int num = 5;
        int[] ints = countBits(num);
        log.info(JsonHelper.toJson(ints));
    }

}
