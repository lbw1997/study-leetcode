package com.project.jvm.practice;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 贪心策略：
 * 1、股票上涨时期买，某天利润可以记为pi = price[i]-price[i-1]；
 *  连续上涨期，即连续几天都上涨pn = (price[2]-prince[1]) + (prince[3]-prince[2])+ ...
 *  即pn = price[n] - prince[1]
 * 2、可以看做所有有利润的机会买，没有则不买
 */
public class BestTimeToSale {

    public static int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) {
            return -1;
        }

        int maxValue = 0;

        for (int i = 1;i<prices.length;i++) {     //sale

            if (prices[i]>prices[i-1]) {
                maxValue += prices[i]-prices[i-1];
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
}
