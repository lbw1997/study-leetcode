package com.project.jvm.leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 */
public class T5 {

    public static String longestPalindrome(String s) {

        //解1
//        if (s == null || s.length()<1) return "";
//        int start = 0, end = 0;
//        for (int i = 0;i<s.length();i++) {
//            int len1 = expandAroundCenter(s,i,i);
//            int len2 = expandAroundCenter(s,i,i+1);
//            int len = Math.max(len1,len2);
//            if (len > end - start) {
//                start = i - (len - 1) / 2;
//                end = i + len / 2;
//            }
//
//        }
//        return s.substring(start,end +1);



        //解2、动态规划

        int len = 0;

        int length = s.length();
        if (length<2) return s;

        boolean[][] dp = new boolean[length][length];

        for (int i = 0;i<length;i++) {
            dp[i][i] = true;
        }

        int start = 0;
        for (int j = 1;j<length;j++) {
            for (int i = 0;i<j;i++) {
                if (s.charAt(i)!=s.charAt(j)) {
                    dp[i][j] = false;
                }else {
                    if (j-i<3) {
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if (dp[i][j]&&len<j-i+1) {
                    len = j-i+1;
                    start = i;
                }
            }
        }

        return s.substring(start,start+len);

    }

//    private static int expandAroundCenter(String s,int left,int right) {
//
//        int L = left;
//        int R = right;
//        while (L>=0 && R<s.length() && s.charAt(L) == s.charAt(R)) {
//            L--;
//            R++;
//        }
//        return R - L - 1;
//    }

    public static void main(String[] args) {
        String s = "ababa";
        System.out.println(longestPalindrome(s));
    }
}
