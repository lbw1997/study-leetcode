package com.project.jvm.practice;

/**
 * 替换字符串中的空格为%20
 */
public class ReplaceSpace {

    public static String replace(String str) {

        int spaceNums = 0;
        char[] chars = str.toCharArray();
        for (char c:chars) {
            if (c == ' ') {
                spaceNums++;
            }
        }

        StringBuffer sb = new StringBuffer(spaceNums*2+str.length());

        for (char c:chars) {
            if (c == ' ') {
                sb.append("%20");
            }else sb.append(c);
        }
        return sb.toString();
    }

    public static String userAPIReplace(String str) {
        str = str.replace(" ","%20");
        return str;
    }

    public static void main(String[] args) {
        String str = "a b c dd v";
        System.out.println(userAPIReplace(str));
    }
}
