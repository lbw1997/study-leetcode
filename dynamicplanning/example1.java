package com.project.jvm.dynamicplanning;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数组从左上角到右下角能有多少种移动方式，每次只能下移一次或者右移一次
 */
public class example1 {

    public static int times(int[][] arr) {

        int n = arr.length;
        int m = arr[0].length;
        long start = System.currentTimeMillis();
        for (int i = 0;i<n;i++) {
            arr[0][i] = 1;
        }
        for (int i = 0;i<m;i++) {
            arr[i][0] = 1;
        }
        for (int i = 1;i<n;i++) {
            for (int j = 1;j<m;j++) {
                arr[i][j] = arr[i-1][j] + arr[i][j-1];
            }
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        return arr[n-1][m-1];
    }

    public static int times2(int m,int n) {
        long start = System.currentTimeMillis();

        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[] dp = new int[n]; //
        // 初始化
        for(int i = 0; i < n; i++){
            dp[i] = 1;
        }

        // 公式：dp[i] = dp[i-1] + dp[i]
        for (int i = 1; i < m; i++) {
            // 第 i 行第 0 列的初始值
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        long end = System.currentTimeMillis();

        System.out.println(end-start);
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[][] arr = new int[1000][1000];
        System.out.println(times(arr));

        //System.out.println(times2(1000,1000));
    }
}
