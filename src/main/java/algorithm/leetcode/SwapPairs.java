package algorithm.leetcode;

import bean.LinkedListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.BeanUtil;
import utils.JsonHelper;

import java.util.List;

/**
 * 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
@Slf4j
public class SwapPairs {

    public LinkedListNode swapPairs(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode first = head;
        LinkedListNode second = head.next;
        first.next = swapPairs(second.next);
        second.next = first;
        return second;
    }

    public LinkedListNode swapPairs2(LinkedListNode head) {
        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        LinkedListNode firstNode = head;
        LinkedListNode secondNode = head.next;

        // Swapping
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        return secondNode;
    }

    @Test
    public void test() {
        int[] nodes = {1, 2, 3, 4, 5};
        LinkedListNode linkedListNode = BeanUtil.generateListNode(nodes);
        LinkedListNode swap = swapPairs(linkedListNode);
        List<Integer> res = swap.toList();
        log.info(JsonHelper.toJson(res));
    }

}
