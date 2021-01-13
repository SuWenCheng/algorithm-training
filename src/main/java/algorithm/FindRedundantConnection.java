package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 冗余连接
 *
 * 在本问题中, 树指的是一个连通且无环的无向图。
 *
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v]，满足u < v，表示连接顶点u和v的无向图的边。
 *
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边[u, v] 应满足相同的格式u < v。
 *
 * 示例 1：
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *   1
 *  / \
 * 2 - 3
 * 示例 2：
 *
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * 注意:
 *
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 * 更新(2017-09-26):
 * 我们已经重新检查了问题描述及测试用例，明确图是无向图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection
 */
@Slf4j
public class FindRedundantConnection {

    /**
     * 并查集
     */
    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
        int parent[] = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int index1 = edge[0];
            int index2 = edge[1];
            if (find(parent, index1) == find(parent, index2)) {
                return edge;
            } else {
                union(parent, index1, index2);
            }
        }
        return new int[0];
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

    @Test
    public void test() {
        int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[] redundantConnection = findRedundantConnection(edges);
        log.info(redundantConnection + "");
    }

}
