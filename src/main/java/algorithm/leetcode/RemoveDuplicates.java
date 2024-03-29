package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 删除字符串中的所有相邻重复项
 *
 * 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 *
 * 示例：
 *
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * 
 *
 * 提示：
 *
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 */
@Slf4j
public class RemoveDuplicates {

    public String removeDuplicates(String S) {
        int len = S.length();
        if (len == 1) {
            return S;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = len - 1; i >= 0; i--) {
            Character c = S.charAt(i);
            if (stack.isEmpty() || stack.peek() != c) {
                stack.push(c);
            } else {
                while (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String S = "abbaca";
        String removeDuplicates = removeDuplicates(S);
        log.info(removeDuplicates);
    }

}
