package algorithm.leetcode;

import bean.TreeNode;

import java.util.*;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder =[9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 */
public class BuildTree {

    Map<Integer, Integer> inMap = new HashMap<>();
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i ++) {
            inMap.put(inorder[i], i);
        }
        this.post = postorder;
        return dfs(0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode dfs(int is, int ie, int ps, int pe) {
        if (ie < is || pe < ps) {
            return null;
        }
        int rootval = post[pe];
        TreeNode root = new TreeNode(rootval);
        int inRootIndex = inMap.get(rootval);
        root.left = dfs(is, inRootIndex - 1, ps, ps + inRootIndex - is - 1);
        root.right = dfs(inRootIndex + 1, ie, ps + inRootIndex - is, pe - 1);
        return root;
    }

}
