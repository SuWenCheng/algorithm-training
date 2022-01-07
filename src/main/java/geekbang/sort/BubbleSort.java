package geekbang.sort;

import utils.JsonHelper;

/**
 * 冒泡排序（递增）
 * 时间复杂度（O²），稳定排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        bubbleSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i -1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

}
