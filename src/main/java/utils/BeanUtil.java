package utils;

import bean.LinkedListNode;

/**
 * 工具类
 */
public class BeanUtil {

    public static LinkedListNode generateListNode(int[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        LinkedListNode head = new LinkedListNode(nodes[0]);
        LinkedListNode end = head;
        for (int i = 1; i < nodes.length; i ++) {
            LinkedListNode temp = new LinkedListNode(nodes[i]);
            end.next = temp;
            end = temp;
        }
        return head;
    }

    /**
     * 打印链表
     */
    public static void printLinkedListNode(LinkedListNode linkedListNode) {
        LinkedListNode n = linkedListNode;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

}
