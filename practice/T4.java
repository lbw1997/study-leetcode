package com.project.jvm.practice;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class T4 {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int k1 = (m+n+1)/2;
        int k2 = (m+n+2)/2;
        return (findKth(nums1,0,nums2,0,k1)+findKth(nums1,0,nums2,0,k2))/2.0;


    }

    private static int findKth(int[] nums1,int i,int[] nums2,int j,int k) {

        if (i>=nums1.length) return nums2[j+k-1];
        if (j>=nums2.length) return nums2[i+k-1];

        if (k == 1) return Math.min(nums1[i],nums2[j]);

        int midValue1 = i+k/2-1<nums1.length?nums1[i+k/2-1]:Integer.MAX_VALUE;
        int midValue2 = j+k/2-1<nums2.length?nums2[j+k/2-1]:Integer.MAX_VALUE;

        if (midValue1<midValue2) {
            return findKth(nums1,i+k/2,nums2,j,k-k/2);
        }else {
            return findKth(nums1,i,nums2,j+k/2,k-k/2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
