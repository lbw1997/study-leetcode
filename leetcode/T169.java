package com.project.jvm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class T169 {

    public static int majorityElement(int[] nums) {

//        HashMap<Integer,Integer> hashMap = new HashMap<>();
//        for (int n:nums) {
//           hashMap.put(n,hashMap.getOrDefault(n,0)+1);
//           if (hashMap.get(n)>nums.length/2) {
//               return n;
//           }
//        }
//        return -1;

        return quickSearch(nums,0,nums.length-1,nums.length/2);
    }

    private static int quickSearch(int[] nums, int lo, int hi, int k) {

        int j = partition(nums,lo,hi);
        if (j == k) {
            return nums[j];
        }
        return j>k? quickSearch(nums,lo,j-1,k):quickSearch(nums,j+1,hi,k);
    }

    private static int partition(int[] nums, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        int v = nums[lo];

        while (true) {
            while (++i<=hi&&nums[i]<v);
            while (--j>=lo&&nums[j]>v);
            if (i>=j) {
                break;
            }
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }

        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }


    public static void main(String[] args) {
        int[] nums = {3,3,4};
        System.out.println(majorityElement(nums));
    }
}
