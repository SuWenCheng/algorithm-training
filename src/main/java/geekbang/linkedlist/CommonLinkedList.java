package geekbang.linkedlist;

import bean.LinkedListNode;
import utils.BeanUtil;


/**
 * 链表常见操作
 */
public class CommonLinkedList {

    public static void main(String[] args) {
        CommonLinkedList commonLinkedList = new CommonLinkedList();

        //链表反转
        int[] nodes = {1, 2, 3, 4, 5};
        LinkedListNode linkedListNode = BeanUtil.generateListNode(nodes);
        LinkedListNode reverse = commonLinkedList.reverse(linkedListNode);
        System.out.println("==========反转后的链表=============");
        BeanUtil.printLinkedListNode(reverse);

        // 合并有序链表
        int[] nodes1 = {1, 3, 5, 7, 9};
        int[] nodes2 = {2, 4, 6, 8, 10, 12};
        LinkedListNode linkedListNode1 = BeanUtil.generateListNode(nodes1);
        LinkedListNode linkedListNode2 = BeanUtil.generateListNode(nodes2);
        LinkedListNode mergeListNode = commonLinkedList.mergeOrder(linkedListNode1, linkedListNode2);
        BeanUtil.printLinkedListNode(mergeListNode);

        // 删除链表倒数第n个结点
        int[] nodes3 = {1, 2, 3, 4, 5};
        LinkedListNode linkedListNode3 = BeanUtil.generateListNode(nodes3);
        LinkedListNode removeLast = commonLinkedList.removeLast(linkedListNode3, 5);
        BeanUtil.printLinkedListNode(removeLast);

        // 链表中间结点
        int[] nodes4 = {1, 2, 3, 4};
        LinkedListNode linkedListNode4 = BeanUtil.generateListNode(nodes4);
        LinkedListNode middleNode = commonLinkedList.middleNode(linkedListNode4);
        BeanUtil.printLinkedListNode(middleNode);
    }

    /**
     * 反转链表
     */
    public LinkedListNode reverse(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode tmp = head;
        LinkedListNode reverseNext;
        LinkedListNode preNode = null;
        while (tmp != null) {
            reverseNext = tmp.next;
            tmp.next = preNode;
            preNode = tmp;
            tmp = reverseNext;
        }
        return preNode;
    }

    /**
     * 判断链表中是否有环
     * 快慢指针（空间复杂度O(1)），如果有环，快慢指针一定会相遇
     * @param head 链表头指针
     * @return 是 true|否 false
     */
    public boolean hasCycle(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
        }
        return false;
    }

    /**
     * 合并两个有序链表
     * @param node1 有序链表1
     * @param node2 有序链表2
     * @return 合并后的链表
     */
    public LinkedListNode mergeOrder(LinkedListNode node1, LinkedListNode node2) {
        LinkedListNode pointer = new LinkedListNode(-1);
        LinkedListNode head = pointer;
        while (node1 != null && node2 != null) {
            int num1 = node1.data;
            int num2 = node2.data;
            if (num1 > num2) {
                pointer.next = node2;
                node2 = node2.next;
            } else {
                pointer.next = node1;
                node1 = node1.next;
            }
            pointer = pointer.next;
        }
        while (node1 != null) {
            pointer.next = node1;
            node1 = node1.next;
            pointer = pointer.next;
        }

        while (node2 != null) {
            pointer.next = node2;
            node2 = node2.next;
            pointer = pointer.next;
        }

        return head.next;
    }

    /**
     * 删除链表倒数第n个结点
     * @param head 链表头结点
     * @param n 倒数第n个数
     * @return 删除倒数第n个结点后的链表
     */
    public LinkedListNode removeLast(LinkedListNode head, int n) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        LinkedListNode pre = null;
        while (n > 0) {
            if (fast != null) {
                fast = fast.next;
                n--;
            } else {
                throw new RuntimeException("参数错误");
            }
        }

        if (fast == null) {
            assert head != null;
            return head.next;
        }

        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;
        return head;
    }

    /**
     * 求链表中间结点
     * @param head 链表头结点
     * @return 链表中间结点
     */
    public LinkedListNode middleNode(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }

}
