package com.project.jvm.leetcode;

/**
 *
 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

 你的算法时间复杂度必须是 O(log n) 级别。

 如果数组中不存在目标值，返回 [-1, -1]。

 示例 1:

 输入: nums = [5,7,7,8,8,10], target = 8
 输出: [3,4]
 示例 2:

 输入: nums = [5,7,7,8,8,10], target = 6
 输出: [-1,-1]
 */
public class T34 {

    public int[] searchRange(int[] nums, int target) {

        int[] ret = new int[2];
        ret[0] = left_bound(nums,target);

        if (ret[0] == -1) {
            ret[1] = -1;
            return ret;
        }
        ret[1] = right_bound(nums,target);
        return ret;
    }

    public int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left<right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                right = mid;
            }else if (nums[mid] > target) {
                right = mid;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left >= nums.length || nums[left] != target) return -1;
        return left;
    }

    public int right_bound(int[] nums, int target) {
        int left = 0,right = nums.length;
        while (left<right) {
            int mid = left + (right - left) /2;
            if (nums[mid] == target) {
                left = mid + 1;
            }else if (nums[mid] > target) {
                right = mid;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return right-1;
    }

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        T34 t = new T34();
        int[] ints = t.searchRange(nums, 8);
        for (int a : ints) {
            System.out.println(a);
        }
    }
}
