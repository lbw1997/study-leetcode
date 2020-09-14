package com.project.jvm.practice;

/**
 * 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
 *
 * 你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
 *
 * 将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
 * 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。
 * 最开始的时候，同一位置上也可能放着两个或者更多的筹码。
 *
 * 返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。
 *
 * 分析：
 * 移动偶数单位没有代价，移动奇数单位代价为1，即比较偶数和奇数数量，以数量多的为标准，移动另一种数，每存在一个代价+1
 */
public class MinCostToMoveChips {

    public static int minCostToMoveChips(int[] chips) {

        int oddNums = 0;
        int evenNums = 0;

        for (int a : chips) {
            if (a%2==1) {
                oddNums++;
            }
            if (a%2==0) {
                evenNums++;
            }

        }
        if (oddNums>evenNums) {
            return evenNums;
        }else {
            return oddNums;
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3};
        System.out.println(minCostToMoveChips(a));
    }
}
