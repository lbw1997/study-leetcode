package com.project.jvm.leetcode;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 */
public class T18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 4) return result;

        // 排序
        Arrays.sort(nums);

        int len =  nums.length - 3;

        for (int i = 0; i < len; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = nums.length - 1; j > i + 1; j--) {
                if (j<nums.length-1&&nums[j] == nums[j+1])continue;
                int L = i + 1;
                int R = j - 1;
                while (R > L) {
                    int sum = nums[i] + nums[L] + nums[R] + nums[j];
                    if (sum == target) {
                        // 由于尾指针去重不了，所以加了一层校验，这一块待优化
                        List<Integer> list = Arrays.asList(nums[i], nums[L], nums[R], nums[j]);
                        if (!result.contains(list)) {
                            result.add(list);
                        }
                        while (R > L && nums[R - 1] == nums[R]) R--; // 去重
                        while (R > L && nums[L + 1] == nums[L]) L++; // 去重
                        L++;
                        R--;
                    }
                    if (sum > target) R--;
                    if (sum < target) L++;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        T18 t = new T18();
        int[] nums = {-1,2,2,-5,0,-1,4};
        List<List<Integer>> lists = t.fourSum(nums, 3);

        lists.forEach(list->{
            list.forEach(System.out::print);
            System.out.println();
        });
    }
}
