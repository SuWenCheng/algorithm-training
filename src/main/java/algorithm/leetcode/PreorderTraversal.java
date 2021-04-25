package algorithm.leetcode;

import bean.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的前序遍历
 *
 * 给定一个二叉树，返回它的前序遍历。
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
 * 输出: [1,2,3]
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 */
public class PreorderTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode head = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (head != null || !stack.isEmpty()) {
            while (head != null) {
                res.add(head.val);
                if (head.right != null) {
                    stack.push(head.right);
                }
                head = head.left;
            }
            head = stack.poll();
        }
        return res;
    }

}
