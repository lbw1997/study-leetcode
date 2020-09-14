package com.project.jvm.leetcode;

import java.util.*;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 *
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 输入：candidates = [2,3,5], target = 8,
 所求解集为：
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */
public class T39 {

    private static List<List<Integer>> ret = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

        dfs(candidates,0,new ArrayDeque<>(),target);
        return ret;
    }

    private static void dfs(int[] candidates, int begin, Deque<Integer> path,int residue) {


    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        List<List<Integer>> lists = combinationSum(candidates, 7);

        lists.forEach(list->{
            for (Integer l : list) {
                System.out.print(l);
            }
            System.out.println();
        });
    }
}
