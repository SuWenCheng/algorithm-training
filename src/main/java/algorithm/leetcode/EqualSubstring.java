package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 尽可能使字符串相等
 *
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s中的第i个字符变到t中的第 i 个字符需要|s[i] - t[i]|的开销（开销可能为 0），
 * 也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，
 * 这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * 示例 2：
 *
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * 示例 3：
 *
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 * 
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s 和t都只含小写英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-equal-substrings-within-budget
 */
@Slf4j
public class EqualSubstring {

    // 双指针
    public int equalSubstring(String s, String t, int maxCost) {
        int len = s.length();
        int[] costs = new int[len];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        for (int i = 0; i < len; i++) {
            costs[i] = Math.abs(sChars[i] - tChars[i]);
        }
        int left = 0, right = 0;
        int res = 0;
        int totalCost = 0;
        while (right < len) {
            totalCost += costs[right];
            while (totalCost > maxCost) {
                totalCost -= costs[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    @Test
    public void test() {
        String s = "abcd", t = "bcdf";
        int cost = 3;
        int equalSubstring = equalSubstring(s, t, cost);
        log.info(equalSubstring + "");
    }

}
