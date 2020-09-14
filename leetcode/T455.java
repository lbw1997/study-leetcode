package com.project.jvm.leetcode;

import java.util.Arrays;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 注意：
 *
 * 你可以假设胃口值为正。
 * 一个小朋友最多只能拥有一块饼干。
 *
 */
public class T455 {

    public static int findContentChildren(int[] g, int[] s) {

//        Arrays.sort(g);
//        Arrays.sort(s);
//
//
//        int ans = 0;
//        int i = g.length-1;
//        int j = s.length-1;
//        while (i>=0&&j>=0) {
//            if (s[j]>=g[i]) {
//                i--;
//                j--;
//                ans ++;
//            }else if (s[j]<g[i]) {
//                i--;
//            }
//        }
//        return ans;

        int ans = 0;
        int index = -1;
        int i = 0;
        int j = 0;
        int min = 0;

        while(i<=g.length-1&&j<=s.length-1) {
            if (s[j]>=g[i]) {

                if (min<=s[j]) {
                    min = s[j];
                    index = j;
                }
            }

            if (index == -1) {
                i++;
            } else {
                s[j] = 0;
                ans ++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] g = {10,9,8,7};
        int[] s = {5,6,7,8};
        System.out.println(findContentChildren(g,s));
    }
}
