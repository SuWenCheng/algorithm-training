package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 视频拼接
 *
 *
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为T秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 *
 * 视频片段clips[i]都用区间进行表示：开始于clips[i][0]并于clips[i][1]结束。我们甚至可以对这些片段自由地再剪辑，例如片段[0, 7]可以剪切成[0, 1] +[1, 3] + [3, 7]三部分。
 *
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回-1 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 示例 2：
 *
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 示例 3：
 *
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释： 
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 示例 4：
 *
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 * 
 *
 * 提示：
 *
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0] <=clips[i][1] <= 100
 * 0 <= T <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 */
@Slf4j
public class VideoStitching {

    /**
     * 动态规划
     */
    public int videoStitching(int[][] clips, int T) {
        // dp[i] 表示将区间 [0,i) 覆盖所需的最少子区间的数量
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (i > clip[0] && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

    /**
     * 贪心算法
     */
    public int videoStitching2(int[][] clips, int T) {
        int[] maxEnd = new int[T];  // maxEnd[j]表示以j为起点能到达的最大的结束位置
        for (int[] clip : clips) {
            int start = clip[0];
            if (start < T) {
                maxEnd[start] = Math.max(maxEnd[start], clip[1]);
            }
        }

        int pre = 0; // 上一个所选区间的结束位置
        int count = 0; // 结果
        int last = 0; // 当前所选区间的最远位置

        for (int i = 0; i < T; i ++) {
            last = Math.max(last, maxEnd[i]);
            // 表示“断层”
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                count ++;
                pre = last;
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[][] clips = {{0,2},{4,6},{8,10},{1,9},{1,5},{5,9}};
        int res = videoStitching2(clips, 10);
        log.info( res + "");
    }

}
