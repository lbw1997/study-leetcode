package com.project.jvm.leetcode;

/**
 * 最长公共前缀
 */
public class T14 {

    public static String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length <1) return "";

        String pre = strs[0];

        for (int i = 1;i<strs.length;i++) {

            pre = longestCommonPrefix(pre,strs[i]);
            if (pre.length() == 0) break;
        }

        return pre;
    }

    public static String longestCommonPrefix(String str1, String str2) {

        int length = Math.min(str1.length(),str2.length());
        int index = 0;
        while (index<length&&str1.charAt(index) == str2.charAt(index)) {
            index ++;
        }
        return str1.substring(0, index);
    }

    public static void main(String[] args) {
        String[] str = {"flower","flo","flc"};
        System.out.println(longestCommonPrefix(str));
    }
}
