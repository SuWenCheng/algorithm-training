package geekbang.hashtable;

import java.util.HashMap;

/**
 * 基于散列表的LRU算法（数组+双链表）
 * 类似：LinkedHashMap
 */
public class LRUBaseHashTable<K, V> {

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;
    /**
     * 头结点
     */
    private DNode<K, V> headNode;

    /**
     * 尾节点
     */
    private DNode<K, V> tailNode;

    /**
     * 链表长度
     */
    private Integer length;

    /**
     * 链表容量
     */
    private Integer capacity;

    /**
     * 散列表存储key
     */
    private HashMap<K, DNode<K, V>> table;

    public LRUBaseHashTable(int capacity) {
        this.length = 0;
        this.capacity = capacity;

        headNode = new DNode<>();

        tailNode = new DNode<>();

        headNode.next = tailNode;
        tailNode.prev = headNode;

        table = new HashMap<>();
    }

    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 新增
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            DNode newNode = new DNode(key, value);
            table.put(key, newNode);
            addNewNode(newNode);
            length++;
            if (length > capacity) {
                DNode<K, V> dNode = popTail();
                table.remove(dNode.key);
                length--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }

    }

    /**
     * 查询
     * @param key
     * @return
     */
    public V get(K key) {
        DNode<K, V> node = table.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * 弹出尾结点
     * @return
     */
    public DNode popTail() {
        DNode<K, V> tail = tailNode.prev;
        removeNode(tail);
        return tail;
    }

    /**
     * 将新节点添加到头部
     * @param newNode
     */
    public void addNewNode(DNode<K, V> newNode) {
        newNode.next = headNode.next;
        newNode.prev = headNode;

        headNode.next.prev = newNode;
        headNode.next = newNode;
    }

    /**
     * 删除节点
     * @param node
     */
    public void removeNode(DNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将节点移到头部
     * @param node
     */
    public void moveToHead(DNode<K, V> node) {
        removeNode(node);
        addNewNode(node);
    }

    /**
     * 双向链表
     */
    static class DNode<K, V> {

        private K key;

        /**
         * 数据
         */
        private V value;

        /**
         * 前驱指针
         */
        private DNode<K, V> prev;

        /**
         * 后继指针
         */
        private DNode<K, V> next;

        DNode() {
        }

        DNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
