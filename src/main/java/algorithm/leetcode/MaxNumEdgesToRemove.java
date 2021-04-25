package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * 保证图可完全遍历
 *
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3 种类型的边：
 *
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi]表示节点 ui 和 vi 之间存在类型为 typei 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，
 * Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 *
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * 输出：2
 * 解释：如果删除 [1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。
 * 再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
 * 示例 2：
 *
 *
 *
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * 输出：0
 * 解释：注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
 * 示例 3：
 *
 *
 *
 * 输入：n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * 输出：-1
 * 解释：在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob 也不能达到节点 1 。因此，图无法完全遍历。
 * 
 *
 * 提示：
 *
 * 1 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
 * edges[i].length == 3
 * 1 <= edges[i][0] <= 3
 * 1 <= edges[i][1] < edges[i][2] <= n
 * 所有元组 (typei, ui, vi) 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable
 */
@Slf4j
public class MaxNumEdgesToRemove {

    // 并查集
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int res = 0;
        for (int[] edge : edges) {
            edge[1] --;
            edge[2] --;
        }

        // 公共边
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufa.union(edge[1], edge[2])) {
                    res ++;
                } else {
                    ufb.union(edge[1], edge[2]);
                }
            }
        }

        // 独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!ufa.union(edge[1], edge[2])) {
                    res ++;
                }
            } else if (edge[0] == 2) {
                if (!ufb.union(edge[1], edge[2])) {
                    res ++;
                }
            }
        }

        if (ufa.getSetCount() != 1 || ufb.getSetCount() != 1) {
            return -1;
        }
        return res;
    }

    private class UnionFind {

        private int[] parents;
        private int[] ranks;
        private int setCount;

        private UnionFind(int capacity) {
            this.parents = new int[capacity];
            this.ranks = new int[capacity];
            Arrays.fill(ranks, 1);
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
            setCount = capacity;
        }

        /**
         * 查找v所属的集合（根节点）- 路径压缩
         */
        public int find(int v) {
            while (v != parents[v]) {
                parents[v] = parents[parents[v]];
                v = parents[v];
            }
            return v;
        }

        /**
         * 合并x、y所在的集合 - 按秩合并
         */
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }

            if (ranks[rootX] > ranks[rootY]) {
                parents[rootY] = rootX;
            } else if (ranks[rootY]> ranks[rootY]) {
                parents[rootX] = rootY;
            } else {
                parents[rootX] = rootY;
                ranks[rootY] ++;
            }
            setCount --;
            return true;
        }

        public int getSetCount() {
            return setCount;
        }
    }

    @Test
    public void test() {
        int n = 4;
        int[][] edges = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        int maxNumEdgesToRemove = maxNumEdgesToRemove(n, edges);
        log.info(maxNumEdgesToRemove + "");
    }

}
