package algorithm.leetcode;

import bean.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;
import utils.TreeOperation;

import java.util.*;

/**
 * 二叉树的锯齿形层序遍历
 *
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 */
@Slf4j
public class ZigzagLevelOrder {

    /**
     * 双重栈
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int flag = 1;
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if (flag > 0) {
                while (!stack1.isEmpty()) {
                    TreeNode pop = stack1.pop();
                    list.add(pop.val);
                    if (pop.left != null) {
                        stack2.push(pop.left);
                    }
                    if (pop.right != null) {
                        stack2.push(pop.right);
                    }
                }
                res.add(list);
            } else {
                while (!stack2.isEmpty()) {
                    TreeNode pop = stack2.pop();
                    list.add(pop.val);
                    if (pop.right != null) {
                        stack1.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack1.push(pop.left);
                    }
                }
                res.add(list);
            }
            flag *= -1;
        }
        return res;
    }

    @Test
    public void test() {
        Integer[] arrs = {3,9,20,null,null,15,7};
        TreeNode treeNode = TreeOperation.generateTree(arrs);
        List<List<Integer>> resLists = zigzagLevelOrder(treeNode);
        log.info(JsonHelper.toJson(resLists));
    }

}
