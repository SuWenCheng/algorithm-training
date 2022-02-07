package algorithm.leetcode;

import bean.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;
import utils.TreeOperation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 后序遍历
 *
 * 给定一个二叉树，返回它的 后序遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 */
@Slf4j
public class PostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        TreeNode pre = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (node.right == null || node.right == pre) {
                res.add(node.data);
                pre = node;
                node = null;
            } else {
                stack.push(node);
                node = node.right;
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    public void dfs(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        dfs(root.left, res);
        dfs(root.right, res);
        res.add(root.data);
    }

    @Test
    public void test() {
        Integer[] arrs = {1,null,2,3};
        TreeNode treeNode = TreeOperation.generateTree(arrs);
        //List<Integer> res = postorderTraversal(treeNode);
        List<Integer> res = postorderTraversal2(treeNode);
        log.info(JsonHelper.toJson(res));
    }

}
