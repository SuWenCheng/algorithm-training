package geekbang.linkedlist;



import java.util.Scanner;

/**
 * 用链表实现LRU缓存淘汰算法
 */
public class LRUBaseLinkedList<T> {

    /**
     * 链表默认容量
     */
    private final static int DEFAULT_CAPACITY = 10;

    /**
     * 链表长度
     */
    private int length;

    /**
     * 头结点
     */
    private final SNode<T> head;

    /**
     * 链表容量
     */
    private final int capacity;

    public LRUBaseLinkedList() {
        this.head = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.head = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void addNode(T data) {
        SNode<T> preNode = findPreNode(data);
        if (preNode != null) {
            deleteNextNode(preNode);
        } else {
            if (length >= capacity) {
                deleteTailNode();
            }
        }
        insertNodeAtHead(data);
    }

    private void deleteTailNode() {
        SNode<T> pre = head;

        if (pre.getNext() == null) {
            return;
        }

        while (pre.getNext().getNext() != null) {
            pre = pre.getNext();
        }

        pre.setNext(null);
        length--;

    }

    private void deleteNextNode(SNode<T> preNode) {
        SNode<T> curNode = preNode.getNext();
        preNode.setNext(curNode.getNext());
        length--;
    }

    private void insertNodeAtHead(T data) {
        SNode<T> next = head.getNext();
        head.setNext(new SNode<T>(data, next));
        length++;
    }

    private SNode<T> findPreNode(T data) {
        SNode<T> preNode = head;
        while (preNode.getNext() != null) {
            if (data.equals(preNode.getNext().getElement())) {
                return preNode;
            }
            preNode = preNode.getNext();
        }
        return null;
    }

    public void printAll() {
        SNode<T> node = head;
        while (node != null) {
            // 排除头结点
            if (node.getElement() != null) {
                System.out.print(node.getElement() + ",");
            }
            node = node.getNext();
        }
        System.out.println();
    }

    public class SNode<T> {

        private T element;
        private SNode<T> next;

        public SNode(T element, SNode<T> next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {

        }

        public T getElement() {
            return element;
        }

        public SNode<T> getNext() {
            return next;
        }

        public void setNext(SNode<T> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList<Integer> list = new LRUBaseLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            list.addNode(scanner.nextInt());
            list.printAll();
        }
    }

}
