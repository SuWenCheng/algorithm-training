package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 */
@Slf4j
public class IntToRoman {

    /**
     * 贪心算法
     */
    public String intToRoman(int num) {
        int[] ints = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length && num >= 0; i++) {
            while (num >= ints[i]) {
                num -= ints[i];
                sb.append(romans[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 超时
     */
    public String intToRoman2(int num) {
        Map<Integer, String[]> romanMap = new HashMap<>();
        romanMap.put(1, new String[]{"I", "V"});
        romanMap.put(2, new String[]{"X", "L"});
        romanMap.put(3, new String[]{"C", "D"});
        romanMap.put(4, new String[]{"M", ""});
        StringBuilder sb = new StringBuilder();
        int carry = 1;
        while (num > 0) {
            int remainder = num % 10;
            if (remainder == 0) {
                continue;
            }
            String[] romans = romanMap.get(carry);
            StringBuilder temp = new StringBuilder();
            if (remainder == 4) {
                temp = temp.append(romans[0] + romans[1]);
                sb.insert(0, temp);
            } else if (remainder == 9) {
                String[] nextRomans = romanMap.get(carry + 1);
                temp = temp.append(romans[0] + nextRomans[0]);
                sb.insert(0, temp);
            } else if (remainder == 5) {
                sb.insert(0, romans[1]);
            } else {

                if (remainder > 5) {
                    while (remainder - 5 > 0) {
                        temp.append(romans[0]);
                        remainder --;
                    }
                    temp.insert(0, romans[1]);
                } else {
                    while (remainder -- > 0) {
                        temp.append(romans[0]);
                    }
                }
                sb.insert(0, temp);
            }
            num /= 10;
            carry ++;
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String res = intToRoman(1994);
        log.info(res);
    }

}
