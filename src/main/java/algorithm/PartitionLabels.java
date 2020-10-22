package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 划分字母区间
 *
 * 符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * 
 *
 * 提示：
 *
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 */
@Slf4j
public class PartitionLabels {

    /**
     * 贪心算法 + 双指针
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int len = S.length();
        int[] lastIndexs = new int[26];
        for (int i = 0; i < len; i ++) {
            lastIndexs[S.charAt(i) - 'a'] = i;
        }

        int start = 0, end = 0;
        for (int i = 0; i < len; i ++) {
            char c = S.charAt(i);
            end = Math.max(end, lastIndexs[c - 'a']);
            if (i == end) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

    @Test
    public void test() {
        List<Integer> res = partitionLabels("ababcbacadefegdehijhklij");
        //List<Integer> res = partitionLabels("ababcbacadefbegdehijhklij");
        log.info(JsonHelper.toJson(res));
    }

}
