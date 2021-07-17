package geekbang.linkedlist;

/**
 * 判断回文链表
 */
public class SinglyLinkedList {

    private Node head;

    /**
     * 判断是否是回文
     */
    public boolean palindrome() {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;
        // 找中心节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node link1;
        Node link2;
        // slow是中心节点，且节点个数为奇数
        if (fast.next == null) {
            link1 = slow.next;
            link2 = reverseLink(slow).next;
        } else {
            // 节点个数为复数
            link1 = slow.next;
            link2 = reverseLink(slow);
        }

        return equalsData(link1, link2);
    }

    public boolean equalsData(Node n1, Node n2) {
        Node left = n1;
        Node right = n2;
        while (left != null && right != null) {
            if (left.data == right.data) {
                left = left.next;
                right = right.next;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 反转链表
     * @param end 链表尾部
     */
    public Node reverseLink(Node end) {
        Node tmp = head;
        Node reverseNext;
        Node preNode = null;
        while (tmp != end) {
            reverseNext = tmp.next;
            tmp.next = preNode;
            preNode = tmp;
            tmp = reverseNext;
        }
        tmp.next = preNode;
        return tmp;
    }

    /**
     * 在尾部插入节点
     */
    public void insertDataTail(int data) {
        Node newNode = new Node(data);

        //空链表
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void printAll() {
        Node n = head;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    private static class Node {
        private final int data;
        private Node next;

        public Node(int data) {
            this(data, null);
        }
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        int[] data = {1,1};
        SinglyLinkedList linkedLinke = new SinglyLinkedList();
        for (int datum : data) {
            linkedLinke.insertDataTail(datum);
        }
        System.out.println("打印链表: ");
        linkedLinke.printAll();
        if (linkedLinke.palindrome()) {
            System.out.println("是回文");
        } else {
            System.out.println("不是回文");
        }
    }

}
