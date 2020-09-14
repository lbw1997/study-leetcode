package com.project.jvm.practice;

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

            int len = nums.length;
            boolean[] flags = new boolean[len];
            Arrays.sort(nums);

            dfs(new ArrayDeque<>(),flags,len,len,nums);
            return ret;
        }

        public static void dfs(Deque<Integer> deque, boolean[] flags, int residue, int len, int[] nums) {

            if (residue == 0) {
                ret.add(new ArrayList<>(deque));
                return;
            }

            for (int i = 0;i<len;i++) {

                if (flags[i]) continue;

                if (i>0 && nums[i] == nums[i-1] && !flags[i-1]) {
                    continue;
                }
                deque.addLast(nums[i]);
                flags[i] = true;
                dfs(deque,flags,residue-1,len,nums);
                flags[i] = false;
                deque.removeLast();

            }
        }

    public static void main(String[] args) {
        int[] nums = {1};
        List<List<Integer>> lists = permuteUnique(nums);

        lists.forEach(list->{
            list.forEach(System.out::print);
            System.out.println();
        });
    }
}
