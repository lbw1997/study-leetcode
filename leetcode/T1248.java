package com.project.jvm.leetcode;

/**
 * 题目描述
 * 给你一个整数数组 nums 和一个整数 k。
 *
 * 如果某个连续子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 *
 * 请返回这个数组中「优美子数组」的数目。
 */
public class T1248 {

    public static int getGreatArray(int[] arr, int k ) {

        int sum = 0;
        int[] newArr = new int[arr.length];
        int ans = 0;

        newArr[0] = 1;
        for (int a:arr) {
            if (a%2 == 1) {
                sum++;
            }
            newArr[sum]++;
            if (sum-k>=0) ans+= newArr[sum-k];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,1,1};
        int k = 3;
        System.out.println(getGreatArray(arr, k));
    }
}
