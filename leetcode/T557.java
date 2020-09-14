package com.project.jvm.leetcode;

public class T557 {

    public String reverseWords(String s) {

        char[] target = s.toCharArray();
        int left = 0,right = 0;
        if (target[0] != ' ') {
            // 交换并返回尾下标值
            left = swapWord(0, getNextIndex(target, left), target);
        }
        // 防止重复交换或者是重复判断
        left++;

        for (; left < target.length; left++) {
            if (target[left] != ' ') {
                if (left > 0 && target[left-1] == ' ') {
                    // 交换并返回尾下标值
                    left = swapWord(left, getNextIndex(target, left), target);
                }
            }
        }
        return new String(target);
    }

    public int getNextIndex(char[] target, int i) {
        while (i+1 < target.length && target[i+1] != ' ') {
            i++;
        }
        return i;
    }

    private int swapWord(int left,int right, char[] target) {
        int next = right+1;
        if (left != right) {
            char temp;
            while (left<right) {
                temp = target[left];
                target[left] = target[right];
                target[right] = temp;
                left ++;
                right --;
            }
        }
        return next;
    }
}
