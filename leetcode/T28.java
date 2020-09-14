package com.project.jvm.leetcode;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 */
public class T28 {

    public int strStr(String haystack, String needle) {

       int N = needle.length(), H = haystack.length();

       if (N == 0) return 0;

       int ph = 0;

       while (ph < H-N+1) {

           while (ph< H-N+1 && haystack.charAt(ph) != needle.charAt(0)) ph ++;

           int pn = 0, curLen = 0;

           while (ph<H && pn<N && haystack.charAt(ph) == needle.charAt(pn)) {
               ph ++;

               pn ++;
               curLen ++;
           }

           if (curLen == N) return ph-N;

           ph = ph - curLen + 1;
       }

       return -1;
    }

    public static void main(String[] args) {
        T28 t = new T28();
        System.out.println(t.strStr("aaaaa", "bba"));
    }
}
