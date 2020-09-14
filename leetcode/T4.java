package com.project.jvm.leetcode;

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

    /**
     * 要求时间复杂度为O(log(m + n))，需要使用二分查找实现，先降低要求不考虑时间复杂度
     * @return 中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //解1、把两个数组组合后再求中位数
//        int n = nums1.length;
//        int m = nums2.length;
//
//        int[] ints = new int[n+m];
//        int p = n+m-1;
//        int p1 = n-1;
//        int p2 = m-1;
//        while (p1>=0&&p2>=0) {
//            ints[p--] = (nums1[p1]>nums2[p2])?nums1[p1--]:nums2[p2--];
//        }
//
//        if (p1 == -1) {
//            System.arraycopy(nums2,0,ints,0,p2+1);
//        }
//        if (p2 == -1) {
//            System.arraycopy(nums1,0,ints,0,p1+1);
//        }
//
//        for (int anInt : ints) {
//            System.out.println(anInt);
//        }
//
//        if ((n+m)%2 == 1) {
//            return ints[(n+m)/2];
//        }else {
//            return (double) (ints[(n+m)/2-1] + ints[(n+m)/2])/2;
//        }

        //解2、使用二分法找到中位数
        int m = nums1.length;
        int n = nums2.length;
        int left = (m+n+1)/2;
        int right = (m+n+2)/2;
        return (findKth(nums1,0,nums2,0,left)+findKth(nums1,0,nums2,0,right))/2.0;

    }

    private static int findKth(int[] nums1,int i,int[] nums2,int j,int k) {

        if (i>=nums1.length) return nums2[j+k-1];
        if (j>=nums2.length) return nums1[i+k-1];

        if (k == 1) {
            return Math.min(nums1[i],nums2[j]);
        }

        int midValue1 = (i+k/2-1)< nums1.length?nums1[i+k/2-1]:Integer.MAX_VALUE;
        int midValue2 = (j+k/2-1)<nums2.length?nums2[j+k/2-1]:Integer.MAX_VALUE;

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
