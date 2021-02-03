package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.JsonHelper;

import java.util.*;

/**
 * 滑动窗口中位数
 *
 * 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；
 * 此时中位数是最中间的两个数的平均数。
 *
 * 例如：
 *
 * [2,3,4]，中位数是3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。
 * 窗口中有 k 个数，每次窗口向右移动 1 位。
 * 你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 *
 * 
 *
 * 示例：
 *
 * 给出nums = [1,3,-1,-3,5,3,6,7]，以及k = 3。
 *
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7      -1
 *  1  3 [-1  -3  5] 3  6  7      -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组[1,-1,-1,3,5,6]。
 *
 * 
 *
 * 提示：
 *
 * 你可以假设k始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-median
 */
@Slf4j
public class MedianSlidingWindow {

    // 动窗口/双重优先队列滑
    public double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        double[] res = new double[len - k + 1];
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; i++) {
            dh.insert(nums[i]);
        }
        int index = 0;
        res[index++] = dh.getMedian();
        int left = 0;
        int right = k;
        while (right < len) {
            dh.insert(nums[right]);
            dh.delete(nums[left]);
            left++;
            right++;
            res[index++] = dh.getMedian();
        }
        return res;
    }

    private class DualHeap {
        private int k;
        // 大数队列
        private PriorityQueue<Integer> large;
        // 小数队列
        private PriorityQueue<Integer> small;
        // 延迟删除哈希表
        Map<Integer, Integer> delays;
        int largeSize;
        int smallSize;

        public DualHeap(int k) {
            small = new PriorityQueue<>(Comparator.reverseOrder());
            large = new PriorityQueue<>(Comparator.naturalOrder());
            delays = new HashMap<>();
            largeSize = 0;
            smallSize = 0;
            this.k = k;
        }

        public double getMedian() {
            if (k % 2 == 0) {
                return ((double) small.peek() + large.peek()) / 2;
            }
            return small.peek();
        }

        public void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                smallSize ++;
            } else {
                large.offer(num);
                largeSize ++;
            }
            makeBalance();
        }

        public void delete(int num) {
            delays.put(num, delays.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                smallSize --;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                largeSize --;
                if (num == large.peek()) {
                    prune(large);
                }
            }
            makeBalance();
        }

        // 弹出堆顶元素，更新哈希表
        private void prune(PriorityQueue<Integer> queue) {
            while (!queue.isEmpty()) {
                int num = queue.peek();
                if (delays.containsKey(num)) {
                    int count = delays.get(num);
                    delays.put(num, count - 1);
                    if(count == 1) {
                        delays.remove(num);
                    }
                    queue.poll();
                } else {
                    break;
                }
            }
        }

        // 调整large和small个数
        private void makeBalance() {
            if (smallSize > largeSize + 1) {
                large.offer(small.poll());
                largeSize ++;
                smallSize --;
                prune(small);
            } else if (smallSize < largeSize) {
                small.offer(large.poll());
                smallSize ++;
                largeSize --;
                prune(large);
            }
        }

    }


    @Test
    public void test() {
        int[] nums = {-2147483648,-2147483648,2147483647,-2147483648,
                -2147483648,-2147483648,2147483647,2147483647,2147483647,
                2147483647,-2147483648,2147483647,-2147483648};
        int k = 3;
        double[] doubles = medianSlidingWindow(nums, k);
        log.info(JsonHelper.toJson(doubles));
    }

}
