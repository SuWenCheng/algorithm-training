package algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自由之路
 *
 * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 *
 * 给定一个字符串ring，表示刻在外环上的编码；给定另一个字符串key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 *
 * 最初，ring的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使key的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完key中的所有字符。
 *
 * 旋转ring拼出 key 字符key[i]的阶段中：
 *
 * 您可以将ring顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串ring的一个字符与 12:00 方向对齐，并且这个字符必须等于字符key[i] 。
 * 如果字符key[i]已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作1 步。按完之后，您可以开始拼写key的下一个字符（下一阶段）, 直至完成所有拼写。
 * 示例：
 *
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 *  对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
 *  对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 *  当然, 我们还需要1步进行拼写。
 *  因此最终的输出是 4。
 * 提示：
 *
 * ring 和key的字符串长度取值范围均为1 至100；
 * 两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 字符串key一定可以由字符串 ring旋转拼出。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/freedom-trail
 */
@Slf4j
public class FindRotateSteps {

    /**
     * 递归
     */
    public int findRotateSteps(String ring, String key) {
        // 列表结构是由于ring中会有相同字符
        List<Integer>[] ringMap = new List[26];
        for (int i = 0; i < ring.length(); i ++) {
            int index = ring.charAt(i) - 'a';
            if (ringMap[index] == null) {
                ringMap[index] = new ArrayList<>();
            }
            ringMap[index].add(i);
        }

        // 已走过的路径，避免重复走
        int[][] done = new int[ring.length()][key.length()];
        for (int i = 0; i < done.length; i ++) {
            Arrays.fill(done[i], -1);
        }
        // 由于每走一次都要加1，不如直接在最后加上全部的1（即key的长度）
        return key.length() + dfs(ring, key, 0, 0, done, ringMap);

    }

    private int dfs(String ring, String key, int ringIndex, int keyIndex, int[][] done, List<Integer>[] ringMap) {
        if (keyIndex == key.length()) {
            return 0;
        }
        if (done[ringIndex][keyIndex] >= 0) {
            return done[ringIndex][keyIndex];
        }
        char cur = key.charAt(keyIndex);
        List<Integer> mapIndexes = ringMap[cur - 'a'];
        int res = Integer.MAX_VALUE;
        for (Integer index : mapIndexes) {
            int d1 = Math.abs(index - ringIndex);
            int d2 = ring.length() - d1;
            int minD = Math.min(d1, d2);
            res = Math.min(dfs(ring, key, index, keyIndex + 1, done, ringMap) + minD, res);
        }
        done[ringIndex][keyIndex] = res;
        return res;
    }

    /**
     * 动态递归
     */
    public int findRotateSteps2(String ring, String key) {
        int n = ring.length(), m = key.length();
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < 26; ++i) {
            pos[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; ++i) {
            pos[ring.charAt(i) - 'a'].add(i);
        }
        // dp[i][j]表示key前i个字符走过的最少步数（其中key[i]==ring[j]）
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dp[i], 0x3f3f3f);
        }
        for (int i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j : pos[key.charAt(i) - 'a']) {
                for (int k : pos[key.charAt(i - 1) - 'a']) {
                    log.info("i : {}  j : {}", i, j);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
        }
        return Arrays.stream(dp[m - 1]).min().getAsInt();
    }

    @Test
    public void test() {
        String ring = "godding";
        String key = "gd";
        log.info(findRotateSteps2(ring, key) + "");

    }

}
