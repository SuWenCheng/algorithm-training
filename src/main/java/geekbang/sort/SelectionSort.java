package geekbang.sort;

import utils.JsonHelper;

/**
 * 选择排序
 * 1.分已排序区间和未排序区间
 * 2.从未排序区间中找到最小的元素，将其放到已排序区间的末尾
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] a = {5, 3};
        selectionSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void selectionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            int min = a[i];
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (a[j] < min) {
                    min = a[j];
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                a[minIndex] = a[i];
                a[i] = min;
            }
        }
    }

}
