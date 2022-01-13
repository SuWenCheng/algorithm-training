package geekbang.sort;

import utils.JsonHelper;

/**
 * 快速排序
 * 分治、递归思想
 * 原地排序，非稳定排序
 * 1.如果要排序数组中下标从 p 到 r 之间的一组数据，选择 p 到 r 之间的任意一个数据作为 pivot（分区点）
 * 2.遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间
 * 3.递归
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        quickSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void quickSort(int[] a, int n) {
        doQuickSort(a, 0, n - 1);
    }

    private static void doQuickSort(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }

        int q = partition(a, p, r);
        doQuickSort(a, p, q - 1);
        doQuickSort(a, q + 1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p, j = p;
        while (j < r) {
            int num = a[j];
            if (num < pivot) {
                a[j] = a[i];
                a[i] = num;
                i++;
            }
            j++;
        }
        a[r] = a[i];
        a[i] = pivot;
        return i;
    }

}
