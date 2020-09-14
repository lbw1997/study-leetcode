package com.project.jvm.leetcode;

/**
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 *
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 *
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 *
 * 示例 1:
 *
 * n = 5
 *
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * 因为第三行不完整，所以返回2.
 */
public class T441 {

    public static int arrangeCoins(int n) {

        long low = 1;
        long high = n;
        while(low <= high){
            long mid = low + (high - low) / 2;
            long sum = mid * (mid + 1) / 2;
            if(sum > n)
                high = mid - 1;
            else if(sum == n)
                return (int)mid;
            else if(sum < n)
                low = mid + 1;
        }
        return (int)(low - 1);
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins(1804289383));
    }
}
