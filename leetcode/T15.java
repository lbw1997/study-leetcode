package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。

 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class T15 {

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        for (int first = 0; first <nums.length; first++) {

            if (first>0 && nums[first] == nums[first-1]) continue;

            int sum = -nums[first];
            int third = nums.length-1;

            for (int second = first+1;second<third;second++) {

                if (second>first+1 && nums[second] == nums[second-1]) continue;
                while (third>second && nums[third]+nums[second]>sum) {
                    third--;
                }

                if (second == third) break;

                if (nums[third] + nums[second] == sum) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ret.add(list);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] nums = {1,-1,-1,0};
        List<List<Integer>> lists = new T15().threeSum(nums);
        for (List<Integer> list:lists) {
            System.out.println(list);
        }
    }
}
