package com.project.jvm.leetcode;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 */
public class T1 {

    public static int[] twoSum(int[] nums,int target) {

        HashMap<Integer,Integer> hashMap = new HashMap<>();

        for (int i = 0; i<nums.length;i++) {
            int value = target - nums[i];
            if (hashMap.containsKey(value)) {
                return new int[] {hashMap.get(value),i};
            }
            hashMap.put(nums[i],i);
        }
        throw new IllegalArgumentException("找不到");
    }

    public static void main(String[] args) {
        int[] nums = {2,7,11,19};
        int target = 9;
        int[] ints = twoSum(nums, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
