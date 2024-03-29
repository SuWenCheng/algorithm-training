package algorithm.leetcode;

/**
 * 实现strStr()函数。
 *
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 *
 * 
 *
 * 说明：
 *
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 * 
 *
 * 提示：
 *
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack 和 needle 仅由小写英文字符组成
 * 通过次数343,945提交次数858,182
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 */

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class StrStr {

    // 参考String.的indexOf
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        char[] haystackChars = haystack.toCharArray();
        char[] needleChars = needle.toCharArray();
        int hCount = haystack.length();
        int nCount = needle.length();
        char first = needleChars[0];
        int tCount = hCount - nCount;
        for (int i = 0; i <= tCount; i++) {
            if (haystack.charAt(i) != first) {
                while (i <= tCount && haystack.charAt(i) != first) {
                    i ++;
                }
            }
            if (i <= tCount) {
                int j = i + 1;
                int end = j + nCount - 1;
                for (int k = 1; j < end && haystackChars[j] == needleChars[k];
                     j++, k++);
                if (j == end) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        String haystack = "hello", needle = "ll";
        int strStr = strStr(haystack, needle);
        log.info(strStr + "");
    }

}
