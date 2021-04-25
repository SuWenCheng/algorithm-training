package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;

/**
 * 找到最小生成树里的关键边和伪关键边
 *
 * 给你一个 n个点的带权无向连通图，节点编号为 0到 n-1，同时还有一个数组 edges，
 * 其中 edges[i] = [fromi, toi, weighti]表示在fromi和toi节点之间有一条带权无向边。
 * 最小生成树(MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 *
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，
 * 会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
 * 伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 *
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 *
 * 示例 1：
 *
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * 输出：[[0,1],[2,3,4,5]]
 * 解释：上图描述了给定图。
 * 下图是所有的最小生成树。
 *
 * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，
 * 我们将这两个下标作为输出的第一个列表。
 * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
 * 示例 2 ：
 *
 *
 *
 * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * 输出：[[],[0,1,2,3]]
 * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。
 * 所以 4 条边都是伪关键边。
 * 
 *
 * 提示：
 *
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti<= 1000
 * 所有 (fromi, toi)数对都是互不相同的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
 */
@Slf4j
public class FindCriticalAndPseudoCriticalEdges {

    /**
     * 并查集
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int len = edges.length;
        // 创建新数组，记录好每条边的顺序
        int[][] newEdges = new int[len][4];
        for (int i = 0; i < len; i++) {
            System.arraycopy(edges[i], 0, newEdges[i], 0, 3);
            newEdges[i][3] = i;
        }
        // 按权重从小到大排序
        Arrays.sort(newEdges, Comparator.comparingInt(a -> a[2]));
        // 求最小生成树权值
        int val = mindelWeight(n, newEdges, -1);

        // 结果
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>(); // 关键边
        List<Integer> list2 = new ArrayList<>(); // 非关建边
        for (int i = 0; i < len; i++) {
            int[] edge = newEdges[i];
            int count1 = mindelWeight(n, newEdges, i);
            // 关键边
            if (count1 < 0 || count1 > val) {
                list1.add(edge[3]);
            } else {
                int[] parents = initParents(n);
                union(parents, edge[0], edge[1]);
                // 权重
                int newWeight = edge[2];
                for (int j = 0; j < len; j ++) {
                    if (j == i) {
                        continue;
                    }
                    int[] newEdge = newEdges[j];
                    if (union(parents, newEdge[0], newEdge[1])) {
                        newWeight += newEdge[2];
                    }
                }
                if (newWeight == val) {
                    list2.add(edge[3]);
                }
            }
        }

        res.add(list1);
        res.add(list2);
        return res;
    }

    /**
     * 初始化父节点
     * @param n 节点数
     * @return 父节点
     */
    private int[] initParents(int n) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        return parents;
    }

    /**
     * Kruskal算法求去掉某条数后的最小生成树权值
     * @param n 节点数
     * @param sortdEdges 按权重排序后的边
     * @param delete 去掉的边
     * @return 最小生成树权值,-1表示无法形成单个连通数
     */
    private int mindelWeight(int n, int[][] sortdEdges, int delete) {
        int[] parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        int val = 0;
        // 连通分量数目
        int count = n;
        for (int i = 0; i < sortdEdges.length; i++) {
            int[] edge = sortdEdges[i];
            if (i == delete) {
                continue;
            }
            int a = edge[0], b = edge[1];
            if (union(parents, a, b)) {
                count --;
                val += edge[2];
                if (count == 1) {
                    break;
                }
            }
        }
        return count == 1 ? val : -1;
    }

    private boolean union(int[] parents, int index1, int index2) {
        int root1 = find(parents, index1);
        int root2 = find(parents, index2);
        if (root1 == root2) {
            return false;
        }
        parents[root1] = root2;
        return true;
    }

    private int find(int[] parents, int index) {
        while (index != parents[index]) {
            index = parents[index];
        }
        return index;
    }

    @Test
    public void test() {
        int n = 14;
        int[][] edges = {{0,1,13},{0,2,6},{2,3,13},{3,4,4},{0,5,11},{4,6,14},{4,7,8},{2,8,6},{4,9,6},{7,10,4},{5,11,3},{6,12,7},{12,13,9},{7,13,2},{5,13,10},{0,6,4},{2,7,3},{0,7,8},{1,12,9},{10,12,11},{1,2,7},{1,3,10},{3,10,6},{6,10,4},{4,8,5},{1,13,4},{11,13,8},{2,12,10},{5,8,1},{3,7,6},{7,12,12},{1,7,9},{5,9,1},{2,13,10},{10,11,4},{3,5,10},{6,11,14},{5,12,3},{0,8,13},{8,9,1},{3,6,8},{0,3,4},{2,9,6},{0,11,4},{2,5,14},{4,11,2},{7,11,11},{1,11,6},{2,10,12},{0,13,4},{3,9,9},{4,12,3},{6,7,10},{6,8,13},{9,11,3},{1,6,2},{2,4,12},{0,10,3},{3,12,1},{3,8,12},{1,8,6},{8,13,2},{10,13,12},{9,13,11},{2,11,14},{5,10,9},{5,6,10},{2,6,9},{4,10,7},{3,13,10},{4,13,3},{3,11,9},{7,9,14},{6,9,5},{1,5,12},{4,5,3},{11,12,3},{0,4,8},{5,7,8},{9,12,13},{8,12,12},{1,10,6},{1,9,9},{7,8,9},{9,10,13},{8,11,3},{6,13,7},{0,12,10},{1,4,8},{8,10,2}};
        List<List<Integer>> result = findCriticalAndPseudoCriticalEdges(n, edges);
        log.info(JsonHelper.toJson(result));
    }
}
