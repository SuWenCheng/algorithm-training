package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;

/**
 * 项目管理
 * 
 * 公司共有n个项目和 m个小组，每个项目要不无人接手，要不就由 m 个小组之一负责。
 *
 * group[i] 表示第i个项目所属的小组，如果这个项目目前无人接手，那么group[i] 就等于-1。（项目和小组都是从零开始编号的）小组可能存在没有接手任何项目的情况。
 *
 * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：
 *
 * 同一小组的项目，排序后在列表中彼此相邻。
 * 项目之间存在一定的依赖关系，我们用一个列表 beforeItems来表示，其中beforeItems[i]表示在进行第i个项目前（位于第 i个项目左侧）应该完成的所有项目。
 * 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。
 *
 * 
 *
 * 示例 1：
 *
 *
 *
 * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * 输出：[6,3,4,1,5,2,0,7]
 * 示例2：
 *
 * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * 输出：[]
 * 解释：与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
 * 
 *
 * 提示：
 *
 * 1 <= m <= n <= 3 * 104
 * group.length == beforeItems.length == n
 * -1 <= group[i] <= m - 1
 * 0 <= beforeItems[i].length <= n - 1
 * 0 <= beforeItems[i][j] <= n - 1
 * i != beforeItems[i][j]
 * beforeItems[i] 不含重复元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies
 */
@Slf4j
public class SortItems {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        // 数据处理， 给没有归属某一组的项目编上2组号
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m ++;
            }
        }

        // 实例化组和邻接表
        List<Integer>[] groupAdj = new ArrayList[m];
        List<Integer> [] itemAdj = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            groupAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            itemAdj[i] = new ArrayList<>();
        }

        // 建图和统计入度数组
        int[] groupIndegree = new int[m];
        int[] intemIndgree = new int[n];
        for (int i = 0; i < n; i++) {
            int currentGroup = group[i];
            for (Integer beforeItem : beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                if (beforeGroup != currentGroup) {
                    groupAdj[beforeGroup].add(currentGroup);
                    groupIndegree[currentGroup] ++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Integer item : beforeItems.get(i)) {
                itemAdj[item].add(i);
                intemIndgree[i] ++;
            }
        }

        // 得到组合项目的拓扑排序结果
        List<Integer> groupList = topologicalSort(groupAdj, groupIndegree, m);
        if (groupList.isEmpty()) {
            return new int[0];
        }
        List<Integer> itemList = topologicalSort(itemAdj, intemIndgree, n);
        if (itemList.isEmpty()) {
            return new int[0];
        }

        Map<Integer, List<Integer>> group2Items = new HashMap<>();
        for (Integer item : itemList) {
            group2Items.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        List<Integer> res = new ArrayList<>();
        for (Integer groupId : groupList) {
            res.addAll(group2Items.getOrDefault(groupId, new ArrayList<>()));
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 拓扑排序
     */
    private List<Integer> topologicalSort(List<Integer>[] adj, int[] inDegree, int n) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // 获取无入边顶点列表
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer front = queue.poll();
            res.add(front);
            // 遍历出边节点，如 a -> b, b为a的出边节点
            for (int successor : adj[front]) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.offer(successor);
                }
            }
        }

        if (res.size() == n) {
            return res;
        }
        return new ArrayList<>();
    }

    @Test
    public void test() {
        int n = 8, m = 2;
        int[] group = {-1,-1,1,0,0,1,0,-1};
        int[][] befores = {{},{6},{5},{6},{3},{},{4},{}};
        List<List<Integer>> beforeItems = new ArrayList<>();
        for (int[] before : befores) {
            List<Integer> items = new ArrayList<>();
            for (int i : before) {
                items.add(i);
            }
            beforeItems.add(items);
        }
        int[] sortItems = sortItems(n, m, group, beforeItems);
        log.info(JsonHelper.toJson(sortItems));
    }

}
