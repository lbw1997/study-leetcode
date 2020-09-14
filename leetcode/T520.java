package com.project.jvm.leetcode;

public class T520 {

    public boolean detectCapitalUse(String word) {

        boolean flag = word.charAt(0) >= 'A' && word.charAt(0) <= 'Z';

        if (flag) {
            int num = 1;
            for (int i = 1;i< word.length();i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    num++;
                }
            }
            return num == 1||num == word.length();
        }else {
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    return false;
                }
            }
            return true;
        }
    }
}
