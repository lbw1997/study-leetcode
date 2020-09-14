package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 */
public class T22 {

    private static List<String> list = new ArrayList<>();

    public static List<String> generateParenthesis(int n) {

        dfs("",n,n);
        return list;
    }

    private static void dfs(String path,int left,int right) {

        if (left == 0 && right == 0) {
            list.add(path);
            return;
        }

        if (left>right) return;

        if (left>0) {
            dfs(path+"(",left-1,right);
        }
        if (right>0) {
            dfs(path+")",left,right-1);
        }
    }


    public static void main(String[] args) {
        List<String> list = generateParenthesis(1);
        list.forEach(System.out::println);
    }
}
