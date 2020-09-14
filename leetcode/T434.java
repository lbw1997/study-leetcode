package com.project.jvm.leetcode;

public class T434 {

    public int countSegments(String s) {
        int count = 0;
        for (int i = 0;i<s.length();i++) {
            if ((i == 0 || s.charAt(i-1) == ' ') && s.charAt(i)!= ' ') {
                count++;
            }
        }
        return count;
    }
}
