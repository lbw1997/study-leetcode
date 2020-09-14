package com.project.jvm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 */
public class T6 {

    public static String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuffer> rows = new ArrayList<>();
        for (int i = 0;i<numRows;i++) {
            rows.add(new StringBuffer());
        }

        int row = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(row).append(c);
            if (row == 0 || row == numRows-1) {
                goingDown = !goingDown;
            }
            row += goingDown?1:-1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuffer str :rows) {
            ret.append(str);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 4));
    }
}
