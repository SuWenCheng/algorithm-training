package geekbang.queue;

/**
 * 循环队列（基于数组）
 * 优点：免去一般顺序队列的数据搬移操作
 * 缺点：会浪费一个数组的存储空间
 */
public class CircularQueue {

    private String[] items;
    private int size;
    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        this.items = new String[capacity];
        this.size = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // 队列满的判断条件
        if (head == (tail + 1) % size) {
            return false;
        }
        items[tail] = item;
        if (tail == size - 1) {
            tail = 0;
        } else {
            tail++;
        }
        // tail = (tail + 1) % size;
        return true;
    }

    // 出队
    public String dequeue() {
        // 队列为空
        if (head == tail) {
            return null;
        }
        String value = items[head];
        head = (head + 1) % size;
        return value;
    }

}
