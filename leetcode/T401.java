package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 例如，上面的二进制手表读取 “3:25”。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 *  
 *
 */
public class T401 {

    private static List<String> list = new ArrayList<>();

    public static List<String> readBinaryWatch(int num) {

        int[] book = new int[10];

        dfs(0,num,book);
        Collections.sort(list);
        return list;
    }

    private static void dfs(int cur,int num,int[] book) {

        if (num == 0) {
            int hour = book[0] + 2*book[1] + 4*book[2] + 8*book[3];
            int minute = book[4] + 2*book[5] + 4*book[6] + 8*book[7] + 16*book[8] + 32*book[9];

            if (hour<12&&minute<60) {
                list.add(String.format("%d:%02d",hour,minute));
            }
            return;
        }

        for (int i = cur;i<book.length;i++) {
            book[i] = 1;
            dfs(i+1,num-1,book);
            book[i] = 0;
        }

    }

    public static void main(String[] args) {
        int num = 2;
        List<String> list = readBinaryWatch(num);
        list.forEach(System.out::println);
    }
}
