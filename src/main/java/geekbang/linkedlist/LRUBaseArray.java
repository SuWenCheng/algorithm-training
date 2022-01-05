package geekbang.linkedlist;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 用数组实现LRU缓存淘汰算法
 */
public class LRUBaseArray<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private final T[] value;

    private final int capacity;

    private int count;

    private final Map<T, Integer> indexMap;

    public LRUBaseArray(int capacity) {
        this.capacity = capacity;
        value = (T[])new Object[capacity];
        indexMap = new HashMap<>(capacity);
        count = 0;
    }

    public LRUBaseArray() {
        this(DEFAULT_CAPACITY);
    }

    public void offer(T data) {
        if (data == null) {
            throw new IllegalArgumentException("不支持null值");
        }
        Integer index = indexMap.get(data);
        if (index == null) {
            if (isFull()) {
                T tail = value[--count];
                indexMap.remove(tail);
            }
            cache(data, count);
            count++;
        } else {
            cache(data, index);
        }

    }

    /**
     * 把最新访问数据移到头部（先右移）
     */
    private void cache(T data, int end) {
        rightShift(end);
        value[0] = data;
        indexMap.put(data, 0);
    }

    /**
     * 指定位置前面的元素集体右移
     */
    private void rightShift(int end) {
        for (int i = end; i > 0; i--) {
            value[i] = value[i - 1];
            indexMap.put(value[i], i);
        }
    }

    public boolean isFull() {
        return count == capacity;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print(value[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUBaseArray<Integer> lruBaseArray = new LRUBaseArray<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            lruBaseArray.offer(scanner.nextInt());
            lruBaseArray.print();
        }
    }
}
