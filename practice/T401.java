package com.project.jvm.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 *例如，上面的二进制手表读取 “3:25”。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 示例：
 *
 * 输入: n = 1
 * 返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 *  
 * 提示：
 *
 * 输出的顺序没有要求。
 * 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
 * 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
 * 超过表示范围（小时 0-11，分钟 0-59）的数据将会被舍弃，也就是说不会出现 "13:00", "0:61" 等时间。
 *
 */

public class T401 {

    /*
        思路：
        1、  用二进制表示时间，4位表示时，6位表示分
             每有一个Num数，则表示有一位1，那么就可以使用Integer.bitCount(hour|minute)，得到对应数的二进制为1个数，
             若相加==num 表示为合法的 之后还需要对时间进行格式化组合

        2、  使用递归的方式，因为不需要关注顺序，所以选择组合，默认book[]数组各位都为1，递归为1，并且回溯为0
             因为时钟都为1会超过12，同理分钟也会超过60，需要进行剪枝
             初始情况下不亮灯，每次点亮一盏，num-1，cur+1，当num == 0时，保存结果
     */

    public static List<String> readBinaryWatch(int num) {

//        List<String> ret = new ArrayList<>();
//
//        for (int i = 0;i<12;i++) {
//
//            for (int j = 0;j<60;j++) {
//                if (Integer.bitCount(i)+Integer.bitCount(j) == num) {
//                    ret.add(String.format("%d:%02d",i,j));
//                }
//            }
//        }
//        return ret;

        List<String> ret = new ArrayList<>();
        int[] book = new int[10];

        dfs(0,num,book,ret);
        Collections.sort(ret);
        return ret;
    }

    private static void dfs(int cur, int num, int[] book, List<String> ret) {

        if (num == 0) {

            int hour = book[0] + 2*book[1] + 4*book[2] + 8*book[3];
            int minute = book[4] + 2*book[5] + 4*book[6] + 8*book[7] + 16*book[8] + 32*book[9];

            if (hour<12 && minute<60) {
                ret.add(String.format("%d:%02d",hour,minute));
            }
            return;
        }

        for (int i = cur;i<book.length;i++) {
            book[i] = 1;
            dfs(i+1,num-1,book,ret);
            book[i] = 0;
        }
    }

    public static void main(String[] args) {
        List<String> list = readBinaryWatch(2);
        list.forEach(System.out::println);
    }
}
