package com.project.jvm.concurrent;


import java.util.Arrays;

/**
 * 堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，
 * 下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
 *
 * 输入使用数组[wi, di, hi]表示每个箱子。
 *
 */
public class PileBox {


    public int pileBox(int[][] box) {

        Arrays.sort(box,(a,b)->(a[0] == b[0] ? a[1] == b[1] ? b[2]-a[2] : b[1]-a[1] : a[0]-b[0]));

        int[] dp = new int[box.length];

        int res = 0;
        for (int i = 0;i<box.length;i++) {
            int maxVal = 0;
            for (int j=0;j<i;j++) {
                if (box[j][1]<box[i][1] && box[j][2]<box[i][2]) {
                    maxVal = Math.max(maxVal,dp[j]);
                }
            }
            dp[i] = maxVal + box[i][2];
            res = Math.max(res,dp[i]);
        }
        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        PileBox p = new PileBox();

        int[][] box = {{1, 1, 1}, {2, 3, 4}, {2, 6, 7}, {3, 4, 5}};

        p.pileBox(box);
    }
}
