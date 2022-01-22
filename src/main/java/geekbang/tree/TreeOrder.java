package geekbang.tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 树的前中后序遍历
 * 时间复杂度：每个节点最多会被访问两次，所以是 2n -> O(n)
 */
public class TreeOrder {

    /**
     * 前序遍历
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.data + " ");
        inOrder(node.right);
    }

    /**
     * 后续遍历
     */
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data + " ");
    }

    /**
     * 广度优先遍历 (Breadth First Search)
     * (按层遍历)
     */
    public List<Integer> BFS(Node node) {
        List<Integer> result = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            result.add(poll.data);
        }
        return result;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
