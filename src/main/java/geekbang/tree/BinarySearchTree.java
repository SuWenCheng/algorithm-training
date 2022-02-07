package geekbang.tree;

import bean.TreeNode;

/**
 * 二叉搜索树
 * 1.在树中的任意一个节点，其左子树中的每个节点的值，都要小于这个节点的值;
 * 2.而右子树节点的值都大于这个节点的值
 */
public class BinarySearchTree {

    private TreeNode tree;

    /**
     * 搜索
     */
    public TreeNode find(int data) {
        TreeNode p = tree;
        while (p != null) {
            if (p.data == data) {
                return p;
            }
            if (p.data > data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    /**
     * 插入
     */
    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (tree == null) {
            tree = newNode;
            return;
        }

        TreeNode p = tree;
        while (true) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = newNode;
                    return;
                }
                p = p.right;
            } else {
              if (p.left == null) {
                  p.left = newNode;
                  return;
              }
              p = p.left;
            }
        }

    }

    /**
     * 删除
     */
    public void delete(int data) {
        TreeNode p = tree;
        // p的父节点
        TreeNode pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        if (p == null) {
            return;
        }

        // [1]被删除节点左右子树都有
        if (p.left != null && p.right != null) {
            // 搜索删除节点右子树的最小值
            TreeNode minPP = p;
            TreeNode minP = p.right;
            while (minP != null) {
                minPP = minP;
                minP = minP.left;
            }

            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        // [2]如果左子树存在, 条件[1]肯定不成立，即p.right == null
        // 即被删除节点只有左子树
        TreeNode child; // p的子节点
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            // [3]被删除节点只有右子树
            child = p.right;
        } else {
            // [4]被删除节点无子节点
            child = null;
        }

        if (pp == null) {
            // 被删除的为根节点
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = p;
        }

    }

    /**
     * 寻找最小节点
     */
    public TreeNode findMin() {
        if (tree == null) {
            return null;
        }
        TreeNode p = tree;
        while (p.left != null) {
            p = p.left;;
        }
        return p;
    }

    /**
     * 寻找最大节点
     */
    public TreeNode findMax() {
        if (tree == null) {
            return null;
        }
        TreeNode p = tree;
        while (p.right != null) {
            p = p.right;;
        }
        return p;
    }

}
