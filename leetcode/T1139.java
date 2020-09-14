package com.project.jvm.leetcode;

public class T1139 {

    //dp[i][j][0]表示i行j列的1往左最长的连续1的个数
    //dp[i][j][1]表示i行j列的1往上最长的连续1的个数
    public int largest1BorderedSquare(int[][] grid) {

       int n = grid.length;
       if (n == 0) return 0;

       int m = grid[0].length;

       int[][][] dp = new int[n+1][m+1][2];
       int ret = 0;
       for (int i = 1;i<=n;i++) {
           for (int j = 1;j<=m;j++) {

               int d= 0;
               if (grid[i-1][j-1] == 1) {
                   dp[i][j][0] = dp[i][j-1][0] +1;
                   dp[i][j][1] = dp[i-1][j][1] +1;

                   d = Math.min(dp[i][j-1][0],dp[i-1][j][1]);

                   while (d>0) {
                       if (dp[i-d][j][0]>d && dp[i][j-d][1]>d) {
                           break;
                       }
                       d--;
                   }
                   ret = Math.max(ret,d+1);
               }
           }
       }
       return ret*ret;
    }

    public static void main(String[] args) {
        T1139 t1139 = new T1139();

    }
}
