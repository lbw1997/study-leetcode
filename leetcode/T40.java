package com.project.jvm.leetcode;

import java.util.*;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 */
public class T40 {

    private static List<List<Integer>> ret = new ArrayList<>();

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {

        int len = candidates.length;
        if (len == 0) return ret;
        Arrays.sort(candidates);

        dfs(candidates,0,new ArrayDeque<>(),target);
        return ret;
    }

    private static void dfs(int[] candidates, int begin, Deque<Integer> deque, int residue) {

        if (residue == 0) {
            ret.add(new ArrayList<>(deque));
            return;
        }

        for (int i = begin;i<candidates.length;i++) {
            if (residue-candidates[i]<0) break;

            if (i>begin && candidates[i] == candidates[i-1]) continue;

            deque.addLast(candidates[i]);
            dfs(candidates,i+1,deque,residue-candidates[i]);
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        List<List<Integer>> lists = combinationSum2(candidates, 8);

        lists.forEach(list->{
            for (Integer l : list) {
                System.out.print(l);
            }
            System.out.println();
        });
    }

}
