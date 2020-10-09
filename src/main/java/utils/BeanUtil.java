package utils;

import bean.ListNode;

/**
 * 工具类
 */
public class BeanUtil {

    public static ListNode generateListNode(int[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nodes[0]);
        ListNode end = head;
        for (int i = 1; i < nodes.length; i ++) {
            ListNode temp = new ListNode(nodes[i]);
            end.next = temp;
            end = temp;
        }
        return head;
    }

}
