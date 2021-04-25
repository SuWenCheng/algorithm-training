package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 交换字符串中的元素
 *
 * 给你一个字符串s，以及该字符串中的一些「索引对」数组pairs，其中pairs[i] =[a, b]表示字符串中的两个索引（编号从 0 开始）。
 *
 * 你可以 任意多次交换 在pairs中任意一对索引处的字符。
 *
 * 返回在经过若干次交换后，s可以变成的按字典序最小的字符串。
 *
 * 
 *
 * 示例 1:
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释： 
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 *
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] <s.length
 * s中只含有小写英文字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 */
@Slf4j
public class SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.isEmpty()) {
            return s;
        }
        // 第 1 步：将任意交换的结点对输入并查集
        int len =s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int x = pair.get(0);
            int y = pair.get(1);
            unionFind.union(x, y);
        }

        // 第 2 步：构建映射关系
        char[] charArray = s.toCharArray();
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            hashMap.computeIfAbsent(root, k -> new PriorityQueue<>()).offer(charArray[i]);
        }

        // 重组字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            sb.append(hashMap.get(root).poll());
        }
        return sb.toString();
    }

    private class UnionFind {

        private int[] parents;
        /**
         * 以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
         */
        private int[] rank;

        private UnionFind(int capacity) {
            this.parents = new int[capacity];
            this.rank = new int[capacity];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
                this.rank[i] = 1;
            }
        }

        /**
         * 查找v所属的集合（根节点）
         */
        public int find(int v) {
            while (v != parents[v]) {
                v = parents[v];
            }
            return v;
        }

        /**
         * 合并x、y所在的集合
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] == rank[rootY]) {
                parents[rootX] = rootY;
                rootY++;
            } else if (rank[rootX] < rank[rootY]) {
                parents[rootX] = rootY;
            } else {
                parents[rootY] = rootX;
            }
        }
    }

    @Test
    public void test() {

    }

}
