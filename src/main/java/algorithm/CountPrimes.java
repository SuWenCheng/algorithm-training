package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 计数质数
 *
 * 统计所有小于非负整数n的质数的数量。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 * 
 *
 * 提示：
 *
 * 0 <= n <= 5 * 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 */
@Slf4j
public class CountPrimes {

        public int countPrimes(int n) {
            boolean[] isPrimes = new boolean[n];
            Arrays.fill(isPrimes, true);
            for (int i = 2; i * i < n; i ++) {
                if (isPrimes[i]) {
                    for (int j = i * i; j < n; j += i) {
                        isPrimes[j] = false;
                    }
                }
            }
            int res = 0;
            for (int i = 2; i < n; i++) {
                if (isPrimes[i]) {
                    res ++;
                }
            }
            return res;
        }
    

        @Test
        public void test() {

        }

}
