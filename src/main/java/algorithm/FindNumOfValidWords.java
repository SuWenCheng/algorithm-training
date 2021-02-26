package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 猜字谜
 *
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 *
 * 字谜的迷面puzzle 按字符串形式给出，如果一个单词word符合下面两个条件，那么它就可以算作谜底：
 *
 * 单词word中包含谜面puzzle的第一个字母。
 * 单词word中的每一个字母都可以在谜面puzzle中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及"based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 返回一个答案数组answer，数组中的每个元素answer[i]是在给出的单词列表 words 中可以作为字谜迷面puzzles[i]所对应的谜底的单词数目。
 *
 * 
 *
 * 示例：
 *
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"], 
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa" 
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为"absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为"actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为"gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 * 
 *
 * 提示：
 *
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j]都是小写英文字母。
 * 每个puzzles[i]所包含的字符都不重复。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 */
@Slf4j
public class FindNumOfValidWords {

    // 状态压缩
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); i++) {
                mask |= (1 << (word.charAt(i) - 'a'));
            }
            if (Integer.bitCount(mask) <= 7) {
                wordMap.put(mask, wordMap.getOrDefault(mask, 0) + 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (String puzzle : puzzles) {
            int total = 0;
            int mask = 0;
            for (int i = 1; i < 7; i++) {
                mask |= (1 << (puzzle.charAt(i) - 'a'));
            }

            int firstCharAt = puzzle.charAt(0) - 'a';
            // 通用枚举二进制子集方法
            int subset = mask;
            do {
                int s = subset | (1 << firstCharAt);
                total += wordMap.getOrDefault(s, 0);
                subset = (subset - 1) & mask;
            } while (subset != mask);

            res.add(total);
        }
        return res;
    }

    @Test
    public void test() {
        String[] words = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        List<Integer> numOfValidWords = findNumOfValidWords(words, puzzles);
        log.info(JsonHelper.toJson(numOfValidWords));
    }

}
