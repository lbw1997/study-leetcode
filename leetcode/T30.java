package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 *
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 */
public class T30 {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> ret = new ArrayList<>();

        int wordNum = words.length;
        int wordLen = words[0].length();

        if (wordLen == 0) return ret;

        HashMap<String,Integer> allWords = new HashMap<>();

        for (String str :words) {
            allWords.put(str,allWords.getOrDefault(str,0)+1);
        }


        for (int i = 0;i < s.length() - wordNum*wordLen+1;i++) {

            HashMap<String,Integer> hasWords = new HashMap<>();

            int num = 0;
            while (num<wordNum) {
                String word = s.substring(i+num*wordLen,i+(num+1)*wordLen);
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word,0);
                    hasWords.put(word,value+1);

                    if (allWords.get(word)>hasWords.get(word)) {
                        break;
                    }

                }else {
                    break;
                }
                num++;
                if (num == wordNum) {
                    ret.add(i);
                }
            }
        }
        return ret;
    }
}
