package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

/**
 * 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 */
@Slf4j
public class MaxArea {

    public int maxArea(int[] height) {
        int res = 0;
        for (int i = 0; i < height.length - 1; i ++) {
            int a = height[i];
            for (int j = i + 1; j < height.length; j ++) {
                int b = height[j];
                int width = j - i;
                int hei = Math.min(a, b);
                int area = hei * width;
                if (area > res) {
                    res = area;
                }
            }
        }
        return res;
    }

    public int maxArea2(int[] height) {
        int res = 0;
        int i = 0, j = height.length - 1;
        while (j > i) {
            int width = j - i;
            int minHeight = Math.min(height[i], height[j]);
            int area = width * minHeight;
            if (area > res) {
                res = area;
            }
            if (height[i] > height[j]) {
                j --;
            } else {
                i ++;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int maxArea = maxArea2(height);
        log.info(maxArea + "");
    }

}
