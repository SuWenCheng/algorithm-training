package algorithm;

import bean.ListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.BeanUtil;
import utils.JsonHelper;

/**
 * 反转链表 II
 *
 * 给你单链表的头节点head 和两个整数left 和 right ，其中left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 */
@Slf4j
public class ReverseBetween {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        // left前一个节点
        ListNode preLeft = null;
        // left节点
        ListNode leftNode = null;
        // right节点
        ListNode rightNode = null;
        ListNode cur = pre;
        for (int i = 0; i <= right; i++) {
            if (i == left - 1) {
                preLeft = cur;
                leftNode = preLeft.next;
            } else if (i == right) {
                rightNode = cur;
                break;
            }
            cur = cur.next;
        }

        // right节点后一个节点
        ListNode after = rightNode.next;
        // 截断链表
        rightNode.next = null;
        reverse(preLeft.next);
        preLeft.next = rightNode;
        leftNode.next = after;
        return pre.next;
    }

    private void reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    @Test
    public void test() {
        int[] nodes = {1,2,3,4,5,6};
        ListNode listNode = BeanUtil.generateListNode(nodes);
        String before = JsonHelper.toJson(listNode.toList());
        ListNode reverseList = reverseBetween(listNode, 2, 5);
        String after = JsonHelper.toJson(reverseList.toList());
        log.info("before: " + before);
        log.info("after: " + after);
    }

}
