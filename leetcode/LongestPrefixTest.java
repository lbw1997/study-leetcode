package com.project.jvm.leetcode;

public class LongestPrefixTest {
    public int longestPrefix(int X, int Y, int[] nums) {
        // write your code here
        int xTimes = 0;
        int yTimes = 0;
        int index = 0;
        for (int i=0;i<nums.length;i++) {
            if (nums[i] == X) {
                xTimes ++;
                if(yTimes != 0&&yTimes == xTimes) {
                    index = i;
                }
            }
            if (nums[i] == Y) {
                yTimes ++;
                if(xTimes != 0&&yTimes == xTimes) {
                    index = i;
                }
            }
        }
        if(xTimes == 0||yTimes == 0||index == 0) return -1;
        return index;
    }

    public static void main(String[] args) {
        int[] nums = {10,12,18,10,14,11,11,14,15,19};
        LongestPrefixTest lp = new LongestPrefixTest();
        System.out.println(lp.longestPrefix(11,19,nums));
    }
}
