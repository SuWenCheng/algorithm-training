package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一个9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。
 *
 *
 * 上图是一个部分填充的有效的数独。
 *
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 * 示例1:
 *
 * 输入:
 * {
 *   {'5','3','.','.','7','.','.','.','.'},
 *   {'6','.','.','1','9','5','.','.','.'},
 *   {'.','9','8','.','.','.','.','6','.'},
 *   {'8','.','.','.','6','.','.','.','3'},
 *   {'4','.','.','8','.','3','.','.','1'},
 *   {'7','.','.','.','2','.','.','.','6'},
 *   {'.','6','.','.','.','.','2','8','.'},
 *   {'.','.','.','4','1','9','.','.','5'},
 *   {'.','.','.','.','8','.','.','7','9'}
 * }
 * 输出: true
 * 示例2:
 *
 * 输入:
 * {
 *  {'8','3','.','.','7','.','.','.','.'},
 *  {'6','.','.','1','9','5','.','.','.'},
 *  {'.','9','8','.','.','.','.','6','.'},
 *  {'8','.','.','.','6','.','.','.','3'},
 *  {'4','.','.','8','.','3','.','.','1'},
 *  {'7','.','.','.','2','.','.','.','6'},
 *  {'.','6','.','.','.','.','2','8','.'},
 *  {'.','.','.','4','1','9','.','.','5'},
 *  {'.','.','.','.','8','.','.','7','9'}
 * }
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 *      但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 *
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字1-9和字符'.'。
 * 给定数独永远是9x9形式的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 */
@Slf4j
public class IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        List<List<Integer>> rows = new ArrayList<>(9);
        List<List<Integer>> cols = new ArrayList<>(9);
        List<List<Integer>> subBoxs = new ArrayList<>(9);
        for (int i = 0; i < 9; i ++) {
            rows.add(new ArrayList<>());
            cols.add(new ArrayList<>());
            subBoxs.add(new ArrayList<>());
        }
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = (int)c;
                    List<Integer> row = rows.get(i);
                    if (row.contains(num)) {
                        return false;
                    }
                    row.add(num);
                    List<Integer> col = cols.get(j);
                    if (col.contains(num)) {
                        return false;
                    }
                    col.add(num);
                    int subBoxIndex = (i / 3) * 3 + j / 3;
                    List<Integer> subBox = subBoxs.get(subBoxIndex);
                    if (subBox.contains(num)) {
                        return false;
                    }
                    subBox.add(num);
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] chars = {
          {'5','3','.','.','7','.','.','.','.'},
          {'6','.','.','1','9','5','.','.','.'},
          {'.','9','8','.','.','.','.','6','.'},
          {'8','.','.','.','6','.','.','.','3'},
          {'4','.','.','8','.','3','.','.','1'},
          {'7','.','.','.','2','.','.','.','6'},
          {'.','6','.','.','.','.','2','8','.'},
          {'.','.','.','4','1','9','.','.','5'},
          {'.','.','.','.','8','.','.','7','9'}
        };
        log.info(isValidSudoku(chars) + "");
    }

}
