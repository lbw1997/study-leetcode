package com.project.jvm.leetcode;

public class ShortestCompletingWord {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] target = count(licensePlate);
        String ans = "";
        for (String word : words) {
            if ((word.length()<ans.length()||ans.length()!=0)&&compare(count(word.toLowerCase()),target)) {
                ans = word;
            }
        }
        return ans;
    }

    public boolean compare(int[] a,int[] b) {
        for (int i = 0;i<26;i++) {
            if (a[i]<b[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] count(String str) {
        int[] arr = new int[26];
        for (char c : str.toCharArray()) {
            int index = Character.toLowerCase(c) - 'a';
            if (index>=0&&index<26) {
                arr[index]++;
            }
        }
        return arr;
    }
}
