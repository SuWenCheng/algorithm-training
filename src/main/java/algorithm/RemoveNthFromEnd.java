package algorithm;

import bean.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 */
public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> arrys = new LinkedList<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        arrys.add(dummy);
        ListNode tmp = head;
        while (tmp != null) {
            arrys.add(tmp);
            tmp = tmp.next;
        }
        int size = arrys.size();
        int rmIndex = size - n;
        ListNode before = arrys.get(rmIndex - 1);
        before.next = before.next.next;
        return dummy.next;
    }

    /**
     * 优化
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1; i ++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return dummy.next;
    }

}
