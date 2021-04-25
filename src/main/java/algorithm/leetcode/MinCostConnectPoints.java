package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 连接所有点的最小费用
 *
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 *
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 *
 * 提示：
 *
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 */
@Slf4j
public class MinCostConnectPoints {

    /**
     * 并查集
     */
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        int[] parent = new int[len];
        for (int i = 0; i < len; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        edges.sort(Comparator.comparingInt(a -> a.len));
        int res = 0;
        for (Edge edge : edges) {
            int rootX = find(parent, edge.x);
            int rootY = find(parent, edge.y);
            if (rootX != rootY) {
                res += edge.len;
                union(parent, rootX, rootY);
            }
        }
        return res;
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    private int find(int[] parent, int index) {
        while (index != parent[index]) {
            index = parent[index];
        }
        return index;
    }

    private int dist(int[][] points, int x, int y) {
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }

    private static class Edge {
        int len, x, y;

        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }

    @Test
    public void test() {
        int[][] points =  {{0,0},{2,2},{3,10},{5,2},{7,0}};
        int result = minCostConnectPoints(points);
        log.info(result + "");
    }

}
