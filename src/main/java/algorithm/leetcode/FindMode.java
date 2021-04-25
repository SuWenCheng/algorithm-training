package algorithm.leetcode;


import bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树中的众数
 *
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 *
 * 提示：如果众数超过1个，不需考虑输出顺序
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 */
public class FindMode {
    List<Integer> res = new ArrayList<>();
    int maxCount = 0;
    int curr = 0;
    int currCount = 0;
    public int[] findMode(TreeNode root) {
        inorderTraversal(root);
        int[] arrs = new int[res.size()];
        for (int i = 0; i < res.size(); i ++) {
            arrs[i] = res.get(i);
        }
        return arrs;
    }

    public void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        int val = root.val;
        if (curr == val) {
            currCount ++;
        } else {
            currCount = 1;
            curr = val;
        }
        if (currCount == maxCount) {
            res.add(val);
        } else if (currCount > maxCount) {
            maxCount = currCount;
            res.clear();
            res.add(val);
        }
        inorderTraversal(root.right);

    }
}
