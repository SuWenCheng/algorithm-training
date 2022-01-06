package geekbang.queue;

/**
 * 基于数组实现队列（顺序队列）
 */
public class ArrayQueue {

    private String[] items;    // 数组
    private int size;          // 数组大小
    private int head = 0;          // 头指针
    private int tail = 0;          // 尾指针

    public ArrayQueue(int capacity) {
        this.size = capacity;
        items = new String[capacity];
    }

    /**
     * 入队
     */
    public boolean enqueue(String item) {
        // 尾指针已经到数组尾部
        if (tail == size) {
            // tail == size && head == 0表示队列已满
            if (head == 0) {
                return false;
            }
            // 数据迁移
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     */
    public String dequeue() {
        if (head == tail) {
            return null;
        }
        return items[head++];
    }

}
