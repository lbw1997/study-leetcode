package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 找零钱问题
 */
public class T322 {

    public int coinChange(int[] coins, int amount) {

        if (coins == null || coins.length < 1) return 0;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {186,419,83,408};
        T322 t = new T322();
        int i = t.coinChange(coins, 6249);
        System.out.println(i);
    }
}
