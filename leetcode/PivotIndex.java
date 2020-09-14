package com.project.jvm.leetcode;

public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int lSum = 0;
        int rSum = 0;
        for(int i=1;i<nums.length;i++) {
            lSum = 0;
            rSum = 0;
            for(int j=0;j<nums.length;j++) {
                if(j<i) {
                    lSum += nums[j];
                }
                if(j>i) {
                    rSum += nums[j];
                }
            }
            if(lSum == rSum) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex p = new PivotIndex();
        int[] nums1 = {1,7,3,6,5,6};
        int[] nums2 = {1,2,3};
        System.out.println(p.pivotIndex(nums1));
        System.out.println(p.pivotIndex(nums2));
    }
}
