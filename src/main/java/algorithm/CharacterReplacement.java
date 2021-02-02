package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 替换后的最长重复字符
 *
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
 * 总共可最多替换k次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 *
 * 注意：字符串长度 和 k 不会超过104。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 示例 2：
 *
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 */
@Slf4j
public class CharacterReplacement {

    // 双指针/窗口滑动
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] counts = new int[26];
        int len = s.length();
        int left = 0, right = 0;
        int maxCount = 0;
        for (;right < len; right++) {
            int index = s.charAt(right) - 'A';
            counts[index] ++;
            maxCount = Math.max(maxCount, counts[index]);
            if (right - left + 1 > maxCount + k) {
                counts[s.charAt(left) - 'A'] --;
                left ++;
            }
        }
        return right - left;
    }

    @Test
    public void test() {
        String s = "BBCDAAABBCA";
        int k = 1;
        int count = characterReplacement(s, k);
        log.info(count + "");
    }

}
