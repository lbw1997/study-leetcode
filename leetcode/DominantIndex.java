package com.project.jvm.leetcode;

import java.util.Arrays;

public class DominantIndex {
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int[] tmp = new int[nums.length];
        System.arraycopy(nums,0,tmp,0,nums.length);
        Arrays.sort(tmp);
        int max = tmp[tmp.length-1];
        for (int i=tmp.length-2;i>0;i--) {
            if(max<tmp[i]*2) return -1;
        }
        for (int j=0;j<nums.length;j++) {
            if (max == nums[j]) return j;
        }
        return 0;
    }
    public static void main(String[] args) {
        DominantIndex d = new DominantIndex();
        int[] nums = {1,2};
        System.out.println(d.dominantIndex(nums));
    }
}
