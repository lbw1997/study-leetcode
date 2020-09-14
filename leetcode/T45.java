package com.project.jvm.leetcode;

public class T45 {

    public int jump(int[] nums) {

        if (nums == null||nums.length<1) return 0;
        int times = 0;
        int position = nums.length-1;


        while (position>0) {
            for (int i = 0;i<position;i++) {
                if (i+nums[i]>=position) {
                    position = i;
                    times ++;
                    break;
                }
            }
        }

        return times;
    }

    public static void main(String[] args) {
        T45 t45 = new T45();
        int[] nums = {2,3,1,1,4};
        System.out.println(t45.jump(nums));
    }
}
