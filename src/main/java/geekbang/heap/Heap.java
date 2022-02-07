package geekbang.heap;

/**
 * 堆
 * 数组中下标为 i 的节点的左子节点，就是下标为 i∗2 的节点，
 * 右子节点就是下标为 i∗2+1 的节点，父节点就是下标为i/2 的节点
 */
public class Heap {

    private int[] a; // 数组，从下标1开始存储数据
    private int n; // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity; count = 0;
    }

    public void insert(int data) {
        if (count >= n) {
            return;
        }
        count++;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2 );
            i = i / 2;
        }
    }

    /**
     * 删除堆顶元素
     * 1.把最后一个节点放到堆顶
     * 2.迭代互换父子节点
     */
    public void removeMax() {
        if (count == 0) {
            return;
        }
        a[1] = a[count];
        count--;
        heapify(a, count, 1);
    }

    /**
     * 自上往下堆化
     * @param a 堆数组
     * @param n 最后一个堆元素的下标
     * @param i 要堆化的元素下标
     */
    public static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (2 * i <= n && a[i] < a[2 * i]) {
                maxPos = 2 * i;
            }
            if (2 * i + 1 <= n && a[maxPos] < a[2 * i + 1]) {
                maxPos = 2 * i + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(a, i, maxPos);
            i = maxPos;
        }
     }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
