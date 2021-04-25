package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * 最小体力消耗路径
 *
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，
 * 其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，
 * 且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。
 * 你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
 *
 * 请你返回从左上角走到右下角的最小体力消耗值。
 *
 * 提示：
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 */
@Slf4j
public class MinimumEffortPath {

    // 并查集
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length;
        int height = heights[0].length;
        int total = row * height;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < height; j++) {
                int index = height * i + j;
                if (i > 0) {
                    edges.add(new int[]{index - height, index, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    edges.add(new int[] {index - 1, index, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        edges.sort(Comparator.comparingInt(a -> a[2]));
        UnionFind unionFind = new UnionFind(total);
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            unionFind.union(a, b);
            if (unionFind.canConnent(0, total - 1)) {
                return edge[2];
            }
        }
        return 0;
    }

    private class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            parents = new int[n];
            ranks = new int[n];
            Arrays.fill(ranks, 1);
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            while (x != parents[x]) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            int rankX = ranks[rootX];
            int rankY = ranks[rootY];
            if (rankX > rankY) {
                parents[rootY] = rootX;
            } else if (rankY > rankX) {
                parents[rootX] = rootY;
            } else {
                parents[rootX] = rootY;
                ranks[rankY] ++;
            }
        }

        public boolean canConnent(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            return rootX == rootY;
        }
    }

    @Test
    public void test() {
        int[][] heights = {{1,2,3},{3,8,4},{5,3,5}};
        int minimumEffortPath = minimumEffortPath(heights);
        log.info(minimumEffortPath + "");
    }
}
