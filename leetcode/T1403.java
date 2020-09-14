package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
 *
 * 如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
 *
 * 与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
 *
 * 注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
 *
 */
public class T1403 {

    public static List<Integer> minSubsequence(int[] nums) {

        if(nums == null || nums.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();

        int sum = 0;
        for (int a:nums) {
            sum += a;
        }

        Arrays.sort(nums);

        int tmp = 0;
        for (int i = nums.length-1;i>=0;i--) {
            tmp += nums[i];
            list.add(nums[i]);
            if (tmp>sum-tmp) {
                break;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] nums = {4};
        List<Integer> integers = minSubsequence(nums);
        integers.forEach(System.out::println);
    }
}
