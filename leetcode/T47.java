package com.project.jvm.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class T47 {

    private static List<List<Integer>> ret = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {

        boolean[] flags = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(0,flags,new ArrayDeque<>(),nums);
        return ret;
    }

    private static void dfs(int depth,boolean[] flags, Deque<Integer> deque, int[] nums) {

        if (depth == nums.length) {
            ret.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0;i<nums.length;i++) {

            if (flags[i]) continue;

            if (i>0&&nums[i] == nums[i-1] && !flags[i-1]) continue;
            deque.addLast(nums[i]);
            flags[i] = true;
            dfs(depth+1,flags,deque,nums);
            flags[i] = false;
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        List<List<Integer>> lists = permuteUnique(nums);
        for(List<Integer> list:lists) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }
}
