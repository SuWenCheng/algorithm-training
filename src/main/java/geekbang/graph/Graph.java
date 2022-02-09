package geekbang.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
     * 获取用户的n度好友（基于广度优先搜索）
     * @param s 目标用户
     * @param degree 好友度数
     */
    public List<Integer> lastestFriendBfs(int s, int degree) {
        List<Integer> result = new ArrayList<>();

        // 记录每个顶点与起始顶点的距离
        int[] degrees = new int[v];

        // 已被访问定顶点
        boolean[] visited = new boolean[v];
        visited[s] = true;

        // 存储已经被访问、但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        // 记录总共遍历的次数
        int cycleNum = 0;

        while (queue.size() > 0) {
            Integer cur = queue.poll();
            // 顶点离起始顶点距离超过 3 时则退出
            if (degrees[cur] == degree) {
                break;
            }
            for (int i = 0; i < adj[cur].size(); i++) {
                int child = adj[cur].get(i);
                if (!visited[child]) {
                    System.out.println(child);
                    queue.add(child);
                    visited[child] = true;
                    degrees[child] = degrees[cur] + 1;
                    if (degrees[child] == degree) {
                        result.add(child);
                    }
                    cycleNum ++;
                }
            }
        }

        System.out.println("广度优先搜索" + degree + "度好友关系一共遍历的次数：" + cycleNum);

        return result;
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
        doDfs(s, t, visited, prev);
        print(prev, s, t);
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

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(4,6);
        graph.addEdge(2,3);
        graph.addEdge(2,7);
        graph.addEdge(2,8);
        graph.addEdge(3,5);
        graph.addEdge(3,6);
        graph.addEdge(5,7);
        graph.addEdge(5,6);
//        graph.bfs(0,6);

        // 深度优先
        List<Integer> friendBfs = graph.lastestFriendBfs(0, 2);
        System.out.println(friendBfs);

    }

}
