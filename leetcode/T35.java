package com.project.jvm.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 */
public class T35 {

    public int searchInsert(int[] nums, int target) {

        if (nums == null || nums.length <1) return 0;

        return search(nums,0,nums.length-1,target);
    }

    public int search(int[] nums,int i, int j, int target) {

        int ans = 0;
        int mid = i + (i-j)/2;
        int num = nums[mid];

        if (num<=target) {
            ans = mid;
            search(nums,mid+1,j,target);
        }
        if (num>target) {
            search(nums,i,mid,target);
        }
        return ans;
    }

}
