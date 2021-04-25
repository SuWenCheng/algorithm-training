package algorithm.leetcode.simple;

import bean.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 递增顺序搜索树
 *
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *
 * 提示：
 *
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-order-search-tree
 */
@Slf4j
public class IncreasingBST {


    public TreeNode increasingBST(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        TreeNode dummyNode = new TreeNode(-1);
        TreeNode cur = dummyNode;
        for (Integer integer : result) {
            TreeNode node = new TreeNode(integer);
            cur.right = node;
            cur = node;
        }
        return dummyNode.right;
    }

    // 中序遍历
    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
