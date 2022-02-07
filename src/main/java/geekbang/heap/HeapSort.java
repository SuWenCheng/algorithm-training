package geekbang.heap;

import utils.JsonHelper;

/**
 * 堆排序
 * 1.建堆
 * 2.排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        sort(a);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        // 1.建堆
        buildHeap(arr);

        // 2.
        int k = arr.length - 1;
        while (k > 0) {
            Heap.swap(arr, 0, k);
            Heap.heapify(arr, --k, 0);
        }
    }

    /**
     * 建堆（往下堆化）
     * @param arr
     */
    private static void buildHeap(int[] arr) {
        int len = arr.length;
        // (arr.length - 1) / 2 为最后一个叶子节点的父节点
        // 也就是最后一个非叶子节点，依次堆化直到根节点
        for (int i = (len - 1) / 2; i >= 0; i--) {
            Heap.heapify(arr, len - 1, i);
        }
    }
}
