package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 基本计算器
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 *
 * 示例 1：
 *
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 *
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 *
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *
 * 提示：
 *
 * 1 <= s.length <= 3* 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 */
@Slf4j
public class Calculate {

    public int calculate(String s) {
        int len = s.length();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        int res = 0;
        int sign = 1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
            } else if (c == '-') {
                sign = -stack.peek();
            } else if (c == '+') {
                sign = stack.peek();
            } else if (c == '(') {
                stack.push(sign);
            } else if (c == ')') {
                stack.pop();
            } else {
                long num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i ++;
                }
                res += num * sign;
                i --;
            }
        }
        return res;
    }

    @Test
    public void test() {
        String s = "(1+(4+5+2)-3)+(6+8)";
        log.info(calculate(s) + "");
    }

}
