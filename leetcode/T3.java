package com.project.jvm.leetcode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class T3 {

    public static int lengthOfLongestSubstring(String s) {


        int length = 0;
        int result = 0;
        int i = 0;
        int flag = 0;

        while (i<s.length()) {

            int pos = s.indexOf(s.charAt(i),flag);
            if (pos<i) {
                if (length > result) {
                    result = length;
                }
                if (result>= s.length()-pos-1) return result;
                flag = pos+1;
                length = i-pos-1;
            }
            i++;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
}
