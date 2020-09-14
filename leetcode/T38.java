package com.project.jvm.leetcode;

/**
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *
 * 注意：整数序列中的每一项将表示为一个字符串。
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 第一项是数字 1

 描述前一项，这个数是 1 即 “一个 1 ”，记作 11

 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21

 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211

 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
 */
public class T38 {

    public String countAndSay(int n) {

        StringBuilder sb = new StringBuilder();
        if (n == 1) return "1";
        int pl = 0;
        int cur = 1;

        String s = countAndSay(n-1);

        for (cur = 1;cur<s.length();cur++) {

            if (s.charAt(pl) != s.charAt(cur)) {
                int count = cur-pl;
                sb.append(count).append(s.charAt(pl));
                pl = cur;
            }
        }
        if (pl != cur) {
            int count = cur - pl;
            sb.append(count).append(s.charAt(pl));
        }
        return sb.toString();
    }


}
