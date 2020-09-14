package com.project.jvm.leetcode;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */
public class T31 {

    public void nextPermutation(int[] nums) {

        for (int i = nums.length-1;i>=0;i--) {

            for (int j = nums.length-1;j>i;j--) {
                if (nums[i]<nums[j]) {
                    swap(nums,i,j);
                    Arrays.sort(nums,i,nums.length);
                    return;
                }
            }
        }
        Arrays.sort(nums);
    }

    public void swap(int[] nums, int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
