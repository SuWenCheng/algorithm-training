package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * N皇后 II
 *
 * n皇后问题研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..", // 解法 1
 *  "...Q",
 *  "Q...",
 *  "..Q."],
 *
 * ["..Q.", // 解法 2
 *  "Q...",
 *  "...Q",
 *  ".Q.."]
 * ]
 * 
 *
 * 提示：
 *
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 */
@Slf4j
public class TotalNQueens {

    public int totalNQueens(int n) {
        return backTracking(0, n, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    public int backTracking(int row, int n, Set<Integer> cols, Set<Integer> slash1, Set<Integer> slash2) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n; i ++) {
            int s1 = i + row;
            int s2 = i - row;
            if (cols.contains(i)) {
                continue;
            }
            if (slash1.contains(s1)) {
                continue;
            }
            if (slash2.contains(s2)) {
                continue;
            }
            cols.add(i);
            slash1.add(i + row);
            slash2.add(i - row);
            count += backTracking(row + 1, n, cols, slash1, slash2);
            cols.remove(i);
            slash1.remove(s1);
            slash2.remove(s2);
        }
        return count;
    }

    public int totalNQueens2(int n) {
        return backTracking2(0, n, 0, 0, 0);
    }
    /**
     * 优化： 改成位运算
     */
    public int backTracking2(int row, int n, int cols, int slash1, int slash2) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        int availablePositions = ((1 << n) - 1) & (~(cols | slash1 | slash2));
        while (availablePositions != 0) {
            // x & (-x)可以获得x的二进制数中醉的位的1的位置
            int pos = availablePositions & (-availablePositions);
            // x & (x - 1)可以将x最低位1置0
            availablePositions = availablePositions & (availablePositions - 1);
            count += backTracking2(row + 1, n, cols | pos, (pos | slash1) << 1, (slash2 | pos) >> 1);
        }

        return count;
    }

    @Test
    public void test() {
        log.info(totalNQueens(4) + "");
        log.info(totalNQueens2(4) + "");
    }

}
