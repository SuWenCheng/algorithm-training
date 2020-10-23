package algorithm;

import bean.ListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.BeanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 */
@Slf4j
public class Palindrome {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        List<Integer> arrs = new ArrayList<>();
        ListNode root = head;
        while (root != null) {
            arrs.add(root.val);
            root = root.next;
        }
        int size = arrs.size();
        int i = 0, j = size - 1;
        while (i < j) {
            if (!arrs.get(i).equals(arrs.get(j))) {
                return false;
            }
            i ++;
            j --;
        }
        return true;
    }

    /**
     * 优化 :
     * 1.取中间节点
     * 2.反转后半部分节点
     * 3.对比
     * 空间复杂度：O(1) 时间复杂度：O(N)
     */
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode middle = getMiddle(head);
        ListNode revSecHalf = reverseNodes(middle.next);
        ListNode firstHalf = head;
        ListNode secondHalf = revSecHalf;
        while (secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    private ListNode getMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode point = head;
        ListNode pre = null;
        while (point != null) {
            ListNode tmp = point.next;
            point.next = pre;
            pre = point;
            point = tmp;
        }
        return pre;
    }

    @Test
    public void test() {
        int[] ints = {1, 2};
        ListNode listNode = BeanUtil.generateListNode(ints);
        log.info(isPalindrome2(listNode) + "");
    }

}
