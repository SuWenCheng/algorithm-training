package geekbang.sort;

import utils.JsonHelper;

/**
 * 归并排序
 * 原理：分治思想
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    /**
     * 归并排序
     * @param a 待排序数组
     * @param n 数组长度
     */
    public void mergeSort(int[] a, int n) {
        doMergeSort(a, 0, n - 1);
    }

    /**
     * 归并排序递归调用函数
     * @param a 带排序数组
     * @param p 待排序数组开始下标
     * @param r 待排序数组结束下标
     */
    public void doMergeSort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = (r + p) / 2;
        doMergeSort(a, p, q);
        doMergeSort(a, q + 1, r);
        merge(a, p, q, r);
    }

    /**
     * 合并数组
     */
    private void merge(int[] a, int p, int q, int r) {
        int[] tmp = new int[r - p + 1];
        int i = p, j = q + 1, k = 0;
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        // 判断有剩余的数组
        int start = i, end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        while (start <= end) {
            tmp[k++] = a[start++];
        }

        if (tmp.length >= 0) {
            System.arraycopy(tmp, 0, a, p, tmp.length);
        }
    }
}
