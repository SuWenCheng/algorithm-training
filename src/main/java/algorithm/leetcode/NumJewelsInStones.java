package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 宝石与石头
 *
 * 给定字符串J代表石头中宝石的类型，和字符串S代表你拥有的石头。S中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 *
 * J中的字母不重复，J和S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
 *
 * 示例 1:
 *
 * 输入: J = "aA", S = "aAAbbbb"
 * 输出: 3
 * 示例 2:
 *
 * 输入: J = "z", S = "ZZ"
 * 输出: 0
 * 注意:
 *
 * S和J最多含有50个字母。
 * J中的字符不重复。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jewels-and-stones
 */
@Slf4j
public class NumJewelsInStones {

    public int numJewelsInStones(String J, String S) {
        int res = 0;
        if (J.length() == 0 || S.length() == 0) {
            return res;
        }
        List<Character> JList = new ArrayList<>();
        for (int i = 0; i < J.length(); i ++) {
            JList.add(J.charAt(i));
        }
        for (int j = 0; j < S.length(); j ++) {
            if (JList.contains(S.charAt(j))) {
                res ++;
            }
        }
        return res;
    }

    @Test
    public void test() {
        String J = "aA";
        String S = "aAAbbbb";
        int res = numJewelsInStones(J, S);
        log.info(res + "");
    }

}
