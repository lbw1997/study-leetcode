package com.project.jvm.leetcode;

public class T53 {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) return 0;

        int dp_0 = nums[0];
        int dp_1 = 0;

        int ret = dp_0;
        for (int i = 1;i<nums.length;i++) {
            dp_1 = Math.max(nums[i],dp_0+nums[i]);
            dp_0 = dp_1;
            ret = Math.max(ret,dp_1);
        }
        return ret;
    }

    public static void main(String[] args) {
        T53 t53 = new T53();
        int[] nums = {1};
        System.out.println(t53.maxSubArray(nums));
    }
}
