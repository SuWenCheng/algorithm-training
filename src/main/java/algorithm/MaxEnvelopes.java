package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.Arrays;

/**
 *  俄罗斯套娃信封问题
 *
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式(w, h)出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 说明:
 * 不允许旋转信封。
 *
 * 示例:
 *
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3 
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 */
@Slf4j
public class MaxEnvelopes {

    // 动态规划
    public int maxEnvelopes(int[][] envelopes) {
        int len = envelopes.length;
        if (len == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        log.info(JsonHelper.toJson(envelopes));
        int[] result = new int[len];
        Arrays.fill(result, 1);
        int max = 1;
        for (int i = 1; i < len; i++) {
            int[] cur = envelopes[i];
            int curMax = 1;
            for (int j = i - 1; j >= 0; j --) {
                if (canInclude(envelopes[j], cur)) {
                    curMax = Math.max(curMax, result[j] + 1);
                }
            }
            result[i] = curMax;
            max = Math.max(max, curMax);
        }
        return max;
    }

    private boolean canInclude(int[] small, int[] big) {
        return big[1] > small[1] && big[0] > small[0];
    }

    @Test
    public void test() {
        int [][] envelopes = {{46,89},{50,53},{52,68},{72,45},{77,81}};
        int maxEnvelopes = maxEnvelopes(envelopes);
        log.info(maxEnvelopes + "");
    }

}
