package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串""。
 *
 * 示例1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母a-z。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 */
@Slf4j
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        StringBuilder res = new StringBuilder("");
        String first = strs[0];
        for (int i = 0; i < first.length(); i ++) {
            char c = first.charAt(i);
            boolean flag = true;
            for (int j = 1; j < strs.length; j ++) {
                String str = strs[j];
                if (str.length() < i + 1 || str.charAt(i) != c) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.append(c);
            } else {
                break;
            }
        }
        
        return res.toString();
    }

    @Test
    public void test() {
        String[] strs = {"aca","cba"};
        log.info(longestCommonPrefix(strs));
    }

}
