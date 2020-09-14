package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 *
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。

 * 示例 1：
 *
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 */
public class T140 {

    public static List<String> wordBreak(String s, List<String> wordDict) {

        LinkedList<String>[] dp = new LinkedList[s.length()+1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1;i<=s.length();i++) {

            LinkedList<String> list = new LinkedList<>();
            for (int j = 0;j<i;j++) {

                if (dp[j].size()>0&&wordDict.contains(s.substring(j,i))) {
                    for (String l :dp[j]) {
                        list.add(l+(l.equals("")?"":" ")+s.substring(j,i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new LinkedList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        List<String> list = wordBreak(s, wordDict);
        list.forEach(System.out::println);
    }
}
