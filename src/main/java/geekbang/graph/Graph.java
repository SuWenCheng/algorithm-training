package geekbang.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 */
public class Graph {

    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    // 无向图一条边存两次
    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索（从s到t的最短路径）
     * @param s 开始顶点的索引
     * @param t 结束顶点的索引
     */
    public void bfs(int s, int t) {
        if (s == t) {
            return;
        }
        // 已被访问定顶点
        boolean[] visited = new boolean[v];
        visited[s] = true;

        // 存储已经被访问、但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        // 记录搜索路径（反向存储）
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

        while (queue.size() > 0) {
            Integer cur = queue.poll();
            for (int i = 0; i < adj[cur].size(); i++) {
                int child = adj[cur].get(i);
                if (!visited[child]) {
                    prev[child] = cur;
                    if (child == t) {
                        print(prev, s, t);
                        return;
                    }
                    queue.add(child);
                    visited[child] = true;
                }
            }
        }
    }

    /**
     * 深度优先搜索（从s到t的最短路径）
     * @param s 开始顶点的索引
     * @param t 结束顶点的索引
     */
    boolean found = false; // 当已经找到终止顶点 t 之后，就不再递归地继续查找
    public void dfs(int s, int t) {
        if (s == t) return;
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; i++) {
            prev[i] = -1;
        }

    }

    private void doDfs(int s, int t, boolean[] visited, int[] prev) {
        if (found) {
            return;
        }
        visited[s] = true;
        if (s == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[s].size(); i++) {
            int child = adj[s].get(i);
            if (!visited[child]) {
                prev[child] = s;
                doDfs(child, t, visited, prev);
            }
        }
    }

    // 递归打印s->t的路径
    private void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

}
