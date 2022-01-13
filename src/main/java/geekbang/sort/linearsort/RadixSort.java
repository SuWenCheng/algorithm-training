package geekbang.sort.linearsort;

import utils.JsonHelper;

/**
 * 基数排序
 * 比较两个数，只需要比较高位，高位相同的再比较低位
 * 场景：给电话号码排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] a = {1234254, 1232456635, 1232345636, 928483, 2352, 1456343, 32450, 3, 2, 1};
        radixSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void radixSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        // 搜索最大的数，用来确定指数的位数
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }

        int exp = 1;
        for (; max / exp >= 1; exp *= 10) {
            countingSort(a, exp);
        }
    }

    /**
     * 计数排序
     * @param a 排序数组
     * @param exp 指数
     */
    private static void countingSort(int[] a, int exp) {
        if (a.length <= 1) {
            return;
        }

        // 计算对应位数数字的个数
        int[] counting = new int[10];
        for (int i = 0; i < 10; i++) {
            counting[a[i] / exp % 10]++;
        }

        // 计算排序后的位置
        for (int i = 1; i < 10; i++) {
            counting[i] += counting[i - 1];
        }

        // 临时数组存放结果
        int[] tmp = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            int num = a[i];
            tmp[counting[num / exp % 10] - 1] = num;
            counting[num / exp % 10]--;
        }

        System.arraycopy(tmp, 0, a, 0, a.length);

    }

}
