package com.project.jvm.leetcode;

import java.util.HashMap;

public class TwoNum {
    public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++) {
            int other = target-nums[i];
            for(int j=i+1;j<nums.length;j++) {
                if(other == nums[j]) {
                    int[] a = {i,j};
                    return a;
                }
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            if (hashMap.containsKey(target-nums[i])) {
                return new int[]{hashMap.get(target-nums[i]),i};
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return null;
    }
}
