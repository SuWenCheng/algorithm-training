package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 查找常用字符
 *
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 *
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 * 
 *
 * 提示：
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 */
@Slf4j
public class CommonChars {

    public List<String> commonChars(String[] A) {
        if (A.length <= 1) {
            Arrays.asList(A);
        }
        List<String> res = new ArrayList<>();
        String first = A[0];
        for (int i = 0; i < first.length(); i ++) {
            String c = first.charAt(i) + "";
            boolean common = true;
            for (int j = 1; j < A.length; j ++) {
                String a = A[j];
                if (!a.contains(c)) {
                    common = false;
                    break;
                }
                A[j] = a.replaceFirst(c, "");

            }
            if (common) {
                res.add(c);
            }
        }

        return res;
    }

    /**
     * 优化 : hash查找
     */
    public List<String> commonChars2(String[] A) {
        if (A.length <= 1) {
            Arrays.asList(A);
        }
        int[] hash = new int[26];
        String first = A[0];
        for (int i = 0; i < first.length(); i ++) {
            hash[first.charAt(i) - 'a'] ++;
        }

        for (int i = 1; i < A.length; i ++) {
            String str = A[i];
            int[] freq = new int[26];
            for (int j = 0; j < str.length(); j ++) {
                freq[str.charAt(j) - 'a'] ++;
            }
            for (int j = 0; j < 26; j ++) {
                hash[j] = Math.min(hash[j], freq[j]);
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i ++) {
            while (hash[i] != 0) {
                res.add(String.valueOf((char)(i + 'a')));
                hash[i] --;
            }
        }

        return res;
    }

    @Test
    public void test() {
        String[] strs = {"bella","label","roller"};
        List<String> res = commonChars2(strs);
        log.info(JsonHelper.toJson(res));
    }

    @Test
    public void testChar() {
        int a = 'a';
        int b = 'b';
        log.info(a + " " + b);
    }

}
