package geekbang.queue;

import bean.LinkedListNode;

/**
 * 基于链表实现的队列（链式队列）
 */
public class QueueBasedOnLinkedList {

    private LinkedListNode head = null;
    private LinkedListNode tail = null;

    // 入队
    public void enqueue(int item) {
        LinkedListNode newNode = new LinkedListNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    //出队
    public Integer dequeue() {
        if (head == null) return null;
        Integer value = head.data;
        head = head.next;
        return value;
    }

    public void printAll() {
        LinkedListNode p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueBasedOnLinkedList queue = new QueueBasedOnLinkedList();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(5);
        queue.printAll();
    }

}
