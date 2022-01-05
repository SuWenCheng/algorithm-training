package algorithm.leetcode;

import bean.LinkedListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.BeanUtil;
import utils.JsonHelper;


/**
 * 重排链表
 *
 * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例1:
 *
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 *
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 */
@Slf4j
public class ReorderList {

    /**
     * 1. 将链表从中间分开
     * 2. 反转后半部链表
     * 3. 把反转后的链表逐一插入前半链表
     */
    public void reorderList(LinkedListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        LinkedListNode fast = head;
        LinkedListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        LinkedListNode newNode = slow.next;
        slow.next = null;
        // 反转后半链表
        newNode = reverseList(newNode);
        LinkedListNode root = head;
        while (newNode != null) {
            LinkedListNode rootTmp = root.next;
            root.next = newNode;
            LinkedListNode newTmp = newNode.next;
            newNode.next = rootTmp;
            root = rootTmp;
            newNode = newTmp;
        }
    }

    /**
     * 反转链表
     */
    private LinkedListNode reverseList(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode tail = head;
        head = head.next;
        tail.next = null;
        while (head != null) {
            LinkedListNode tmp = head.next;
            head.next = tail;
            tail = head;
            head = tmp;
        }
        return tail;
    }

    @Test
    public void test() {
        int[] arrs = {1, 2, 3, 4, 5};
        LinkedListNode linkedListNode = BeanUtil.generateListNode(arrs);
        reorderList(linkedListNode);
        log.info(JsonHelper.toJson(linkedListNode.toList()));
    }

}
