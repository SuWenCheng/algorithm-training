package geekbang.sort.linearsort;

import utils.JsonHelper;

/**
 * 计数排序
 * （假设数组中存储的都是非负数）
 */
public class CountingSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1, 0, 3, 2, 1};
        countingSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void countingSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }

        // 查找数组中数据的范围
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        // 计算每个元素的个数，放入临时数组
        int[] count = new int[max + 1];
        for (int i = 0; i < n; i++) {
            count[a[i]]++;
        }

        // 累加
        for (int i = 1; i <= max; i++) {
            count[i] = count[i - 1] + count[i];
        }

        // 临时数组存结果
        int[] tmp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = a[i];
            tmp[count[num] - 1] = num;
            count[num]--;
        }

        // 临时数组拷贝到结果数组
        System.arraycopy(tmp, 0, a, 0, n);
    }
}
