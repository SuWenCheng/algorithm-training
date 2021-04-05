package algorithm;

import bean.ListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.BeanUtil;
import utils.JsonHelper;


/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 */
@Slf4j
public class ReverseList {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    @Test
    public void test() {
        int[] nodes = {1,2,3,4,5};
        ListNode listNode = BeanUtil.generateListNode(nodes);
        String before = JsonHelper.toJson(listNode.toList());
        ListNode reverseList = reverseList(listNode);
        String after = JsonHelper.toJson(reverseList.toList());
        log.info("before: " + before);
        log.info("after: " + after);
    }

}
