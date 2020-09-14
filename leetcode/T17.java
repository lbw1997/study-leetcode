package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class T17 {

    private static List<String> ret = new ArrayList<>();
    private static HashMap<String,String> hashMap = new HashMap<>();


    public static List<String> letterCombinations(String digits) {


        hashMap.put("2","abc");
        hashMap.put("3","def");
        hashMap.put("4","ghi");
        hashMap.put("5","jkl");
        hashMap.put("6","mno");
        hashMap.put("7","pqrs");
        hashMap.put("8","tuv");
        hashMap.put("9","wxyz");


        if (digits.length()!= 0) {
            dfs("",digits);
        }
        return ret;
    }

    /*
        combination表示当前已组合好的字串，next_digits表示剩余的数字串
        思路：从数字串中每次选中一个字符，并与已组合好的字串相加，
        当没有剩余数字时表示找到一个结果，存放到ret中返回，并作为程序的出口。

     */
    private static void dfs(String combination, String next_digits) {

        if (next_digits.length() == 0) {
            ret.add(combination);
            return;
        }

        String digit = next_digits.substring(0,1);
        String letters = hashMap.get(digit);
        for (int i = 0;i<letters.length();i++) {
            String letter = letters.substring(i,i+1);
            dfs(combination+letter,next_digits.substring(1));
        }
    }

    public static void main(String[] args) {
        List<String> list = letterCombinations("23");
        list.forEach(System.out::println);
    }
}
