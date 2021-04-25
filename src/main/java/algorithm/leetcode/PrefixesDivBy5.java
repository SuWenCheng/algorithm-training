package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 可被 5 整除的二进制前缀
 *
 * 给定由若干0和1组成的数组 A。我们定义N_i：从A[0] 到A[i]的第 i个子数组被解释为一个二进制数（从最高有效位到最低有效位）。
 *
 * 返回布尔值列表answer，只有当N_i可以被 5整除时，答案answer[i] 为true，否则为 false。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：[0,1,1]
 * 输出：[true,false,false]
 * 解释：
 * 输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为真。
 * 示例 2：
 *
 * 输入：[1,1,1]
 * 输出：[false,false,false]
 * 示例 3：
 *
 * 输入：[0,1,1,1,1,1]
 * 输出：[true,false,false,false,true,false]
 * 示例4：
 *
 * 输入：[1,1,1,0,1]
 * 输出：[false,false,false,false,false]
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 30000
 * A[i] 为0或1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-prefix-divisible-by-5
 */
@Slf4j
public class PrefixesDivBy5 {

    public List<Boolean> prefixesDivBy5(int[] A) {
        int len = A.length;
        List<Boolean> res = new ArrayList<>(len);
        int val = 0;
        for (int j : A) {
            val = (2 * val + j) % 5;
            if (val == 0) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] A = {1,1,0,0,0,1,0,0,1};
        List<Boolean> booleans = prefixesDivBy5(A);
        log.info(JsonHelper.toJson(booleans));
    }

}
