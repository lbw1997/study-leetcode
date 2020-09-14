package com.project.jvm.leetcode;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 */
public class T16 {

    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int best = 1000000000;

        int n = nums.length;

        for (int i = 0;i<n;i++) {

            int j = i+1;
            int k = n-1;
            while (j<k) {
                int sum = nums[i]+nums[j]+nums[k];
                if (sum == target) return target;

                if (Math.abs(sum-target)<Math.abs(best-target)) {
                    best = sum;
                }

                if (sum>target) {
                    int k0 = k-1;
                    while (k0>j&&nums[k] == nums[k0]) {
                        k0--;
                    }
                    k = k0;
                }else {
                    int j0 = j+1;
                    while (j0<k&&nums[j]==nums[j0]) {
                        j0++;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        System.out.println(threeSumClosest(nums, 1));
    }
}
