package algorithm.leetcode;

import bean.LinkedListNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.BeanUtil;
import utils.JsonHelper;

/**
 * 对链表进行插入排序
 *
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 * 
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 */
@Slf4j
public class InsertionSortList {

    /**
     * 空（哑）节点
     */
    public LinkedListNode insertionSortList(LinkedListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode dummyNode = new LinkedListNode(0);
        dummyNode.next = head;
        LinkedListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.data <= curr.data) {
                lastSorted = curr;
            } else {
                LinkedListNode prev = dummyNode;
                while (prev.next.data <= curr.data) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyNode.next;
    }

    @Test
    public void test() {
        int[] nodes = {6, 5, 3, 1, 8, 7, 2, 4};
        LinkedListNode linkedListNode = BeanUtil.generateListNode(nodes);
        LinkedListNode result = insertionSortList(linkedListNode);
        log.info(JsonHelper.toJson(result.toList()));
    }

}
