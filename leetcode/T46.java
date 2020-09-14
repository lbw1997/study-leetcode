package com.project.jvm.leetcode;

import java.util.*;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class T46 {

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> ret = new ArrayList<>();

        ArrayList<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, ret, 0);
        return ret;
    }

    private static void backtrack(int n, ArrayList<Integer> output, List<List<Integer>> ret, int first) {

        if (first == n) {
            ret.add(new ArrayList<>(output));
        }

        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output, ret, first + 1);
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> lists = permute(nums);

        lists.forEach(list->{
            list.forEach(System.out::print);
            System.out.println();
        });
    }
}
