package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 距离顺序排列矩阵单元格
 *
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 *
 * 另外，我们在该矩阵中给出了一个坐标为(r0, c0) 的单元格。
 *
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 *
 * 示例 1：
 *
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 *
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 *
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * 
 *
 * 提示：
 *
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 */
@Slf4j
public class AllCellsDistOrder {

    /**
     * 桶排序
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int total = R * C;
        int[][] res = new int[total][2];
        // 最大距离
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        List<LinkedList<Pos>> buckets = new ArrayList<>();
        for (int i = 0; i <= maxDist; i ++) {
            buckets.add(new LinkedList<>());
        }
        for (int i = 0; i < R; i ++) {
            for (int j = 0; j < C; j ++) {
                Pos pos = new Pos(i, j);
                int dist = getDist(i, j, r0, c0);
                buckets.get(dist).add(pos);
            }
        }

        int index = 0;
        for (LinkedList<Pos> bucket : buckets) {
            for (Pos pos : bucket) {
                res[index][0] = pos.r;
                res[index][1] = pos.c;
                index ++;
            }
        }
        return res;
    }

    private int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    private class Pos {
        int r;
        int c;
        private Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
