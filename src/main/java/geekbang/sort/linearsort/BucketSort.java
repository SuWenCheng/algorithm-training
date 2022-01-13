package geekbang.sort.linearsort;

import geekbang.sort.QuickSort;
import utils.JsonHelper;

/**
 * 桶排序
 * 1.确定桶数量
 * 2.把元素放进桶内（必要时进行桶扩容）
 * 3.对每个桶进行快速排序
 * 4.合并桶内元素
 */
public class BucketSort {

    public static void main(String[] args) {
        int[] a = {124, 123, 36, 83, 2, 1, 3, 3, 2, 1};
        bucketSort(a, a.length);
        System.out.println(JsonHelper.toJson(a));
    }

    public static void bucketSort(int[] a, int bucketSize) {
        if (a.length <= 1) {
            return;
        }

        // 最大值、最小值
        int min = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            } else if (a[i] < min) {
                min = a[i];
            }
        }

        // 桶数量
        int bucketCount = (max - min) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        // 每个桶已有元素
        int[] bucketArray = new int[bucketCount];
        for (int value : a) {
            int bucketIndex = (value - min) / bucketSize;
            if (bucketArray[bucketIndex] == buckets[bucketIndex].length) {
                resizeBucket(buckets, bucketIndex);
            }
            buckets[bucketIndex][bucketArray[bucketIndex]++] = value;
        }

        // 对每个桶排序
        int k = 0;
        for (int i = 0; i < bucketCount; i++) {
            int[] bucket = buckets[i];
            int bucketLength = bucketArray[i];
            if (bucketLength == 0) {
                continue;
            }
            QuickSort.quickSort(bucket, bucketLength);
            for (int j = 0; j < bucketLength; j++) {
                a[k++] = bucket[j];
            }
        }

    }

    // 桶扩容
    private static void resizeBucket(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        System.arraycopy(tempArr, 0, newArr, 0, tempArr.length);
        buckets[bucketIndex] = newArr;
    }

}
