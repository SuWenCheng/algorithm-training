package geekbang.sort;

import utils.JsonHelper;

/**
 * 插入排序（递增）
 * 1.将数组中的数据分为两个区间，已排序区间和未排序区间
 * 2.取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序
 * 3.重复步骤2，直到未排序区间中元素为空
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        insertionSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

}
