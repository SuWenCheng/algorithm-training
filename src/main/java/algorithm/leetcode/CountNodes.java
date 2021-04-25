package algorithm.leetcode;

import bean.TreeNode;
import lombok.extern.slf4j.Slf4j;

/**
 * 完全二叉树的节点个数
 *
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 *
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 *
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 *
 * 输出: 6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 */
@Slf4j
public class CountNodes {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftLevel = countLevel(root.left);
        int rightLevel = countLevel(root.right);
        TreeNode head = root;
        if (leftLevel == rightLevel) {
            return countNodes(root.right) + (1 << leftLevel);
        } else {
            return countNodes(root.left) + (1 <<
                    rightLevel);
        }

    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level ++;
            root = root.left;
        }
        return level;
    }

}
