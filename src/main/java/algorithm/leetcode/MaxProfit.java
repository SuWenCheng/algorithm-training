package algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组prices，其中第i个元素代表了第i天的股票价格 ；非负整数fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:  
 * 在此处买入prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 */
@Slf4j
public class MaxProfit {

    /**
     * 动态规划
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int dp[][] = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }

        return dp[len - 1][0];
    }

    /**
     * 贪心算法
     */
    public int maxPrices2(int[] prices, int fee) {
        int min = prices[0], len = prices.length, res = 0, tmp = 0, i = 0;
        while (++ i < len) {
            int price = prices[i];
            if (price < min) {
                min = price;
            } else if ((tmp = price - min - fee) > 0) {
                res += tmp;
                min = price - fee;
            }
        }
        return res;
    }

    @Test
    public void test() {
        // 1,3,2,8,11,10,11,8,11,4,9
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        log.info(maxPrices2(prices, fee) + "");
    }

}
